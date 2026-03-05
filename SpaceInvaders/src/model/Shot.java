package model;

import java.util.Timer;
import java.util.TimerTask;

	public class Shot extends SpaceCraft{
	
	private Timer timer;
	
	public Shot(int x, int y) {		
		super(x,y);	// posY es la posY de la nave + 2 (empieza 2px más arriba) 
		moveEvery50ms();

	}
	// Cambio el metodo porque hacia referencia a los anteriores atributos, no habia herencia.
	private void move() {
	    this.y--;
	    if (this.y < 0) {
	        stopShot();
	        return;
	    }
	    if (AlienManager.getAlienManager().isAnAlienThere(this.x, this.y)) {
	        AlienManager.getAlienManager().killAlien(this.x, this.y);
	        stopShot();
	        return;
	    }
	}
	
	private void moveEvery50ms(){
		timer = new Timer();
		
		timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                move();
            }
        }, 0, 50);	
}
	private void stopShot() {//aqui se para el disparo(se para el  timer)
		if (timer != null) {
			timer.cancel();
		}
	}

	//Y los getters
	public int getX() { 
		return this.x; 
	}
	
	
	public int getY() { 
		return this.y; 
	}
	
	

	//Este también lo cambio, ls pos x,y
	public boolean isThisPosition(int x, int y) {
	    return this.x == x && this.y == y;
	}
}
