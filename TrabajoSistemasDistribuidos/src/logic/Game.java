package logic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Game {
    //Main game class, instances the server if necesary, play the game
    private String ip;
    private int port;

    private GameHost host;
    private ExecutorService pool;

    private boolean hosting;
    private Socket socket;

    private GameClient gameClient;


    public Game(){
        this.hosting = false;
    }

    public boolean isHosting() {
        return hosting;
    }
    public void setHosting(boolean hosting) {
        this.hosting = hosting;
    }
    public void startGame(){
        if(isHosting()){
            //comportamiento de host
            this.pool = Executors.newCachedThreadPool();
            this.host = new GameHost(this.pool);
            this.pool.submit(this.host);
            System.out.println("Host correctamente hostando");
            this.ip = "localhost";
        }
        //comportamiento cliente
        try{
            if (ip.equals("localhost")){
                while(host.getPort()==0){
                    Thread.sleep(1000);
                }
                port = host.getPort();
            }else{
                //Introducir IP
                System.out.println("Introduce IP");
                //ip = new Scanner(System.in).nextLine();
            }

            System.out.println(host.getPort());
            this.socket = new Socket(ip,port);
            if (isHosting()){
                System.out.println("Local correctly conected");
            }else{
                System.out.println("Client correctly conected");
            }
            this.gameClient = new GameClient(this.socket);
            this.gameClient.initialaceStreams();
            this.gameClient.playGame();

        } catch (UnknownHostException uhe){
            uhe.printStackTrace();
        } catch (IOException io){
            io.printStackTrace();
        } /*catch (ClassNotFoundException classnot){
            classnot.printStackTrace();
        }*/catch (InterruptedException interrupt ){
            interrupt.printStackTrace();
        }






    }



}
