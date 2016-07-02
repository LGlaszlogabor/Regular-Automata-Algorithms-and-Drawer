
package run;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import core.DvaDisplayer;
import core.NDVA;

public class Labor2 {
	private static void readWords(NDVA ndva,String filename){
		try (
			    InputStream fis = new FileInputStream(filename);
			    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
			    BufferedReader br = new BufferedReader(isr);
			) {
				String line;
			    while ((line = br.readLine()) != null) {
			        System.out.println("Word:"+line+"---recognized:"+ndva.recognizeWord(line));
			    }
			}
			catch(IOException ex){
				System.out.println("Word file read error!");
			}
	}
	
	public static void main(String[] args){
		NDVA dva = new NDVA(new File("src\\automata1.txt"));
		String filename = "src\\automata1";
		dva.generateDotCode(filename);
		new DvaDisplayer(filename,"Teljes");
		new File(filename+".gv").delete();
		new File(filename+".png").delete();
		Labor2.readWords(dva,"src\\szavak.txt");
	}	
}
