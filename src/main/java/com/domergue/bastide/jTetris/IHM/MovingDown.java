package com.domergue.bastide.jTetris.IHM;

import java.util.Random;

import com.domergue.bastide.jTetris.components.Board;
import com.domergue.bastide.jTetris.components.tetriminos.Tetrimino;
import com.domergue.bastide.jTetris.components.tetriminos.TetriminoBuilder;
import com.domergue.bastide.jTetris.components.tetriminos.TetriminoPieces;
import com.domergue.bastide.jTetris.components.throwables.BottomTouched;

public class MovingDown extends Thread {
	
	private Board board;
	private Thread mainThread;
	private boolean notInterupted = true;

	public MovingDown(Board board, Thread thread){
		this.board = board;
		this.mainThread = thread;
	}
	
	public void run(){
		while(mainThread.isAlive() && notInterupted){
			try {
				board.moveMovingTetriminoDown();
				Thread.sleep(board.getSpeed());
			} catch (BottomTouched e) {
				TetriminoBuilder tBuild = TetriminoBuilder.getInstance();
				Tetrimino tetrimino = tBuild.pickRandom();
				board.addNewMovingTetrimino(tetrimino);
				board.setSpeed(board.getNormalSpeed());
			} catch (InterruptedException e) {
				continue;
			}
		}
		System.out.println("Fermeture de jTetris\nMerci d'avoir joué !");
	}
	
	
}
