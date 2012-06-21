package bomberman;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class Move extends AbstractAction {
	private static final long serialVersionUID = 5040158135264301591L;
	private BomberMan b;
	private String richtung;
	private Spielfeld spielfeld;
	private boolean offline;

	public Move(BomberMan b, String r, Spielfeld spielfeld, boolean offline) {
		this.b = b;
		this.richtung = r;
		this.spielfeld = spielfeld;
		this.offline = offline;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Animation a = new Animation(this.b, richtung, this.spielfeld,offline);
		Thread t = new Thread(a);
		t.start();
		
		// Fuer offlinemodus
		if (b.getPlayerID() == 1 && !spielfeld.isServerActive() && !spielfeld.isClientActive()) {
		spielfeld.removeKeysP1();
		} else if (b.getPlayerID() == 2 && !spielfeld.isServerActive() && !spielfeld.isClientActive()) {
		spielfeld.removeKeysP2();
		}
		
		// Fuer Server
		if (spielfeld.isServerActive()) {
		spielfeld.removeKeysP1();
		}
		
		// Fuer Client
		if (spielfeld.isClientActive()) {
		spielfeld.removeKeysP2();
		}
	}
}
