import java.awt.Point;
import javax.swing.ImageIcon;
import java.net.*;
import java.awt.image.*;
import java.awt.*;

public class Moving extends ImageIcon {
	public int x;				
	public int y;		
	protected int xDirection;
	protected int yDirection;
	protected int xBoundary;
	protected int yBoundary;
	protected int steps;
	protected int margin;
	protected int size;	
	protected String name;
	

	public Moving(URL imgURL,String name, int x, int y, int margin,int size, int steps, int xBoundary, int yBoundary) {
		super (imgURL);
		this.name=name;
		this.x = x;
		this.y = y;
		this.margin = margin;
		this.size=size;
		this.xDirection = 1;
		this.yDirection = 1;
		this.steps = steps;
		this.xBoundary = xBoundary;
		this.yBoundary = yBoundary;
	}
	
	
	public Moving(URL imgURL,String name, int margin,int size, int steps, int xBoundary, int yBoundary) {
		this (imgURL,name, 0, 0, margin,size, steps, xBoundary, yBoundary);
		x= (int) (Math.random() * xBoundary);
		y= (int) (Math.random() * yBoundary);
	}
	
	
	
	
	
	
	
	public void draw(Graphics g, ImageObserver io) {
		((Graphics2D)g).drawImage(this.getImage(), x, y, size, size, io);
	}
	public boolean collide (Point p2) {
		Point p = new Point(this.x, this.y);
		if (p.distance(p2) <= margin) return true;
		return false;
	}
	public void move() {};
	
}
