package test;

import java.io.File;

import NDVA.NDVA;
import api.NDVADrawer;

public class NDVADrawerTest {
	public static void main(String args[]){
		new NDVADrawer(new NDVA(new File("src\\automata1.txt"))).draw();
	}
}
