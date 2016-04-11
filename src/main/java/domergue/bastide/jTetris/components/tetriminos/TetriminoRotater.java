package main.java.domergue.bastide.jTetris.components.tetriminos;

public class TetriminoRotater {

	private static TetriminoRotater instance;
	public static final byte ROTATE_LEFT = 1;
	public static final byte ROTATE_RIGHT = 2;
	
	private TetriminoRotater(){
		
	}
	
	public static TetriminoRotater getInstance(){
		return (instance == null) ? instance = new TetriminoRotater() : instance;
	}
	
	public Tetrimino rotate(Tetrimino tetrimino, byte rotation){
		Shape newShape = new Shape();
		
		switch(rotation){
			case ROTATE_RIGHT:
				for(int i = 0; i < Shape.MATRIX_SIZE; i++){
					for(int k = 0, j = Shape.MATRIX_SIZE - 1; k < Shape.MATRIX_SIZE; k++, j--){
						newShape.setUnits(i, k, tetrimino.getShape().getUnits(j, i));
					}
				}
				break;
			case ROTATE_LEFT:
				for(int i = 0, j = Shape.MATRIX_SIZE - 1; i < Shape.MATRIX_SIZE; i++, j--){
					for(int k = 0; k < Shape.MATRIX_SIZE; k++){
						newShape.setUnits(i, k, tetrimino.getShape().getUnits(k, j));
					}
				}
				break;
			default:
				break;
		}
		tetrimino.setShape(newShape);
		return tetrimino;
		
	}

	
	
}
