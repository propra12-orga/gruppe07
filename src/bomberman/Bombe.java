package bomberman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Bombe extends AbstractAction {
	private static final long serialVersionUID = -7038533653091561580L;
	private BomberMan player1;
	private BomberMan player2;
	private JLayeredPane s;
	private JLabel bomb = new JLabel(new ImageIcon("src/gfx/bomb/bomb2.gif"));
	private JLabel boom1 = new JLabel(new ImageIcon("src/gfx/bomb/bombfast.gif"));
	private JLabel boom2 = new JLabel(new ImageIcon("src/gfx/bomb/bombfast.gif"));
	private JLabel boom3 = new JLabel(new ImageIcon("src/gfx/bomb/bombfast.gif"));
	private JLabel boom4 = new JLabel(new ImageIcon("src/gfx/bomb/bombfast.gif"));
	private JLabel boom5 = new JLabel(new ImageIcon("src/gfx/bomb/bombfast.gif"));
	private int x, y, width=40, height=40;
	private Spielfeld spielfeld;
	private JLabel walls[][];
	private Timer LayTimer = new Timer(1225,new LayBomb());
	private Timer ExploTimer = new Timer(1725,new ExploBomb());
	private Timer LayTimer2 = new Timer(0,new LayBomb());
	private Timer ExploTimer2 = new Timer(500,new ExploBomb());
	private boolean kette;
	private boolean bombIsLayed;
	
	
	public Bombe(BomberMan player1, BomberMan player2, JLayeredPane s, Spielfeld spielfeld) { 
		this.player1 = player1;
		this.player2 = player2;
		this.s = s;
		this.spielfeld = spielfeld;
		this.walls = spielfeld.getWalls();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public JLabel getBomb() {
		return bomb;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (!LayTimer.isRunning()) {
			x = player1.getX();
			y = player1.getY();
			bomb.setBounds(x,y, width, height);
			s.add(bomb, 2);
			bombIsLayed = true;
		
			LayTimer.start();
		}
		ExploTimer.start();
	}
	
	// Anfang der Explosion
	class LayBomb implements ActionListener {
		  public void actionPerformed(ActionEvent e) {

		    s.remove(bomb);
		    bombIsLayed = false;
		    s.repaint();
		    Explosion();
		    LayTimer.stop();
		    LayTimer2.stop();

		  }
	}
	
	// Ende der Explosion
	class ExploBomb implements ActionListener {
		  public void actionPerformed(ActionEvent e) {
			s.remove(boom1);
			s.remove(boom2);
			s.remove(boom3);
			s.remove(boom4);
			s.remove(boom5);
			s.repaint();
			ExploTimer.stop();
			ExploTimer2.stop();
		  }
	}
	
	public void Kettenreaktion() {
		kette=true;
		LayTimer.stop();
		ExploTimer.stop();
		
		LayTimer2.start();
		ExploTimer2.start();
		kette = false;
	}
	
	public boolean bombActive() {
		return bombIsLayed;
	}
	
	
	public void Explosion() {
		boom1.setBounds(x, y, width, height);
		s.add(boom1,0);
		
		// Pruefe ein Feld weiter rechts
		if (walls[(x/40)+1][(y/40)].getName().equals("walkable")) {
			boom2.setBounds(x+40, y, width, height);
			s.add(boom2,1);
		}
		else if (walls[(x/40)+1][(y/40)].getName().equals("destroyable")) {
			boom2.setBounds(x+40, y, width, height);
			s.add(boom2,1);
			walls[(x/40)+1][(y/40)].setIcon(null);
			walls[(x/40)+1][(y/40)].setName("walkable");
		}
		
		// Pruefe Felder auf walls
		// Pruefe ein Feld weiter links
		if (walls[(x/40)-1][(y/40)].getName().equals("walkable")) {
			boom3.setBounds(x-40, y, width, height);
			s.add(boom3,1);
		}
		else if (walls[(x/40)-1][(y/40)].getName().equals("destroyable")) {
			boom3.setBounds(x-40, y, width, height);
			s.add(boom3,1);
			walls[(x/40)-1][(y/40)].setIcon(null);
			walls[(x/40)-1][(y/40)].setName("walkable");
		}
		
		// Pruefe ein Feld weiter oben
		if (walls[(x/40)][(y/40)+1].getName().equals("walkable")) {
			boom4.setBounds(x, y+40, width, height);
			s.add(boom4,1);
		}
		else if (walls[(x/40)][(y/40)+1].getName().equals("destroyable")) {
			boom4.setBounds(x, y+40, width, height);
			s.add(boom4,1);
			walls[x/40][(y/40)+1].setIcon(null);
			walls[x/40][(y/40)+1].setName("walkable");
		}
		
		// Pruefe ein Feld weiter unten
		if (walls[(x/40)][(y/40)-1].getName().equals("walkable")) {
			boom5.setBounds(x, y-40, width, height);
			s.add(boom5,1);
		}
		else if (walls[(x/40)][(y/40)-1].getName().equals("destroyable")) {
			boom5.setBounds(x, y-40, width, height);
			s.add(boom5,1);
			walls[x/40][(y/40)-1].setIcon(null);
			walls[x/40][(y/40)-1].setName("walkable");
		}
		
		//Pruefe Felder auf Spieler
		//Aktuelles Feld
		if (x == player1.getX() && y == player1.getY()) {
			spielfeld.unbindAllControls();
			JOptionPane.showMessageDialog(null, "Spieler " + player2.getPlayerID() + " hat das Spiel gewonnen", "Spielende", 1);
		} 
		else if (x == player2.getX() && y == player2.getY()) {
			spielfeld.unbindAllControls();
			JOptionPane.showMessageDialog(null, "Spieler " + player1.getPlayerID() + " hat das Spiel gewonnen", "Spielende", 1);
		}
		
		//Feld weiter rechts
		if (x+40 == player1.getX() && y == player1.getY()) {
			spielfeld.unbindAllControls();
			JOptionPane.showMessageDialog(null, "Spieler " + player2.getPlayerID() + " hat das Spiel gewonnen", "Spielende", 1);
		} 
		else if (x+40 == player2.getX() && y == player2.getY()) {
			spielfeld.unbindAllControls();
			JOptionPane.showMessageDialog(null, "Spieler " + player1.getPlayerID() + " hat das Spiel gewonnen", "Spielende", 1);
		}
		
		//Feld weiter links
		if (x-40 == player1.getX() && y == player1.getY()) {
			spielfeld.unbindAllControls();
			JOptionPane.showMessageDialog(null, "Spieler " + player2.getPlayerID() + " hat das Spiel gewonnen", "Spielende", 1);
		} 
		else if (x-40 == player2.getX() && y == player2.getY()) {
			spielfeld.unbindAllControls();
			JOptionPane.showMessageDialog(null, "Spieler " + player1.getPlayerID() + " hat das Spiel gewonnen", "Spielende", 1);
		}
		
		//Feld weiter oben
		if (x == player1.getX() && y+40 == player1.getY()) {
			spielfeld.unbindAllControls();
			JOptionPane.showMessageDialog(null, "Spieler " + player2.getPlayerID() + " hat das Spiel gewonnen", "Spielende", 1);
		} 
		else if (x == player2.getX() && y+40 == player2.getY()) {
			spielfeld.unbindAllControls();
			JOptionPane.showMessageDialog(null, "Spieler " + player1.getPlayerID() + " hat das Spiel gewonnen", "Spielende", 1);
		}
		
		//Feld weiter unten
		if (x == player1.getX() && y-40 == player1.getY()) {
			spielfeld.unbindAllControls();
			JOptionPane.showMessageDialog(null, "Spieler " + player2.getPlayerID() + " hat das Spiel gewonnen", "Spielende", 1);
		} 
		else if (x == player2.getX() && y-40 == player2.getY()) {
			spielfeld.unbindAllControls();
			JOptionPane.showMessageDialog(null, "Spieler " + player1.getPlayerID() + " hat das Spiel gewonnen", "Spielende", 1);
		}			

		// Pruefe auf Bomben
		// Wenn Spieler 1 Bombe legt ...
			if (player1.getPlayerID() == 1) {				
				
				// Nur ausf�hren, wenn nicht durch eine Kettenreaktion ausgeloest und Bombe 2 liegt
				if (kette == false && spielfeld.getBomb2().bombActive() == true) {					
					
					// ... Pruefe auf Bombe 2
					// Aktuelle Position
					if (x == spielfeld.getBomb2().getX() && y == spielfeld.getBomb2().getY()) {
						spielfeld.getBomb2().Kettenreaktion();
					}
					
					// Ein Feld nach Rechts
					if (x+40 == spielfeld.getBomb2().getX() && y == spielfeld.getBomb2().getY()) {
						spielfeld.getBomb2().Kettenreaktion();
					}
					
					// Ein Feld nach Links
					if (x-40 == spielfeld.getBomb2().getX() && y == spielfeld.getBomb2().getY()) {
						spielfeld.getBomb2().Kettenreaktion();
					}
					
					// Ein Feld nach Oben
					if (x == spielfeld.getBomb2().getX() && y+40 == spielfeld.getBomb2().getY()) {
						spielfeld.getBomb2().Kettenreaktion();
					}
					// Ein Feld nach Unten
					if (x == spielfeld.getBomb2().getX() && y-40 == spielfeld.getBomb2().getY()) {
						spielfeld.getBomb2().Kettenreaktion();
					}
			}
		}
			
			// Wenn Spieler 2 Bombe legt ...
			if (player1.getPlayerID() == 2) {				
				
				// Nur ausf�hren, wenn nicht durch eine Kettenreaktion ausgeloest und Bombe 2 liegt
				if (kette == false && spielfeld.getBomb1().bombActive() == true) {
					
					// ... Pruefe auf Bombe 2
					// Aktuelle Position
					if (x == spielfeld.getBomb1().getX() && y == spielfeld.getBomb1().getY()) {
						spielfeld.getBomb1().Kettenreaktion();
					}
					
					// Ein Feld nach Rechts
					if (x+40 == spielfeld.getBomb1().getX() && y == spielfeld.getBomb1().getY()) {
						spielfeld.getBomb1().Kettenreaktion();
					}
					
					// Ein Feld nach Links
					if (x-40 == spielfeld.getBomb1().getX() && y == spielfeld.getBomb1().getY()) {
						spielfeld.getBomb1().Kettenreaktion();
					}
					
					// Ein Feld nach Oben
					if (x == spielfeld.getBomb1().getX() && y+40 == spielfeld.getBomb1().getY()) {
						spielfeld.getBomb1().Kettenreaktion();
					}
					// Ein Feld nach Unten
					if (x == spielfeld.getBomb1().getX() && y-40 == spielfeld.getBomb1().getY()) {
						spielfeld.getBomb1().Kettenreaktion();
					}
			}
		}
	}
}
