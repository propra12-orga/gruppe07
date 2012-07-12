package bomberman;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

/**
 * Erzeugt den BomberMan, welcher ben&ouml;tigt wird, damit Spielfiguren auf dem Spielfeld zu sehen sind.<br>
 * <br>
 * Weist jedem Spieler eine Player-ID zu, sodass man an beliebiger Position einen neuen Spieler einf&uuml;gen kann.
 */
public class BomberMan {
	private int playerID = 1;
	private JLabel bomberMan;
	private int x=40, y=40, width=40 ,height=40;
	private int rasterPunktX;
	private int rasterPunktY;
	
	/**
	 * Erzeugt die eigentliche Spielfigur.<br>
	 * <br>
	 * @param id &Uuml;berpr&uuml;ft die Player-ID. Ist diese =2, dann wird dann eine zweite Spielfigur bei x,y eingef&uuml;gt. 
	 */
	public BomberMan(int id) {
		this.playerID = id;
		if( playerID == 2) {
			x=760;
			y=520;
		}
		bomberMan = new JLabel(new ImageIcon("src/gfx/player"+ this.playerID +"/down0.gif"));
		rasterPunktX = x/40;
		rasterPunktY = y/40;
	}
	
	/**
	 * Legt die aktuelle Position der Spielfigur fest und speichert die Werte f&uuml;r sp&auml;teren Gebrauch.<br>
	 * <br>
	 * @param jPanel Die Spielfigur wird auf dem jPanel erzeugt.
	 */
	public void put(JLayeredPane jPanel) {
		bomberMan.setBounds(x, y, width, width);
		jPanel.add(bomberMan, 0);
	}
	
	/**
	 * @return x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Der Spieler kann sich in dem Rahmen von x=40 bis x=760 bewegen. Dabei geht er bei jeder Bewegung 40 Pixel.<br>
	 * <br>
	 * @param x Neue x-Position der Spielfigur, nachdem eine Bewegung durchgef&uuml;hrt wurde.
	 */
	public void setX(int x) {
		if(x <= 760 && x >= 40)
			this.x = x;
		
		if (x % 40 == 0) {
			rasterPunktX = x/40;
		}
	}

	/**
	 * Der Spieler kann sich in dem Rahmen von y=40 bis y=520 bewegen. Dabei geht er bei jeder Bewegung 40 Pixel.<br>
	 * @param y Neue y-Position der Spielfigur, nachdem eine Bewegung durchgef&uuml;hrt wurde.
	 */
	public void setY(int y) {
		if(y <= 520 && y >= 40)
			this.y = y;
		
		if (y % 40 == 0) {
			rasterPunktY = y/40;
		}
	}

	/**
	 * @return y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return bomberMan
	 */
	public JLabel getBomberMan() {
		return bomberMan;
	}

	/**
	 * @return width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return rasterPunktX
	 */
	public int getRasterPunktX() {
		return rasterPunktX;
	}
	
	/**
	 * @return rasterPunktY
	 */
	public int getRasterPunktY() {
		return rasterPunktY;
	}
	
	/**
	 * @return playerID
	 */
	public int getPlayerID() {
		return playerID;
	}
	
	/**
	 * Setzt alle Werte auf 0.
	 */
	public void remove() {
		x = 0;
		y = 0;
		rasterPunktX = 0;
		rasterPunktY = 0;
	}
}
