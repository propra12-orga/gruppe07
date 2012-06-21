package bomberman;

import java.net.*; 
import java.util.Scanner;
import java.io.*; 

import javax.swing.Action;
 
public class Bombclient extends Thread {
	
	private Spielfeld spielfeld;
	private Socket server;
	private Scanner in  = null; 
	private PrintWriter out = null;
	
	public Bombclient(String ipadresse,int socket, Spielfeld spielfeld) throws UnknownHostException, IOException {
		server = new Socket(ipadresse, socket);
		this.spielfeld = spielfeld;
	}
	
	public void run() {
		spielfeld.setClientActive(true);
 
		try {
			// Richte Scanner und PrintWriter zum Datentausch ein
			in  = new Scanner( server.getInputStream() ); 
			out = new PrintWriter( server.getOutputStream(), true );
			
			
			String level = in.nextLine();	// Empfange Levelpfad vom server
			spielfeld.startGame(level);		// Starte Spiel mit empfangenem Levelpfad
			      
			spielfeld.removeKeysP1();
			
			// Erstelle Moves fuer Player 1 (Server)
			Action moveUp = new Move(spielfeld.getPlayer1(), "up", spielfeld, false);
			Action moveDown = new Move(spielfeld.getPlayer1(), "down", spielfeld, false);
			Action moveLeft = new Move(spielfeld.getPlayer1(), "left", spielfeld, false);
			Action moveRight = new Move(spielfeld.getPlayer1(), "right", spielfeld, false);
			
			while (true) {
				
				String richtung = in.nextLine();	// Empfange Richtung vom Server (Player1)
				
				if (richtung.equals("up")) {
						moveUp.actionPerformed(null);
				}
				if (richtung.equals("down")) {
						moveDown.actionPerformed(null);
				}
				if (richtung.equals("left")) {
						moveLeft.actionPerformed(null);
				}
				if (richtung.equals("right")) {
						moveRight.actionPerformed(null);
				}
			}
		}
		
		catch ( UnknownHostException e ) { 
			e.printStackTrace(); 
		} 
		catch ( IOException e ) { 
			e.printStackTrace(); 
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
}
