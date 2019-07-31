//package Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import api.Constant;
import gameengine.Controller;
import gameui.GridMap;
import gameui.GridMap.GridBox;
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
            assertTrue("Is the grid map Correct?", isGridMapCorrect());
	}
        private boolean isGridMapCorrect() {
            Controller injectedController = new Controller();
            return injectedController.oponent.mapModel[0].length == (injectedController.player.mapModel[0].length);
	}
        
	@Test
	public void testTryToPlaceShipOnMap() {
            assertTrue("Is ship placed on Map?", isShipPlacedOnMap());
	}

	private boolean isShipPlacedOnMap() {
            Controller injectedController = new Controller();
            Ship ship = new Ship(1, true);
            int x = 5;
            int y = 4;
            injectedController.playerView.TryToPlaceShipOnMap(ship, y, x);
            /*
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
             */
            return injectedController.player.mapModel[y][x].space().equals("s");
	}

	@Test
	public void testTryToPlaceShipOnModel() {
            assertTrue("Is ship placed on Model?", isShipPlacedOnModel());
	}
        private boolean isShipPlacedOnModel() {
            Controller injectedController = new Controller();
            Ship ship = new Ship(1, true);
            int x = 5;
            int y = 4;
            injectedController.playerView.TryToPlaceShipOnMap(ship, y, x);
            return injectedController.player.mapModel[y][x].space().equals("s");
	}
	@Test
	public void testApplyModelToView() {
            //System.out.println("TestResult14: " + isApplyModelToViewCorrect());
            assertTrue("Is applyModelToView method correct?", isApplyModelToViewCorrect());
	}
        private boolean isApplyModelToViewCorrect() {
            Controller injectedController = new Controller();
            Ship ship = new Ship(1, true);
            int x = 5;
            int y = 4;
            injectedController.playerView.TryToPlaceShipOnMap(ship, x, y);
            GridBox gridBoxInView = injectedController.playerView.getGridBoxByCoordinate(x, y);
            
            return gridBoxInView.shipInstance == injectedController.player.mapModel[x][y].ShipInstance;
	}
	//@Ignore
	@Test
	public void testGetGridBoxByCoordinate() {
            assertTrue("Is getGridBoxByCoordinate method correct?", isGetGridBoxByCoordinateCorrect());
	}
        private boolean isGetGridBoxByCoordinateCorrect() {
            Controller injectedController = new Controller();
            Ship ship = new Ship(1, true);
            int x = 5;
            int y = 4;
            injectedController.playerView.TryToPlaceShipOnMap(ship, x, y);
            GridBox gridBoxInView = injectedController.playerView.getGridBoxByCoordinate(5, 4);
            
            return gridBoxInView.shipInstance == injectedController.player.mapModel[x][y].ShipInstance;
	}
}
