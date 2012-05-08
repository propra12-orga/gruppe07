package bomberman;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BomberMan {
	private JLabel bomberMan = new JLabel(new ImageIcon("src/gfx/explodable_wall.png"));
	private int x=40, y=40, width=40, height=40;
	
	public BomberMan() {
		bomberMan.setBounds(x, y, width, width);
	}
	
	public void put(JPanel jPanel) {
		jPanel.add(bomberMan);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
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
}
