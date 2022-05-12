/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.RunGame;

import RPG.UserInput.ParseInput;
import RPG.EnemyClasses.Enemy;
import RPG.PlayerClasses.Player;
import java.util.Random;

/**
 *
 * @author alex
 * 
 * This class controls all of the methods and data for each encounter
 */
public class Encounter
{

    //variables for the rancounter
    Random rand;
    private final Player player;
    private final Enemy enemy;
    private int block;

    //instantiates a new encounter using player and enemy objects
    public Encounter(Player player, Enemy enemy)
    {
        this.player = player;
        this.enemy = enemy;
        this.block = 0;
        rand = new Random();
    }

    //returns the player in this encounter
    public Player getPlayer()
    {
        return player;
    }

    //returns the enemy in this encounter
    public Enemy getEnemy()
    {
        return enemy;
    }

    //helper method that rolls a d20
    private int rollD20()
    {
        return rand.nextInt(20) + 1 + player.getRollModifier();
    }

    //if the player chooses to attack this method runs
    //the enemies stats isn't mentioned as normally in dnd and video games you do not get
    //to see the enemies stats
    public void attack()
    {
        int attack = this.rollD20();
        System.out.println("You roll a " + attack);
        if (attack > enemy.getArmourClass()) //checks that the attack is higher than the enemies armour class
        {
            System.out.println("This hits!");
            enemy.setHealth(player.getDamage() * -1); //damages the enemy (-1 as the health is added to the enemy)
        }
        else
        {
            System.out.println("That doesn't hit."); //outputs if the roll doesn't hit
        }
    }

    //if the player chooses to block this method runs
    public void block()
    {
        block += player.getArmourClass(); //it adds armour class to block
        System.out.println("You are blocking for " + player.getArmourClass());
    }

    //if the player chooses to use their skill this method runs
    public void skill()
    {
        if (!player.getUsedSkill()) //checks if the player has used their skill before
        {
            //it sets the used skill to true as you can only use your skill once per encounter
            player.setUsedSkill(true);
            player.skill(); //runs the players skill
        }
        else
        {
            System.out.println("You have already used your skill this encounter");
        }
    }

    //this code runs the enemys turn
    public void enemyTurn()
    {
        int attack;

        if (enemy.getHealth() <= 0) //firstly checks if the enemy is dead as the player goes first
        {
            System.out.println(enemy + " dies");
        }
        else
        {
            System.out.println(enemy + " attacks");
            attack = rand.nextInt(20) + 1 + enemy.getRollModifier();
            System.out.println(enemy + " rolls a " + attack);

            if (attack > player.getArmourClass()) //checks that enemies attack is higher than the armour class
            {
                System.out.println("It Hits!");
                int damage = ((enemy.getDamage() - block) * -1); //does damage - block to the character
                if (damage > 0)
                {
                    damage = 0;
                }
                player.setHealth(damage);
                
                if (block - enemy.getDamage() < 0) // takes away the block by the enemies damage if it would be negative instead set it to 0
                {
                    block = 0;
                }
                else
                {
                    block = block - enemy.getDamage();// takes away the block by the enemies damage
                }
            }
            else
            {
                System.out.println("It Missed!");
            }

            System.out.println(enemy + " has " + enemy.getHealth() + " health left."); //output enemies health
            System.out.println(player.encounterToString()); 
        }
    }

    //this is the main method in control off the encounter
    public boolean runEncounter()
    {
        do
        {
            ParseInput.parseEncounterCommandInput(this); //get the encounter command
            this.enemyTurn();//run the enemies turn

            if (player.getUsedSkill() && !player.getReversedSkill()) //if the player has just used the skill set it to false
            {
                player.reverseSkill();
            }
        }
        while (enemy.getHealth() > 0 && player.getHealth() > 0); //if the enemy of the player dies end the encounter

        return this.endEncounter(); //runs the end of the encounter
    }

    //runs the end of the encounter
    //if the player is killed returns false otherwise returns true
    public boolean endEncounter()
    {
        if (player.getHealth() <= 0)
        {
            System.out.println("Oh no you have been killed by the " + enemy);
            System.out.println("Your final stats were:");
            System.out.println(player);
            return false;
        }
        else
        {
            player.setUsedSkill(false);
            player.setReversedSkill(false);
            player.incrementRoomNumber();
            applyReward();
            return true;
        }
    }

    //applies the reward to the player
    public void applyReward()
    {
        System.out.println("You win against " + player.getRoom().getEnemy());
        System.out.println("You won " + player.getRoom().getReward());
        System.out.println("You rest and regain 20 health");
        player.setHealth(20);
        player.getRoom().getReward().addReward(player);
        System.out.println("Your current stats are: ");
        System.out.println(player);
    }
}
