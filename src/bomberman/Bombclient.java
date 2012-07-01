package bomberman;

import java.net.*; 
import java.util.Scanner;
import java.io.*; 

import javax.swing.Action;
import javax.swing.JOptionPane;
 
public class Bombclient extends Thread {
	
	private Spielfeld spielfeld;
	private Socket server;
	private Scanner in  = null; 
	private PrintWriter out = null;
	String action;
	
	public Bombclient(String ipadresse,int socket, Spielfeld spielfeld) throws UnknownHostException {
		try {
			server = new Socket(ipadresse, socket);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Verbindung konnte nicht hergestellt werden", "Fehler", 1);
			
		}
		this.spielfeld = spielfeld;
	}
	
	public void run() {
 
		try {
			// Richte Scanner und PrintWriter zum Datentausch ein
			in  = new Scanner( server.getInputStream() ); 
			out = new PrintWriter( server.getOutputStream(), true );
			spielfeld.clientmode = true;
			spielfeld.getMenu().deaktivateNeuButton();
			
			String level = in.nextLine();	// Empfange Levelpfad vom server
			spielfeld.start2PGame(level);		// Starte Spiel mit empfangenem Levelpfad
			      
			spielfeld.removeKeysP1();
			
			// Erstelle Moves fuer Player 1 (Server)
			Action moveUp = new Move(spielfeld.getPlayer1(), "up", spielfeld, false);
			Action moveDown = new Move(spielfeld.getPlayer1(), "down", spielfeld, false);
			Action moveLeft = new Move(spielfeld.getPlayer1(), "left", spielfeld, false);
			Action moveRight = new Move(spielfeld.getPlayer1(), "right", spielfeld, false);
			
			// Bombe fuer Server
			Action bombe = new Bombe(spielfeld.getPlayer1(), spielfeld.getPlayer2(), spielfeld.getLayeredPane(), spielfeld, false);
			
			while (true) {
				
				if (in.hasNext()) {
					action = in.nextLine();	// Empfange Richtung vom Server (Player1)
				}
				if (action.equals("up")) {
						moveUp.actionPerformed(null);
				}
				if (action.equals("down")) {
						moveDown.actionPerformed(null);
				}
				if (action.equals("left")) {
						moveLeft.actionPerformed(null);
				}
				if (action.equals("right")) {
						moveRight.actionPerformed(null);
				}
				if (action.equals("bomb")) {
					bombe.actionPerformed(null);
				}
				if (action.equals("beendet")) {
					server.close();
					break;
				}
				action = null;
			}
		}
		catch (NullPointerException e) {
			
		}
		catch ( UnknownHostException e ) { 
			e.printStackTrace(); 
			JOptionPane.showMessageDialog(null,
					"UnknownHost", "Fehler", 1);
		} 
		catch ( IOException e ) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"ioexception", "Fehler", 1);
		}
		
		// beende Verbindung
		finally {
			if ( server != null ) 
				try { server.close(); } catch ( IOException e ) { } 
		} 
	}
	
	public void sendPrintln(String send) {
		out.println(send);
	}
	
	public void setAction(String action) {
		this.action = action;
	}
}
