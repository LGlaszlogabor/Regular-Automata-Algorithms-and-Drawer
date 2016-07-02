package core;

import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DvaDisplayer extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DvaDisplayer(String filename,String title){
		try {
			Runtime.getRuntime().exec("\"C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe\" -Tpng "+System.getProperty("user.dir")
			+"\\"+filename+".gv"+" -o "+System.getProperty("user.dir")+"\\"+filename+".png");
		} catch (IOException e) {
			System.out.println("Execution Error!!!");
		}
		try {
			  Thread.sleep(300);
			} catch (InterruptedException ie) {
			}
		setLayout(new FlowLayout());	
		try {
			ImageIcon img = new ImageIcon(ImageIO.read(new File(filename+".png")));
			setBounds(100,100, img.getIconWidth()+100,img.getIconHeight()+100);
			getContentPane().add(new JLabel(img));
		} catch (IOException e) {
			System.out.println("Imagr read error!!!");
		}
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(title);
		setVisible(true);
	}
}
