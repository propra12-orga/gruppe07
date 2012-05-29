package bomberman;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Tuere extends JFrame {
	private static final long serialVersionUID = -2181712976539020219L;
	private JPanel jPanel;
	private BomberMan b;
	private JLabel win1 = new JLabel(new ImageIcon("src/gfx/win3.gif"));
	private JLabel win2 = new JLabel(new ImageIcon("src/gfx/explode.gif"));
	private JLabel exit = new JLabel(new ImageIcon("src/gfx/door/door.png"));
	private int x = 400, y = 280;
	private Spielfeld s;
	private Bombe bo;

	public Tuere(JPanel jPanel, BomberMan b, Spielfeld s, Bombe bo) {
		this.jPanel = jPanel;
		this.b = b;
		this.s = s;
		this.bo = bo;
		exit.setBounds(x, y, 40, 40);
		exit.setVisible(true);
		jPanel.add(exit);
		
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	private boolean isExit() {
		if(b.getX() == x && b.getY() == y) {
			return true;
		}
		return false;
	}
	
	public void end() {
		if(isExit()) {
			JLabel w[][] = s.getSolidWalls(); 
			for (int i = 0; i < 22; i++) {
				for (int j = 0; j < 15; j++) {
					jPanel.remove(w[i][j]);
				}
			}
			
			jPanel.remove(exit);
			jPanel.remove(b.getBomberMan());
			jPanel.remove(bo.getBomb());
			
			jPanel.add(win1);
			win1.setBounds(1,1,840,600);
			win1.setVisible(true);
			jPanel.add(win2);
			win2.setBounds(225, 10, 390, 196);
			win2.setVisible(true);
			
		}
	}

}
