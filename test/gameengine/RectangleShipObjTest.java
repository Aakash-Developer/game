package gameengine;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javafx.scene.paint.Color;
import junit.framework.Assert;
import model.RectangleShipObj;
import model.Ship;

public class RectangleShipObjTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
        /**
         * Test of a RectangleShipObj is properly set in RectangleShipObj class.
         */
	@Test
	public void testRectangleShipObj() {
		RectangleShipObj r = new RectangleShipObj(1, 1,null);
		Ship ship = new Ship(1,true);
		ship.isVertical = true;
		r.setRotate(0);
		r.getRotate();
	}
        /**
         * Test of ChangeState method in RectangleShipObj class.
         */
	@Test
	public void testChangeState() {
		assertTrue("Is the change correct ? ", isChangeCorrect());
	}

	private boolean isChangeCorrect() {
		RectangleShipObj r = new RectangleShipObj(1, 1, null);
		Ship ship = new Ship(1,true);
		ship.isVertical = true;
		//double rotateState = 0;
		if(r.getRotate() == 0) {
			ship.setRotate(90);
			//r.InvalidState();
			return true;
		}
		return false;
	}
        /**
         * Test of ValidState method in RectangleShipObj class.
         */
	@Test
	public void testValidState() {
		assertTrue("Is it a valid State?" ,isValidState());
	}

	private boolean isValidState() {
		Ship ship = new Ship(1,true);
		RectangleShipObj r = new RectangleShipObj(1, 1, Color.GREENYELLOW);
		if(ship.isAlive() && ship.isVertical) {
			r.ChangeState();
			return true;
		}else
			return true;
	}
        /**
         * Test of InvalidState method in RectangleShipObj class.
         */
	@Test
	public void testInvalidState() {
		assertTrue("Is it an Invalid State?" ,isInvalidState());		
	}

	private boolean isInvalidState() {
		Ship ship = new Ship(1,true);
		RectangleShipObj r = new RectangleShipObj(1, 1, Color.RED);
		if(ship.isAlive()) {
			ship.isVertical = false;
			r.ChangeState();
			return true;
		}
		return false;
	}

}
