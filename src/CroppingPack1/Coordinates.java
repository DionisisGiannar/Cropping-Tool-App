package CroppingPack1;

public class Coordinates {
	private int x=0;
	private int y=0;
	private int width=0;
	private int height=0;
	
	public Coordinates() {
		
	}
	public Coordinates(int x, int y, int w, int h) {
		this.setX(x);
		this.setY(y);
		this.setWidth(w);
		this.setHeight(h);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
