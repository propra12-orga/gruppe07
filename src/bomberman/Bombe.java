package bomberman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

public class Bombe extends AbstractAction {
	private static final long serialVersionUID = -7038533653091561580L;
	private BomberMan b;
	private JLayeredPane s;
	private JLabel bomb = new JLabel(new ImageIcon("src/gfx/bomb/1.png"));
	private JLabel boom1 = new JLabel(new ImageIcon("src/gfx/bomb/6.png"));
	private JLabel boom2 = new JLabel(new ImageIcon("src/gfx/bomb/6.png"));
	private JLabel boom3 = new JLabel(new ImageIcon("src/gfx/bomb/6.png"));
	private JLabel boom4 = new JLabel(new ImageIcon("src/gfx/bomb/6.png"));
	private JLabel boom5 = new JLabel(new ImageIcon("src/gfx/bomb/6.png"));
	private int x=40, y=40, width=40, height=40;
	private Spielfeld spielfeld;
	private JLabel walls[][];
	Timer LayTimer = new Timer(3000,new LayBomb());
	Timer ExploTimer = new Timer(3500,new ExploBomb());
	
	public Bombe(BomberMan b, JLayeredPane s, Spielfeld spielfeld) { 
		this.b = b;
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
			x = b.getX();
			y = b.getY();
			bomb.setBounds(x,y, width, height);
			s.add(bomb, 2);
		
			LayTimer.start();
		}
		ExploTimer.start();
	}
	
	class LayBomb implements ActionListener {
		  public void actionPerformed(ActionEvent e) {

		    s.remove(bomb);
		    s.repaint();
		    Explosion();
		    LayTimer.stop();

		  }
	}
	
	class ExploBomb implements ActionListener {
		  public void actionPerformed(ActionEvent e) {
			s.remove(boom1);
			s.remove(boom2);
			s.remove(boom3);
			s.remove(boom4);
			s.remove(boom5);
			s.repaint();
		  }
	}
	
	public void Explosion() {
		boom1.setBounds(x, y, width, height);
		s.add(boom1,1);
		
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
		
	}
}
