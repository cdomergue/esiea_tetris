package main.java.domergue.bastide.jTetris.components.tetriminos;

public class Shape {

	public static final int MAX_SHAPE_COLUMNS = 4;
	public static final int MAX_SHAPE_LINES = 4;
	
	private Boolean[][] units;

	public Shape(){
		this.units = new Boolean[MAX_SHAPE_COLUMNS][MAX_SHAPE_LINES];
		initUnits();
	}

	private void initUnits() {
		for(int i = 0; i < 4; i++){
			for (int j = 0; j < 4; j++){
				this.units[i][j] = false;
			}
		}
	}
	
	public boolean getUnits(int column, int line) throws IndexOutOfBoundsException {
		if(column > MAX_SHAPE_COLUMNS -1 || line > MAX_SHAPE_LINES - 1){
			throw new IndexOutOfBoundsException();
		}
		return units[column][line];
	}

	public void setUnits(int column, int line, boolean value) throws IndexOutOfBoundsException {
		if(column > MAX_SHAPE_COLUMNS -1 || line > MAX_SHAPE_LINES - 1){
			throw new IndexOutOfBoundsException();
		}
		this.units[column][line] = value;
	}
	
	
}
