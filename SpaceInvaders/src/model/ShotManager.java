package model;

import java.util.ArrayList;

public class ShotManager {
	private static ShotManager myShotManager;
	private ArrayList<Shot> shots;
	
	private ShotManager() {
		    shots = new ArrayList<>();  // estaba sin inicializar, daba NullPointerException
	}
	
	public static ShotManager getShotManager() {
		if (myShotManager==null) {
			myShotManager=new ShotManager();
		}
		return myShotManager;
	}
	
	public boolean isAShotThere(int x, int y) {
		for (int i=0; i<shots.size();i++)
		{
			if(shots.get(i) != null && shots.get(i).isThisPosition(x, y)) {
				return true;}
		}
		return false;
	}
	//para añadir un disparo a la lista!!
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
