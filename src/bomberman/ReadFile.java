package bomberman;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
	private FileReader fr;
    private BufferedReader br;
    private String zeile = "";
    
    public ReadFile(String fileName) {
    	try {
			fr = new FileReader(fileName);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
    	br = new BufferedReader(fr);
    }
    
    public void read() {
	    try {
			while( (zeile = br.readLine()) != null )
			{
			  System.out.println(zeile);
			}
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
		}
	
	    try {
			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
    }
}