package model;

public class Square {
	private int posX;
	private int posY;
	private SpaceCraft player=null;
	private SpaceCraft alien=null;
	
	public Square(int x, int y) {
		this.posX=x;	
		this.posY=y;
	}
	
	public int getX() { return this.posX;}
	public int getY() { return this.posY;}
	
	public void addPlayer() {
		if (this.player==null) {
			player= new Player(this.posX, this.posY, "Color");
		}
	}
	
	public void deletePlayer() {
		this.player=null;
	}
	
	public boolean isPlayerInSquare() {
		return this.player!=null;
	}

	public void move(int x, int y) {
		this.posX = x;
		this.posY = y;
	}
	
	public boolean alienSquare() {
		return this.alien != null;
	}
	
	public void movePlayerRight() {	
		this.player.moveRight();
	}
	
	public void movePlayerLeft() {	
		this.player.moveLeft();
	}

	public void addAlien() {
		if (alien==null) {
			this.alien= new Alien(posX, posY);
		}
	}
	
	public void deleteAlien() {
		this.alien=null;
	}
	
	public boolean isAlienInSquare() {
		return this.alien!=null;
	}
}
