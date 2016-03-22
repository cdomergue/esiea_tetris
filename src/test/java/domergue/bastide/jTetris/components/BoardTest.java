package test.java.domergue.bastide.jTetris.components;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.java.domergue.bastide.jTetris.components.Board;

public class BoardTest {
	
	private Board board;
	
	@Before
	public void beforeTests(){
		board = Board.getInstance();
	}

	@Test
	public void shouldBeASingleton(){
		Board board1 = Board.getInstance();
		Board board2 = Board.getInstance();
		assertEquals(board2, board1);
	}
	
	@Test
	public void boardShouldBe21x10ByDefault() {
		assertEquals(10, board.getCells().length);
		assertEquals(21, board.getCells()[0].length);
	}
	
	@Test
	public void boardShouldBeInitialised() {
		assertEquals(false, board.getCell(4, 2));
		assertEquals(false, board.getCell(6, 9));
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void shouldThrowOutOfBoundExceptionWhenGettingCells(){
		board.getCell(160, 10);
	}

}
