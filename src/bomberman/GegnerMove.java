package bomberman;

import javax.swing.JLabel;

/**
 * Sorgt fuer die Bewegung des Gegners ueber das Spielfeld.
 */
public class GegnerMove implements Runnable {
	private JLabel gegnerimg;
	public int x, y;
	private Spielfeld spielfeld;
	private JLabel walls[][];
	private int nextx,nexty;
	private int zahl;
	private boolean death;
	private Gegner gegner;
	
	public GegnerMove(Gegner gegner,JLabel gegnerimg, Spielfeld spielfeld, int x, int y) {
		this.gegnerimg = gegnerimg;
		this.spielfeld = spielfeld;
		this.walls = spielfeld.getWalls();
		this.x = x;
		this.y = y;
		this.gegner = gegner;
	}

	/**
	 * Wenn der Thread gestartet wurde, wird eine whileschleife in Gang gesetzt, die per Zufall eine<br>
	 * Bewegungsrichtung bestimmt. Dann wird geprueft, ob sich an der Stelle, auf die sich der Gegner<br>
	 * bewegen soll, ein freies Feld befindet. Wenn das der Fall ist, bewegt sich der Gegner auf dieses<br>
	 * Feld, wenn nicht dann bleibt er stehen und wartet auf die naechste Richtungsangabe
	 */
	public void run() {
		while (spielfeld.gamerunning && !death) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				gegnerimg.setIcon(null);
				break;
			}
			zahl = (int) (Math.random()*4);
			if (zahl == 0) {
				nextx = x+40;
				if ((walls[(x/40)+1][(y/40)].getName().equals("walkable") || walls[(x/40)+1][(y/40)].getName().equals("exit"))) {
					while (x < nextx) {
						x++;
						gegnerimg.setLocation(x, y);
						gegner.setXY(x, y);
						gegnerimg.repaint();
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							gegnerimg.setIcon(null);
							break;
						}
					}
				}
			}
			
			if (zahl == 1) {
				nextx = x-40;
				if ((walls[(x/40)-1][(y/40)].getName().equals("walkable") || walls[(x/40)-1][(y/40)].getName().equals("exit"))) {
					while (x > nextx) {
						x--;
						gegnerimg.setLocation(x, y);
						gegner.setXY(x, y);
						gegnerimg.repaint();
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							gegnerimg.setIcon(null);
							break;
						}
					}
				}
			}
			
			if (zahl == 2) {
				nexty = y+40;
				if ((walls[(x/40)][(y/40)+1].getName().equals("walkable") || walls[(x/40)][(y/40)+1].getName().equals("exit"))) {
					while (y < nexty) {
						y++;
						gegnerimg.setLocation(x, y);
						gegner.setXY(x, y);
						gegnerimg.repaint();
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							gegnerimg.setIcon(null);
							break;
						}						
					}
				}
			}
			
			if (zahl == 3) {
				nexty = y-40;
				if ((walls[(x/40)][(y/40)-1].getName().equals("walkable") || walls[(x/40)][(y/40)-1].getName().equals("exit"))) {
					while (y > nexty) {
						y--;
						gegnerimg.setLocation(x, y);
						gegner.setXY(x, y);
						gegnerimg.repaint();
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							gegnerimg.setIcon(null);
							break;
						}
					}
				}
			}
		}		
	}
}
