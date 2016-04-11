package test.java.domergue.bastide.jTetris.components;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.java.domergue.bastide.jTetris.components.Board;
import main.java.domergue.bastide.jTetris.components.tetriminos.Tetrimino;
import main.java.domergue.bastide.jTetris.components.tetriminos.TetriminoBuilder;
import main.java.domergue.bastide.jTetris.components.tetriminos.TetriminoPieces;
import main.java.domergue.bastide.jTetris.components.tetriminos.TetriminoRotater;

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
	public void boardShouldBe10x21ByDefault() {
		assertEquals(21, board.getCells().length);
		assertEquals(10, board.getCells()[0].length);
	}
	
	@Test
	public void boardShouldBeInitialised() {
		assertEquals(0, board.getCell(4, 2).getTetriminoId());
		assertEquals(0, board.getCell(6, 9).getTetriminoId());
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void shouldThrowOutOfBoundExceptionWhenGettingCells(){
		board.getCell(160, 10);
	}
	
	@Test
	public void shouldHaveATetrimino() {
		TetriminoBuilder tBuild = TetriminoBuilder.getInstance();
		Tetrimino tetrimino = tBuild.build(TetriminoPieces.PIECE_S);
		board.addNewMovingTetrimino(tetrimino);

		assertEquals(tetrimino.getTetriminoId(), board.getCell(0, 3).getTetriminoId());
		assertEquals(tetrimino.getTetriminoId(), board.getCell(0, 4).getTetriminoId());
		assertEquals(tetrimino.getTetriminoId(), board.getCell(2, 5).getTetriminoId());
		assertEquals(tetrimino.getTetriminoId(), board.getCell(3, 6).getTetriminoId());
			
	}
	
	@Test
	public void moveTetriminoDown() {
		TetriminoBuilder tBuild = TetriminoBuilder.getInstance();
		Tetrimino tetrimino = tBuild.build(TetriminoPieces.PIECE_S);
		board.addNewMovingTetrimino(tetrimino);
		board.moveMovingTetriminoDown();
		assertEquals(tetrimino.getTetriminoId(), board.getCell(1, 3).getTetriminoId());
		assertEquals(tetrimino.getTetriminoId(), board.getCell(1, 4).getTetriminoId());
		assertEquals(tetrimino.getTetriminoId(), board.getCell(3, 5).getTetriminoId());
		assertEquals(tetrimino.getTetriminoId(), board.getCell(4, 6).getTetriminoId());
	}
	
	@Test
	public void moveTetriminoLeft() {
		TetriminoBuilder tBuild = TetriminoBuilder.getInstance();
		Tetrimino tetrimino = tBuild.build(TetriminoPieces.PIECE_S);
		board.addNewMovingTetrimino(tetrimino);
		board.moveMovingTetriminoLeft();
		assertEquals(tetrimino.getTetriminoId(), board.getCell(0, 2).getTetriminoId());
		assertEquals(tetrimino.getTetriminoId(), board.getCell(0, 3).getTetriminoId());
		assertEquals(tetrimino.getTetriminoId(), board.getCell(2, 4).getTetriminoId());
		assertEquals(tetrimino.getTetriminoId(), board.getCell(3, 5).getTetriminoId());
	}
	
	@Test
	public void moveTetriminoRight() {
		TetriminoBuilder tBuild = TetriminoBuilder.getInstance();
		Tetrimino tetrimino = tBuild.build(TetriminoPieces.PIECE_S);
		board.addNewMovingTetrimino(tetrimino);
		board.moveMovingTetriminoRight();
		assertEquals(tetrimino.getTetriminoId(), board.getCell(0, 4).getTetriminoId());
		assertEquals(tetrimino.getTetriminoId(), board.getCell(0, 5).getTetriminoId());
		assertEquals(tetrimino.getTetriminoId(), board.getCell(2, 6).getTetriminoId());
		assertEquals(tetrimino.getTetriminoId(), board.getCell(3, 7).getTetriminoId());
	}

}
