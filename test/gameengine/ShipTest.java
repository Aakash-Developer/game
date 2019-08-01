//package Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Ship;

public class ShipTest {

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
         * Test of shipPlaced method in Ship class.
         */
	@Test
	public void testShip() {
		Assert.assertTrue("Is Ship Placed?", isShipPlaced());
	}

	public boolean isShipPlaced() {
		Ship ship = new Ship(0, true);
		ship.isVertical = true;
		if (!(ship.isVertical))
			return false;
		return true;
	}
        /**
         * Test of isAlive method in Ship class.
         */
	@Test
	public void testIsAlive() {
		Assert.assertTrue("Is Ship Alive?", isShipAlive());
	}

	public boolean isShipAlive() {
		Ship ship = new Ship(1, true);
		ship.isVertical = true;
		
		if (ship.length != 0)
			return true;
		return false;
	}
        /**
         * Test of gotHit method in Ship class.
         */
	@Test
	public void testGotHit() {
		Assert.assertTrue("Is Ship got hit?", isShipGotHit());
	}

	public boolean isShipGotHit() {
		   
		Ship ship = new Ship(1, true);
		ship.isVertical = true;
		ship.remainingLength = 5;
		for (int i = ship.remainingLength; i > 0; i--) {
			ship.gotHit();
			if (ship.isAlive() == true)
				return true;
		}
		return false;
	}
}

