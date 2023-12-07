package logic;

import model.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameHost extends Thread{
    private String ip;
    private int port1;
    private int port2;
    private boolean keepPlaying;
    private boolean gameEnd;
    private boolean endTurn;
    private GameClient activePlayer;
    private GameClient pasivePlayer;
    private ExecutorService pool;




    public GameHost(ExecutorService pool){
        this.pool = pool;
        this.port1 = 0;
        this.port2 = 0;
        this.keepPlaying=true;
        this.gameEnd=false;
    }
    public GameHost(){
        this.pool = Executors.newCachedThreadPool();
        this.port1 = 0;
        this.port2 = 0;
        this.keepPlaying=true;
        this.gameEnd=false;
    }
    public int getPort1() {
        return port1;
    }
    public void setPort1(int port) {
        this.port1 = port;
    }
    public int getPort2() {
        return port2;
    }
    public void setPort2(int port) {
        this.port2 = port;
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

        try {
            this.ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        //CHECK! : He metido el while keeping playing dentro del try porque tiene sentido en la estructura de
        //partidas pero igual luego causa problemas (Es para conservar los sockets si van a seguir jugando las mismas personas)
        try (ServerSocket serverSocket = new ServerSocket(0);) {

            try {
                this.port1 = serverSocket.getLocalPort();
                this.activePlayer = new GameClient(serverSocket.accept());
                this.pasivePlayer = new GameClient(serverSocket.accept());
                while (this.keepPlaying) {
                    this.playGame();
                }
            } catch (IOException io) {
                //Problemas de conexion con un jugador
                io.printStackTrace();
            }

        } catch (IOException io) {
            io.printStackTrace();
        }




    }

    private void playGame() throws IOException{
        //Play 1 game (match)
        Random random = new Random();
        DataUpdate updateActive, updatePasive;
        PlayerAction playerAction;
        this.endTurn = false;
        if (random.nextInt()%2==0){this.switchPlayers();}
        //Inicializar partida
        this.activePlayer.initializeMatch();
        this.pasivePlayer.initializeMatch();
        activePlayer.startTurn();

        while(!this.gameEnd){
            //Logica de turno
            this.activePlayer.startTurn();
            while(!endTurn) {//Mientras que el usuario no termine el turno no cambiamos de jugador activo
                //Antes de la accion del usuario enviamos un resumen con el estado actual de la partida
                updateActive = this.generateUpdate(false, 0, 0, 0);
                updatePasive = updateActive.invertData();
                activePlayer.send(updateActive);
                pasivePlayer.send(updateActive);
                //Recibimos la accion del usuario y la procesamos
                playerAction = activePlayer.recibe();
                this.processAction(playerAction);
                //Permanecemos en el bucle si no ha terminado el turno
            }
            //Terminamos turno y cambiamos de jugador activo
         this.activePlayer.endTurn();
         this.switchPlayers();
        }
    }
    private void switchPlayers(){
        GameClient aux;
        aux = activePlayer;
        activePlayer = pasivePlayer;
        pasivePlayer = aux;
    }
    private DataUpdate generateUpdate(boolean endGame,int dmgPerHit, int numHits,int blockGain){
        return new DataUpdate(endGame, true, Target.Self, dmgPerHit, numHits, 0, blockGain,
                false, null,null,this.pasivePlayer.getCharacter().getHp(),this.pasivePlayer.getCharacter().getBlock(),pasivePlayer.getCharacter().getStance(),
                this.activePlayer.getCharacter().getHp(),this.activePlayer.getCharacter().getBlock(), activePlayer.getCharacter().getStance(),activePlayer.getCharacter().getEnergy(),activePlayer.getCharacter().getHand().getAllCards());
    }

    private void processAction(PlayerAction playerAction){
        this.endTurn = playerAction.isEndTurn();
        if (!this.endTurn){
            switch (playerAction.getPlayedCard().getCardType()){
                case Attack -> processAttack((AttackCard) playerAction.getPlayedCard());
                case Skill -> processSkill((SkillCard) playerAction.getPlayedCard());
                case Power -> processPower(null);
                case Curse -> processCurse(null);
                case Status -> processStatus(null);
            }
        }
        //Do stuff
    }
    private void processAttack(AttackCard attack){
        /**********************************                   CONTINUE: Corregir esto para que si el coste es -1, consuma toda la energia  */
        GameCharacter gc = activePlayer.getCharacter();
        //Mecanica basica de hace daño
        int hits = attack.getNumHits();
        int dmg = gc.calculateCardDamage(attack);
        for (int n = 0; n<hits;n++){
            gc.takeDamage(dmg);
        }
        //Agregamos escudo si corresponde
        gc.addBlock(attack.getBlockGain());
        //Robamos o descartamos las cartas que correspondan

        //cambiamos la postura del personaje

        //consumimos la energia;
    }
    private void processSkill(SkillCard skill){

    }

    //Estos seran implementados en futuras versiones
    private void processPower(Card power){

    }
    private void processStatus(StatusCard status){

    }
    private void processCurse(Card curse){

    }


}

/*
	Juego

	Servidor
	2 clientes

	Partida con varias rondas

	El servidor tiene una lista de palabras ("pala1", "pala2", "pala3", etc)
	Coge 3 palabras al azar

	3 rondas, 1 palabra por ronda

	Servidor la manda a clientes
	Clientes muestran por pantalla y los jugadores tienen que escribirla
	Si aciertan, esperan a la siguiente ronda y guardan el tiempo por rondas
	Si fallan, reintentan hasta acertar

	Al final de las 3 rondas: el servidor manda por XML el resultado a los clientes (el ganador)


	Partida: 3 palabras para las rondas, los dos clientes, tiempos cliente-ronda


	parte de servidor:

	clase main {
		SS / pool hilos


		while (abierto){
			p = new Partida···;

			while (p.jugadores.size() < p.max) {
				GestorCliente g = new gestorCliente(ss.accept(), p);
			}
		}

	}

	Clase Partida {
		datos;
		Lista<Socket> jugadores;
		Mapa<Socket, Lista<Long>> resultados;
		int maximo;
		bool empa;


		boolean meterJugador(Socket s) {
			jugadores.add(s);
			resultados.put(s, new Lista<Long>());
		}

		boolean meterResultado(Socket jugador, long tiempo) {
			resultados.get(jugador).add(tiempo);
		}
	}

	Clase GestorCliente extends Thread {
		Partida p;
		Socket s;

	}



 */