package logic;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class GameClient {
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    public GameClient(Socket socket){
        this.socket = socket;
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

    public ObjectOutputStream getOutput() {
        return outputStream;
    }
    //public void setOutputStream(ObjectOutputStream outputStream) {this.outputStream = outputStream;}

    public ObjectInputStream getInput() {
        return inputStream;
    }
    //public void setInputStream(ObjectInputStream inputStream) {this.inputStream = inputStream;}









}