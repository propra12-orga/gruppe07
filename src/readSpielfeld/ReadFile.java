package readSpielfeld;

import java.io.*;

/**
 * Liest das Spielfeld aus einer Datei aus.
 * 
 */
public class ReadFile {
	private FileReader input;
	private BufferedReader bufRead;
	private char spielFeld[][] = new char[21][15];

	/**
	 *  
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