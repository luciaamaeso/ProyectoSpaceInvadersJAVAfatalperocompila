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
		boolean out=isOutOfRange(this.y-1);
		if (!out) {
			this.y--;
			//Colision???
			if (AlienManager.getAlienManager().isAnAlienThere(this.x, this.y)) {
				AlienManager.getAlienManager().killAlien(this.x, this.y);
				ShotManager.getShotManager().removeShot(this.x, this.y);
			} 
		} else {	//Si el disparo esta fuera, para el timer y lo borra
			stopShot();
			ShotManager.getShotManager().removeShot(this.x, this.y);
		}
	}
	
	private boolean isOutOfRange(int y) {
		return (y < 0);	
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
	public void stopShot() {//aqui se para el disparo(se para el  timer)
		if (timer != null) {
			timer.cancel();
		}
	}

	//Este también lo cambio, ls pos x,y
	public boolean isThisPosition(int x, int y) {
	    return this.x == x && this.y == y;
	}
}
