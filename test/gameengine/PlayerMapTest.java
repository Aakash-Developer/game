package gameengine;

import gameengine.Controller;
import gameengine.OponentMap;
import gameengine.PlayerMap;
import model.Ship;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PlayerMapTest {

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
         * Test of properly setting of a player map  in PlayerMap class
         */
	@Test
	public void testPlayerMap() {
	    assertTrue("Is the player map Correct?", isPlayerMapCorrect());		
	}
        private boolean isPlayerMapCorrect() {
            Controller injectedController = new Controller();
            PlayerMap playerMap = new PlayerMap(injectedController);
            return playerMap.mapModel[0].length == (injectedController.oponent.mapModel[0].length);
	}
        /**
         * Test of calculation of the computer winner in PlayerMap class
         */
        @Test
	public void testShipNumForComputerWinner() {
	    Controller injectedController = new Controller();
            Ship ship = new Ship(1, true);
            int x = 5;
            int y = 4;
            injectedController.playerView.TryToPlaceShipOnMap(ship, x, y);
            for(int i=0;i<5;i++){
                injectedController.player.mapView.shipsNumOnMap--;
            }
            int expShipNum = 0;
            int resultShipNum = injectedController.player.mapView.shipsNumOnMap;
            System.out.println("resultShipNum: " + resultShipNum);
            assertEquals(expShipNum, resultShipNum);		
	}
}
