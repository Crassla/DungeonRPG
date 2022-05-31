/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package RPG.RewardClasses;

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
public class RewardTest
{
    private Reward reward;
    
    public RewardTest()
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
        reward = new Reward(RewardType.DAMAGE);
        reward.setReward(10);
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of toString method, of class Reward.
     */
    @Test
    public void testToString()
    {
        System.out.println("toString");
        String expResult = "10 damage\n";
        String result = reward.toString();
        assertEquals(expResult, result);
    }
    
}
