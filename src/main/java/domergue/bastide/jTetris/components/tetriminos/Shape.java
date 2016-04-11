package main.java.domergue.bastide.jTetris.components.tetriminos;

public class Shape {

	public static final int MATRIX_SIZE = 4;
	
	private Boolean[][] units;

	public Shape(){
		this.units = new Boolean[MATRIX_SIZE][MATRIX_SIZE];
		initUnits();
	}

	private void initUnits() {
		for(int i = 0; i < 4; i++){
			for (int j = 0; j < 4; j++){
				this.units[i][j] = false;
			}
		}
	}
	
	public boolean getUnits(int line, int column) throws IndexOutOfBoundsException {
		if(column > MATRIX_SIZE -1 || line > MATRIX_SIZE - 1){
			throw new IndexOutOfBoundsException();
		}
		return units[line][column];
	}

	public void setUnits(int line, int column, boolean value) throws IndexOutOfBoundsException {
		if(column > MATRIX_SIZE -1 || line > MATRIX_SIZE - 1){
			throw new IndexOutOfBoundsException();
		}
		this.units[line][column] = value;
	}
	
	@Override
	public String toString(){
		String string = "";
		for(int i = 0; i < 4; i++){
			for (int j = 0; j < 4; j++){
				string += this.units[i][j] == true ? "#" : ".";
				string += " ";
			}
			string += "\n";
		}
		return string;
		
	}
	
	
}
