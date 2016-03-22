package main.java.domergue.bastide.jTetris.components.tetriminos;

public class Tetrimino {
	
	private int tetriminoId;
	private Shape shape;
	
	
	public Tetrimino(int tetriminoId, Shape shape){
		this.tetriminoId = tetriminoId;
		this.shape = shape;
	}
	
	public Shape getShape() {
		return shape;
	}
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	public int getTetriminoId() {
		return tetriminoId;
	}
	public void setTetriminoId(int tetriminoId) {
		this.tetriminoId = tetriminoId;
	}
	
	
}
