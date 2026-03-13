package model;

import java.util.Timer;
import java.util.TimerTask;

public class Alien extends SpaceCraft{
	
	// Atributos
	private Timer timer;
	
	// Constructora
	public Alien(int x, int y) {
		super(x, y);		//no se si cambiar color, porq los enemigos son siempre de 1 color?
		moveEvery350ms();	//segun se crea, empieza a bajar automaticamente
	}
	
	//Métodos para moverse
	private void moveEvery350ms()
	{
		timer = new Timer();
		
		timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                moveDown();
            }
        }, 0, 350);

	}
	
	public boolean checkIfEndMatrix()
	{
		if (y>=60)
		{
			return true;
		}
		else
		{
			return false;
		}
	}	
	

	//Para matar a un alien
	public void stopTimer() {
        timer.cancel();
    }
	
	// Comprobar posición
	public boolean isThisPosition(int posX, int posY) {
		if (x==posX && y==posY) {
			return true;
		}
		else {
			return false;
		}
	}
		
	
}

