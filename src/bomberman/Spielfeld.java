package bomberman;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

import readSpielfeld.ReadFile;

public class Spielfeld extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLayeredPane jPanel = new JLayeredPane();
	private JButton startButton = new JButton();
	private JButton beendenButton = new JButton();
	private JLabel introBild = new JLabel(new ImageIcon("src/gfx/intro.jpg"));
	private JLabel walls[][] = new JLabel[21][15];
	private char[][] spielFeld;
	private Action moveRight, moveLeft, moveUp, moveDown, moveRight2,
			moveLeft2, moveUp2, moveDown2;
	private Bombe bombe1, bombe2;
	public Tuere exit;
	private BomberMan player1 = new BomberMan(1);
	private BomberMan player2 = new BomberMan(2);

	Spielfeld(String title) {
		super(title);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		int frameWidth = 960;
		int frameHeight = 750;
		setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setLocation(x, y);
		setResizable(false);
		Container cp = getContentPane();
		cp.setLayout(null);

		Color DARKGREEN = new Color(51, 153, 0);
		jPanel.setBounds(60, 60, 840, 600);
		jPanel.setOpaque(true);
		jPanel.setBackground(DARKGREEN);
		jPanel.setBorder(new javax.swing.border.LineBorder(Color.BLACK, 3));
		cp.add(jPanel);
		jPanel.add(introBild);
		introBild.setBounds(1, 1, 840, 600);
		startButton.setBounds(56, 680, 137, 25);
		startButton.setText("Starten");
		startButton.setMargin(new Insets(2, 2, 2, 2));
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				introBild.setVisible(false);
				createWorld();
				player1.put(jPanel);
				player2.put(jPanel);
			}
		});
		cp.add(startButton);

		beendenButton.setBounds(760, 680, 137, 25);
		beendenButton.setText("Beenden");
		beendenButton.setMargin(new Insets(2, 2, 2, 2));
		beendenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				System.exit(0);
			}
		});
		cp.add(beendenButton);
		setVisible(true);

		// Moves for first player
		moveRight = new Move(player1, "right", this);
		startButton.getInputMap().put(KeyStroke.getKeyStroke("D"), "moveRight");
		startButton.getActionMap().put("moveRight", moveRight);
		beendenButton.getInputMap().put(KeyStroke.getKeyStroke("D"),
				"moveRight");
		beendenButton.getActionMap().put("moveRight", moveRight);

		moveLeft = new Move(player1, "left", this);
		startButton.getInputMap().put(KeyStroke.getKeyStroke("A"), "moveLeft");
		startButton.getActionMap().put("moveLeft", moveLeft);
		beendenButton.getInputMap()
				.put(KeyStroke.getKeyStroke("A"), "moveLeft");
		beendenButton.getActionMap().put("moveLeft", moveLeft);

		moveUp = new Move(player1, "up", this);
		startButton.getInputMap().put(KeyStroke.getKeyStroke("W"), "moveUp");
		startButton.getActionMap().put("moveUp", moveUp);
		beendenButton.getInputMap().put(KeyStroke.getKeyStroke("W"), "moveUp");
		beendenButton.getActionMap().put("moveUp", moveUp);

		moveDown = new Move(player1, "down", this);
		startButton.getInputMap().put(KeyStroke.getKeyStroke("S"), "moveDown");
		startButton.getActionMap().put("moveDown", moveDown);
		beendenButton.getInputMap()
				.put(KeyStroke.getKeyStroke("S"), "moveDown");
		beendenButton.getActionMap().put("moveDown", moveDown);

		bombe1 = new Bombe(player1, player2, jPanel, this);
		startButton.getInputMap().put(KeyStroke.getKeyStroke("F"), "bombe1");
		startButton.getActionMap().put("bombe1", bombe1);
		beendenButton.getInputMap().put(KeyStroke.getKeyStroke("F"), "bombe1");
		beendenButton.getActionMap().put("bombe1", bombe1);

		// Moves for second player
		moveRight2 = new Move(player2, "right", this);
		startButton.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),
				"moveRight2");
		startButton.getActionMap().put("moveRight2", moveRight2);
		beendenButton.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),
				"moveRight2");
		beendenButton.getActionMap().put("moveRight2", moveRight2);

		moveLeft2 = new Move(player2, "left", this);
		startButton.getInputMap().put(KeyStroke.getKeyStroke("LEFT"),
				"moveLeft2");
		startButton.getActionMap().put("moveLeft2", moveLeft2);
		beendenButton.getInputMap().put(KeyStroke.getKeyStroke("LEFT"),
				"moveLeft2");
		beendenButton.getActionMap().put("moveLeft2", moveLeft2);

		moveUp2 = new Move(player2, "up", this);
		startButton.getInputMap().put(KeyStroke.getKeyStroke("UP"), "moveUp2");
		startButton.getActionMap().put("moveUp2", moveUp2);
		beendenButton.getInputMap()
				.put(KeyStroke.getKeyStroke("UP"), "moveUp2");
		beendenButton.getActionMap().put("moveUp2", moveUp2);

		moveDown2 = new Move(player2, "down", this);
		startButton.getInputMap().put(KeyStroke.getKeyStroke("DOWN"),
				"moveDown2");
		startButton.getActionMap().put("moveDown2", moveDown2);
		beendenButton.getInputMap().put(KeyStroke.getKeyStroke("DOWN"),
				"moveDown2");
		beendenButton.getActionMap().put("moveDown2", moveDown2);

		bombe2 = new Bombe(player2, player1, jPanel, this);
		startButton.getInputMap().put(KeyStroke.getKeyStroke("K"), "bombe2");
		startButton.getActionMap().put("bombe2", bombe2);
		beendenButton.getInputMap().put(KeyStroke.getKeyStroke("K"), "bombe2");
		beendenButton.getActionMap().put("bombe2", bombe2);
	}

	private void createWorld() {
		ReadFile rf = null;
		try {
			rf = new ReadFile("src/readSpielfeld/level1.txt");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			this.spielFeld = rf.read();
		} catch (IOException e) {
			System.out.println(e.getMessage());
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

				// Ausgang
				if (spielFeld[i][j] == 'T') {
					walls[i][j] = new JLabel(new ImageIcon(
							"src/gfx/door/door.png"));
					walls[i][j].setBounds(40 * i, 40 * j, 40, 40);
					walls[i][j].setName("exit");
					jPanel.add(walls[i][j]);

					JLabel[] toRemove = new JLabel[3];
					toRemove[0] = this.player1.getBomberMan();
					toRemove[1] = this.player2.getBomberMan();
					toRemove[2] = walls[i][j];

					exit = new Tuere(i, j, jPanel, toRemove);
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

	public JButton getStartButton() {
		return startButton;
	}

	public void setKeysBackP1() {
		startButton.getActionMap().put("moveRight", moveRight);
		startButton.getActionMap().put("moveLeft", moveLeft);
		startButton.getActionMap().put("moveUp", moveUp);
		startButton.getActionMap().put("moveDown", moveDown);
	}

	public void setKeysBackP2() {
		startButton.getActionMap().put("moveRight2", moveRight2);
		startButton.getActionMap().put("moveLeft2", moveLeft2);
		startButton.getActionMap().put("moveUp2", moveUp2);
		startButton.getActionMap().put("moveDown2", moveDown2);
	}

	public void removeKeysP1() {
		startButton.getActionMap().put("moveRight", null);
		startButton.getActionMap().put("moveLeft", null);
		startButton.getActionMap().put("moveUp", null);
		startButton.getActionMap().put("moveDown", null);
	}

	public void removeKeysP2() {
		startButton.getActionMap().put("moveRight2", null);
		startButton.getActionMap().put("moveLeft2", null);
		startButton.getActionMap().put("moveUp2", null);
		startButton.getActionMap().put("moveDown2", null);
	}

	public void unbindAllControls() {
		startButton.getInputMap().clear();
		startButton.getActionMap().clear();
	}

	public Bombe getBomb1() {
		return bombe1;
	}

	public Bombe getBomb2() {
		return bombe2;
	}

	public static void main(String[] args) {
		new Spielfeld("bomberman");
	}
}