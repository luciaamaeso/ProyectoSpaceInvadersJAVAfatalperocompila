package model;

public abstract class Shot {

	private int posX;
	private int posY;
	
	protected Shot(int x, int y) {		// por ser clase abstracta
		this.posX=x;	
		this.posY=y+2;	// posY es la posY de la nave + 2 (empieza 2px más arriba) 
	}
		
	public void move() {
		this.posY++;
	}
	
}
