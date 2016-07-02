package run;

import java.io.File;

import core.NDVA;
import core.DvaDisplayer;

public class Labor5 {
	public static void main(String[] args){
		NDVA dva = new NDVA(new File("src\\automata1.txt"));
		String filename = "src\\automata1";
		dva.generateDotCode(filename);
		new DvaDisplayer(filename,"Teljes");
		new File(filename+".gv").delete();
		new File(filename+".png").delete();
		
		
		NDVA dva1 = new NDVA(new File("src\\automata1.txt"));
		dva1.eliminateUnreachableStates();
		String filename1 = "src\\lla2";
		dva1.generateDotCode(filename1);
		new DvaDisplayer(filename1,"Elerhetetlenek nelkul");
		new File(filename1+".gv").delete();
		new File(filename1+".png").delete();
		
		NDVA dva2 = new NDVA(new File("src\\automata1.txt"));
		dva2.eliminateUnproductiveStates();
		String filename2 = "src\\lla3";
		dva2.generateDotCode(filename2);
		new DvaDisplayer(filename2,"NemProduktivak nelkul");
		new File(filename2+".gv").delete();
		new File(filename2+".png").delete();
		
		NDVA dva3 = new NDVA(new File("src\\automata1.txt"));
		dva3.eliminateUnreachableStates();
		dva3.eliminateUnproductiveStates();
		String filename3 = "src\\lla4";
		dva3.generateDotCode(filename3);
		new DvaDisplayer(filename3,"Elerhetetlen+NemProduktivak nelkul");
		new File(filename3+".gv").delete();
		new File(filename3+".png").delete();
		
	}
}
