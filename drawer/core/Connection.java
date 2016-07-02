package core;

import java.awt.Graphics;

import javax.swing.JComponent;

public class Connection extends JComponent{
	private static final long serialVersionUID = 1L;
	private State b;
	private State e;
	private String name;
	private int level;
	public Connection(State from,State to,String name,Integer level){
		this.b = from;
		this.e = to;
		this.name = name;
		this.level = level;
	}
	
	public void paint(Graphics g) {
		if(b == null || e == null) return;
		if(b.getName() == null ) System.out.println("fgdgdfg");
		if(b.getName().equals(e.getName())){
			g.drawString(name, e.getX(), e.getY()-level*40);
			
			g.drawArc(e.getX()-10, e.getY()-level*40, 20,level*60, 0, 180);
		}
		else{
			g.drawString(name, (b.getX()+e.getX())/2, (b.getY()+e.getY())/2);
			g.drawLine(e.getX(), e.getY(), b.getX(), b.getY());
		}
	}
}