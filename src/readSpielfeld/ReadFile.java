package readSpielfeld;

import java.io.*;

/**
 * Liest das Spielfeld aus einer Datei aus.<br>
 * <br>
 * Zur Zeit handelt es sich dabei um 5 verschiedene Level, oder die M&ouml;glichkeit das Spiel mit einem zuf&auml;llig<br>
 * generierten Level zu starten.
 */
public class ReadFile {
	private FileReader input;
	private BufferedReader bufRead;
	private char spielFeld[][] = new char[21][15];

	/**
	 * Liest Das Level aus einer Textdatei mit dem Namen "levelx.txt" ein (x steht hierbei für eine Zahl zwischen 1 und 5)
	 * @param fileName Der Dateiname mit Pfad.
	 * @throws FileNotFoundException 
	 */
	public ReadFile(String fileName) throws FileNotFoundException {
		input = new FileReader(fileName);
		bufRead = new BufferedReader(input);
	}

	/**
	 * Kopiert die Datei in ein 2-Dimensinales Array
	 * 
	 * @return spielFeld[][]
	 * @throws IOException
	 */
	public char[][] read() throws IOException {
		int count=0;
		String line;
		        
		while ((line = bufRead.readLine()) != null){
			for(int i=0; i < spielFeld.length; i++) {
				spielFeld[i][count] = line.charAt(i);
			}
			count++;
		}
		return this.spielFeld;
	}
}