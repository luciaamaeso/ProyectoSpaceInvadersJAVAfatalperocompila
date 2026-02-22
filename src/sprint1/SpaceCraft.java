package sprint1;

import java.util.ArrayList;
import java.util.Iterator;

public class SpaceCraft {
	// Atributos
	
	private ArrayList<Square> space;
	private Shot[] bullets;

	
	// Constructora
	
	public SpaceCraft() {
	
	}
	
	// ha sido golpeada?
	
	public boolean checkIfHitted(Shot bullet) {
		Iterator<Square> squares = this.space.iterator();
		while(squares.hasNext()) {
			Square act = squares.next();
			if(act.getX() == bullet.getPositionX() && act.getY() == bullet.getPositionY()) {  // aquí hay varios
				return true;
			} 
		}
		return false;
	}
	
	// Moverse
	
	// Disparar
	
	public void disparar() {
		new Shot(...);
	}
}
