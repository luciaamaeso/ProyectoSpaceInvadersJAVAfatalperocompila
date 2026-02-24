package model;

import java.util.Observable;

public class Board extends Observable {
	private static Board myBoard;
	private Object[][] squares;
	private int length = 100;
	private int width = 60;
	
	private Board() {
		squares = new Object[length][width];
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < width; j++) {
				squares[i][j] = new Object();
			}
		}
	}
	
	public static Board getMyBoard() {
		if(myBoard == null) {
			myBoard = new Board();
		}
		
		return myBoard;
	}
	
	public void setBoard() {

		
		
		setChanged();
		this.notifyObservers(new Object[] {Boolean.TRUE}); //Se setea el cambio y seguidamente se notifica a los observers que el board esta listo para updatearse
	
	}
	
}
