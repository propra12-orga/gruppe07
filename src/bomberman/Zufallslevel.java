package bomberman;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

/**
 * Erzeugt ein Zufallsgeneriertes Level nach bestimmten Kriterien.
 */

public class Zufallslevel {
	
	private BomberMan player1;
	private BomberMan player2;
	private JLayeredPane jPanel;
	private Spielfeld spielfeld;
	private JLabel walls[][];
	int x1,x2,y1,y2;				// x and y for Player1 and 2
	
	public Zufallslevel(Spielfeld spielfeld,BomberMan player1, BomberMan player2, JLayeredPane jPanel) {
		this.walls = spielfeld.getWalls();
		this.player1 = player1;
		this.player2 = player2;
		this.jPanel = jPanel;
		this.spielfeld = spielfeld;
	}

	/**
	 * Methode, die aufgerufen werden muss, ein neues Zufallslevel zu generieren.<br>
	 * Setzt zuerst die beiden Spieler durch Zufall in einem bestimmten bereich, <br>
	 * dann die Bombe. Auﬂerdem werden die Randwaende erzeugt. Die uebrig gebliebenen <br>
	 * Felder bekommen den Namen "walkable". Als letztes startet eine for-Schleifen die <br>
	 * Klasse createField(), welche jedem einzelnen Feld Eigenschaften zuweisst.
	 */
	public void createZufallsLevel() {
		//Player 1
		x1 = (int)(Math.random() * 3 + 1);				// Formel = (Math.random() * (high - low) + low)
		y1 = (int)(Math.random() * 2 + 1);
		walls[x1][y1] = new JLabel(new ImageIcon(""));
		walls[x1][y1].setBounds(40 * x1, 40 * y1, 40, 40);
		walls[x1][y1].setName("walkable");
		player1.setX(x1 * 40);
		player1.setY(y1 * 40);
		
		//Player 2
		x2 = (int)(Math.random() * 3 + 17);
		y2 = (int)(Math.random() * 2 + 12);
		walls[x2][y2] = new JLabel(new ImageIcon(""));
		walls[x2][y2].setBounds(40 * x2, 40 * y2, 40, 40);
		walls[x2][y2].setName("walkable");
		player2.setX(x2 * 40);
		player2.setY(y2 * 40);
		
		//Randwaende oben
		for (int i=0; i<21;i++) {
			walls[i][0] = new JLabel(new ImageIcon("src/gfx/solid_wall.png"));
			walls[i][0].setBounds(40 * i, 0, 40, 40);
			walls[i][0].setName("nowalkable");
			jPanel.add(walls[i][0]);
		}
		
		//Randwaende unten
		for (int i=0; i<21;i++) {
			walls[i][14] = new JLabel(new ImageIcon("src/gfx/solid_wall.png"));
			walls[i][14].setBounds(40 * i, 40 * 14, 40, 40);
			walls[i][14].setName("nowalkable");
			jPanel.add(walls[i][14]);
		}
		
		//Randwaende links
		for (int j=1; j<14;j++) {
			walls[0][j] = new JLabel(new ImageIcon("src/gfx/solid_wall.png"));
			walls[0][j].setBounds(0, 40 * j, 40, 40);
			walls[0][j].setName("nowalkable");
			jPanel.add(walls[0][j]);
		}
		
		//Randwaende rechts
		for (int j=1; j<14;j++) {
			walls[20][j] = new JLabel(new ImageIcon("src/gfx/solid_wall.png"));
			walls[20][j].setBounds(40 * 20, 40 * j, 40, 40);
			walls[20][j].setName("nowalkable");
			jPanel.add(walls[20][j]);
		}
		
		// Ausgang, welcher nach dem Zufallsprinzip an eine bestimmte Position gesetzt wird.
		int exitpos = (int)(Math.random() * 10);
		int x3 = 0, y3 = 0;
		
		if (exitpos == 0) {
			x3 = (int)(Math.random() * 8 + 1);
			y3 = (int)(Math.random() * 3 +11);
		}
		
		if (exitpos == 1) {
			x3 = 6;
			y3 = 11;
		}
		
		if (exitpos == 2) {
			x3 = 7;
			y3 = 10;
		}
		
		if (exitpos == 3) {
			x3 = 8;
			y3 = 9;
		}
		
		if (exitpos == 4) {
			x3 = 9;
			y3 = 8;
		}
		
		if (exitpos == 5) {
			x3 = 10;
			y3 = 7;
		}
		
		if (exitpos == 6) {
			x3 = 11;
			y3 = 6;
		}
		
		if (exitpos == 7) {
			x3 = 12;
			y3 = 5;
		}
		
		if (exitpos == 8) {
			x3 = 13;
			y3 = 4;
		}
		
		if (exitpos == 9) {
			x3 = (int)(Math.random() * 8 + 12);
			y3 = (int)(Math.random() * 3 + 1);
		}		
		
		walls[x3][y3] = new JLabel(new ImageIcon("src/gfx/explodable_wall.png"));
		walls[x3][y3].setBounds(40 * x3, 40 * y3, 40, 40);
		walls[x3][y3].setName("hidden");
		jPanel.add(walls[x3][y3]);
		
		spielfeld.createExit(x3,y3);
		
		// Alles freigebliebene auf "walkable" setzen
		for (int i = 1; i < 20; i++) {
			for (int j = 1; j < 14; j++) {				
				if (!((i == x1 && j == y1) || (i == x2 && j == y2) || (i == x3 && j == y3))) {
					createWalkable(i,j);
				}
			}
		}
		
		// Spielfeldgenerator starten
		for (int i = 1; i < 14; i++) {
			for (int j = 1; j < 20; j++) {				
				createField(j,i);
			}
		}
	}
	
	/**
	 * Diese Methode weisst jedem Einzelfeld, welches ihm uebergeben wurde, einen<br>
	 * bestimmten Mauertyp oder ein Lauffeld zu. Zuerst werden den Spielern Platz <br>
	 * von jeweils 3 Feldern reserviert, die direkt an die Spielerposition anknuepfen<br>
	 * und walkable sind. Dadurch haben die Spieler beim platzieren der ersten Bombe genug<br>
	 * Ausweichmoeglichkeiten. Als naechtes wird die erste Reihe generiert (beginnend von oben<br>
	 * rechts), welche nach anderen Regeln als der Rest des Spielfelds erzeugt wird.<br>
	 * Danach geht es weiter mit den restlichen Reihen des Spielfeld.
	 */
	
	public void createField(int x, int y) {
		if ((x == x1 && y == y1) || (x == x2 && y == y2) || 
			(x == x1 + 1 && y == y1) || (x == x2 - 1 && y == y2) ||
			(x == x1 + 2 && y == y1) || (x == x2 - 2 && y == y2) ||
			(x == x1 + 3 && y == y1) || (x == x2 - 3 && y == y2)) {
			createWalkable(x,y);
			
			// erste Reihe
		} else if (!walls[x][y].getName().equals("hidden") && y == 1) {		
			if ((x == 1 && y == 1) || (x == 19 && y == 1) ||
				
				(walls[x-1][y].getName().equals("nowalkable") &&
				walls[x][y-1].getName().equals("nowalkable") &&
				walls[x-1][y-1].getName().equals("nowalkable"))) {
				
				int z = (int) (Math.random() * 2);
				
				if (z == 0) {
					createWalkable(x,y);
				}
				
				if (z == 1) {
					createDestroyable(x,y);
				}
			} else {
				int z = (int) (Math.random() * 3);
				
				if (z == 0) {
					createWalkable(x,y);
				}
				
				if (z == 1) {
					createDestroyable(x,y);
				}
				
				if (z == 2) {
					createNoWalkable(x,y);
				}
			}
			
			//nach der 1. Reihe
		} else if (!walls[x][y].getName().equals("hidden") && y > 1) {
			if((x == 1 && y == 13) || (x == 19 && y == 13) ||
				checkwalls(x,y)	) {
				
				int z = (int) (Math.random() * 2);				
				if (z == 0) {
					createWalkable(x,y);
				}
				
				if (z == 1) {
					createDestroyable(x,y);
				}

			} else {
				int z = (int) (Math.random() * 3);
				
				if (z == 0) {
					createWalkable(x,y);
				}
				
				if (z == 1) {
					createDestroyable(x,y);
				}
				
				if (z == 2) {
					createNoWalkable(x,y);
				}
			}
		}
	}
	
	/**
	 * Wichtigste Methode dieser Klasse. Diese ist dafuer Zustaendig, dass <br>
	 * Beide Spieler ueberall auf dem Spielfeld hingelangen koennen und nicht durch <br>
	 * Feste Mauern eingesperrt oder blockiert werden. Es wird wird jeweils ein Feld<br>
	 * diagonal rechts, oben, diagonal linksoben, links, diagonal linksunten und unten <br>
	 * abgefragt. Wenn mindestens 2 der abgefragten Felder die Mauer "nowalkable" enthalten,<br>
	 * gibt die Funktion true zurueck und uebermittelt somit der Funktion createField(), dass <br>
	 * das zu erstellende Feld nicht auch den Mauertyp "nowalkable" sein darf, da es sonst zu <br>
	 * einer Blockade kommen kann.
	 */
	
	public boolean checkwalls(int x, int y) {		
		// Checke diagonal nach rechts
		if ((walls[x+1][y-1].getName().equals("nowalkable") &&
			walls[x][y-1].getName().equals("nowalkable")) ||
			
			(walls[x+1][y-1].getName().equals("nowalkable") &&
			walls[x-1][y-1].getName().equals("nowalkable")) ||
			
			(walls[x+1][y-1].getName().equals("nowalkable") &&
			walls[x-1][y].getName().equals("nowalkable")) ||
			
			(walls[x+1][y-1].getName().equals("nowalkable") &&
			walls[x-1][y+1].getName().equals("nowalkable")) ||
			
			(walls[x+1][y-1].getName().equals("nowalkable") &&
			walls[x][y-1].getName().equals("nowalkable")) ||
			
			(walls[x][y-1].getName().equals("nowalkable") &&
			walls[x-1][y-1].getName().equals("nowalkable")) ||
			
			(walls[x][y-1].getName().equals("nowalkable") &&
			walls[x-1][y].getName().equals("nowalkable")) ||
			
			(walls[x][y-1].getName().equals("nowalkable") &&
			walls[x-1][y+1].getName().equals("nowalkable")) ||
			
			(walls[x][y-1].getName().equals("nowalkable") &&
			walls[x][y+1].getName().equals("nowalkable")) ||
			
			(walls[x-1][y-1].getName().equals("nowalkable") &&
			walls[x-1][y].getName().equals("nowalkable")) ||
			
			(walls[x-1][y-1].getName().equals("nowalkable") &&
			walls[x-1][y+1].getName().equals("nowalkable")) ||
			
			(walls[x-1][y-1].getName().equals("nowalkable") &&
			walls[x][y+1].getName().equals("nowalkable")) ||
			
			(walls[x-1][y].getName().equals("nowalkable") &&
			walls[x-1][y+1].getName().equals("nowalkable")) ||
			
			(walls[x-1][y].getName().equals("nowalkable") &&
			walls[x][y+1].getName().equals("nowalkable"))) {
			return true;
			}
		return false;
	}
	
	/**
	 * Erstellt ein Feld mit dem Namen "walkable", auf dem der Spieler laufen kann
	 */
	
	public void createWalkable(int x, int y) {
		walls[x][y] = new JLabel(new ImageIcon(""));
		walls[x][y].setBounds(40 * x, 40 * y, 40, 40);
		walls[x][y].setName("walkable");	
	}
	
	/**
	 * Erstellt den Mauertyp "destroyable", welcher durch eine Bombe zerstoert werden kann
	 */
	
	public void createDestroyable(int x, int y) {
		walls[x][y] = new JLabel(new ImageIcon("src/gfx/explodable_wall.png"));
		walls[x][y].setBounds(40 * x, 40 * y, 40, 40);
		walls[x][y].setName("destroyable");
		jPanel.add(walls[x][y]);
	}
	
	/**
	 * Erstellt den Mauertyp "nowalkable", welcher unzerstoerbar ist.
	 */
	
	public void createNoWalkable(int x, int y) {
		walls[x][y] = new JLabel(new ImageIcon("src/gfx/solid_wall.png"));
		walls[x][y].setBounds(40 * x, 40 * y, 40, 40);
		walls[x][y].setName("nowalkable");
		jPanel.add(walls[x][y]);
	}
}
