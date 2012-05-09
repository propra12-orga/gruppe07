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
	
	public Animation(BomberMan b, String r) {
		this.b = b;
		this.richtung = r;
		this.nextPositionX = b.getX();
		this.nextPositionY = b.getY();
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
			
			if(richtung.equals("right")) {
				nextPositionX = b.getX()+8;
			}
			if(richtung.equals("left")) {
				nextPositionX = b.getX()-8;
			}
			if(richtung.equals("up")) {
				nextPositionY = b.getY()-8;
			}
			if(richtung.equals("down")) {
				nextPositionY = b.getY()+8;
			}
			
			currentFrame = (currentFrame + 1) % frames.length;
			icon = frames[currentFrame];
			
			b.getBomberMan().setIcon(icon);			
			b.getBomberMan().setLocation(nextPositionX, b.getY());
			b.setX(nextPositionX);
			b.setY(nextPositionY);
			b.getBomberMan().repaint();	
		}
	}
}
