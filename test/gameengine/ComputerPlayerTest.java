/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameengine;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.Tuple;

/**
 *
 * @author z_angelu
 */
public class ComputerPlayerTest {
    
    public ComputerPlayerTest() {
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
     * Test of getNextComputerMove method, of class ComputerPlayer.
     */
    @Test
    public void testGetNextComputerMove() {
        System.out.println("getNextComputerMove");
        ComputerPlayer instance = null;
        Tuple expResult = null;
        Tuple result = instance.getNextComputerMove();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
