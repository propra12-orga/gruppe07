package bomberman;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class Move extends AbstractAction {
	private static final long serialVersionUID = 5040158135264301591L;
	private BomberMan b;
	private String richtung;
	private Spielfeld spielfeld;

	public Move(BomberMan b, String r, Spielfeld spielfeld) {
		this.b = b;
		this.richtung = r;
		this.spielfeld = spielfeld;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Animation a = new Animation(this.b, richtung, this.spielfeld);
		Thread t = new Thread(a);
		t.start();
		while (t.isAlive()) {
			spielfeld.getStartButton().getActionMap().put("moveRight", null);
			spielfeld.getStartButton().getActionMap().put("moveLeft", null);
			spielfeld.getStartButton().getActionMap().put("moveUp", null);
			spielfeld.getStartButton().getActionMap().put("moveDown", null);
		}
	}
}
