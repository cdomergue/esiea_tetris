package main.java.domergue.bastide.jTetris.components;


public class Board {
	
	public static final int DEFAULT_COLUMNS = 10;
	public static final int DEFAULT_LINES = 21;
	
	private static Board instance;
	private Cell[][] cells;
	
	private Board (){
		cells = new Cell[DEFAULT_COLUMNS][DEFAULT_LINES];
		initCells();
	}

	private void initCells() {
		for(int column = 0; column < DEFAULT_COLUMNS; column++){
			for(int line = 0; line < DEFAULT_LINES; line++){
				cells[column][line] = new Cell(0);
			}
		}
	}
	
	public static final Board getInstance(){
		if(instance == null){
			instance = new Board();
		}
		return(instance);
	}

	public Cell[][] getCells() {
		return this.cells;
	}

	public Cell getCell(int i, int j) throws IndexOutOfBoundsException {
		return this.cells[i][j];
	}
	
}
