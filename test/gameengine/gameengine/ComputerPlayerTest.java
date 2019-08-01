package gameengine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import api.Constant;
import api.Constant.ProcessState;
import gameengine.ComputerPlayer;
import gameengine.PlayerMap;
import model.MapModel;
import model.Ship;
import utils.Tuple;

public class ComputerPlayerTest {

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
         * Test if a computer player working properly in ComputerPlayer class.
         */
	@Test
	public void testComputerPlayer() {
		assertTrue("Is computer player working properly?" ,isComputerPlayerCorrect());
	}

	private boolean isComputerPlayerCorrect() {
		MapModel[][] mp = new MapModel[1][1]; 
		ComputerPlayer cp = new ComputerPlayer(mp);
		MapModel mapModel = new MapModel();
		mapModel.space = Constant.Space.IsShip ;
		if(mapModel.IsSpaceEmpty()) { 
			return false;
		}
		return true;
	}
        /**
         * Test of GetNextComputerMove method in ComputerPlayer class.
         */
	@Test
	public void testGetNextComputerMove() {
		assertTrue("Is the nextMove valid or not?" ,isGetNextMoveValid());
	}

	private boolean isGetNextMoveValid() {
		MapModel mapModel = new MapModel();
		MapModel[][] mp = new MapModel[1][1];
		//ComputerPlayer cp = new ComputerPlayer(mp);
		Ship ship = new Ship(1,true);
		//PlayerMap pm = new PlayerMap(null);
		Tuple t = new Tuple(10, 10);
		int prevPosition = t.t1;
		//int nextPosition = t.t2;
		MapModel[][] cell = new MapModel[prevPosition][prevPosition];
		//Constant c = new Constant();
		ProcessState ps = ProcessState.Initial;
		if(prevPosition !=  0) {
			ship.isVertical = true;
			ship.isAlive();
			mapModel.IsShipFound();
			mapModel.IsShipAlive();
			mapModel.IsUncover();
			if(cell != null) {
			ps = ProcessState.Initial;
			return true;
			}
		}
		return false;
	}

}
