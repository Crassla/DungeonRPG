/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package RPG.EnemyClasses;

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
public class EnemyTest
{
    private Enemy enemy;
    
    public EnemyTest()
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
        enemy = new Enemy("Test", EnemyType.MINIBOSS);
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of getName method, of class Enemy.
     */
    @Test
    public void testGetName()
    {
        System.out.println("getName");
        String expResult = "Test";
        String result = enemy.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHealth method, of class Enemy.
     */
    @Test
    public void testGetHealth()
    {
        System.out.println("getHealth");
        int expResult = 50;
        int result = enemy.getHealth();
        assertEquals(expResult, result);
    }

    /**
     * Test of getArmourClass method, of class Enemy.
     */
    @Test
    public void testGetArmourClass()
    {
        System.out.println("getArmourClass");
        int expResult = 30;
        int result = enemy.getArmourClass();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRollModifier method, of class Enemy.
     */
    @Test
    public void testGetRollModifier()
    {
        System.out.println("getRollModifier");
        int expResult = 18;
        int result = enemy.getRollModifier();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDamage method, of class Enemy.
     */
    @Test
    public void testGetDamage()
    {
        System.out.println("getDamage");
        int expResult = 30;
        int result = enemy.getDamage();
        assertEquals(expResult, result);
    }
}
