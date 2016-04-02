package main.java.domergue.bastide.jTetris.components.tetriminos;

public class Tetrimino {
	
	private long tetriminoId;
	private Shape shape;
	
	
	public Tetrimino(long tetriminoId, Shape shape){
		this.tetriminoId = tetriminoId;
		this.shape = shape;
	}
	
	public Shape getShape() {
		return shape;
	}
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	public long getTetriminoId() {
		return tetriminoId;
	}
	public void setTetriminoId(long tetriminoId) {
		this.tetriminoId = tetriminoId;
	}
	
	
}
