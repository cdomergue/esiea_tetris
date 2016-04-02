package test.java.domergue.bastide.jTetris.components.tetriminos;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.java.domergue.bastide.jTetris.components.tetriminos.Shape;

public class ShapeTest {

	private Shape shape;
	
	@Before
	public void beforeTests(){
		this.shape = new Shape();
	}
	@Test
	public void testIfHasGoodUnits() {
		assertEquals(false, this.shape.getUnits(1,2));
	}
	
	@Test
	public void testSetUnits(){
		shape.setUnits(3, 2, true);
		assertEquals(true, this.shape.getUnits(3,2));
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testOutOfBound(){
		shape.setUnits(5, 23, true);
		
	}

}
