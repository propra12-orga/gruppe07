package bomberman;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

/**
 * Erstellt eine T&uuml;re, die als Sieg-Option fungiert.<br>
 * <br>
 * Beim erreichen der T&uuml;re wird eine Siegesmeldung ausgegeben, welcher
 * Spieler gewonnen hat<br>
 * und es gibt die M&ouml;glichkeit das Spiel neu zu starten, oder zu beenden.
 */
public class Tuere extends JFrame {
	private static final long serialVersionUID = -2181712976539020219L;
	private JLabel win1 = new JLabel(new ImageIcon("src/gfx/win.gif"));
	private int rasterPunktX, rasterPunktY;
	private JLayeredPane jPanel;
	private JLabel[] toRemove;

	/**
	 * Übergibt die Parameter rasterPunktX, rasterPunktY, jPanel und toRemove
	 * an die Klasse Tuere.<br>
	 * <br>
	 * 
	 * @param rasterPunktX
	 *            Raster-Punkt x auf dem Spielfeld. In diesem Raster bewegt sich
	 *            die Spielfigur.
	 * @param rasterPunktY
	 *            Raster-Punkt y auf dem Spielfeld. In diesem Raster bewegt sich
	 *            die Spielfigur.
	 * @param jPanel
	 *            Die T&uuml;re kann auf das bereits vorhandene jPanel erzeugt
	 *            werden.
	 * @param toRemove
	 *            Bei einem Sieg, l&auml;sst sich die T&uuml;re entfernen.
	 */
	public Tuere(int rasterPunktX, int rasterPunktY, JLayeredPane jPanel,
			JLabel[] toRemove) {
		this.jPanel = jPanel;
		this.toRemove = toRemove;
		this.rasterPunktX = rasterPunktX;
		this.rasterPunktY = rasterPunktY;
	}

	/**
	 * Sieg-Kriterien
	 * 
	 * @param b
	 *            Wenn ein Spieler (b = BomberMan) in der T&uuml;re steht, wird
	 *            das Spiel beendet.
	 */
	public void isExit(BomberMan b) {
		if (b.getRasterPunktX() == rasterPunktX
				&& b.getRasterPunktY() == rasterPunktY) {
			end();
		}
	}

	/**
	 * @param jPanel
	 *            Wenn ein Spieler (b = BomberMan) in der T&uuml; steht, wird
	 *            das aktuelle<br>
	 *            jPanel entfernt und durch einen "win-screen" ersetzt.
	 */
	private void end() {
		for (JLabel r : toRemove) {
			jPanel.remove(r);
		}

		jPanel.add(win1, 12, 0);
		win1.setBounds(1, 1, 840, 600);
		win1.setVisible(true);
		win1.repaint();
	}

	/**
	 * @return win1
	 */
	public JLabel getWin1() {
		return win1;
	}
}
