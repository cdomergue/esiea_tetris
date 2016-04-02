package main.java.domergue.bastide.jTetris.components.tetriminos;

public class TetriminoBuilder {

	private static TetriminoBuilder instance;
	private long lastTetriminoId = 1; 
	
	private TetriminoBuilder(){
		
	}
	
	public static TetriminoBuilder getInstance(){
		if(instance == null){
			instance = new TetriminoBuilder();
		}
		return instance;
	}
	
	public long getLastTetriminoId(){
		return lastTetriminoId;
	}

	public Tetrimino build(TetriminoPieces piece){
		Shape shape = new Shape();
		switch(piece){
			
		case PIECE_I:
			shape.setUnits(0,0,true);
			shape.setUnits(1,0,true);
			shape.setUnits(2,0,true);
			shape.setUnits(3,0,true);
			break;
		default:
			break;
			
		}
		Tetrimino tetrimino = new Tetrimino(lastTetriminoId, shape);
		lastTetriminoId++;
		return tetrimino;
		
	}
}
