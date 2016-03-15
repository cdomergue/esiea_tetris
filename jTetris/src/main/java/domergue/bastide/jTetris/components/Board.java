package main.java.domergue.bastide.jTetris.components;


public class Board {
	
	public static final int DEFAULT_COLUMNS = 10;
	public static final int DEFAULT_LINES = 21;
	
	private static Board instance;
	private Boolean[][] cells;
	
	private Board (){
		initCells();
	}

	private void initCells() {
		cells = new Boolean[DEFAULT_COLUMNS][DEFAULT_LINES];
		for(int column = 0; column < DEFAULT_COLUMNS; column++){
			for(int line = 0; line < DEFAULT_LINES; line++){
				cells[column][line] = false;
			}
		}
	}
	
	public static final Board getInstance(){
		if(instance == null){
			instance = new Board();
		}
		return(instance);
	}

	public Boolean[][] getCells() {
		return this.cells;
	}

	public Boolean getCell(int i, int j) throws IndexOutOfBoundsException {
		return this.cells[i][j];
	}
	
}
