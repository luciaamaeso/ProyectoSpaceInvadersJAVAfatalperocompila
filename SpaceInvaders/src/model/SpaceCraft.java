package model;

public abstract class SpaceCraft {
	// Atributos
	protected int x;
	protected int y;
	// private Shot[] bullets;  esto lo he hecho pensando en el futuro

	// Constructora
	
	protected SpaceCraft(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	// Moverse
	
	protected void moveLeft() {
		this.x = x - 1;
	}
	
	protected void moveRight() {
		this.x = x+1;
	}
	
	public void moveUp() {
		this.y = y + 1;
	}
	
	public void moveDown() {
		this.y = y + 1;
	}

	
}
