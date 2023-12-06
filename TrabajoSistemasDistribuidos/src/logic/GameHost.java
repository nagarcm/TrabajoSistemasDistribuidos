package logic;

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
    private GameClient activePlayer;
    private GameClient pasivePlayer;
    private ExecutorService pool;


    public GameHost(ExecutorService pool){
        this.pool = pool;
        this.port1 = 0;
        this.port2 = 0;
    }
    public GameHost(){
        this.pool = Executors.newCachedThreadPool();
        this.port1 = 0;
        this.port2 = 0;

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

        try(ServerSocket serverSocket = new ServerSocket(0);){
            while (this.keepPlaying){
                try{
                    this.port1 = serverSocket.getLocalPort();
                    this.activePlayer = new GameClient(serverSocket.accept());
                    this.pasivePlayer = new GameClient(serverSocket.accept());
                    this.playGame();

                }catch(IOException io){
                    io.printStackTrace();
                }
            }
        } catch (IOException io){
            io.printStackTrace();
        }




    }

    public void playGame(){
        Random random = new Random();
        if (random.nextInt()%2==0){this.switchPlayers();}








    }
    private void switchPlayers(){
        GameClient aux;
        aux = activePlayer;
        activePlayer = pasivePlayer;
        pasivePlayer = aux;
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