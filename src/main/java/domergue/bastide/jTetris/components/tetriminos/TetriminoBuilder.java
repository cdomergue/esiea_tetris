package main.java.domergue.bastide.jTetris.components.tetriminos;

public class TetriminoBuilder {

	private static TetriminoBuilder instance;
	private long lastTetriminoId = 1; 
	
	private TetriminoBuilder(){
		
	}
	
	public static TetriminoBuilder getInstance(){
		return (instance == null) ? instance = new TetriminoBuilder() : instance;
	}
	
	public long getLastTetriminoId(){
		return lastTetriminoId;
	}

	public Tetrimino build(TetriminoPieces piece){
		Shape shape = new Shape();
		switch(piece){
			
		case PIECE_I:
			shape.setUnits(0,0,true);
			shape.setUnits(0,1,true);
			shape.setUnits(0,2,true);
			shape.setUnits(0,3,true);
			break;
		case PIECE_O:
			shape.setUnits(0, 0, true);
			shape.setUnits(1, 0, true);
			shape.setUnits(0, 1, true);
			shape.setUnits(1, 1, true);
			break;
		case PIECE_T:
			shape.setUnits(0, 0, true);
			shape.setUnits(0, 1, true);
			shape.setUnits(0, 2, true);
			shape.setUnits(1, 1, true);
			break;
		case PIECE_L:
			shape.setUnits(0, 0, true);
			shape.setUnits(0, 1, true);
			shape.setUnits(0, 2, true);
			shape.setUnits(1, 0, true);
			break;
		case PIECE_J:
			shape.setUnits(0, 0, true);
			shape.setUnits(0, 1, true);
			shape.setUnits(0, 2, true);
			shape.setUnits(1, 2, true);
			break;
		case PIECE_Z:
			shape.setUnits(0, 0, true);
			shape.setUnits(0, 1, true);
			shape.setUnits(1, 1, true);
			shape.setUnits(1, 2, true);
			break;
		case PIECE_S:
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
}
