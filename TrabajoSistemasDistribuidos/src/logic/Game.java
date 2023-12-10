package logic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Game {
	// Main game class, instances the server if necesary, play the game
	private String ip;
	private int port;

	private GameHost host;
	private ExecutorService pool;

	private boolean hosting;
	private Socket socket;

	private GameClient gameClient;

	public Game() {
		this.hosting = false;
		this.ip = "";
		this.port = 0;
	}

	public boolean isHosting() {
		return hosting;
	}

	public void setHosting(boolean hosting) {
		this.hosting = hosting;
	}

	public int getPort() {
		return port;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void startServer() {
		if (isHosting()) {
			// comportamiento de host
			this.pool = Executors.newCachedThreadPool();
			this.host = new GameHost(this.pool);
			this.pool.submit(this.host);
			System.out.println("Hosting Correct");
			this.ip = "localhost";

			try {
				while (host.getPort() == 0) {
					Thread.sleep(1000);
				}
				port = host.getPort();
				this.ip=host.getIp();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		// comportamiento cliente

	}

	public void startGame() {
		try {
			this.socket = new Socket(ip, port);
			if (isHosting()) {
				System.out.println("Local correctly conected, waiting to server");
			} else {
				System.out.println("Client correctly conected, waiting to server");
			}
			this.gameClient = new GameClient(this.socket);
			this.gameClient.initialaceStreams();
			
			System.out.println("Correct Initialization");

		} catch (

		UnknownHostException uhe) {
			uhe.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	public void playGame() {
		this.gameClient.playGame();
	}

}
