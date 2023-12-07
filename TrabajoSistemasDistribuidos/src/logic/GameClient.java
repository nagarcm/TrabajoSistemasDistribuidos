package logic;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import model.*;

public class GameClient {
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    private GameCharacter character;
    public GameClient(Socket socket){
        this.socket = socket;
        this.character = new GameCharacter("Try", 85);
        try{
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
            this.inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException io){
            io.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public GameCharacter getCharacter() {
        return character;
    }

    public void setCharacter(GameCharacter character) {
        this.character = character;
    }

    public ObjectOutputStream getOutput() {
        return outputStream;
    }
    //public void setOutputStream(ObjectOutputStream outputStream) {this.outputStream = outputStream;}

    public ObjectInputStream getInput() {
        return inputStream;
    }
    //public void setInputStream(ObjectInputStream inputStream) {this.inputStream = inputStream;}

    public void initializeMatch(){
        //Mazo Aleatorio de 5 ataques, 5 skills y 5 cartas random (no status ni curses)
        for (int i = 0; i < 5; i++) {
            character.getDeck().addCard(CardManager.getRandomCard(CardType.Attack));
        }
        for (int i = 0; i < 5; i++) {
            character.getDeck().addCard(CardManager.getRandomCard(CardType.Skill));
        }
        for (int i = 0; i < 5; i++) {
            character.getDeck().addCard(CardManager.getRandomCard());
        }
    }

    public void endTurn(){
        this.character.turnReset();
    }

    public void startTurn(){
        this.character.turnStart();
    }
    public boolean endGame(){return this.character.getHp()==0;}
    public void send(DataUpdate dataUpdate) throws IOException{
        this.outputStream.writeObject(dataUpdate);
        this.outputStream.flush();
    }
    public PlayerAction recibe() throws IOException{
        try {
            return (PlayerAction) this.inputStream.readObject();
        } catch (ClassNotFoundException classNotFoundException){
            classNotFoundException.printStackTrace();
        }
        return null;
    }


}