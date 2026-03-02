package model;

public class Square {
	private int posX;
	private int posY;
	private SpaceCraft player=null;
	
	public Square(int x, int y) {
		this.posX=x;	
		this.posY=y;
	}
	
	public int getX() { return this.posX;}
	public int getY() { return this.posY;}
	public void move(int x, int y) { this.posX = x; this.posY = y;}
	
	public void addPlayer() {
		player= new Player(this.posX, this.posY, "Color");
	}
	
	public void deletePlayer() {
		this.player=null;
	}
	
	public boolean isPlayerInSquare() {
		return this.player==null;
	}
	
	public void playerShoots() {
		this.player.shoot();
	}
	
	public void movePlayerRight() {	
		this.player.moveRight();
	}
	
	public void movePlayerLeft() {	
		this.player.moveLeft();
	}

	
	
}
