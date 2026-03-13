package model;

import java.util.ArrayList;

public class ShotManager {

	// Atributos
	private static ShotManager myShotManager;
	private ArrayList<Shot> shots;
	
	// Patrón Singleton
	private ShotManager() {
		    shots = new ArrayList<>();  // estaba sin inicializar, daba NullPointerException
	}
	
	public static ShotManager getShotManager() {
		if (myShotManager==null) {
			myShotManager=new ShotManager();
		}
		return myShotManager;
	}
	
	// Posición del disparo
	public boolean isAShotThere(int x, int y) {
		for (int i=0; i<shots.size();i++)
		{
			if(shots.get(i) != null && shots.get(i).isThisPosition(x, y)) {
				return true;}
		}
		return false;
	}
	
	// Añadir o eliminar disparo
	public void addShot(int x, int y) {
	    Shot shot = new Shot(x, y);
	    shots.add(shot);
	}
	
	public void removeShot(int x, int y) {
		for (int i = 0; i < shots.size(); i++) {	// busco el disparo
			if (shots.get(i).isThisPosition(x, y)) {
				shots.get(i).stopShot();
				shots.remove(i);
			}
		}
	}	
	
	
}
