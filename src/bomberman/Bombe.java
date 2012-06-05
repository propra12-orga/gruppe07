package bomberman;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class Bombe extends AbstractAction {
	private static final long serialVersionUID = -7038533653091561580L;
	private BomberMan b;
	private JLayeredPane s;
	private JLabel bomb = new JLabel(new ImageIcon("src/gfx/bomb/1.png"));
	private int x=40, y=40, width=40, height=40;
	
	public Bombe(BomberMan b, JLayeredPane s) { 
		this.b = b;
		this.s = s;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public JLabel getBomb() {
		return bomb;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		bomb.setBounds(b.getX(), b.getY(), width, height);
		s.add(bomb, 2);
	}
}
