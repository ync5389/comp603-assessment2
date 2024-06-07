/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package TicTacToe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ync5389
 */
public class PlayerNameInputTest {
    
    public PlayerNameInputTest() {
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
     * Test of getPlayerName method, of class PlayerNameInput.
     */
    @Test
    public void testGetPlayerName() {
        System.out.println("getPlayerName");
        PlayerNameInput instance = new PlayerNameInput();
        String result = instance.getPlayerName();
        //        assertEquals(expResult, result);

        // Check if result is a String
            assertTrue(result instanceof String);
           
        // Check if result is not null and not empty
            assertNotNull(result);
            assertFalse(result.isEmpty());
    }
}
