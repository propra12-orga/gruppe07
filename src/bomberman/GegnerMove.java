package bomberman;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class GegnerMove implements Runnable {
	private JLabel gegnerimg;
	private int x, y;
	private Spielfeld spielfeld;
	private JLabel walls[][];
	private int nextx,nexty;
	private int zahl;
	private boolean death;
	private Bombe bombe1;
	private Bombe bombe2;
	
	public GegnerMove(JLabel gegnerimg, Spielfeld spielfeld, int x, int y) {
		this.gegnerimg = gegnerimg;
		this.spielfeld = spielfeld;
		this.walls = spielfeld.getWalls();
		this.x = x;
		this.y = y;
		bombe1 = spielfeld.getBomb1();
		bombe2 = spielfeld.getBomb2();
	}

	@Override
	public void run() {
		while (spielfeld.gamerunning && !death) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			zahl = (int) (Math.random()*4);
			if (zahl == 0) {
				nextx = x+40;
				if ((walls[(x/40)+1][(y/40)].getName().equals("walkable") || walls[(x/40)+1][(y/40)].getName().equals("exit"))) {
					while (x < nextx) {
						x++;
						gegnerimg.setLocation(x, y);
						gegnerimg.repaint();
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
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
						gegnerimg.repaint();
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
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
						gegnerimg.repaint();
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
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
						gegnerimg.repaint();
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}		
	}
}
