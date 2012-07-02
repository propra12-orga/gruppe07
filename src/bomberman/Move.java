package bomberman;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * Erzeugt die Bewegungen der Spielfiguren.<br>
 * <br>
 * Spielfiguren lassen sich bewegen und die Aktionen werden zwischen Host und Client ausgetauscht,<br>
 * sodass ein Spiel im Netzwerk m&ouml;glich ist.
 */
public class Move extends AbstractAction {
	private static final long serialVersionUID = 5040158135264301591L;
	private BomberMan b;
	private String richtung;
	private Spielfeld spielfeld;
	private boolean offline;

	/**
	 * &Uuml;bergibt die Parameter "BomberMan", "String" und "Spielfeld" an die Klasse Move.<br>
	 * <br>
	 * @param b Weist der aktuellen Methode Move die Werte aus der Klasse BomberMan zu.<br>
	 * @param r &Uuml;bergibt die Richtungsanweisungen an die Klasse Animation.<br>
	 * @param spielfeld Weist der aktuellen Methode Move die Werte aus der Klasse Spielfeld zu.<br>
	 * @param offline Wenn der Host offline ist, ist keine Bewegung mehr m&ouml;glich.
	 */
	public Move(BomberMan b, String r, Spielfeld spielfeld, boolean offline) {
		this.b = b;
		this.richtung = r;
		this.spielfeld = spielfeld;
		this.offline = offline;
	}
	
	/**
	 * &uuml;berpr&uuml;ft, ob eine Taste gedr&uuml;ckt wurde und f&uumlhrt dann eine Bewegung in die entsprechende Richtung aus.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Animation a = new Animation(this.b, richtung, this.spielfeld,offline);
		Thread t = new Thread(a);
		t.start();
		
		// Fuer offlinemodus
		if (b.getPlayerID() == 1 && !spielfeld.servermode && !spielfeld.clientmode) {
		spielfeld.removeKeysP1();
		} else if (b.getPlayerID() == 2 && !spielfeld.servermode && !spielfeld.clientmode) {
		spielfeld.removeKeysP2();
		}
		
		// Fuer Server
		if (spielfeld.servermode && b.getPlayerID() == 1) {
		spielfeld.removeKeysP1();
		}
		
		// Fuer Client
		if (spielfeld.clientmode && b.getPlayerID() == 2) {
		spielfeld.removeKeysP2();
		}
	}
}
