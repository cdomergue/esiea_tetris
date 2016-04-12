package com.domergue.bastide.jTetris.components;

import com.domergue.bastide.jTetris.components.tetriminos.Tetrimino;
import com.domergue.bastide.jTetris.components.throwables.BottomTouched;
import com.domergue.bastide.jTetris.components.throwables.SideTouched;

public class Board {

	public static final int DEFAULT_COLUMNS = 10;
	public static final int DEFAULT_LINES = 21;

	private static Board instance;
	private Tetrimino movingTetrimino;
	private Cell[][] cells;

	private Board() {
		cells = new Cell[DEFAULT_LINES][DEFAULT_COLUMNS];
		initCells();
	}

	public void addNewMovingTetrimino(Tetrimino tetrimino) {
		tetrimino.setX(0);
		tetrimino.setY((DEFAULT_COLUMNS / 2) - 2);
		this.setMovingTetrimino(tetrimino);
		putMovingTetrimino();
	}

	public void moveMovingTetriminoDown() throws BottomTouched {
		if (movingTetrimino.getX() + 1 < DEFAULT_LINES - 3) {
			movingTetrimino.setX(movingTetrimino.getX() + 1);
		} else {
			movingTetrimino.getShape().moveUnitsDown();
		}
		updateMovingTetrimino();
	}

	public void moveMovingTetriminoLeft() throws SideTouched {
		if (movingTetrimino.getY() - 1 >= 0) {
			movingTetrimino.setY(movingTetrimino.getY() - 1);
		} else {
			movingTetrimino.getShape().moveUnitsLeft();
		}
		updateMovingTetrimino();

	}

	public void moveMovingTetriminoRight() throws SideTouched {
		if (movingTetrimino.getY() + 1 < DEFAULT_COLUMNS - 3) {
			movingTetrimino.setY(movingTetrimino.getY() + 1);
			updateMovingTetrimino();
		} else {
			movingTetrimino.getShape().moveUnitsRigth();
		}
		updateMovingTetrimino();
	}

	public void updateMovingTetrimino() {
		removeMovingTetrimino();
		putMovingTetrimino();
	}

	private void removeMovingTetrimino() {
		for (int line = 0; line < DEFAULT_LINES; line++) {
			for (int column = 0; column < DEFAULT_COLUMNS; column++) {
				if (cells[line][column].getTetriminoId() == movingTetrimino.getTetriminoId()) {
					cells[line][column].setTetriminoId(0);
					cells[line][column].setOccupied(false);
				}
			}
		}
	}

	private void putMovingTetrimino() {
		for (int i = movingTetrimino.getX(), k = 0; i < movingTetrimino.getX() + 4; i++, k++) {
			for (int j = movingTetrimino.getY(), l = 0; j < movingTetrimino.getY() + 4; j++, l++) {
				cells[i][j].setTetriminoId(movingTetrimino.getTetriminoId());
				if (movingTetrimino.getShape().getUnits(k, l) == true) {
					cells[i][j].setOccupied(true);
				}
			}
		}
	}

	private void initCells() {
		for (int line = 0; line < DEFAULT_LINES; line++) {
			for (int column = 0; column < DEFAULT_COLUMNS; column++) {
				cells[line][column] = new Cell();
			}
		}
	}

	public void resetCells() {
		initCells();
	}

	public static final Board getInstance() {
		if (instance == null) {
			instance = new Board();
		}
		return (instance);
	}

	public Cell[][] getCells() {
		return this.cells;
	}

	public Cell getCell(int i, int j) throws IndexOutOfBoundsException {
		return this.cells[i][j];
	}

	public Tetrimino getMovingTetrimino() {
		return movingTetrimino;
	}

	public void setMovingTetrimino(Tetrimino movingTetrimino) {
		this.movingTetrimino = movingTetrimino;
	}

	@Override
	public String toString() {
		String string = "";
		for (int line = 0; line < DEFAULT_LINES; line++) {
			for (int column = 0; column < DEFAULT_COLUMNS; column++) {
				string += cells[line][column].isOccupied() ? "# " : ". ";
			}
			string += "\n";
		}
		return string;
	}

}
