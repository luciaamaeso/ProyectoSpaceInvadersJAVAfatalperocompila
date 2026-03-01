package model;
import java.util.ArrayList;

public class Square {
	int posX;
	int posY;
	Alien alien=null;
	Shot shot=null;
	SpaceCraft spaceCraft=null;
	
	
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
	
}
