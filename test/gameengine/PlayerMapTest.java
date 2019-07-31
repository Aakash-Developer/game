//package Test;

import gameengine.Controller;
import gameengine.OponentMap;
import gameengine.PlayerMap;
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

	@Test
	public void testPlayerMap() {
	    assertTrue("Is the player map Correct?", isPlayerMapCorrect());		
	}
        private boolean isPlayerMapCorrect() {
            Controller injectedController = new Controller();
            PlayerMap playerMap = new PlayerMap(injectedController);
            return playerMap.mapModel[0].length == (injectedController.oponent.mapModel[0].length);
	}
}
