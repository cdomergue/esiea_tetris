package com.domergue.bastide.jTetris.components.tetriminos;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.domergue.bastide.jTetris.components.tetriminos.Shape;
import com.domergue.bastide.jTetris.components.tetriminos.Tetrimino;

public class TetriminoTest {
	
	private Tetrimino tetrimino;
	private Shape shape;

	
	@Before
	public void beforeTests(){
		this.shape = new Shape();
		this.tetrimino = new Tetrimino(1, shape);
		
	}
	
	@Test
	public void testIfHasTheGoodShape() {
		assertEquals(false, tetrimino.getShape().getUnits(2,3));
	}

}
