package com.domergue.bastide.jTetris.components;

public class Cell {
	
	private long tetriminoId;
	private boolean occupied;
	
	public Cell(){
		this.setTetriminoId(0);
		this.setOccupied(false);
		
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
	
	
}
