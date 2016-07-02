package core;

import java.awt.Graphics;

public class BeginState extends State{
	private static final long serialVersionUID = 1L;
	boolean end;
	public BeginState(int x,int y,String name){
		super(x,y,name);
	}
	
	public BeginState(int x,int y,String name,boolean end){
		super(x,y,name);
		this.end = end;
	}
	
	public void paint(Graphics g) {
	   super.paint(g);
	   g.drawLine(x-14, y-5, x-40, y-5);
	   g.drawLine(x-14, y-5, x-22, y-12);
	   g.drawLine(x-14, y-5, x-22, y+2);
	   if (end)g.drawArc(x-CIRCLE_SIZE/2, y-CIRCLE_SIZE/2-6, CIRCLE_SIZE+4, CIRCLE_SIZE+4, 0, 360);	   
	}
}
