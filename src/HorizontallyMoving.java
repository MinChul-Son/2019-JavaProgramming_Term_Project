import java.net.URL;



public class HorizontallyMoving extends Moving{
	public HorizontallyMoving(URL imgURL,String name, int x, int y, int margin,int size, int steps, int xBoundary, int yBoundary) {
		
		super (imgURL,name, x, y, margin, size, steps, xBoundary, yBoundary);
	}
	
	public HorizontallyMoving(URL imgURL,String name,int margin,int size, int steps, int xBoundary, int yBoundary) {
		super (imgURL,name, margin,size, steps, xBoundary, yBoundary);
	}

	public void move() {
		if (xDirection > 0 && x >= xBoundary) {
			xDirection = -1;
			y += (yDirection * steps * 5);
		}
		if (xDirection < 0 && x <= 0) {
			xDirection = 1;
			y += (yDirection * steps * 5);
		}
		x += (xDirection * steps);

		if (yDirection > 0 && y >= yBoundary) {
			yDirection = -1;
		}
		if (yDirection < 0 && y <= 0) {
			yDirection = 1;
		}
	}
}
