package bomberman;

import javax.swing.JLabel;

public class Zeit implements Runnable {
	private JLabel zeit;
	private int current;
	private Spielfeld spielfeld;
	
	public Zeit(Spielfeld spielfeld,JLabel zeit) {
		this.spielfeld = spielfeld;
		this.zeit = zeit;
	}
	
	@Override
	public void run() {
		try {
			while(spielfeld.gamerunning) {
				Thread.sleep(1000);
				current++;
				this.zeit.setText("Zeit: "+current+ " Sekunden");
			}
		} catch (InterruptedException e) {
			current = 0;
		}
	}
	
	public void resetzeit() {
		current = 0;
	}
}
