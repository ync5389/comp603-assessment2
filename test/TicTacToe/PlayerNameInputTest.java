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
        // Check if result is a String
            assertTrue(result instanceof String);
    }
    

    @Test
    public void testGetPlayerNameWithEmptyInput() {
        System.out.println("getPlayerNameWithEmptyInput");
        PlayerNameInput instance = new PlayerNameInput();
        instance.setPlayerName(""); // Assuming you have a setter method
        String result = instance.getPlayerName();

        // Check if result is an empty string
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetPlayerNameWithNullInput() {
        System.out.println("getPlayerNameWithNullInput");
        PlayerNameInput instance = new PlayerNameInput();
        instance.setPlayerName(null); // Assuming you have a setter method
        String result = instance.getPlayerName();

        // Check if result is null
        assertNull(result);
    }
    
    @Test
    public void testGetPlayerNameWithLongInput() {
        System.out.println("getPlayerNameWithLongInput");
        PlayerNameInput instance = new PlayerNameInput();
        String longName = "ThisIsAVeryLongNameThatExceedsTheMaximumLength";
        instance.setPlayerName(longName); // Assuming you have a setter method
        String result = instance.getPlayerName();

        // Check if result is equal to the input
        assertEquals(longName, result);
    }
    
    @Test
    public void testGetPlayerNameWithSpaceInput() {
        System.out.println("getPlayerNameWithSpaceInput");
        PlayerNameInput instance = new PlayerNameInput();
        instance.setPlayerName(" "); // Assuming you have a setter method
        String result = instance.getPlayerName();

        // Check if result is a space
        assertTrue(result.trim().isEmpty());
    }

}
