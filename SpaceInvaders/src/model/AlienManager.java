package model;

public class AlienManager {
	private static AlienManager myAlienManager;
	private Alien[] aliens;
	
	private AlienManager() {
		aliens = new Alien[4];	//4 aliens de momento (igual a futuro tiene que cambiar porque son 4-8 aliens)
	}
	
	public static AlienManager getAlienManager() {
		if (myAlienManager==null) {
			myAlienManager=new AlienManager();
		}
		return myAlienManager;
	}
	
	public boolean isAnAlienThere(int x, int y) {
		for (int i=0; i<4;)
		{
			if(aliens[i].isThisPosition(x, y)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkIfEndMatrix() {
		for (int i=0; i<4;)
		{
			if (aliens[i].checkIfEndMatrix()) {
				return true;
			}
		}
		return false;
	}
}
