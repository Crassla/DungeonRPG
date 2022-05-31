/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package RPG.PlayerClasses;

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
public class BarbarianTest
{
    private Player player;
    
    public BarbarianTest()
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
        player = new Barbarian();
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of returnClass method, of class Barbarian.
     */
    @Test
    public void testReturnClass()
    {
        System.out.println("returnClass");
        int expResult = 3;
        int result = player.returnClass();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetHealth()
    {
        System.out.println("getHealth");
        int expResult = 45;
        int result = player.getHealth();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetMaxHealth()
    {
        System.out.println("getMaxHealth");
        int expResult = 45;
        int result = player.getMaxHealth();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetDamage()
    {
        System.out.println("getDamage");
        int expResult = 50;
        int result = player.getDamage();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetArmourClass()
    {
        System.out.println("getArmourClass");
        int expResult = 10;
        int result = player.getArmourClass();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetRollModifer()
    {
        System.out.println("getRollModifer");
        int expResult = 10;
        int result = player.getRollModifier();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetRoom()
    {
        System.out.println("getRoom");
        int expResult = 1;
        int result = player.getRoomNum();
        assertEquals(expResult, result);
    }
}
