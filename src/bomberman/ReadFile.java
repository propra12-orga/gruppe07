package bomberman;

import java.io.*;
import java.nio.charset.Charset;

public class ReadFile {
	private Charset encoding = Charset.defaultCharset();
	private InputStream in;
	private Reader reader;
	private File file;
	private char spielFeld[][] = new char[21][15];

	public ReadFile(String fileName) {
		file = new File(fileName);
		try {
			openFile();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	private void openFile() throws FileNotFoundException {
		this.in = new FileInputStream(this.file);
		this.reader = new InputStreamReader(this.in, this.encoding);
	}

	public void read() throws IOException {
		int r, i=0, j=0;
		while ((r = reader.read()) != -1) {
			// newline
			if(r==13) {
				i++;
				j=0;
				continue;
			}
			if(r == 10) {
				System.out.println();
				continue;
			}
			System.out.print((char)r);
			spielFeld[j][i] = (char)r;
			j++;
		}
	}
}