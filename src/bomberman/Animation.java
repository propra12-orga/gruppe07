package bomberman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Animation implements Runnable {
	private BomberMan b;
	private int currentFrame = 0, nextPositionX, nextPositionY;
	private ImageIcon frames[] = new ImageIcon[5];
	private ImageIcon icon = new ImageIcon();
	private String richtung;
	private Spielfeld spielfeld;
	
	public Animation(BomberMan b, String r, Spielfeld spielfeld) {
		this.b = b;
		this.richtung = r;
		this.nextPositionX = b.getX();
		this.nextPositionY = b.getY();
		this.spielfeld = spielfeld;
	}	
	
	@Override
	public void run() {
		for(int i=0; i<=4; i++) {
			ImageIcon img = new ImageIcon("src/gfx/player1/"+ richtung +""+ i +".png");
			frames[i] = img;
		}
		
		Timer timer = new Timer(50, null);
		timer.addActionListener(new Animator(timer));
		timer.start();
	}
	
	class Animator implements ActionListener {
		private Timer t;
		
		public Animator(Timer t) {
			this.t = t;
		}

		public synchronized void actionPerformed(ActionEvent evt)
		{
			if(currentFrame == 4) {
				t.stop();
			}
			
			if(richtung.equals("right") && spielfeld.solidWalls[b.getRasterPunktX()+1][b.getRasterPunktY()].getName().equals("walkable")) {
				nextPositionX = b.getX()+8;
			}
			if(richtung.equals("left") && spielfeld.solidWalls[b.getRasterPunktX()-1][b.getRasterPunktY()].getName().equals("walkable")) {
				nextPositionX = b.getX()-8;
			}
			if(richtung.equals("up") && spielfeld.solidWalls[b.getRasterPunktX()][b.getRasterPunktY()-1].getName().equals("walkable")) {
				nextPositionY = b.getY()-8;
			}
			if(richtung.equals("down") && spielfeld.solidWalls[b.getRasterPunktX()][b.getRasterPunktY()+1].getName().equals("walkable")) {
				nextPositionY = b.getY()+8;
			}
			
			currentFrame = (currentFrame + 1) % frames.length;
			icon = frames[currentFrame];
			
			b.getBomberMan().setIcon(icon);			
			b.getBomberMan().setLocation(nextPositionX, b.getY());
			b.setX(nextPositionX);
			b.setY(nextPositionY);
			b.setRasterPunktX(nextPositionX/40);
			b.setRasterPunktY(nextPositionY/40);
			b.getBomberMan().repaint();
		}
	}
}
