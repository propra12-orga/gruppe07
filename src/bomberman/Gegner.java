package bomberman;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

/**
 * Erstellt eine neue Spielfigur Gegner.
 */
public class Gegner{
	private JLabel gegnerimg;
	private int x, y, width;
	private Spielfeld spielfeld;
	private JLayeredPane jPanel;
	private Thread gegnermove;
	private Bombe bombe1;
	private Bombe bombe2;
	private BomberMan player1;
	private BomberMan player2;
	
	public Gegner(Spielfeld spielfeld, int x, int y, JLayeredPane jPanel) {
		gegnerimg = new JLabel(new ImageIcon("src/gfx/player3/down0.png"));
		this.spielfeld = spielfeld;
		this.x = x;
		this.y = y;
		width = 40;
		this.jPanel = jPanel;
		bombe1 = spielfeld.getBomb1();
		bombe2 = spielfeld.getBomb2();
		player1 = spielfeld.getPlayer1();
		player2 = spielfeld.getPlayer2();
		
		put(x,y);
		gegnermove = new Thread(new GegnerMove(this, gegnerimg, spielfeld, x,y));
		gegnermove.start();
		new Thread (new GegnerInteraktion()).start();
	}
	
	/**
	 * Gegner wird auf dem Spielfeld platziert und dem jPanel hinzugefuegt.
	 */
	public void put(int x,int y) {
		gegnerimg.setBounds(x, y, width, width);
		jPanel.add(gegnerimg, 1);
	}
	
	/**
	 * Aktualisiert die Position x und y des Gegners
	 */
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}
	/**
	 * Prueft, ob sich ein Gegner auf einem Explosionsfeld befindet. Wenn das der Fall ist,<br>
	 * wird der GegnerMove-Thread unterbrochen, wodurch das Icon gegnerimg auf Null gesetzt wird<br>
	 * und die whileschleife unterbrochen wird.<br>
	 * Wenn ein Gegner sich auf dem selben Feld wie ein Spieler befindet, erhaelt der Spieler eine<br>
	 * Nachricht und seine Keybindings werden entfernt. Ausserdem wird die whileschleife unterbrochen.
	 */
	class GegnerInteraktion implements Runnable {
		
		public void run() {
			while (spielfeld.gamerunning) {
				if (bombe1.explosion &&
						((bombe1.boom1.getLocation().x == x && bombe1.boom1.getLocation().y == y) ||
						(bombe1.boom1.getLocation().x +40 == x && bombe1.boom1.getLocation().y == y) ||
						(bombe1.boom1.getLocation().x -40 == x && bombe1.boom1.getLocation().y == y) ||
						(bombe1.boom1.getLocation().x == x && bombe1.boom1.getLocation().y +40 == y) ||
						(bombe1.boom1.getLocation().x == x && bombe1.boom1.getLocation().y -40 == y))) {
					gegnermove.interrupt();
					break;
				}
				
				if (bombe2.explosion &&
						((bombe2.boom1.getLocation().x == x && bombe2.boom1.getLocation().y == y) ||
						(bombe2.boom1.getLocation().x +40 == x && bombe2.boom1.getLocation().y == y) ||
						(bombe2.boom1.getLocation().x -40 == x && bombe2.boom1.getLocation().y == y) ||
						(bombe2.boom1.getLocation().x == x && bombe2.boom1.getLocation().y +40 == y) ||
						(bombe2.boom1.getLocation().x == x && bombe2.boom1.getLocation().y -40 == y))) {
					gegnermove.interrupt();
					break;
				}
				
				if (player1.getX() == x && player1.getY() == y) {
					spielfeld.unbindAllControls();
					JOptionPane.showMessageDialog(null,
							"Spieler 1 ist tot", "Spielende", 1);
					spielfeld.gamerunning = false;
					break;
				}
				
				if (player2.getX() == x && player2.getY() == y) {
					spielfeld.unbindAllControls();
					JOptionPane.showMessageDialog(null,
							"Spieler 2 ist tot", "Spielende", 1);
					spielfeld.gamerunning = false;
					break;
				}
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
