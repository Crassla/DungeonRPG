/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.EnemyClasses;

import RPG.Interfaces.Nameable;

/**
 *
 * @author Alex
 *
 * Class which stores and controls access to all enemy stats
 */
public class Enemy implements Nameable
{

    //declaration of all enemy variables
    private String name;
    private int damage;
    private int health;
    private int armourClass;
    private int rollModifier;
    private EnemyType enemyType;

    //sets all of the base stats for the enemy class and modifies them based on the enemy type
    public Enemy(String name, EnemyType enemyType)
    {
        this.name = name;
        this.damage = 20;
        this.health = 40;
        this.armourClass = 20;
        this.rollModifier = 13;
        this.enemyType = enemyType;

        if (null != enemyType)
        {
            switch (enemyType)
            {
                case MINIBOSS:
                    this.damage += 10;
                    this.health += 10;
                    this.armourClass += 10;
                    this.rollModifier += 5;
                    break;
                case BOSS:
                    this.damage += 20;
                    this.health += 20;
                    this.armourClass += 20;
                    this.rollModifier += 8;
                    break;
                case BABY:
                    this.damage /= 2;
                    this.health /= 2;
                    this.armourClass /= 2;
                    this.rollModifier /= 2;
                    break;
            }
        }
    }

    //sets the enemy name
    @Override
    public void setName(String name)
    {
        this.name = name;
    }
    
    //gets the enemy name
    @Override
    public String getName()
    {
        return this.name;
    }

    //gets the enemys current health
    public int getHealth()
    {
        return this.health;
    }

    //sets the enemies health
    //if health is to be set to a negative value instead just set it to 0
    public void setHealth(int health)
    {
        if (this.health + health < 0)
        {
            this.health = 0;
        }
        else
        {
            this.health += health;
        }
    }

    //gets the enemies armour class
    public int getArmourClass()
    {
        return this.armourClass;
    }

    //get the enemies roll modifier
    public int getRollModifier()
    {
        return this.rollModifier;
    }

    //gets the enemies damage
    public int getDamage()
    {
        return this.damage;
    }

    //string to print out when in an encounter
    public String encounterStats()
    {
        String output = "";

        switch (enemyType)
        {
            case BABY:
                output += "Baby ";
                break;
            case NORMAL:
                output += "Normal ";
                break;
            case MINIBOSS:
                output += "MiniBoss ";
                break;
            case BOSS:
                output += "Boss ";
                break;
        }

        output += this.name + " with health: " + this.health + " ";

        return output;
    }

    //string to print out normally
    @Override
    public String toString()
    {
        return this.name;
    }
}
