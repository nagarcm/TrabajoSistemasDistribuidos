package aplication;

import java.util.concurrent.*;

import logic.GameHost;

public class Server {
	public static void main(String[] args) {
		 ExecutorService pool = Executors.newCachedThreadPool();
		 GameHost host = new GameHost(pool);
		pool.submit(host);
		System.out.println("Hosting Correct");
		String ip = "localhost";
		int port;

		try {
			while (host.getPort() == 0) {
				Thread.sleep(1000);
			}
			port = host.getPort();
			ip=host.getIp();
			
			System.out.println("Servidor iniciado\nLocalHost: "+ip+"\nPort: "+port);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
