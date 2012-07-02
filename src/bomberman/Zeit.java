package bomberman;

import javax.swing.JLabel;

/**
 * Eine fortlaufende Zeit, w&auml;hrend das Spiel aktiv ist.<br>
 * <br>
 * In der Mitte des Spiels, &uuml;ber dem eigentlichen Spielfeld, l&auml;uft w&auml;hrend dem Spiele eine Uhr mit zur Zeitmessung.
 */
public class Zeit implements Runnable {
	private JLabel zeit;
	private int current;
	private Spielfeld spielfeld;
	
	/**
	 * &Uuml;bergibt die Parameter Spielfeld und zeit an die Klasse Zeit.<br>
	 * @param spielfeld Weist der aktuellen Methode Zeit die Werte aus der Klasse Spielfeld zu.<br>
	 * @param zeit Ein neues jLabel wird erzeugt.
	 */
	public Zeit(Spielfeld spielfeld,JLabel zeit) {
		this.spielfeld = spielfeld;
		this.zeit = zeit;
	}
	
	@Override
	/**
	 * W&auml;hrend das Spiel aktiv ist, l&auml;uft die Zeit weiter.
	 */
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
	
	/**
	 * Startet man das Spiel neu, wird die Zeit wieder auf 0 zur&uuml;ck gesetzt.s
	 */
	public void resetzeit() {
		current = 0;
	}
}
