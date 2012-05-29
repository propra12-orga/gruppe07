package bomberman;

import java.io.*;
import java.nio.charset.Charset;

public class ReadFile {
	private Charset encoding = Charset.defaultCharset();
	private InputStream in;
	private Reader reader;
	private Reader buffer;
	private File file;
	private String zeile = "";

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
		this.buffer = new BufferedReader(this.reader);
	}

	public void read() throws IOException {
		int r;
		while ((r = reader.read()) != -1) {
			char ch = (char)r;
			// TODO ch in array schreiben.
		}
	}
}