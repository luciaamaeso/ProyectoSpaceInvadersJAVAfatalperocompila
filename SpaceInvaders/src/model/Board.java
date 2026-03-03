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
		//inicialización de la matriz
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < width; j++) {
				squares[i][j] = new Square(i,j);
				if(AlienManager.getAlienManager().isAnAlienThere(i, j)) {
					squares[i][j].addAlien();									// aquí es el set de los aliens
				} else if(i == playerPosition.getX() && j == playerPosition.getY()) {
					this.playerPosition.addPlayer();
					squares[i][j] = playerPosition;								// aquí es el set del player
				}
			}
		}
		
		
		int[][] matrixToGameScreen = new int[width][length];
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < width; j++) {
				if(squares[i][j].alienSquare()) {
					matrixToGameScreen[j][i] = 1; // El número 1 para Alien
				} else if(squares[i][j].isPlayerInSquare()) {
					matrixToGameScreen[j][i] = 2;  // El 2 para Player/SpaceCraft
				} else {
					matrixToGameScreen[j][i] = 0; // El 0 para casilla de "aire"
				}
			}
		}
		
		setChanged();
		this.notifyObservers(matrixToGameScreen);
	
	}
	
	
	
	public void actBoard() {
		if(this.playerPosition != null) {
			int[][] matrixToGameScreen = new int[width][length];
			for(int i = 0; i < length; i++) {
				for(int j = 0; j < width; j++) {
					if(AlienManager.getAlienManager().isAnAlienThere(i, j)) {
						matrixToGameScreen[j][i] = 1; // El número 1 para Alien
					} else {
						matrixToGameScreen[j][i] = 0; // El 0 para casilla de aire
					}
				}
			}
			matrixToGameScreen[this.playerPosition.getY()][this.playerPosition.getX()] = 2; // el 2 es para el jugador
			setChanged();
	        this.notifyObservers(matrixToGameScreen);
		} else {
			// aquí se habría perdido el juego
			setChanged();
			this.notifyObservers("Se ha perdido el juego");
		}
		}
	
	public void movePlayerUp() {
		boolean out=isOutOfRange(this.playerPosition.getX(), this.playerPosition.getY()-1);
		if (!out) {
			int x = this.playerPosition.getX();
			int y =  this.playerPosition.getY()-1;
			if(!AlienManager.getAlienManager().isAnAlienThere(x, y)) {
				this.playerPosition.move(x,y);
				squares[x][y] = this.playerPosition;
				squares[x][y+1].deletePlayer();}
		}
		actBoard();
	}
	
	public void movePlayerLeft() {
		boolean out=isOutOfRange(this.playerPosition.getX()-1, this.playerPosition.getY());
		if (!out) {
			int x = this.playerPosition.getX() - 1;
			int y =  this.playerPosition.getY();
			if(!AlienManager.getAlienManager().isAnAlienThere(x, y)) {
				this.playerPosition.move(x,y);
				squares[x][y] = this.playerPosition;
				squares[x+1][y].deletePlayer();}
		}
		actBoard();
	}
	
	public void movePlayerRight() {
		boolean out=isOutOfRange(this.playerPosition.getX()+1, this.playerPosition.getY());
		if (!out) {
			int x = this.playerPosition.getX() + 1;
			int y =  this.playerPosition.getY();
			if(!AlienManager.getAlienManager().isAnAlienThere(x, y)) {
				this.playerPosition.move(x,y);
				squares[x][y] = this.playerPosition;
				squares[x-1][y].deletePlayer();}
		}
		actBoard();
	}
	
	public void movePlayerDown() {
		boolean out=isOutOfRange(this.playerPosition.getX(), this.playerPosition.getY() + 1);
		if (!out) {
			int x = this.playerPosition.getX();
			int y =  this.playerPosition.getY() + 1;
			if(!AlienManager.getAlienManager().isAnAlienThere(x, y)) {
				this.playerPosition.move(x,y);
				squares[x][y] = this.playerPosition;
				squares[x][y-1].deletePlayer();}
		} 
		actBoard();
	}
	
	
	private boolean isOutOfRange(int x, int y) {
		boolean out=false;
		if (y < 0 || y >= width || x < 0 || x >= length ) {
			out=true;
		}
		return out;
		
	}
		
		public void playerShoot() {
			this.playerPosition.playerShoots();
		}	

}
        
	

