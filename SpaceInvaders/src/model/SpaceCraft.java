package model;

import java.util.Observable;

public abstract class SpaceCraft extends Observable {
	// Atributos
	
	protected int x;
	protected int y;
	protected String color;
	// private Shot[] bullets;  esto lo he hecho pensando en el futuro

	
	// Constructora
	
	protected SpaceCraft(int x, int y, String color) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.addObserver(null);  // Aquí hay que comprobar el nombre
	}
	
	// Moverse
	
	protected void moveLeft() {
		this.x = x - 1;
	}
	
	protected void moveRight() {
		this.x = x+1;
	}
	
	public void moveUp() {
		this.y = y + 1;
	}
	
	public void moveDown() {
		this.y = y + 1;
	}

	// Disparar
	protected void shoot() {
		// aquí falta por hacer
	}
	
}
