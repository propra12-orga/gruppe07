package bomberman;

import java.net.*; 
import java.util.Scanner;
import java.io.*; 

import javax.swing.Action;
import javax.swing.JOptionPane;
 
/**
 * Erzeugt den Clienten, welcher zum spielen im Netzwerk ben&ouml;tigt wird.<br>
 * <br>
 * Sucht nach der Host-IP und nimmt diese bei Richtigkeit an, um ein Spiel im Netzwerk zu starten.
 */
public class Bombclient extends Thread {
	
	private Spielfeld spielfeld;
	private Socket server;
	private Scanner in  = null; 
	private PrintWriter out = null;
	String action;
	
	/**
	 * Zugriff auf die IP-Adresse, den Socket und das Spielfeld wird gew&auml;hrt.<br>
	 * <br>
	 * @param ipadresse &Uuml;bergibt die IP-Adresse zur &Uuml;berpr&uuml;fung and das Spiel.<br>
	 * @param socket Erstellt sucht im Netzwerk einen verf&uuml;gbaren Socket, mit der passenden IP-Adresse.<br>
	 * @param spielfeld Weist der aktuellen Methode Animation die Werte aus der Klasse Spielfeld zu.<br>
	 * @throws UnknownHostException Ist die eingegebene IP-Adresse falsch, wird eine Fehlermeldung zur&uuml;ck gegeben.<br>
	 */
	public Bombclient(String ipadresse,int socket, Spielfeld spielfeld) throws UnknownHostException {
		try {
			server = new Socket(ipadresse, socket);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Verbindung konnte nicht hergestellt werden", "Fehler", 1);
			
		}
		this.spielfeld = spielfeld;
	}
	
	/**
	 * Bei verf&uuml;gbarer IP-Adresse wird das Spiel gestartet und alle Werte vom Server eingelesen,<br>
	 * um eine exakte Kopie des Hosts zu erstellen. Lediglich die Steuerung wird auf Spieler 2 angewandt.
	 */
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
	
	/**
	 * @param send &Uuml;bergibt die IP-Adresse vom Clienten an den Host.
	 */
	public void sendPrintln(String send) {
		out.println(send);
	}
	
	/**
	 * @param action &Uuml;berpr&uuml;ft, ob schon Aktionen auf Seiten des Hosts get&auml;tigt wurden.
	 */
	public void setAction(String action) {
		this.action = action;
	}
}
