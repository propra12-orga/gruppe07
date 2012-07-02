package bomberman;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*; 
import java.net.*; 
import java.util.Scanner;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
 
/**
 * Erzeugt den Server, welcher zum spielen im Netzwerk ben&ouml;tigt wird.<br>
 * <br>
 * Wartet auf eine Verbindung &uuml;ber die Netzwerk-IP. Wurde eine Verbindung erfolgreich hergestellt, wird das Spiel gestartet.
 */
public class Bombserver extends Thread {
	
	private ServerSocket server = null;
	private Socket client = null;
	private JFrame f = new JFrame("Bitte warten");
	private Spielfeld spielfeld;
	private PrintWriter out = null;
	private Scanner in = null;
	String action;

	/**
	 * Zugriff auf den Socket und das Spielfeld wird gew&auml;hrt.<br>
	 * <br>
	 * @param serversocket Erstellt einen Serversocket, mit dem sich Clienten verbinden k&ouml;nnen.
	 * @param spielfeld Weist der aktuellen Methode Animation die Werte aus der Klasse Spielfeld zu.<br>
	 * @throws IOException
	 */
	public Bombserver(int serversocket, Spielfeld spielfeld) throws IOException {
		server = new ServerSocket( serversocket );
		this.spielfeld = spielfeld;
	}
	
	public void run() {
		zeigeWarteFenster();
    while ( true ) { 
    	try {
	        client = server.accept();		// nehme eine Verbindung an	        
	        serverRunning ( client );
    	} 
    	catch ( IOException e ) {
    		spielfeld.servermode = false;
    		break;		// Unterbreche schleife, wenn server.close() ausgefuehrt wurde
    	} 
    	finally { 
    		if ( client != null ) 
    			try { client.close(); } catch ( IOException e ) { } 
    		} 
    	}
	}
  
	/**
	 * Aktionen, welche m&ouml;glich sind, w&auml;hrend der Server gestartet ist.
	 * @param client &Uuml;bernimmt die Aktionen des Clienten und stellt diese auch auf dem Spielfeld des Hosts dar.
	 * @throws IOException 
	 */
	private void serverRunning(Socket client) throws IOException {
		spielfeld.servermode = true;
		spielfeld.getMenu().deaktivateNeuButton();
		String level = spielfeld.randomLevel();		// erstelle Randomlevel-Pfad
		
		// Richte Scanner und PrintWriter zum Datentausch ein
		in  = new Scanner( client.getInputStream() ); 
		out = new PrintWriter( client.getOutputStream(), true );
		
		out.println( level );	// Uebertrage Pfad zum Client
		
		// Blende Wartefenster aus
		f.setVisible(false);
		f.dispose();

		spielfeld.start2PGame(level);		// Starte Spiel mit generiertem Levelpfad
		
		spielfeld.removeKeysP2();
		
		// Erstelle Moves fuer Player 2 (Client)
		Action moveUp = new Move(spielfeld.getPlayer2(), "up", spielfeld, false);
		Action moveDown = new Move(spielfeld.getPlayer2(), "down", spielfeld, false);
		Action moveLeft = new Move(spielfeld.getPlayer2(), "left", spielfeld, false);
		Action moveRight = new Move(spielfeld.getPlayer2(), "right", spielfeld, false);
		
		// Bombe fuer Client
		Action bombe = new Bombe(spielfeld.getPlayer2(), spielfeld.getPlayer1(), spielfeld.getLayeredPane(), spielfeld, false);
		
		while (true) {
			if (in.hasNext()) {
			action = in.nextLine();	// Empfange Aktion vom Client (Player2)
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
  
	/**
	 * Zeigt das Warte-Fenster beim Host an, solange sich noch kein Client mit dem Host verbunden hat.
	 */
	public void zeigeWarteFenster() {
		// Richte JFrame ein
		f.setSize(300, 100);
		f.setResizable(false);
		f.setLocationRelativeTo(f.getParent());
		
		f.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
				try {
					server.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		});
		
		// Richte JButton ein
		JButton abbrechen = new JButton();
	    abbrechen.setBounds(82, 30, 137, 25);
		abbrechen.setText("Abbrechen");
		abbrechen.setHorizontalAlignment(JLabel.CENTER);
		
		// Bei Klick auf abbrechen
		abbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				f.setVisible(false);
				f.dispose();
				try {
					server.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		// Richte Ausgabetext ein
		JLabel text = new JLabel("Bitte warten bis Client verbindet.");
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setVerticalAlignment(JLabel.TOP);
		
		Container cp = f.getContentPane();
		cp.add(abbrechen);
		cp.add(text);
		
		f.setVisible(true);
	}
	
	/**
	 * @param send &Uuml;bergibt saämtliche Aktionen vom Host an den Clienten.
	 */
	public void sendPrintln(String send) {
		out.println(send);
	}
	
	/**
	 * @param action &Uuml;berpr&uuml;ft, ob Aktionen auf Seiten des Clienten get&auml;tigt wurden.
	 */
	public void setAction(String action) {
		this.action = action;
	}
}