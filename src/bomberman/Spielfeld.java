package bomberman;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

import readSpielfeld.ReadFile;

public class Spielfeld extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLayeredPane jPanel = new JLayeredPane();
	private JLabel introBild = new JLabel(new ImageIcon("src/gfx/intro.jpg"));
	private JLabel walls[][] = new JLabel[21][15];
	private JLabel jPunkte1 = new JLabel();
	private JLabel jPunkte2 = new JLabel();
	private JLabel zeit = new JLabel();
	private char[][] spielFeld;
	private Action moveRight, moveLeft, moveUp, moveDown, moveRight2,
			moveLeft2, moveUp2, moveDown2;
	private Bombe bombe1, bombe2;
	public Tuere exit;
	private BomberMan player1 = new BomberMan(1);
	private BomberMan player2 = new BomberMan(2);
	public boolean servermode = false;
	public boolean clientmode = false;
	private Bombserver server = null;
	private Bombclient client = null;
	private int punkte1 = 0, punkte2 = 0;
	private JPanel spielsteuerung;
	private Zufallslevel level;
	private Zeit z;
	private Thread zeitThread;
	public boolean gamerunning;
	private Menu menu;

	Spielfeld(String title) {
		super(title);
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
				if (servermode) {
					getBombserver().sendPrintln("beendet");
					getBombserver().setAction("beendet");
				}
				if (clientmode) {
					getBombclient().sendPrintln("beendet");
					getBombclient().setAction("beendet");
				}
		    }
		});
		int frameWidth = 844;
		int frameHeight = 680;
		setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setLocation(x, y);
		getContentPane().setBackground(Color.BLACK);
		setResizable(false);
		Container cp = getContentPane();
		cp.setLayout(null);
		Color DARKGREEN = new Color(51, 153, 0);
		jPanel.setBounds(0, 50, 840, 600);
		jPanel.setOpaque(true);
		jPanel.setBackground(DARKGREEN);
		jPanel.setBorder(new javax.swing.border.LineBorder(Color.BLACK, 3));
		cp.add(jPanel);
		jPanel.add(introBild, 0);
		introBild.setBounds(1, 1, 840, 600);
		
		level = new Zufallslevel(this, player1,player2, jPanel);
		z = new Zeit(this, zeit);
		zeitThread = new Thread (z);
		
		jPunkte1.setBounds(20, 25, 150, 25);
		jPunkte1.setText("Player1: 0 Punkte");
		jPunkte1.setFont(new Font("Arial", Font.PLAIN, 14));
		jPunkte1.setForeground(Color.WHITE);
		cp.add(jPunkte1);

		jPunkte2.setBounds(700, 25, 150, 25);
		jPunkte2.setText("Player2: 0 Punkte");
		jPunkte2.setFont(new Font("Arial", Font.PLAIN, 14));
		jPunkte2.setForeground(Color.WHITE);
		cp.add(jPunkte2);

		zeit.setBounds(360, 25, 150, 25);
		zeit.setText("Zeit: 0 Sekunden");
		zeit.setFont(new Font("Arial", Font.PLAIN, 14));
		zeit.setForeground(Color.WHITE);
		cp.add(zeit);
		
		menu = new Menu(this);
		
		setVisible(true);

		bombe1 = new Bombe(player1, player2, jPanel, this, true);
		bombe2 = new Bombe(player2, player1, jPanel, this, true);

		moveRight = new Move(player1, "right", this, true);
		moveLeft = new Move(player1, "left", this, true);
		moveUp = new Move(player1, "up", this, true);
		moveDown = new Move(player1, "down", this, true);

		moveRight2 = new Move(player2, "right", this, true);
		moveLeft2 = new Move(player2, "left", this, true);
		moveUp2 = new Move(player2, "up", this, true);
		moveDown2 = new Move(player2, "down", this, true);
		
		spielsteuerung = new JPanel();
		spielsteuerung.setFocusable(true);
		
		setPlayerKeys();
		
		cp.add(spielsteuerung);
	}

	public String randomLevel() {
		String path = "src/readSpielfeld/";
		int low = 1, high = 5;
		String level = "level" + (int) (Math.random() * (high - low) + low)
				+ ".txt";

		return path + level;
	}

	private void createWorld(String level) {
		ReadFile rf = null;
		try {
			rf = new ReadFile(level);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		try {
			this.spielFeld = rf.read();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

		for (int i = 0; i < spielFeld.length; i++) {
			for (int j = 0; j < spielFeld[0].length; j++) {
				// Unzerstuerbare Wand
				if (spielFeld[i][j] == 'X') {
					walls[i][j] = new JLabel(new ImageIcon(
							"src/gfx/solid_wall.png"));
					walls[i][j].setBounds(40 * i, 40 * j, 40, 40);
					walls[i][j].setName("nowalkable");
					jPanel.add(walls[i][j]);
				}

				// Zerstuerbare Wand
				if (spielFeld[i][j] == 'Z') {
					walls[i][j] = new JLabel(new ImageIcon(
							"src/gfx/explodable_wall.png"));
					walls[i][j].setBounds(40 * i, 40 * j, 40, 40);
					walls[i][j].setName("destroyable");
					jPanel.add(walls[i][j]);
				}

				// Nichts
				if (spielFeld[i][j] == 'O') {
					walls[i][j] = new JLabel(new ImageIcon(""));
					walls[i][j].setBounds(40 * i, 40 * j, 40, 40);
					walls[i][j].setName("walkable");
				}

				// Ausgang ohne Wand
				if (spielFeld[i][j] == 'T') {
					walls[i][j] = new JLabel(new ImageIcon(
							"src/gfx/door/door.png"));
					walls[i][j].setBounds(40 * i, 40 * j, 40, 40);
					walls[i][j].setName("exit");
					jPanel.add(walls[i][j]);

					createExit(i, j);
				}

				// Ausgang mit Wand
				if (spielFeld[i][j] == 'U') {
					walls[i][j] = new JLabel(new ImageIcon(
							"src/gfx/explodable_wall.png"));
					walls[i][j].setBounds(40 * i, 40 * j, 40, 40);
					walls[i][j].setName("hidden");
					jPanel.add(walls[i][j]);

					createExit(i, j);
				}
				
				if (spielFeld[i][j] == 'G') {
					walls[i][j] = new JLabel(new ImageIcon(""));
					walls[i][j].setBounds(40 * i, 40 * j, 40, 40);
					walls[i][j].setName("walkable");
					
					new Gegner(this,i*40,j*40,jPanel);
				}

				// Player(s)
				if (spielFeld[i][j] == 'P') {
					walls[i][j] = new JLabel(new ImageIcon(""));
					walls[i][j].setBounds(40 * i, 40 * j, 40, 40);
					walls[i][j].setName("walkable");
					player1.setX(i * 40);
					player1.setY(j * 40);
				}
				if (spielFeld[i][j] == 'L') {
					walls[i][j] = new JLabel(new ImageIcon(""));
					walls[i][j].setBounds(40 * i, 40 * j, 40, 40);
					walls[i][j].setName("walkable");
					player2.setX(i * 40);
					player2.setY(j * 40);
				}
			}
		}
	}

	public JLabel[][] getWalls() {
		return walls;
	}

	public void setPlayerKeys() {
		// Moves for first player
		spielsteuerung.getInputMap().put(KeyStroke.getKeyStroke("D"), "moveRight");
		spielsteuerung.getActionMap().put("moveRight", moveRight);

		spielsteuerung.getInputMap().put(KeyStroke.getKeyStroke("A"), "moveLeft");
		spielsteuerung.getActionMap().put("moveLeft", moveLeft);

		spielsteuerung.getInputMap().put(KeyStroke.getKeyStroke("W"), "moveUp");
		spielsteuerung.getActionMap().put("moveUp", moveUp);

		spielsteuerung.getInputMap().put(KeyStroke.getKeyStroke("S"), "moveDown");
		spielsteuerung.getActionMap().put("moveDown", moveDown);

		spielsteuerung.getInputMap().put(KeyStroke.getKeyStroke("F"), "bombe1");
		spielsteuerung.getActionMap().put("bombe1", bombe1);

		// Moves for second player
		spielsteuerung.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),"moveRight2");
		spielsteuerung.getActionMap().put("moveRight2", moveRight2);


		spielsteuerung.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "moveLeft2");
		spielsteuerung.getActionMap().put("moveLeft2", moveLeft2);

		spielsteuerung.getInputMap().put(KeyStroke.getKeyStroke("UP"), "moveUp2");
		spielsteuerung.getActionMap().put("moveUp2", moveUp2);

		spielsteuerung.getInputMap().put(KeyStroke.getKeyStroke("DOWN"),"moveDown2");
		spielsteuerung.getActionMap().put("moveDown2", moveDown2);

		spielsteuerung.getInputMap().put(KeyStroke.getKeyStroke("K"), "bombe2");
		spielsteuerung.getActionMap().put("bombe2", bombe2);
	}

	public void setKeysBackP1() {
		spielsteuerung.getActionMap().put("moveRight", moveRight);
		spielsteuerung.getActionMap().put("moveLeft", moveLeft);
		spielsteuerung.getActionMap().put("moveUp", moveUp);
		spielsteuerung.getActionMap().put("moveDown", moveDown);
		spielsteuerung.getActionMap().put("bombe1", bombe1);
	}

	public void setKeysBackP2() {
		spielsteuerung.getActionMap().put("moveRight2", moveRight2);
		spielsteuerung.getActionMap().put("moveLeft2", moveLeft2);
		spielsteuerung.getActionMap().put("moveUp2", moveUp2);
		spielsteuerung.getActionMap().put("moveDown2", moveDown2);
		spielsteuerung.getActionMap().put("bombe2", bombe2);
	}

	public void removeKeysP1() {
		spielsteuerung.getActionMap().put("moveRight", null);
		spielsteuerung.getActionMap().put("moveLeft", null);
		spielsteuerung.getActionMap().put("moveUp", null);
		spielsteuerung.getActionMap().put("moveDown", null);
		spielsteuerung.getActionMap().put("bombe1", null);
	}

	public void removeKeysP2() {
		spielsteuerung.getActionMap().put("moveRight2", null);
		spielsteuerung.getActionMap().put("moveLeft2", null);
		spielsteuerung.getActionMap().put("moveUp2", null);
		spielsteuerung.getActionMap().put("moveDown2", null);
		spielsteuerung.getActionMap().put("bombe2", null);
	}

	public void unbindAllControls() {
		spielsteuerung.getInputMap().clear();
		spielsteuerung.getActionMap().clear();
	}

	public void updatePunktePlayer1(int p) {
		this.punkte1 = p;
		jPunkte1.setText("Player1: " + p + " Punkte");
	}

	public void updatePunktePlayer2(int p) {
		this.punkte2 = p;
		jPunkte2.setText("Player2: " + p + " Punkte");
	}

	public int getPunkte1() {
		return punkte1;
	}

	public void setPunkte1(int punkte1) {
		this.punkte1 = punkte1;
	}

	public int getPunkte2() {
		return punkte2;
	}

	public void setPunkte2(int punkte2) {
		this.punkte2 = punkte2;
	}

	public Bombe getBomb1() {
		return bombe1;
	}

	public Bombe getBomb2() {
		return bombe2;
	}
	
	/**
	 * Bereitet das Spiel auf den Neustart vor, indem durch gamerunning=false die Threads abgebrochen werden,<br>
	 * Zeit, Punkte und PlayerKeys zurueckgesetzt werden und das alte Spielfeld bzw. ein eventueller Winscreen<br>
	 * entfernt wird.
	 */
	public void prepareForRestart() {
		gamerunning = false;
		z.resetzeit();
		zeitThread.interrupt();
		
		zeit.setText("Zeit: 0 Sekunden");
		updatePunktePlayer1(0);
		updatePunktePlayer2(0);
		
		exit.getWin1().setVisible(false);
		exit.getWin2().setVisible(false);
		jPanel.remove(exit.getWin1());
		jPanel.remove(exit.getWin2());
		jPanel.removeAll();
		jPanel.repaint();
		setPlayerKeys();
	}
	
	/**
	 * Startet ein neues Spiel mit einem existierenden Level, dessen Pfad per String uebergeben werden muss.<br>
	 * Es werden 2 Spieler auf das Spielfeld gesetzt.
	 * @param level
	 */
	public void start2PGame(String level) {
		gamerunning = true;
		introBild.setVisible(false);
		jPanel.remove(introBild);
		createWorld(level);
		player1.put(jPanel);
		player2.put(jPanel);		
		
		spielsteuerung.requestFocusInWindow();
		
		// Die Zeit z&auml;hlen
		z = new Zeit(this, zeit);
		zeitThread = new Thread (z);
		zeitThread.start();
	}
	/**
	 * Startet ein neues Spiel mit einem Zufallsgenerierten Level.<br>
	 * Es werden 2 Spieler auf das Spielfeld gesetzt.
	 */
	public void startRandomlevel2PGame() {
		gamerunning = true;
		introBild.setVisible(false);
		jPanel.remove(introBild);
		level.createZufallsLevelFor2P();
		player1.put(jPanel);
		player2.put(jPanel);
		spielsteuerung.requestFocusInWindow();
		
		// Die Zeit z&auml;hlen
		z = new Zeit(this, zeit);
		zeitThread = new Thread (z);
		zeitThread.start();
	}
	
	/**
	 * Startet ein neues Spiel mit einem existierenden Level, dessen Pfad per String uebergeben werden muss.<br>
	 * Es wird 1 Spieler auf das Spielfeld gesetzt.
	 * @param level
	 */
	public void start1PGame(String level) {
		gamerunning = true;
		removeKeysP2();
		introBild.setVisible(false);
		jPanel.remove(introBild);
		createWorld(level);
		player2.remove();
		player1.put(jPanel);
		spielsteuerung.requestFocusInWindow();
		
		// Die Zeit z&auml;hlen
		z = new Zeit(this, zeit);
		zeitThread = new Thread (z);
		zeitThread.start();
	}
	
	/**
	 * Startet ein neues Spiel mit einem Zufallsgenerierten Level.<br>
	 * Es wird 1 Spieler auf das Spielfeld gesetzt.
	 */
	public void startRandomlevel1PGame() {
		gamerunning = true;
		removeKeysP2();
		introBild.setVisible(false);
		jPanel.remove(introBild);
		level.createZufallsLevelFor1P();
		player2.remove();
		player1.put(jPanel);
		spielsteuerung.requestFocusInWindow();
		
		
		// Die Zeit z&auml;hlen
		z = new Zeit(this, zeit);
		zeitThread = new Thread (z);
		zeitThread.start();
	}
	
	/**
	 * Fragt ab, ob der Spieler Host oder Client sein will und erstellt dementsprechend<br>
	 * einen neuen Bombserver oder Bombclient. Wenn der Spieler die Option Client ausgewaehlt hat<br>
	 * muss er zusaetzlich die IP-Adresse des Hosts eingeben.
	 */
	public void startNetworkGame() {
		Object[] options = { "Host", "Client" };
		int abfrage = JOptionPane.showOptionDialog(null,
				"Moechten Sie Host oder Client sein?",
				"Bitte ausw&auml;hlen", JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, options,
				options[0]);
		// Wenn Option Host, starte Bombserver
		if (abfrage == 0) {
			try {
				server = new Bombserver(3141, getSpielfeld());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			server.start();

			// Wenn Option Client, starte Bombclient
		} else if (abfrage == 1) {

			String ipadresse = JOptionPane
					.showInputDialog("Bitte IP-Adresse des Hosts eingeben");
			try {
				client = new Bombclient(ipadresse, 3141, getSpielfeld());
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			client.start();
		}
	}

	public Spielfeld getSpielfeld() {
		return this;
	}
	
	public void interruptBombClient() {
		client.interrupt();
	}

	public Bombserver getBombserver() {
		return server;
	}

	public Bombclient getBombclient() {
		return client;
	}

	public BomberMan getPlayer1() {
		return player1;
	}

	public BomberMan getPlayer2() {
		return player2;
	}

	public JLayeredPane getLayeredPane() {
		return jPanel;
	}

	public JLabel getZeit() {
		return zeit;
	}
	
	public Menu getMenu() {
		return menu;
	}
	
	public void createExit(int x, int y) {
		JLabel[] toRemove = new JLabel[3];
		toRemove[0] = this.player1.getBomberMan();
		toRemove[1] = this.player2.getBomberMan();
		toRemove[2] = walls[x][y];

		exit = new Tuere(x, y, jPanel, toRemove);
	}

	public static void main(String[] args) {
		new Spielfeld("bomberman");
	}
}