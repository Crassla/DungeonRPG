/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.GameSetup;

import RPG.Interfaces.Nameable;
import RPG.RewardClasses.RewardType;
import RPG.RewardClasses.Reward;
import RPG.EnemyClasses.EnemyType;
import RPG.EnemyClasses.Enemy;
import java.util.Random;

/**
 *
 * @author Alex
 * 
 * This class stores the data for the enemy, reward and name of each room
 */
public class Room implements Nameable
{
    //variables for the room
    private Enemy enemy;
    private final Reward reward;
    private String name;
    
    //instantiates a room object
    public Room(String name, EnemyType enemyType, String enemyName)
    {
        Random rand = new Random();
        this.name = name;
       
        RewardType rewardType;
        
        this.enemy = new Enemy(enemyName, enemyType); //adds enemy to the room
        
        int randomReward = rand.nextInt(4);
        
        switch(randomReward) //generates a random reward for the room
        {
            case 0:
                rewardType = RewardType.ROLLMODIFIER;
                break;
            case 1:
                rewardType = RewardType.ARMOURCLASS;
                break;
            case 2:
                rewardType = RewardType.HEALTH;
                break;
            case 3:
                rewardType = RewardType.DAMAGE;
                break;
            default:
                rewardType = null;
                
        }
        
        this.reward = new Reward(rewardType); //sets the reward
    }
    
    //sets the name
    @Override
    public void setName(String name)
    {
        this.name = name;
    }
    
    //gets the name
    @Override
    public String getName()
    {
        return this.name;
    }
    
    //returns the enemy
    public Enemy getEnemy()
    {
        return enemy;
    }
    
    //returns the reward
    public Reward getReward()
    {
        return reward;
    }
    
    //prints out the room in a nice way for the user
    @Override
    public String toString()
    {
        String output = "";
        
        output += "<html>You walk into " + this.name + "<br>";
        output += "In the middle of the room you see a potion that will add " + this.reward + "<br><br>";
        
        output += "Suddenly a " + enemy.encounterStats() + "appears<br></html>";
        
        return output;
    }
}
