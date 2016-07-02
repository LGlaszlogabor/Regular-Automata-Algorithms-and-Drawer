package core;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JPanel;

public class NDVAPanel extends JPanel{
	private static final long serialVersionUID = 1L;

	public NDVAPanel(){
		repaint();
	}
	
	public void paint(Graphics g){
		for(Component comp:getComponents()){
			comp.paint(g);
		}
	}
}
