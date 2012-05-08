package bomberman;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class MoveRight extends AbstractAction {
	private static final long serialVersionUID = 5040158135264301591L;
	BomberMan b;	

	public MoveRight(BomberMan b) {
		this.b = b;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		b.getBomberMan().setBounds(b.getX()+40, b.getY(), b.getWidth(), b.getHeight());
		b.setX(b.getX()+40);
	}

}
