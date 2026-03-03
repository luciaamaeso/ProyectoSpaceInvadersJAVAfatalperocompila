package model;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Observable;

	public class Shot extends SpaceCraft{

	private int posX;
	private int posY;
	
	private Timer timer;
	
	public Shot(int x, int y) {		
		super(x,y);	// posY es la posY de la nave + 2 (empieza 2px más arriba) 
		moveEvery50ms();

	}
		
	private void move() {
		this.posY--;
				//Si el sisparo esta fuera, lo para(el timer etc))
				if (this.posY < 0) {
					stopShot();
					return;
				}
				//Colision???
				if (AlienManager.getAlienManager().isAnAlienThere(this.posX, this.posY)) {
					AlienManager.getAlienManager().killAlien(this.posX, this.posY);
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
	public int getX() {
		return this.posX;
	}
	public int getY() {
		return this.posY;
	}
	private void stopShot() {//aqui se para el disparo(se para el  timer)
		if (timer != null) {
			timer.cancel();
		}
	}
	
}
