package logic;

import model.AttackCard;
import model.Card;
import model.SkillCard;
import model.StatusCard;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.*;

public class GameClient {
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private boolean turn;
    private DataUpdate lastUpdate;
    private boolean keepPlaying;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ObjectInputStream getInput() {
        return input;
    }

    public void setInput(ObjectInputStream input) {
        this.input = input;
    }

    public ObjectOutputStream getOutput() {
        return output;
    }

    public void setOutput(ObjectOutputStream output) {
        this.output = output;
    }
    public GameClient(Socket socket){
        this.socket = socket;
        this.keepPlaying = true;
        this.turn = false;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public DataUpdate getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(DataUpdate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void initialaceStreams(){
        try {
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    public void playGame(){
        try {//Ponemos el try fuera porque si hay problemas de conexion no tiene sentido tratar de seguir jugando
            while (keepPlaying){
                lastUpdate = (DataUpdate) input.readObject();
                processUpdate(lastUpdate);
                if (turn){
                    output.writeObject(this.generateAction());
                }
            }
        }catch (IOException ioe ){
            ioe.printStackTrace();
        } catch (ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }

    }

    private void processUpdate(DataUpdate update){

    }
    private PlayerAction generateAction(){
        //Para pruebas
        Random r = new Random();
        int n;
        while(true){
            n = r.nextInt();
            if(n % 3 == 0){
                return new PlayerAction(true, new AttackCard("",null,"",0,0,0,0));
            } else{
                n = n%lastUpdate.getHand().size();
                Card c = lastUpdate.getHand().get(n);
                if((c instanceof AttackCard)? ((AttackCard)c).getEnergyCost()<=lastUpdate.getEnergy():((SkillCard)c).getEnergyCost()<=lastUpdate.getEnergy()){
                    return new PlayerAction(false,c);
                }
            }
        }
    }

}
