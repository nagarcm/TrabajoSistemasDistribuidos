package interfaz;

import model.*;
import logic.*;
import java.util.*;
import java.io.*;


public class InterfazTextual {
	private Game game;
	private Scanner teclado;
	
	public InterfazTextual() {
		this.game = new Game();
		this.teclado = new Scanner(System.in);
	}
	public InterfazTextual(Game game) {
		this.game = game;
		this.teclado = new Scanner(System.in);
	}
	
	
	public void startGame() {
		System.out.println("Bienvenido a Slay The Spire PvP v0.1");
		System.out.println("¿Que desea hacer?\n1. Jugar hosteando Partida.\n2.Conectarse a Partida Online.\n3.Desconectar.");
		String linea = teclado.nextLine().strip();

		
		while(!(linea.equals("1") || linea.equals("2") || linea.equals("3"))) {
			System.out.println("Por favor introduzca una opcion valida");
			linea = teclado.nextLine().strip();
		}
		
		
		if (linea.equals("1")) {
			this.game.setHosting(true);
			this.game.startServer();
			
			System.out.println("Se ha hosteado correctamente en el puerto "+ this.game.getPort()+".");
			System.out.println("¿Desea mostrar tambien la IP local?(No = N/No; Si = introduzca cualquier tecla)");
			String in = teclado.nextLine().strip();
			if(!in.equalsIgnoreCase("No") && !in.equalsIgnoreCase("N")) {
				System.out.println("La IP local es "+ this.game.getIp());
			}
		System.out.println("Conectando con el servidor Local");
		this.game.startGame();
		
		System.out.println("\n\nEmpezando juego:");
		this.game.playGame();
		
		}else if (linea.equals("2")) {
			this.game.setHosting(false);
			System.out.println("¿En que IP se aloja la partida a la que quiere conectarse?(l/Local/localhost para conectarse localmente)");
			String in = teclado.nextLine().strip();
			boolean b = false;
			while(!b) {
				if (in.equalsIgnoreCase("l") ||in.equalsIgnoreCase("local")||in.equalsIgnoreCase("localhost")) {
					this.game.setIp("localhost");
					b=true;
				}else{
					String[] parts = in.split(".");
					if(parts.length==4 ) {
						//Podriamos comprobar mas pero por simplicidad me abstendre
						this.game.setIp(in);
						b=true;
					}else {
						System.out.println("Por favor introduzca una ip valida");
					}
				}
			}
			b=false;
			int n;
			//while(!b) {
				System.out.println("Introduzca el puerto al que desea conectarse:");
				in = teclado.nextLine().strip();
				try {
					n = Integer.parseInt(in);
					//System.out.println(n);
					//if(0>n && n<= 65535) {
					//	b=true;
						this.game.setPort(n);
					//}
				}catch (NumberFormatException e) {
					e.printStackTrace();
				}
				
			//}
			System.out.println("Conectandose al servidor...");
			this.game.startGame();
			System.out.println("Correctamente Conectado!");
			System.out.println("\n\nEmpezando juego:");
			this.game.playGame();
					}
		System.out.println("Gracias por haber jugado");
	}
	
	
	
	
	
	
	

}
