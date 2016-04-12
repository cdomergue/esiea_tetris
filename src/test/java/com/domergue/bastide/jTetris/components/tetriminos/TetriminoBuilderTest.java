package com.domergue.bastide.jTetris.components.tetriminos;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.domergue.bastide.jTetris.components.tetriminos.Tetrimino;
import com.domergue.bastide.jTetris.components.tetriminos.TetriminoBuilder;
import com.domergue.bastide.jTetris.components.tetriminos.TetriminoPieces;

public class TetriminoBuilderTest {
	
	private TetriminoBuilder tetriminoBuilder;
	
	@Before
	public void beforeTests(){
		this.tetriminoBuilder = TetriminoBuilder.getInstance();
	}
	
	@Test
	public void testTeriminoIPiece() {
		Tetrimino tetriminoI = tetriminoBuilder.build(TetriminoPieces.PIECE_I);
		
		assertEquals(true, tetriminoI.getShape().getUnits(0, 2));
		assertEquals(true, tetriminoI.getShape().getUnits(0, 3));
		assertEquals(false, tetriminoI.getShape().getUnits(3, 2));
	}


}
