package com.domergue.bastide.jTetris.components;

import com.domergue.bastide.jTetris.components.tetriminos.Shape;
import com.domergue.bastide.jTetris.components.tetriminos.Tetrimino;
import com.domergue.bastide.jTetris.components.tetriminos.TetriminoBuilder;
import com.domergue.bastide.jTetris.components.tetriminos.TetriminoRotater;
import com.domergue.bastide.jTetris.components.throwables.BottomTouched;
import com.domergue.bastide.jTetris.components.throwables.OtherPieceTouched;
import com.domergue.bastide.jTetris.components.throwables.SideTouched;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class Board {

	public static final int DEFAULT_COLUMNS = 10;
	public static final int DEFAULT_LINES = 21;

	private static Board instance;
	private Tetrimino movingTetrimino;
	private int movingTetriminoLastX = 0;
	private int movingTetriminoLastY = 0;
	private Shape movingTetriminoLastShape;
	private Cell[][] cells;
	private int speed = 500;
	private int normalSpeed = 500;
	private Score score = Score.getInstance();
	private boolean gameEnded = false;

	private Board() {
		cells = new Cell[DEFAULT_LINES][DEFAULT_COLUMNS];
		initCells();
	}

	public void addNewMovingTetrimino(Tetrimino tetrimino) {
		tetrimino.setX(0);
		tetrimino.setY((DEFAULT_COLUMNS / 2) - 2);
		this.setMovingTetrimino(tetrimino);
		try {
			putMovingTetrimino();
			score.add(20);
		} catch (OtherPieceTouched e) {
			//Partie perdu
			System.out.println("Impossible de placer une nouvelle pièce. Fin du jeu.");
			gameEnded = true;
		}
	}

	public void moveMovingTetriminoDown() throws BottomTouched {
		saveLastCoordinates();
		if (movingTetrimino.getX() + 1 < DEFAULT_LINES - 3) {
			movingTetrimino.setX(movingTetrimino.getX() + 1);
		} else {
			movingTetrimino.getShape().moveUnitsDown();
		}
		tryUpdateMovingTetrimino();
	}

	public void moveMovingTetriminoLeft() throws SideTouched {
		saveLastCoordinates();
		if (movingTetrimino.getY() - 1 >= 0) {
			movingTetrimino.setY(movingTetrimino.getY() - 1);
		} else {
			movingTetrimino.getShape().moveUnitsLeft();
		}
		tryUpdateMovingTetrimino();

	}

	public void moveMovingTetriminoRight() throws SideTouched {
		saveLastCoordinates();
		if (movingTetrimino.getY() + 1 < DEFAULT_COLUMNS - 3) {
			movingTetrimino.setY(movingTetrimino.getY() + 1);
		} else {
			movingTetrimino.getShape().moveUnitsRigth();
		}
		tryUpdateMovingTetrimino();
	}

	public void tryUpdateMovingTetrimino() {
		try {
			updateMovingTetrimino();
		} catch (OtherPieceTouched e) {
			restoreLastCoordinates();
			try {
				updateMovingTetrimino();
				addNewMovingTetrimino(TetriminoBuilder.getInstance().pickRandom());
				updateMovingTetrimino();
			} catch (OtherPieceTouched e1) {
				System.err.println("Erreur, impossible de restaurer la pièce");
			}
		}
	}

	private void restoreLastCoordinates() {
		movingTetrimino.setX(movingTetriminoLastX);
		movingTetrimino.setY(movingTetriminoLastY);
		movingTetrimino.setShape(movingTetriminoLastShape);
	}

	private void saveLastCoordinates() {
		this.movingTetriminoLastX = movingTetrimino.getX();
		this.movingTetriminoLastY = movingTetrimino.getY();
		this.movingTetriminoLastShape = new Shape(movingTetrimino.getShape());
	}

	private void updateMovingTetrimino() throws OtherPieceTouched {
		removeMovingTetrimino();
		putMovingTetrimino();
	}

	public void rotate(){
		removeMovingTetrimino();
		TetriminoRotater.getInstance().rotate(movingTetrimino, TetriminoRotater.ROTATE_LEFT);
		try {
			putMovingTetrimino();
		} catch (OtherPieceTouched otherPieceTouched) {
			TetriminoRotater.getInstance().rotate(movingTetrimino, TetriminoRotater.ROTATE_RIGHT);
			try {
				putMovingTetrimino();
			} catch (OtherPieceTouched otherPieceTouched1) {
				System.err.println("Impossible de pivoter la pièce");
			}
		}
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

	private void putMovingTetrimino() throws OtherPieceTouched {
		for (int i = movingTetrimino.getX(), k = 0; i < movingTetrimino.getX() + 4; i++, k++) {
			for (int j = movingTetrimino.getY(), l = 0; j < movingTetrimino.getY() + 4; j++, l++) {
				if(movingTetrimino.getShape().getUnits(k, l)){
					if(cells[i][j].isOccupied()){
						throw new OtherPieceTouched();
					}
					cells[i][j].setTetriminoId(movingTetrimino.getTetriminoId());
					cells[i][j].setOccupied(true);
                    cells[i][j].setColor(movingTetrimino.getShape().getColor());

				}
			}
		}
	}
	
	public int checkLine(){
		int cpt = 0;
		int nbOfLines = 0;
		for (int line = 0; line < DEFAULT_LINES; line++) {
			for (int column = 0; column < DEFAULT_COLUMNS; column++) {
				if(cells[line][column].isOccupied()){
					cpt++;
				}
			}
			if(cpt == DEFAULT_COLUMNS){
				nbOfLines++;
				removeLine(line);
				successLine();
			}
			cpt = 0;
		}
		return nbOfLines;
	}

	private void successLine() {
		score.add(200);
	}

	private void removeLine(int line) {
		for(int i = line; i > 0; i--){
			for(int j = 0; j < DEFAULT_COLUMNS; j++){
				cells[i][j].setTetriminoId(cells[i-1][j].getTetriminoId());
				cells[i][j].setOccupied(cells[i-1][j].isOccupied());
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

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getNormalSpeed() {
		return normalSpeed;
	}

	public boolean isGameEnded() {
		return gameEnded;
	}

	public void setGameEnded(boolean gameEnded) {
		this.gameEnded = gameEnded;
	}
}
