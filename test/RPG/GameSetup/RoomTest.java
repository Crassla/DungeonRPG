/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package RPG.GameSetup;

import RPG.EnemyClasses.EnemyType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author GGPC
 */
public class RoomTest
{
    private Room room;
    
    public RoomTest()
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
        room = new Room("Potion", EnemyType.MINIBOSS, "Enemy");
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of getName method, of class Room.
     */
    @Test
    public void testGetName()
    {
        System.out.println("getName");
        String expResult = "Potion";
        String result = room.getName();
        assertEquals(expResult, result);
    }
}
