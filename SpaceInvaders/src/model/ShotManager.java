package model;

import java.util.ArrayList;

public class ShotManager {
	private static ShotManager myShotManager;
	private ArrayList<Shot> shots;
	
	private ShotManager() {}
	
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
	
}
