package Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import api.Constant;
import model.MapModel;
import model.Ship;

public class GridMapTest {

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

	@Test
	public void testGridMap() {
		fail("Not yet implemented");
	}

	@Test
	public void testTryToPlaceShipOnMap() {
		assertTrue("Is ship placed on Map?", isShipPlacedOnMap());
	}

	private boolean isShipPlacedOnMap() {
		Ship ship = new Ship(1, true);
		int length = ship.length;
		int x = 5;
		int y = 4;
		if (x > 0 && x < Constant.GRID_SIZE && y > 0 && y < Constant.GRID_SIZE) {
			if (y < y + length) {
				return false;
			} else {
				return true;
			}
		}
		if(ship.isVertical) {
			for(int i = 0; i < y ; i++) {
				MapModel[][] mapModel = new MapModel[4][4];
				mapModel[i][x].SetAShip(ship);
			}
			
			
		}
		return false;
	}

	@Test
	public void testTryToPlaceShipOnModel() {
		fail("Not yet implemented");
	}

	@Test
	public void testApplyModelToView() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testGetGridBoxByCoordinate() {
		fail("Not yet implemented");
	}

}
