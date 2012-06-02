package readSpielfeld;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Liest das Spielfeld aus einer Datei aus.
 * 
 */
public class ReadFile {
	private Charset encoding = Charset.defaultCharset();
	private InputStream in;
	private Reader reader;
	private File file;
	private char spielFeld[][] = new char[21][15];

	/**
	 *  
	 * @param fileName Der Dateiname mit Pfad.
	 */
	public ReadFile(String fileName) {
		file = new File(fileName);
		try {
			openFile();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}

	/**
	 * &Ouml;ffnet die Datei.
	 * 
	 * @throws FileNotFoundException
	 */
	private void openFile() throws FileNotFoundException {
		this.in = new FileInputStream(this.file);
		this.reader = new InputStreamReader(this.in, this.encoding);
	}

	/**
	 * Kopiert die Datei in ein 2-Dimensinales Array
	 * 
	 * @return spielFeld[][]
	 * @throws IOException
	 */
	public char[][] read() throws IOException {
		int r, i=0, j=0;
		char ch;
		
		while ((r = reader.read()) != -1) {
			ch = (char)r;
			
			// newline
			if(r==13) {
				// es gibt weniger Spalten als ben�tigt!
				if(j<21) {
					System.out.println("Es gibt nur "+ j +" Spalten in Zeile "+ (i+1) +", es m�ssen aber 21 sein.");
					System.exit(0);
				}
				
				// es gibt mehr Zeilen als erlaubt!
				if(i+1>14) {
					System.out.println("Es gibt mehr Zeilen als erlaubt.");
					System.exit(0);
				}
				
				i++;
				j=0;
				continue;
			}
			if(r == 10) {
				continue;
			}
			
			// es gibt mehr Spalten als ben�tigt!
			if(j>20) {
				System.out.println("Es gibt mehr als 21 Spalten in Zeile "+ (i+1) +".");
				System.exit(0);
			}
			
			// ist das Zeichen erlaubt?
			if( ch != 'X' && ch != 'P' && ch != 'O' && ch != 'L' && ch != 'T' && ch != 'Z' ) {
				System.out.println("Das Zeichen "+ ch +" in Spalte "+ (j+1) +" und Zeile "+ (i+1) +" ist verboten.");
				System.exit(0);
			}
			
			spielFeld[j][i] = ch;
			j++;
		}
		
		return this.spielFeld;
	}
}