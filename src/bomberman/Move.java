package bomberman;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class Move extends AbstractAction {
	private static final long serialVersionUID = 5040158135264301591L;
	private BomberMan b;
	private String richtung;

	public Move(BomberMan b, String r) {
		this.b = b;
		this.richtung = r;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Animation a = new Animation(this.b, richtung);
		Thread t = new Thread(a);
		t.start();
	}
}
