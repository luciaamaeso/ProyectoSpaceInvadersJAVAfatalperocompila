package model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class Board extends Observable {
	private static Board myBoard;
	private Object[][] squares;
	private int length = 100;
	private int width = 60;
	private Player player;
	private ArrayList<Alien> aliens = new ArrayList();
	private ArrayList<Shot> shots = new ArrayList();
	
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
		aliens.clear();//Limpiamos los array tanto de los tiros como de los aliens para empezar.
		shots.clear();
		player = new Player(50, 55, "green"); //Definimos al jugador en la posicion(50,55)
		Random rnd = new Random();

		//aqui faltan mas cosas, asi solo se añaden los aliens.
		setChanged();
		this.notifyObservers(new Object[] {Boolean.TRUE});} //Se setea el cambio y seguidamente se notifica a los observers que el board esta listo para updatearse
	


		public void movePlayerLeft() {
			player.moveLeft();
			setChanged();
			this.notifyObservers(new Object[] {Boolean.TRUE});}
			
		

		public void movePlayerRight() {
			player.moveRight();
			setChanged();
			this.notifyObservers(new Object[] {Boolean.TRUE});}
			
		

		public void playerShoot() {
			player.shoot();
			setChanged();
			this.notifyObservers(new Object[] {Boolean.TRUE});}

		public Player getPlayer() {
			return player;
		}

		public ArrayList<Alien> getAliens() {
			return aliens;
		}

		public ArrayList<Shot> getShots() {
			return shots;
		}	
}
        
	

