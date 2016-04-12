package com.domergue.bastide.jTetris.components.tetriminos;

import com.domergue.bastide.jTetris.components.throwables.BottomTouched;
import com.domergue.bastide.jTetris.components.throwables.SideTouched;

public class Shape {

	public static final int MATRIX_SIZE = 4;

	private Boolean[][] units;

	public Shape() {
		this.units = new Boolean[MATRIX_SIZE][MATRIX_SIZE];
		initUnits(this.units);
	}
	
	/**
	 * Create a clone of the shape
	 * 
	 * @param cloneShape the shape to clone
	 */
	public Shape(Shape cloneShape){
		this.units = new Boolean[MATRIX_SIZE][MATRIX_SIZE];
		for(int i = 0; i < MATRIX_SIZE; i++){
			for (int j = 0; j < 4; j++) {
				this.units[i][j] = cloneShape.units[i][j];
			}
		}
	}

	private void initUnits(Boolean[][] units) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				units[i][j] = false;
			}
		}
	}

	public boolean getUnits(int line, int column) throws IndexOutOfBoundsException {
		if (column > MATRIX_SIZE - 1 || line > MATRIX_SIZE - 1) {
			throw new IndexOutOfBoundsException();
		}
		return units[line][column];
	}

	public void setUnits(int line, int column, boolean value) throws IndexOutOfBoundsException {
		if (column > MATRIX_SIZE - 1 || line > MATRIX_SIZE - 1) {
			throw new IndexOutOfBoundsException();
		}
		this.units[line][column] = value;
	}

	@Override
	public String toString() {
		String string = "";
		for (int i = 0; i < MATRIX_SIZE; i++) {
			for (int j = 0; j < MATRIX_SIZE; j++) {
				string += this.units[i][j] == true ? "#" : ".";
				string += " ";
			}
			string += "\n";
		}
		return string;

	}

	public void moveUnitsDown() throws BottomTouched {
		int voidLine = 0;
		int cpt = 0;
		for (int i = 0; i < MATRIX_SIZE; i++) {
			for (int j = 0; j < MATRIX_SIZE; j++) {
				if (units[i][j] == true) {
					break;
				}
				cpt++;
			}
			if (cpt == 4) {
				voidLine = i;
			} else {
				voidLine = 0;
			}
			cpt = 0;
		}
		if (voidLine != 0) {
			Boolean[][] newUnits = new Boolean[MATRIX_SIZE][MATRIX_SIZE];
			initUnits(newUnits);
			for (int i = 0; i < MATRIX_SIZE - 1; i++) {
				for (int j = 0; j < 4; j++) {
					newUnits[i + 1][j] = units[i][j];
				}
			}
			this.units = newUnits;
		} else {
			throw new BottomTouched();
		}
	}

	public void moveUnitsRigth() throws SideTouched {
		int voidColumn = 0;
		int cpt = 0;
		for (int i = 0; i < MATRIX_SIZE; i++) {
			for (int j = 0; j < MATRIX_SIZE; j++) {
				if (units[j][i] == true) {
					break;
				}
				cpt++;
			}
			if (cpt == 4) {
				voidColumn = i;
			} else {
				voidColumn = 0;
			}
			cpt = 0;
		}
		if (voidColumn != 0) {
			Boolean[][] newUnits = new Boolean[MATRIX_SIZE][MATRIX_SIZE];
			initUnits(newUnits);
			for (int i = 0; i < MATRIX_SIZE - 1; i++) {
				for (int j = 0; j < MATRIX_SIZE; j++) {
					newUnits[j][i + 1] = units[j][i];
				}
			}
			this.units = newUnits;
		} else {
			throw new SideTouched();
		}

	}

	public void moveUnitsLeft() throws SideTouched {
		int voidColumn = -1;
		int cpt = 0;
		for (int i = MATRIX_SIZE - 1; i >= 0; i--) {
			for (int j = 0; j < MATRIX_SIZE; j++) {
				if (units[j][i] == true) {
					break;
				}
				cpt++;
			}
			if (cpt == 4) {
				voidColumn = i;
			} else {
				voidColumn = -1;
			}
			cpt = 0;
		}
		if (voidColumn != -1) {
			Boolean[][] newUnits = new Boolean[MATRIX_SIZE][MATRIX_SIZE];
			initUnits(newUnits);
			for (int i = MATRIX_SIZE - 1; i >= 1; i--) {
				for (int j = 0; j < MATRIX_SIZE; j++) {
					newUnits[j][i - 1] = units[j][i];
				}
			}
			this.units = newUnits;
		} else {
			throw new SideTouched();
		}

	}

}
