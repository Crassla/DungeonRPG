/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package RPG.GameSetup;

import RPG.PlayerClasses.Paladin;
import RPG.PlayerClasses.Player;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alex
 */
public class GameTest
{

    private Game game;
    private Player player;

    public GameTest()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp()
    {
        player = new Paladin(50, 50, 22, 22, 8, 1);
        game = new Game(10, player, 0);
    }

    @After
    public void tearDown()
    {
    }

    /**
     * Test of getMapSize method, of class Game.
     */
    @Test
    public void testGetMapSize()
    {
        System.out.println("getMapSize");
        int expResult = 10;
        int result = game.getMapSize();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMapLength method, of class Game.
     */
    @Test
    public void testGetMapLength()
    {
        System.out.println("getMapLength");
        int expResult = 10;
        int result = game.getMapLength();
        assertEquals(expResult, result);
    }

    /**
     * Test of mapEmpty method, of class Game.
     */
    @Test
    public void testMapEmpty()
    {
        System.out.println("mapEmpty");
        boolean expResult = false;
        boolean result = game.mapEmpty();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPlayer method, of class Game.
     */
    @Test
    public void testGetPlayer()
    {
        System.out.println("getPlayer");
        Player expResult = player;
        Player result = game.getPlayer();
        assertEquals(expResult, result);
    }

    /**
     * Test of nextRoom method, of class Game.
     */
    @Test
    public void testNextRoom()
    {
        System.out.println("nextRoom");
        boolean expResult = false;
        boolean result = game.nextRoom();
        assertEquals(expResult, result);
    }
}
