package core;

import java.awt.Graphics;

public class EndState extends State{
	private static final long serialVersionUID = 1L;

	public EndState(int x,int y,String name){
		super(x,y,name);
	}
	
	public void paint(Graphics g) {
	   super.paint(g);
	   g.drawArc(x-CIRCLE_SIZE/2, y-CIRCLE_SIZE/2-6, CIRCLE_SIZE+4, CIRCLE_SIZE+4, 0, 360);
	}
}
