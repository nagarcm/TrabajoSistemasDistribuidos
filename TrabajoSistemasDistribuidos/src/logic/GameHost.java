package logic;

import jdk.jshell.spi.ExecutionControl;
import model.*;
import persistence.Persistencia;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameHost extends Thread{
    private String ip;
    private int port;
    private boolean keepPlaying;
    private boolean gameEnd;
    private boolean endTurn;
    private HostClient activePlayer;
    private HostClient pasivePlayer;
    private ExecutorService pool;
    private Card lastPlayed;




    public GameHost(ExecutorService pool){
        this.pool = pool;
        this.port = 0;
        this.keepPlaying=true;
        this.gameEnd=false;
    }
    public GameHost(){
        this.pool = Executors.newCachedThreadPool();
        this.port = 0;
        this.keepPlaying=true;
        this.gameEnd=false;
    }
    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port = port;
    }


    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }

    public boolean isKeepPlaying() {
        return keepPlaying;
    }
    public void setKeepPlaying(boolean keepPlaying) {
        this.keepPlaying = keepPlaying;
    }

    @Override
    public void run(){
    	System.out.println("Inicializado el Host en otro hilo");
    	//Dado que este es el ordenador donde se hosteara el juego, inicializamos el card manager con la coleccion de cartas que tomemos de persistencia.
    	Persistencia.initialiceData();
        CardManager.addAll(Persistencia.getAllCards());
        System.out.println("Datos de las cartas iniciados");
        try {
            this.ip = InetAddress.getLocalHost().getHostAddress();
            System.out.println("Ip asignada");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        //CHECK! : He metido el while keeping playing dentro del try porque tiene sentido en la estructura de
        //partidas pero igual luego causa problemas (Es para conservar los sockets si van a seguir jugando las mismas personas)
        try (ServerSocket serverSocket = new ServerSocket(0);) {
            // Lo hacemos aqui porque tras finalizar la(s) partida(s) queremos finalizar la conexion con el cliente
            try {
                //para pruebas
//                System.out.println("IP: " + ip);
//                System.out.println("PORT: " + serverSocket.getLocalPort());
                this.port = serverSocket.getLocalPort();
                //Conexion con ambos clientes
                this.activePlayer = new HostClient(serverSocket.accept());
                this.pasivePlayer = new HostClient(serverSocket.accept());

                activePlayer.initializeStream();
                pasivePlayer.initializeStream();
                while (this.keepPlaying) {
                    this.playGame();
                    this.gameEnd = false;
                }
            } catch (IOException io) {
                //Problemas de conexion con un jugador
                io.printStackTrace();
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    private void playGame() throws IOException{//hacemos throw para agrupar las excepciones de conexion con clientes
    	System.out.println("StartGame");
        int turn =0;
        //Play 1 game (match)
        Random random = new Random();
        DataUpdate updateActive, updatePasive;
        PlayerAction playerAction;
        if (random.nextInt()%2==0){this.switchPlayers();}
        //Inicializar partida
        this.activePlayer.initializeMatch();
        this.pasivePlayer.initializeMatch();
        activePlayer.startTurn();
        System.out.println("PreLoop");
        while(!this.gameEnd){
            System.out.println("Turno "+turn);
            //Logica de turno
            this.endTurn = false;
            this.activePlayer.startTurn();
            while(!endTurn && !this.gameEnd) {
                //Mientras que el usuario no termine el turno o no haya un jugador con 0 vida no cambiamos de jugador activo

                //Antes de la accion del usuario enviamos un resumen con el estado actual de la partida
                updateActive = this.generateUpdate(false, 0, 0, 0);
                updatePasive = updateActive.invertData();
                activePlayer.send(updateActive);
                pasivePlayer.send(updatePasive);
                //Recibimos la accion del usuario y la procesamos
                playerAction = activePlayer.recibe();
                this.processAction(playerAction); // Process action cambia el endTurn internamente
                //Permanecemos en el bucle si no ha terminado el turno
            }
            //Terminamos turno y cambiamos de jugador activo
         this.activePlayer.endTurn();
         this.switchPlayers();
         turn++;
        }
        DataUpdate d = this.generateUpdate(true, 0, 0, 0);
        d.setLastUpdate(true);
        
        activePlayer.send(d);//Mandamos el final de la partida este esta identificado por endGame = true,PlaynextAction = true y Target = null
        d = d.invertData();
        d.setLastUpdate(true);
        pasivePlayer.send(d);
/*
        playerAction = activePlayer.recibe();
        this.keepPlaying = this.keepPlaying && playerAction.isEndTurn() && playerAction.getPlayedCard()==null;
        //Si el jugador ha devuelto turnEnd =true <<Y>> carta nula continuamos jugando
        playerAction = pasivePlayer.recibe();
        this.keepPlaying = this.keepPlaying && playerAction.isEndTurn() && playerAction.getPlayedCard()==null;*/
        this.keepPlaying=false;
    }
    private void switchPlayers(){
        HostClient aux;
        aux = activePlayer;
        activePlayer = pasivePlayer;
        pasivePlayer = aux;
    }
    private DataUpdate generateUpdate(boolean endGame,int dmgPerHit, int numHits,int blockGain){
        return new DataUpdate(endGame  , true, Target.Self, dmgPerHit, numHits, 0, blockGain,
                false, null,null,this.pasivePlayer.getCharacter().getHp(),this.pasivePlayer.getCharacter().getBlock(),pasivePlayer.getCharacter().getStance(),
                this.activePlayer.getCharacter().getHp(),this.activePlayer.getCharacter().getBlock(), activePlayer.getCharacter().getStance(),activePlayer.getCharacter().getEnergy(),activePlayer.getCharacter().getLastCardPlayed(),activePlayer.getCharacter().getHand().getAllCards());
    }
    private DataUpdate finalUpdate(){
        return new DataUpdate(true  , true, null, 0, 0, 0, 0,
                false, null,null,0,0,CharacterStance.None,
                0,0, CharacterStance.None,0,null,null);

    }

    private void processAction(PlayerAction playerAction){
        this.endTurn = playerAction.isEndTurn();
        if (!this.endTurn){
            this.lastPlayed = playerAction.getPlayedCard();
            this.activePlayer.getCharacter().getHand().removeCard(lastPlayed);
            this.activePlayer.getCharacter().getDiscardPile().addCard(lastPlayed);
            switch (playerAction.getPlayedCard().getCardType()){
                case Attack -> processAttack((AttackCard) playerAction.getPlayedCard());
                case Skill -> processSkill((SkillCard) playerAction.getPlayedCard());
                //case Power -> processPower(null);
                //case Curse -> processCurse(null);
                //case Status -> processStatus(null);
            }
        }
        this.gameEnd = this.activePlayer.getCharacter().getHp()==0 || this.pasivePlayer.getCharacter().getHp()==0;
        //Do stuff
    }
    private void processAttack(AttackCard attack){      

        GameCharacter gc = activePlayer.getCharacter();
        int hits = attack.getNumHits();
        if (attack.getEnergyCost()==-1){
            //consumir toda la energia como numero de hits
            hits = gc.getEnergy();
        }
        
        //Mecanica basica de hace daño

        int dmg = gc.calculateCardDamage(attack);
        
        for (int n = 0; n<hits;n++){
        	pasivePlayer.getCharacter().takeDamage(dmg);
        }
        //Agregamos escudo si corresponde
        gc.addBlock(attack.getBlockGain());
        //Robamos o descartamos las cartas que correspondan
        
        for (int i = 0, draw = attack.getCardsDraw(); i < draw; i++) {
            gc.drawCard();
        }
        for (int i = 0, discard = attack.getCardsDiscarted(); i < discard; i++) {
            gc.discardCard();
        }
        //cambiamos la postura del personaje
        if(attack.getStance()!=null && attack.getStance()!=CharacterStance.None && gc.getStance() != attack.getStance()){
            gc.setStance(attack.getStance());
            //Evento de cambio de postura
        }
        //añadimos los buffs y debuffs que correspondan
        if (attack.getBuffs()!=null && !attack.getBuffs().isEmpty()){
            gc.getBuffs().addAll(attack.getBuffs());
        }
        if(attack.getDebuffs()!=null && !attack.getDebuffs().isEmpty()){
            pasivePlayer.getCharacter().getBuffs().addAll(attack.getDebuffs());
        }
        //consumimos la energia;
        if (attack.getEnergyCost() != -1){
            gc.consumeEnergy(attack.getEnergyCost());
        }else {
            gc.setEnergy(0);
        }
        attack.played();// UPDATE: Por el momento no hace nada pero esta llamada servira para actualizar cartas con mecanicas en funcion del uso
        gc.setLastCardPlayed(attack);
    }
    private void processSkill(SkillCard skill){
        int times ;
        GameCharacter gc = activePlayer.getCharacter();
        if(skill.getEnergyCost() == -1){
            //hacer cosas con las skills de coste X
            // UPDATE : En este caso por la limitacion en cuanto se va a implementar nos limitamos a un caso
            //en futuras actualizaciones tendremos que hacer mas cosas
            gc.addBlock(skill.getBlockGain()*gc.getEnergy());
        }else {
            //añadimos block
            gc.addBlock(skill.getBlockGain());
            //añadimos energia
            gc.addEnergy(skill.getEnergyGain());
            //descartamos y robamos cartas
            
            for (int i = 0, draw = skill.getCardsDraw(); i < draw; i++) {
                gc.drawCard();
            }
            for (int i = 0, discard = skill.getCardsDiscarted(); i < discard; i++) {
                gc.discardCard();
            }
            //cambiamos la postura si es necesario
            if(skill.getStance()!=CharacterStance.None && gc.getStance() != skill.getStance()){
                gc.setStance(skill.getStance());
                //Evento de cambio de postura
            }
            
            //añadimos los buffs y debuffs que correspondan
            if (skill.getBuffsSelf()!=null && !skill.getBuffsSelf().isEmpty()){
                gc.getBuffs().addAll(skill.getBuffsSelf());
            }
            if(skill.getDebuffEnemy()!=null && !skill.getDebuffEnemy().isEmpty()){
                pasivePlayer.getCharacter().getBuffs().addAll(skill.getDebuffEnemy());
            }
            
            //añadimos la carta que corresponda a la pila de extraccion
            if (CardManager.existCardName(skill.getNameCardToAdd())){
                gc.getDrawPile().addCard(CardManager.createCardByName(skill.getNameCardToAdd()));
            }
        }
            //Consumimos enercia
            if (skill.getEnergyCost() != -1) {
                gc.consumeEnergy(skill.getEnergyCost());
            } else {
                gc.setEnergy(0);
            }
        skill.played();
        gc.setLastCardPlayed(skill);
    }

    //Estos seran implementados en futuras versiones
    private void processPower(Card power) throws ExecutionControl.NotImplementedException{
        throw new ExecutionControl.NotImplementedException("This function its not implement yet");
    }
    private void processStatus(StatusCard status)throws ExecutionControl.NotImplementedException{
        throw new ExecutionControl.NotImplementedException("This function its not implement yet");
    }
    private void processCurse(Card curse)throws ExecutionControl.NotImplementedException{
        throw new ExecutionControl.NotImplementedException("This function its not implement yet");
    }
}
