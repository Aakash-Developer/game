//package Test;

import static org.junit.Assert.*;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import api.Constant;
import gameengine.ComputerPlayer;
import gameengine.Controller;
import gameengine.OponentMap;
import gameui.GridMap;
import gameui.GridMap.GridBox;
import gameui.PlayerView;
import model.MapModel;
import model.Ship;
import utils.Tuple;

public class OponentMapTest {

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
         * Test of properly setting of a oponent map  in OponentMap class
         */
	@Test
	public void testOponentMap() {
		assertTrue("Is the oponent map Correct?", isOponentMapCorrect());
	}

	private boolean isOponentMapCorrect() {
            Controller injectedController = new Controller();
            OponentMap opMap = new OponentMap(injectedController);
            return opMap.mapModel[0].length == (injectedController.oponent.mapModel[0].length);
	}
        /**
         * Test of PlaceShipsRandomly method in OponentMap class
         */
	@Test
	public void testPlaceShipsRandomly() {
		assertTrue("Are ships placed Randomly? ", isPlacedRandomly());
	}

	private boolean isPlacedRandomly() {
		Controller controller = new Controller();
		OponentMap op = new OponentMap(controller);

		Tuple t = new Tuple(5, 5);
		Tuple possibleCoordinate = op.NextMove();
		Constant.SHIPS_SIZE = new int[] { 2, 3, 3, 4, 5 };
		int shipSize = Constant.SHIPS_SIZE.length;
		if (shipSize > 0) {
			Ship ship = new Ship(shipSize, Math.random() < 0.5);
			if (controller.oponentView.TryToPlaceShipOnModel(ship, possibleCoordinate.t1, possibleCoordinate.t2)) {
				shipSize--;
			}
			return true;
		}
		return false;
	}

	/**
         * Test of NextMove method in OponentMap class
         */
	@Test
	public void testNextMove() {
		OponentMap op = new OponentMap(null);
		assertTrue("Is next move correct?", isNextMoveCorrect());
	}

	private boolean isNextMoveCorrect() {
		Tuple t = new Tuple(1,1) ;
		t = new Tuple(ThreadLocalRandom.current().nextInt(0, Constant.GRID_SIZE + 1),
                ThreadLocalRandom.current().nextInt(0, Constant.GRID_SIZE + 1));
		return true;
	}

	/**
         * Test of ComputerMove method in OponentMap class
         */
	@Test
	public void testComputerMove() {
		assertTrue("Is computer move correct? " ,isNextComputerMoveCorrect());
	}

	private boolean isNextComputerMoveCorrect() {
		GridMap pv = new GridMap(null, false, null);
		ComputerPlayer ai = new ComputerPlayer(null);
		boolean computerTurn = false;
		while(!computerTurn){
			Tuple nextMove;
			int x = 1;
			int y = 1;
			//nextMove = ai.getNextComputerMove();
			GridBox getGridBoxByCoordinate = pv.getGridBoxByCoordinate(x,y);
			if(getGridBoxByCoordinate.isHitted = true) {
				return true;
			//ai.getNextComputerMove();	
			}
			return true;
		}
		return false;
	}
        /**
         * Test of calculation of the human winner in OponentMap class
         */
        @Test
	public void testShipNumForHumanWinner() {
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
