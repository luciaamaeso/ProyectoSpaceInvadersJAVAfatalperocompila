package model;
import java.util.ArrayList;

public class Square {
	private int posX;
	private int posY;
	private Alien alien=null;
	private Shot shot=null;
	private SpaceCraft spaceCraft=null;
	
	
	public Square(int x, int y) {
		this.posX=x;	
		this.posY=y;
	}
	
	public void addShot(Shot shotType) {
		if (shot!=null) {
			shot=shotType;	// Ahora solo pixel, en un futuro otros tipos
		}
	}
	
	public void deleteShot() {
		shot=null;
	}
	
	
	public void addAlien() {
		if (alien!=null) {
			alien= new Alien(posX, posY);
		}
	}
	
	public void deleteAlien() {
		if (shot!=null) {		// Si hay un disparo en la casilla, se elimina al alien
			alien=null;
		}
	}
	
	public void addSpaceCraft(SpaceCraft sc) {
		if (spaceCraft!=null) {
			this.spaceCraft=sc;
		}
	}
	
	public void deleteSpaceCraft() {
		spaceCraft=null;
	}
	
	public int getX() { return this.posX;}
	public int getY() { return this.posY;}
	
	public void move(int x, int y) { this.posX = x; this.posY = y; }
	
	public boolean alienSquare() {
		if(this.alien != null) { 
			return true;
		} 
		return false;
	}
	
	public boolean shotSquare() {
		if(this.shot != null) { 
			return true;
		} 
		return false;
	}
	
	public boolean spaceCraftSquare() {
		if(this.spaceCraft != null) { 
			return true;
		} 
		return false;
	}
	
}
