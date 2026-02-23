package sprint1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

public abstract class SpaceCraft extends Observable {
	// Atributos
	
	protected ArrayList<Square> space;
	protected String color;
	// private Shot[] bullets;  esto lo he hecho pensando en el futuro

	
	// Constructora
	
	protected SpaceCraft(ArrayList<Square> squares, String color) {
		this.space = squares;
		this.color = color;
		this.addObserver(null);  // Aquí hay que comprobar el nombre
	}
	
	// ha sido golpeada?
	
	public void checkIfHitted(Shot bullet) {
		
		Iterator<Square> squares = this.space.iterator();
		while(squares.hasNext()) {
			Square act = squares.next();
			if(act.getX() == bullet.getPositionX() && act.getY() == bullet.getPositionY()) {  // aquí hay varios
				this.setChanged();
				this.notifyObservers(new Object[]{true});
			} 
		}
		

		
	}
	
	// Moverse
	
	protected void move() {
		
	}
	
	// Disparar
	
	protected void shoot() {
		new Shot(...);  // Aquí supongo que todo el proceso de crear un disparo, a la vez que el notificar a los observers, lo hace la constructora de shot
	}
}
