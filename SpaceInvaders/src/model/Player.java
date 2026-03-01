package model;


public class Player extends SpaceCraft {
	
	
	public Player(int x, int y, String color) {
		super(x,y,color);
	}
	
	protected void moveLeft() {
		this.x = x - 1;
	}
	
	protected void moveRight() {
		this.x = x+1;
	}

	public int getY() {
		return y;
	}
	public int getX() {
		return x;
	}
}
