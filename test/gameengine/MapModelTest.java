package gameengine;

import gameengine.Controller;
import gameui.GridMap;
import model.MapModel;
import model.Ship;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aiken
 */
public class MapModelTest {
    
    public MapModelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of IsSpaceEmpty method, of class MapModel.
     */
    @Test
    public void testIsSpaceEmpty() {
        Controller injectedController = new Controller();
        Ship ship = new Ship(1, true);
        int x = 5;
        int y = 4;
        injectedController.playerView.TryToPlaceShipOnMap(ship, x, y);
        GridMap.GridBox gridBoxInView = injectedController.playerView.getGridBoxByCoordinate(x, y);
        boolean expResult = false;
        boolean result = injectedController.player.mapModel[x][y].IsSpaceEmpty();
        //System.out.println("IsSpaceEmpty Result: " + result);
        assertEquals(expResult, result);
    }

    /**
     * Test of IsUncover method, of class MapModel.
     */
    @Test
    public void testIsUncover() {
        Controller injectedController = new Controller();
        Ship ship = new Ship(1, true);
        int x = 5;
        int y = 4;
        injectedController.playerView.TryToPlaceShipOnMap(ship, x, y);
        boolean expResult = false;
        boolean result = injectedController.player.mapModel[x][y].IsUncover();
        //System.out.println("IsUncover Result: " + result);
        assertEquals(expResult, result);
    }

    /**
     * Test of IsShipAlive method, of class MapModel.
     */
    @Test
    public void testIsShipAlive() {        
        Controller injectedController = new Controller();
        Ship ship = new Ship(1, true);
        int x = 5;
        int y = 4;
        injectedController.playerView.TryToPlaceShipOnMap(ship, x, y);
        boolean expResult = true;
        boolean result = injectedController.player.mapModel[x][y].IsShipAlive();
        //System.out.println("IsShipAlive Result: " + result);
        assertEquals(expResult, result);
    }

    /**
     * Test of IsShipFound method, of class MapModel.
     */
    @Test
    public void testIsShipFound() {      
        Controller injectedController = new Controller();
        Ship ship = new Ship(1, true);
        int x = 5;
        int y = 4;
        injectedController.playerView.TryToPlaceShipOnMap(ship, x, y);
        boolean expResult = false;
        boolean result = injectedController.player.mapModel[x][y].IsShipFound();
        //System.out.println("IsShipFound Result: " + result);
        assertEquals(expResult, result);
    }

    /**
     * Test of IsAMissedHit method, of class MapModel.
     */
    @Test
    public void testIsAMissedHit() {
        Controller injectedController = new Controller();
        Ship ship = new Ship(1, true);
        int x = 5;
        int y = 4;
        injectedController.playerView.TryToPlaceShipOnMap(ship, x, y);
        boolean expResult = false;
        boolean result = injectedController.player.mapModel[x][y].IsAMissedHit();
        //System.out.println("IsAMissedHit Result: " + result);
        assertEquals(expResult, result);
    }

    /**
     * Test of SetAShip method, of class MapModel.
     */
    @Test
    public void testSetAShip() {
        Controller injectedController = new Controller();
        Ship ship = new Ship(1, true);
        int x = 5;
        int y = 4;
        injectedController.playerView.TryToPlaceShipOnMap(ship, x, y);
        MapModel instance = new MapModel();
        instance.SetAShip(ship);
        boolean expResult = true;
        boolean result = instance.IsShipAlive();
        //System.out.println("IsSetAShip Result: " + result);
        assertEquals(expResult, result);
    }

    /**
     * Test of space method, of class MapModel.
     */
    @Test
    public void testSpace() {
        Controller injectedController = new Controller();
        Ship ship = new Ship(1, true);
        int x = 5;
        int y = 4;
        injectedController.playerView.TryToPlaceShipOnMap(ship, x, y);
        String expResult = "s";
        String result = injectedController.player.mapModel[x][y].space();
        //System.out.println("Space method Result: " + result);
        assertEquals(expResult, result);
    }

    /**
     * Test of uncover method, of class MapModel.
     */
    @Test
    public void testUncover() { 
        Controller injectedController = new Controller();
        Ship ship = new Ship(1, true);
        int x = 5;
        int y = 4;
        injectedController.playerView.TryToPlaceShipOnMap(ship, x, y);
        String expResult = " ";
        String result = injectedController.player.mapModel[x][y].uncover();
        //System.out.println("Uncover method Result: " + result);
        assertEquals(expResult, result);
    }
    
}
