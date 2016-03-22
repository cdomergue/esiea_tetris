package main.java.domergue.bastide.jTetris.components.tetriminos;

public class Shape {

	public static final int MAX_SHAPE_COLUMNS = 4;
	public static final int MAX_SHAPE_LINES = 4;
	
	private Boolean[][] shape;

	public Shape(Boolean[][] shape){
		this.shape = shape;
	}
	
	public Boolean[][] getShape() {
		return shape;
	}

	public void setShape(Boolean[][] shape) {
		this.shape = shape;
	}
	
	
}
