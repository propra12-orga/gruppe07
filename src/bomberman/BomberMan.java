package bomberman;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class BomberMan {
	private int playerID = 1;
	private JLabel bomberMan;
	private int x=40, y=40, width=40 ,height=40;
	private int rasterPunktX;
	private int rasterPunktY;
	
	public BomberMan(int id) {
		this.playerID = id;
		if( playerID == 2) {
			x=760;
			y=520;
		}
		bomberMan = new JLabel(new ImageIcon("src/gfx/player"+ this.playerID +"/down0.png"));
		rasterPunktX = x/40;
		rasterPunktY = y/40;
	}
	
	public void put(JLayeredPane jPanel) {
		bomberMan.setBounds(x, y, width, width);
		jPanel.add(bomberMan, 0);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		if(x <= 760 && x >= 40)
			this.x = x;
		
		if (x % 40 == 0) {
			rasterPunktX = x/40;
		}
	}

	public void setY(int y) {
		if(y <= 520 && y >= 40)
			this.y = y;
		
		if (y % 40 == 0) {
			rasterPunktY = y/40;
		}
	}

	public int getY() {
		return y;
	}

	public JLabel getBomberMan() {
		return bomberMan;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getRasterPunktX() {
		return rasterPunktX;
	}
	
	public int getRasterPunktY() {
		return rasterPunktY;
	}
	
	public int getPlayerID() {
		return playerID;
	}
	
	public void remove() {
		x = 0;
		y = 0;
		rasterPunktX = 0;
		rasterPunktY = 0;
	}
}
