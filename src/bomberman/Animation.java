package bomberman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * F&uuml;gt die Animation der Spielfigur(en) hinzu.<br>
 * <br>
 * Weist Spieler 1 die Tasten W (hoch), A (links), S (runter) und D (rechts) zu.
 */
public class Animation implements Runnable {
	private BomberMan b;
	private int currentFrame = 0, nextPositionX, nextPositionY;
	private ImageIcon frames[] = new ImageIcon[5];
	private ImageIcon icon = new ImageIcon();
	private String richtung;
	private JLabel walls[][];
	private Spielfeld spielfeld;
	
	/**
	 * &Uuml;bergibt die Parameter "BomberMan", "String" und "Spielfeld" an die Klasse Animation.<br>
	 * <br>
	 * @param b Weist der aktuellen Methode Animation die Werte aus der Klasse BomberMan zu.<br>
	 * @param r &Uuml;bergibt die Richtungsanweisungen an die Klasse Animation.<br>
	 * @param spielfeld Weist der aktuellen Methode Animation die Werte aus der Klasse Spielfeld zu.<br>
	 */
	public Animation(BomberMan b, String r, Spielfeld spielfeld) {
		this.b = b;
		this.richtung = r;
		this.nextPositionX = b.getX();
		this.nextPositionY = b.getY();
		this.walls = spielfeld.getWalls();
		this.spielfeld = spielfeld;
	}	
	
	/**
	 * Image-wechsel beim laufen der Spielfigur.<br>
	 * <br>
	 * @param run Die Bilder der einzelnen Animationszustände werden geladen<br>
	 * (hier im Ordner player1, die Bilder 0-4 f&uuml;r die derzeit ausgew&auml;hlte Richtung nach einem Tastendruck). 
	 */
	@Override
	public void run() {
		for(int i=0; i<=4; i++) {
			ImageIcon img = new ImageIcon("src/gfx/player" + b.getPlayerID() + "/"+ richtung +""+ i +".png");
			frames[i] = img;
		}
		
		Timer timer = new Timer(50, null);
		timer.addActionListener(new Animator(timer));
		timer.start();
	}
	
	/**
	 * Erzeugt die eigentliche Animation.<br>
	 * <br>
	 * Bewegt die Spielfigur bei Tastendruck um 40 Pixel in die jeweilige Richtung.<br>
	 * Hierbei wird alle 8 Pixel eine neue Positionsabfrage erstellt um eine fl&uuml;ssige Bewegung zu erzeugen.
	 */
	class Animator implements ActionListener {
		private Timer t;
		
		public Animator(Timer t) {
			this.t = t;
		}
		
		/**
		 * @param t.stop(); Die Animation wird angehalten, sobald currentFrame == 4 erreicht wurde.
		 * @param spielfeld.setKeysBack(); Tasten werden für erneute Eingabe zur&uuml;ck gesetzt.
		 */
		public void actionPerformed(ActionEvent evt)
		{
			if(currentFrame == 4) {
				t.stop();
				if (b.getPlayerID() == 1) {
					spielfeld.setKeysBackP1();
				} else {
					spielfeld.setKeysBackP2();
				}
			}
			
			if(richtung.equals("right") && (walls[b.getRasterPunktX()+1][b.getRasterPunktY()].getName().equals("walkable") || walls[b.getRasterPunktX()+1][b.getRasterPunktY()].getName().equals("exit"))) {
				nextPositionX = b.getX()+8;
			}
			if(richtung.equals("left") && (walls[b.getRasterPunktX()-1][b.getRasterPunktY()].getName().equals("walkable") || walls[b.getRasterPunktX()-1][b.getRasterPunktY()].getName().equals("exit"))) {
				nextPositionX = b.getX()-8;
			}
			if(richtung.equals("up") && (walls[b.getRasterPunktX()][b.getRasterPunktY()-1].getName().equals("walkable") || walls[b.getRasterPunktX()][b.getRasterPunktY()-1].getName().equals("exit"))) {
				nextPositionY = b.getY()-8;
			}
			
			if(richtung.equals("down") && (walls[b.getRasterPunktX()][b.getRasterPunktY()+1].getName().equals("walkable") || walls[b.getRasterPunktX()][b.getRasterPunktY()+1].getName().equals("exit"))) {
				nextPositionY = b.getY()+8;
			}
			
			currentFrame = (currentFrame + 1) % frames.length;
			icon = frames[currentFrame];
			
			b.getBomberMan().setIcon(icon);			
			b.getBomberMan().setLocation(nextPositionX, nextPositionY);
			b.setX(nextPositionX);
			b.setY(nextPositionY);
			b.getBomberMan().repaint();
			spielfeld.exit.isExit(b);
		}
	}
}
