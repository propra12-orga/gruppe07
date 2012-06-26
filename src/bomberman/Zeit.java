package bomberman;

import javax.swing.JLabel;

public class Zeit implements Runnable {
	private JLabel zeit;
	private int current;
	
	public Zeit(JLabel zeit) {
		this.zeit = zeit;
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				Thread.sleep(1000);
				current++;
				this.zeit.setText("Zeit: "+current+ " Sekunden");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
