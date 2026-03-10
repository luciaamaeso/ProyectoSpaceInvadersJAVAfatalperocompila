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
	
	protected void moveUp() {
		this.y = y + 1;
	}
	
	protected void moveDown() {
		if (y+1 <= 60) {
			y++;
		}
	}
	
	protected int getX() {
        return x;
    }

    protected int getY() {
        return y;
    }

	
}
