/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.RunGame;

import RPG.EnemyClasses.Enemy;
import RPG.GUI.View;
import RPG.PlayerClasses.Player;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

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
    private final View view;
    private int block;

    //instantiates a new encounter using player and enemy objects
    public Encounter(Player player, Enemy enemy, View view)
    {
        this.player = player;
        this.enemy = enemy;
        this.block = 0;
        rand = new Random();
        this.view = view;
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
    public int attack()
    {
        int attack = this.rollD20();
        String output = "<html>";
        output += ("You roll a " + attack) + "<br>";
        if (attack > enemy.getArmourClass()) //checks that the attack is higher than the enemies armour class
        {
            output += ("This hits!") + "<br>";
            enemy.setHealth(player.getDamage() * -1); //damages the enemy (-1 as the health is added to the enemy)
        }
        else
        {
            output += ("That doesn't hit.") + "<br></html>"; //outputs if the roll doesn't hit
        }
        view.setEnemyHealth(enemy.getHealth() + "");
        view.updateMainLabel(output);
        Timer timer = new Timer(1000, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                enemyTurn();
            }
        });
        timer.setRepeats(false);
        timer.start();
        return -1;
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
    public int enemyTurn()
    {
        int attack;
        String output = "<html>";

        if (enemy.getHealth() <= 0) //firstly checks if the enemy is dead as the player goes first
        {
            output += (enemy + " dies") + "<br></html>";
            view.updateMainLabel(output);
            Timer timer = new Timer(1000, new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent event)
                {
                    endEncounter();
                }
            });
            timer.setRepeats(false);
            timer.start();
            return 0;
        }
        else
        {
            output += (enemy + " attacks") + "<br><br>";
            attack = rand.nextInt(20) + 1 + enemy.getRollModifier();
            output += (enemy + " rolls a " + attack) + "<br>";

            if (attack > player.getArmourClass()) //checks that enemies attack is higher than the armour class
            {
                output += ("It Hits!") + "<br>";
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
                output += ("It Missed!") + "<br>";
            }

            output += (enemy + " has " + enemy.getHealth() + " health left.") + "<br></html>"; //output enemies health
            view.setEncounterPlayerHealth(player.getHealth() + "");
            view.updateMainLabel(output);
            return -1;
        }
    }

    //this is the main method in control off the encounter
    public void runEncounter()
    {
        view.setEncounterScreen();
        view.setEnemyHealth(player.getHealth() + "");
        view.setEncounterPlayerHealth(enemy.getHealth() + "");
        view.updateMainLabel(player.getRoom().toString());
    }

    //runs the end of the encounter
    //if the player is killed returns 1 otherwise it returns 0
    public int endEncounter()
    {
        String output = "<html>";
        view.setGameScreen();

        if (player.getHealth() <= 0)
        {
            output += ("Oh no you have been killed by the " + enemy) + "<br>";
            output += ("Your final stats were:") + "<br>";
            output += (player) + "<br></html>";
            view.updateMainLabel(output);
            return 1;
        }
        else
        {
            player.setUsedSkill(false);
            player.setReversedSkill(false);
            player.incrementRoomNumber();
            try
            {
                Thread.sleep(2000);
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(Encounter.class.getName()).log(Level.SEVERE, null, ex);
            }
            return applyReward();
        }
    }

    //applies the reward to the player
    public int applyReward()
    {
        String output = "<html>";
        output += ("You win against " + player.getRoom().getEnemy()) + "<br>";
        output += ("You won " + player.getRoom().getReward()) + "<br>";
        output += ("You rest and regain 20 health") + "<br>";
        player.setHealth(20);
        player.getRoom().getReward().addReward(player);
        output += ("Your current stats are: ") + "<br>";
        output += (player) + "<br></html>";

        view.setGameScreen();
        view.setPlayerHealthLabel(player.getName(), player.getPlayerClass(), "" + player.getHealth());
        view.setPlayerDamageLabel("" + player.getDamage());
        view.setPlayerArmourLabel("" + player.getArmourClass());
        view.setPlayerRollLabel("" + player.getRollModifier());
        view.updateMainLabel(output);
        
        return 0;
    }
}
