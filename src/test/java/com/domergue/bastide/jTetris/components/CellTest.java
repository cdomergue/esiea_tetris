package com.domergue.bastide.jTetris.components;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.domergue.bastide.jTetris.components.Cell;

public class CellTest {
	
	private Cell cell;

	@Before
	public void beforeTests(){
		cell = new Cell();
	}
	
	@Test
	public void testIfCellIsInitialised() {
		assertEquals(0, cell.getTetriminoId());
	}
	
	@Test
	public void testIfCanSetPieceId(){
		cell.setTetriminoId(42);
		assertEquals(42, cell.getTetriminoId());
	}

}
