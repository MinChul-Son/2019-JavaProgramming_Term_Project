import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.net.URL;

import javax.swing.ImageIcon;

public class Moving2 extends ImageIcon {
	public int x;				
	public int y;				
	protected int xDirection;
	protected int yDirection;
	protected int xBoundary;
	protected int yBoundary;
	protected int steps;
	protected int margin;
	protected int width;
	protected int height;
	protected String name;
	

	public Moving2(URL imgURL,String name, int x, int y, int margin, int steps, int xBoundary, int yBoundary,int width, int height) {
		super (imgURL);
		this.name=name;
		this.x = x;
		this.y = y;
		this.margin = margin;
		this.xDirection = 1;
		this.yDirection = 1;
		this.steps = steps;
		this.xBoundary = xBoundary;
		this.yBoundary = yBoundary;
		this.width= width;
		this.height=height;
		
		
		
	}
	public void draw2(Graphics g, ImageObserver io) {
		((Graphics2D)g).drawImage(this.getImage(), x, y, width, height, io);
	}
}