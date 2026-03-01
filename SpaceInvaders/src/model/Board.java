package model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class Board extends Observable {
	private static Board myBoard;
	private Square[][] squares;
	private int length = 100;
	private int width = 60;
	private Square playerPosition = new Square(50,55);

	
	private Board() {
		squares = new Square[length][width];
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < width; j++) {
				squares[i][j] = new Square(i,j);
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
		// set aliens
		
		
		// set player
		Player player = new Player(this.playerPosition.getX(), this.playerPosition.getY(), "Blue");
		squares[this.playerPosition.getX()][this.playerPosition.getY()].addSpaceCraft(player);
		
		int[][] matrixToGameScreen = new int[length][width];
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < width; j++) {
				if(squares[i][j].alienSquare()) {
					matrixToGameScreen[i][j] = 1; // El número 1 para Alien
				} else if(squares[i][j].spaceCraftSquare()) {
					matrixToGameScreen[i][j] = 2;  // El 2 para Player/SpaceCraft
				} else {
					matrixToGameScreen[i][j] = 0; // El 0 para casilla de "aire"
				}
			}
		}
		
		setChanged();
		this.notifyObservers(matrixToGameScreen);
	}
	
	public void actBoard() {
		int[][] matrixToGameScreen = new int[length][width];
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < width; j++) {
				if(squares[i][j].alienSquare()) {
					matrixToGameScreen[i][j] = 1; // El número 1 para Alien
				} else if(squares[i][j].spaceCraftSquare()) {
					matrixToGameScreen[i][j] = 2;  // El 2 para Player/SpaceCraft
				} else if(squares[i][j].shotSquare()) {
					matrixToGameScreen[i][j] = 3; // El 3 para casilla de disparo
				} else {
					matrixToGameScreen[i][j] = 0; // El 0 para casilla de aire
				}
			}
		}
		
		setChanged();
		this.notifyObservers(matrixToGameScreen);
	}
	


	

		public void movePlayerLeft() {
			this.playerPosition.move(this.playerPosition.getX() - 1, this.playerPosition.getY());
		}
			
		

		public void movePlayerRight() {
			this.playerPosition.move(this.playerPosition.getX() + 1, this.playerPosition.getY());
		}
			
		
		// todo esto de aquí abajo da error porque he quitado el atributo player
		public void playerShoot() {
			player.shoot();
			setChanged();
			this.notifyObservers(new Object[] {Boolean.TRUE});}
			return shots;
		}	
}
        
	

