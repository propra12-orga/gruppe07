package bomberman;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

/**
 * Erstellt eine T&uuml;re, die als Sieg-Option fungiert.<br>
 * <br>
 * Beim erreichen der T&uuml;re wird eine Siegesmeldung ausgegeben, welcher Spieler gewonnen hat<br>
 * und es gibt die M&ouml;glichkeit das Spiel neu zu starten, oder zu beenden.
 */
public class Tuere extends JFrame {
	private static final long serialVersionUID = -2181712976539020219L;
	private JLabel win1 = new JLabel(new ImageIcon("src/gfx/win3.gif"));
	private JLabel win2 = new JLabel(new ImageIcon("src/gfx/explode.gif"));
	private int rasterPunktX, rasterPunktY;
	private JLayeredPane jPanel;
	private JLabel[] toRemove;

	public Tuere(int rasterPunktX, int rasterPunktY, JLayeredPane jPanel, JLabel[] toRemove) {
		this.jPanel = jPanel;
		this.toRemove = toRemove;
		this.rasterPunktX = rasterPunktX;
		this.rasterPunktY = rasterPunktY;
	}

	
	/**
	 * 
	 * @param b Wenn ein Spieler (b = BomberMan) in der T&uuml;re steht, wird das Spiel beendet.
	 */
	public void isExit(BomberMan b) {
		 if(b.getRasterPunktX() == rasterPunktX && b.getRasterPunktY() == rasterPunktY) {
			 end();
		 }
	 }
	 
	/**
	 * @param jPanel Wenn ein Spieler (b = BomberMan) in der T&uuml; steht, wird das aktuelle<br>
	 * jPanel entfernt und durch einen "win-screen" ersetzt.
	 */
	private void end() {
		for( JLabel r : toRemove ) {
			jPanel.remove(r);
		}
		
		jPanel.add(win1, 12, 0);
		win1.setBounds(1, 1, 840, 600);
		win1.setVisible(true);
		win1.repaint();
		
		jPanel.add(win2, 11, 0);
		win2.setBounds(226, 13, 390, 196);
		win2.setVisible(true);
		win2.repaint();
	}

}
