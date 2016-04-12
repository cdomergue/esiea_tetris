package com.domergue.bastide.jTetris.components;

public class Cell {
	
	private long tetriminoId;
	private boolean occupied;
	private String color;

	public Cell() {
		this.setTetriminoId(0);
		this.setOccupied(false);
		this.setColor("yellow");

	}

	public long getTetriminoId() {
		return tetriminoId;
	}

	public void setTetriminoId(long tetriminoId) {
		this.tetriminoId = tetriminoId;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}


	public void setColor(String color) {
		this.color = color;
	}

	public String getColor(){
		return this.color;
	}
}
