package model;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Observable;

	public class Shot extends Observable {

	private int posX;
	private int posY;
	
	private Timer timer;
	
	public Shot(int x, int y) {		
		this.posX=x;	
		this.posY=y+2;	// posY es la posY de la nave + 2 (empieza 2px más arriba) 
		moveEvery50ms();

	}
		
	private void move() {
		this.posY--;
		setChanged();
		notifyObservers();
		
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
	
}
