package model;

public class Square {

	// Atributos
	private int posX;
	private int posY;
	private SpaceCraft player=null;
	private SpaceCraft alien=null;
	
	// Constructora
	public Square(int x, int y) {
		this.posX=x;	
		this.posY=y;
	}
	
	// Getters
	public int getX() { return this.posX;}
	public int getY() { return this.posY;}
	

	// Añadir o quitar jugador
	public void addPlayer() {
		if (this.player==null) {
			player= new Player(this.posX, this.posY);
		}
	}

	public void deletePlayer() {
		this.player=null;
	}

	// Añadir o quitar alien
	public void addAlien() {
		if (alien==null) {
			this.alien= new Alien(posX, posY);
		}
	}
	
	public void deleteAlien() {
		this.alien=null;
	}

	// Comprobar que contiene la casilla
	public boolean isPlayerInSquare() {
		return this.player!=null;
	}

	public boolean isAlienInSquare() {
		return this.alien!=null;
	}

	// Setter de x e y o moverse
	public void move(int x, int y) {
		this.posX = x;
		this.posY = y;
	}
}
