//package Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import api.Constant;
import api.Constant.GameType;
import api.Constant.Turn;
import gameengine.ComputerPlayer;
import gameengine.OponentMap;
import gameengine.PlayerMap;
import gameui.GridMap;
import gameui.PlayerView;
import model.MapModel;

public class ControllerTest {

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
	public void testController() {
		assertTrue("Is Controller working Correctly?", isControllerWorking());
	}

	private boolean isControllerWorking() {
		GameType gameType = Constant.GameType.TWO_PLAYER;
		PlayerView mainWindowView;
		boolean computerTurn = false;
		boolean playerTurn = false;
		OponentMap oponent = new OponentMap(null);
		PlayerMap player = new PlayerMap(null);
		GridMap oponentView;
		GridMap playerView;
		Turn turn = Constant.Turn.None;
		ComputerPlayer ai;
		MapModel[][] mapModel = null;
		if (gameType != null) {
			if (turn == Constant.Turn.Player1) {
				new PlayerMap(null);
				playerView = player.mapView;
				return true;
			} else if (turn == Constant.Turn.Player2) {
				new PlayerMap(null);
				oponentView = oponent.mapView;
			} else if (!computerTurn) {
			//	oponent.placeShipsRandomly();
				oponentView = oponent.mapView;
				return true;
			} else if (!playerTurn) {
				player.iniShipsToPlace = 5;
				playerView = player.mapView;
				return true;
			}
		}
		return false;
	}
}
