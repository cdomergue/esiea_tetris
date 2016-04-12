package com.domergue.bastide.jTetris.components;

import com.domergue.bastide.jTetris.components.Cell;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScoreTest {
	
	private Score score;

	@Before
	public void beforeTests(){
		score = Score.getInstance();
	}

	@Test
	public void testIfCanSetScore(){
		score.setCurrentScore(42);
		assertEquals(42, score.getCurrentScore());
	}

	@Test
	public void testAddScore(){
		score.setCurrentScore(0);
		score.add(42);
		assertEquals(42, score.getCurrentScore());
	}

}
