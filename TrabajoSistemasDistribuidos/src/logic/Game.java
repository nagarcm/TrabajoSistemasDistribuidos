package logic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Game {
    //Main game class, instances the server if necesary, play the game

    private String ip;
    private int port;

    //private GameClient cliente;

    private GameHost host;
    private ExecutorService pool;
    private boolean hosting;

    public Game(){
        //this.cliente = new GameClient();
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
        }
        //comportamiento cliente

    }



}
