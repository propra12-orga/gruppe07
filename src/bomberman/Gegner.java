package bomberman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class Gegner{
	private JLabel gegnerimg;
	private int x, y, width ,height;
	private int rasterPunktX;
	private int rasterPunktY;
	private Spielfeld spielfeld;
	private JLabel walls[][];
	private int nextx,nexty;
	private int zahl;
	private JLayeredPane jPanel;
	private Thread gegnermove;
	
	public Gegner(Spielfeld spielfeld, int x, int y, JLayeredPane jPanel) {
		gegnerimg = new JLabel(new ImageIcon("src/gfx/player3/down0.png"));
		this.spielfeld = spielfeld;
		this.walls = spielfeld.getWalls();
		this.x = x;
		this.y = y;
		width = 40;
		this.jPanel = jPanel;
		
		put(x,y);
		gegnermove = new Thread(new GegnerMove(gegnerimg, spielfeld, x,y));
		gegnermove.start();
		
		
	}
	
	public void put(int x,int y) {
		gegnerimg.setBounds(x, y, width, width);
		jPanel.add(gegnerimg, 1);
	}
}
