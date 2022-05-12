/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.RewardClasses;

import RPG.PlayerClasses.Player;
import java.util.Random;

/**
 *
 * @author Alex
 * 
 * This class manages all of the reward data and methods
 */
public class Reward
{
    //reward variables
    private final int reward;
    private final RewardType rewardType;
    
    //instantiates a reward
    public Reward(RewardType rewardType)
    {
        Random rand = new Random();
        
        this.reward = rand.nextInt(10) + 1; //creates a random reward
        this.rewardType = rewardType; //sets the reward type
    }
    
    //applies the reward to the player using the players methods
    public void addReward(Player player)
    {
        switch(rewardType)
        {
            case HEALTH:
                player.setMaxHealth(reward);
                player.setHealth(reward);
                break;
            case DAMAGE:
                player.setDamage(reward);
                break;
            case ROLLMODIFIER:
                player.setRollModifier(reward);
                break;
            case ARMOURCLASS:
                player.setArmourClass(reward);
                break;
            default: 
                System.out.println("Add reward failed");
        }
    }
    
    //prints out the reward nicely to the user
    @Override
    public String toString()
    {
        String output = "" + this.reward;
        
        switch (rewardType)
        {
            case HEALTH:
                output += " health\n";
                break;
            case DAMAGE:
                output += " damage\n";
                break;
            case ROLLMODIFIER:
                output += " roll modifier\n";
                break;
            case ARMOURCLASS:
                output += " armour class\n";
                break;
        }
        
        return output;
    }
}
