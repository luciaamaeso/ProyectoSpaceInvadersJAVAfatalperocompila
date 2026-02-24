package model;

import java.util.Timer;
import java.util.TimerTask;

public class Alien extends SpaceCraft{
	//alien de 1 pixel
	
	private Timer timer;
	
	public Alien(int x, int y) {
		super(x,y,"blue");	//no se si cambiar color, porq los enemigos son siempre de 1 color?
		moveEvery200ms();	//segun se crea, empieza a bajar automaticamente
	}
	
	//Los alien se moveran hacia abajo una posici n cada 200 milisegundos
	
	private void moveDown() {
		y=y++;
		setChanged();
        notifyObservers();
        //casilla comprueba al guardar en ella q hay un alien si checkIfEndMatrix() o si hay un player
	}
	
	private void moveEvery200ms()
	{
		timer = new Timer();
		
		timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                moveDown();
            }
        }, 0, 200);

	}
	
	//Cuando se muere el alien
	public void stopTimer() {
        timer.cancel();
    }
	
	//checkIfTouchPlayer()
	
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
	
	//Si un disparo toca a un enemigo se destruye. 
}

