import java.net.URL;

public class DiagonallyMoving extends Moving{
	public DiagonallyMoving(URL imgURL,String name, int x, int y, int margin,int size, int steps, int xBoundary, int yBoundary) {
	
		super (imgURL,name, x, y, margin,size, steps, xBoundary, yBoundary);
	}
	
	public DiagonallyMoving(URL imgURL,String name, int margin,int size, int steps, int xBoundary, int yBoundary) {
		super (imgURL,name, margin, size, steps, xBoundary, yBoundary);
	}

	public void move() {
		if (xDirection > 0 && x >= xBoundary) {
			xDirection = -1;
		}
		if (xDirection < 0 && x <= 0) {
			xDirection = 1;
		}
		x += (xDirection * steps);

		if (yDirection > 0 && y >= yBoundary) {
			yDirection = -1;
		}
		if (yDirection < 0 && y <= 0) {
			yDirection = 1;
		}
		y += (yDirection * steps);
	}
}
