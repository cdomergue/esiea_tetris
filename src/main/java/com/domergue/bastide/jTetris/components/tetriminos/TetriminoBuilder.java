package com.domergue.bastide.jTetris.components.tetriminos;

import java.util.Random;

public class TetriminoBuilder {

	private static TetriminoBuilder instance;
	private long lastTetriminoId = 1;

	private TetriminoBuilder() {

	}

	public static TetriminoBuilder getInstance() {
		return (instance == null) ? instance = new TetriminoBuilder() : instance;
	}

	public long getLastTetriminoId() {
		return lastTetriminoId;
	}

	public Tetrimino build(TetriminoPieces piece) {
		Shape shape = new Shape();
		switch (piece) {

		case PIECE_I:
			shape.setColor("bblue");
			shape.setUnits(0, 0, true);
			shape.setUnits(0, 1, true);
			shape.setUnits(0, 2, true);
			shape.setUnits(0, 3, true);
			break;
		case PIECE_O:
			shape.setColor("yellow");
			shape.setUnits(0, 0, true);
			shape.setUnits(1, 0, true);
			shape.setUnits(0, 1, true);
			shape.setUnits(1, 1, true);
			break;
		case PIECE_T:
			shape.setColor("purple");
			shape.setUnits(0, 0, true);
			shape.setUnits(0, 1, true);
			shape.setUnits(0, 2, true);
			shape.setUnits(1, 1, true);
			break;
		case PIECE_L:
			shape.setColor("orange");
			shape.setUnits(0, 0, true);
			shape.setUnits(0, 1, true);
			shape.setUnits(0, 2, true);
			shape.setUnits(1, 0, true);
			break;
		case PIECE_J:
			shape.setColor("blue");
			shape.setUnits(0, 0, true);
			shape.setUnits(0, 1, true);
			shape.setUnits(0, 2, true);
			shape.setUnits(1, 2, true);
			break;
		case PIECE_Z:
			shape.setColor("red");
			shape.setUnits(0, 0, true);
			shape.setUnits(0, 1, true);
			shape.setUnits(1, 1, true);
			shape.setUnits(1, 2, true);
			break;
		case PIECE_S:
			shape.setColor("green");
			shape.setUnits(0, 1, true);
			shape.setUnits(0, 2, true);
			shape.setUnits(1, 0, true);
			shape.setUnits(1, 1, true);
		default:
			break;

		}
		Tetrimino tetrimino = new Tetrimino(lastTetriminoId, shape);
		lastTetriminoId++;
		return tetrimino;

	}
	
	public Tetrimino pickRandom(){
		return build(randomPiece());
	}
	
	private TetriminoPieces randomPiece(){
		Random random = new Random();
		switch(random.nextInt(7)){
		case 1:
			return TetriminoPieces.PIECE_I;
		case 2:
			return TetriminoPieces.PIECE_J;
		case 3:
			return TetriminoPieces.PIECE_L;
		case 4:
			return TetriminoPieces.PIECE_O;
		case 5:
			return TetriminoPieces.PIECE_S;
		case 6:
			return TetriminoPieces.PIECE_T;
		case 7:
			return TetriminoPieces.PIECE_Z;
		default:
			return TetriminoPieces.PIECE_I;
		}
	}
}
