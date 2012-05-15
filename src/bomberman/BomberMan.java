package bomberman;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BomberMan {
	private JLabel bomberMan = new JLabel(new ImageIcon("src/gfx/player1/6.png"));
	private int x=40, y=40, width=40, height=40;
	private int rasterPunktX;
	private int rasterPunktY;
	
	public BomberMan() {
		bomberMan.setBounds(x, y, width, width);
		rasterPunktX = x/40;
		rasterPunktY = y/40;
		
	}
	
	public void put(JPanel jPanel) {
		jPanel.add(bomberMan);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		if(x <= 760 && x >= 40)
			this.x = x;
	}

	public void setY(int y) {
		if(y <= 520 && y >= 40)
			this.y = y;
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

	public void setRasterPunktX(int x) {
		if(x < 1) {
			rasterPunktX = 1;
		} else if (x > 19) {
			rasterPunktX = 19;
		} else {
			this.rasterPunktX = x;
		}
	}

	public int getRasterPunktY() {
		return rasterPunktY;
	}

	public void setRasterPunktY(int y) {
		if(y < 1) {
			rasterPunktY = 1;
		} else if (y > 13) {
			rasterPunktY = 13;
		} else {
			this.rasterPunktY = y;
		}
	}
}
