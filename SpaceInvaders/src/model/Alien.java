package model;

import java.util.Timer;
import java.util.TimerTask;

public class Alien extends SpaceCraft{
	//alien de 1 pixel
	
	private Timer timer;
	
	public Alien(int x, int y) {
		super(x, y);		//no se si cambiar color, porq los enemigos son siempre de 1 color?
		moveEvery350ms();	//segun se crea, empieza a bajar automaticamente
	}
	
	//Los alien se moveran hacia abajo una posicion cada 350 milisegundos
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
	
	//Cuando se muere el alien
	public void stopTimer() {
        timer.cancel();
    }
	
	public boolean isThisPosition(int posX, int posY) {
		if (x==posX && y==posY) {
			return true;
		}
		else {
			return false;
		}
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
	
}

