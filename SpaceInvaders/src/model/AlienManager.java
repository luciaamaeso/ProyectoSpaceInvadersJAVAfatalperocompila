package model;

import java.util.Random;

public class AlienManager {
	private static AlienManager myAlienManager;
	private Alien[] aliens;
	private Random random = new Random();
	
	private AlienManager() {
		aliens = new Alien[random.nextInt(5) + 4];	// se crea array de 4 a 8 aliens aleatoriamente
		int x, y;
		for (int i = 0; i < aliens.length; i++) {
			do {
				x = random.nextInt(100); //pos x cualquiera en los 100 pixeles
				y = random.nextInt(10); //pos y cualquiera en los primeros 10 pixeles por ejemplo
			} while (isAnAlienThere(x, y));
			
			aliens[i] = new Alien(x, y); //cuando se haya encontrado una posición sin un alien, se crea
		}																
	}
	
	public static AlienManager getAlienManager() {
		if (myAlienManager==null) {
			myAlienManager=new AlienManager();
		}
		return myAlienManager;
	}
	
	public boolean isAnAlienThere(int x, int y) {
		for (int i=0; i<aliens.length;i++)
		{
			if(aliens[i] != null && aliens[i].isThisPosition(x, y)) {
				return true;}
		}
		return false;
	}
	
	public boolean checkIfEndMatrix() {
		for (int i=0; i<aliens.length; i++)
		{
			if (aliens[i] != null && aliens[i].checkIfEndMatrix()) {
				return true;}
		}
		return false;}
	
	public void removeAlien(int ali) { //Quitar un alien si muere
		if (ali >= 0 && ali < aliens.length && aliens[ali] != null) {
			aliens[ali].stopTimer();
			aliens[ali] = null;}
	}
	
	public void killAlien(int x, int y) { //Matsr al alien en la pos x,y
		for (int i = 0; i < aliens.length; i++) {
			if (aliens[i] != null && aliens[i].isThisPosition(x, y)) {
				removeAlien(i);
				return;}
		}}

	public boolean empty() {
		boolean javilo = true;
		int i = 0;
		while(i<aliens.length) {
			if(aliens[i] != null) {
				javilo = false;
			}
			i++;
		}
		return javilo;
	}

}
