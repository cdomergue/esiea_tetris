package com.domergue.bastide.jTetris.components.tetriminos;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.domergue.bastide.jTetris.components.tetriminos.Tetrimino;
import com.domergue.bastide.jTetris.components.tetriminos.TetriminoBuilder;
import com.domergue.bastide.jTetris.components.tetriminos.TetriminoPieces;
import com.domergue.bastide.jTetris.components.tetriminos.TetriminoRotater;

public class TetriminoRotaterTest {
	
	private TetriminoRotater tRot;
	private TetriminoBuilder tBuild;

	@Before
	public void beforeTests(){
		tRot = TetriminoRotater.getInstance();
		tBuild = TetriminoBuilder.getInstance();
	}
	
	@Test
	public void testRotateRight() {
		Tetrimino tetriminoL = tBuild.build(TetriminoPieces.PIECE_L);
		tetriminoL = tRot.rotate(tetriminoL, TetriminoRotater.ROTATE_RIGHT);
		assertTrue(tetriminoL.getShape().getUnits(0, 2));
		assertTrue(tetriminoL.getShape().getUnits(0, 3));
		assertTrue(tetriminoL.getShape().getUnits(1, 3));
		assertTrue(tetriminoL.getShape().getUnits(2, 3));
	}
	
	@Test
	public void testRotateLeft() {
		Tetrimino tetriminoL = tBuild.build(TetriminoPieces.PIECE_L);
		System.out.println(tetriminoL.getShape());
		tetriminoL = tRot.rotate(tetriminoL, TetriminoRotater.ROTATE_LEFT);
		System.out.println(tetriminoL.getShape());
		assertTrue(tetriminoL.getShape().getUnits(1, 0));
		assertTrue(tetriminoL.getShape().getUnits(2, 0));
		assertTrue(tetriminoL.getShape().getUnits(3, 0));
		assertTrue(tetriminoL.getShape().getUnits(3, 1));
	}

}
