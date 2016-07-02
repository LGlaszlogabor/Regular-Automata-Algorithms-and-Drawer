package core;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class State extends JComponent {
	private static final long serialVersionUID = 1L;
	protected static final int CIRCLE_SIZE = 30;

	protected int x,y;
	protected String name;
	
	public State(int x,int y,String name){
		this.x = x;
		this.y = y;
		this.name = name;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.black);
	    g.fillArc(x-CIRCLE_SIZE/2+2, y-CIRCLE_SIZE/2-4, CIRCLE_SIZE, CIRCLE_SIZE, 0, 360);
	    g.setColor(Color.red);
		g.drawString(name, x, y);
		g.setColor(Color.black);
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public String getName(){
		return name;
	}
}
