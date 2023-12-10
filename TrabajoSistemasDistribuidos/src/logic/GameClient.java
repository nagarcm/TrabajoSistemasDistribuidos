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
	private Scanner teclado;
	private PlayerAction action;

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

	public GameClient(Socket socket) {
		this.socket = socket;
		this.keepPlaying = true;
		this.turn = false;
		this.teclado = new Scanner(System.in);
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

	public void initialaceStreams() {
		try {
			output = new ObjectOutputStream(socket.getOutputStream());
			input = new ObjectInputStream(socket.getInputStream());
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void playGame() {

		try {// Ponemos el try fuera porque si hay problemas de conexion no tiene sentido
				// tratar de seguir jugando
			while (keepPlaying) {
				System.out.println("Leyendo Actualizacion...");
				lastUpdate = (DataUpdate) input.readObject();
				processUpdate(lastUpdate);
				if (turn) {
					System.out.println(this.action.toString());
					output.writeObject(this.action);
				}
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("El servidor se ha desconectado repentinamente");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}

	}

	private void processUpdate(DataUpdate update) {// aqui comprobariamos si el servidor nos esta preguntando si
													// queremos terminar la partida
		if(!update.isLastUpdate()) {
		System.out.println(update.toString());// ESto lo hacemos solo porque estamos haciendo interfaz textual
		int n = 0;
		this.turn = update.isPlayNextAction();
		if (turn) {
			boolean b =false;
			
			while(!b) {
				do {
					System.out.println("Introduzca la carta a jugar(numero)");
					n = teclado.nextInt();
				} while (n < 0 || n > update.getHand().size());
				
				if(n==0) {
					b=true;
				}else if(update.getHand().get(n - 1).getEnergyCost()>update.getEnergy()) {
					System.out.println("La carta tiene damasiado costo. Escoge otra o termina tu turno");
				}else {
					b=true;
				}
				
			}
			if (n == 0) {
				this.action = new PlayerAction(true, null);
			} else {
				this.action = new PlayerAction(false, update.getHand().get(n - 1));
			}

		}

	}else
		
	{
		System.out.println("JUEGO FINALIZADO");
		if(update.getFinalOwnHP()==0) {
			System.out.println("HAS PERDIDO");
		}else {
			System.out.println("HAS GANADO!");
		}
		this.keepPlaying=false;
	}
	}
}
