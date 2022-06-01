/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.PlayerClasses;

import RPG.GameSetup.Room;
import RPG.Interfaces.Nameable;

/**
 *
 * @author Alex
 *
 * This is the super class for all player classes
 */
abstract public class Player implements Nameable
{

    //basic stats every class will have
    private final String className;
    private String name;
    private int maxHealth;
    private int damage;
    private int health;
    private int armourClass;
    private int rollModifier;
    private int currentRoomNum;
    private Room currentRoom;
    private boolean usedSkill;
    private boolean reversedSkill;

    //super constructor for player classes
    //is protected because a player should not be able to use this constructor
    //only its sub clasess
    protected Player(String className, int health, int maxHealth, int damage, int armourClass, int rollModifier, int currentRoomNum)
    {
        this.className = className;
        this.health = health;
        this.maxHealth = maxHealth;
        this.damage = damage;
        this.armourClass = armourClass;
        this.rollModifier = rollModifier;
        this.currentRoomNum = currentRoomNum;

        //sets used skill and reversed skill to false
        this.usedSkill = false;
        this.reversedSkill = false;
    }

    //skill which is class based
    abstract public void skill();
    
    //returns the skill as a string
    abstract public String skillString();

    //reverses class based skill
    abstract public void reverseSkill();

    //returns the class number
    abstract public int returnClass();

    //sets the players Room to a given room object
    public void setRoom(Room room)
    {
        this.currentRoom = room;
    }

    //returns the players room
    public Room getRoom()
    {
        return this.currentRoom;
    }
    
    //returns the players classname
    public String getPlayerClass()
    {
        return this.className;
    }

    //adds one to the count of how many rooms the player has gone through
    public void incrementRoomNumber()
    {
        this.currentRoomNum++;
    }

    //returns the number of rooms the player has gone through
    public int getRoomNum()
    {
        return this.currentRoomNum;
    }

    //sets the players name
    @Override
    public void setName(String name)
    {
        this.name = name;
    }

    //returns the players name
    @Override
    public String getName()
    {
        return this.name;
    }

    //sets the players max health
    public void setMaxHealth(int health)
    {
        this.maxHealth += health;
    }

    //returns the players max health
    public int getMaxHealth()
    {
        return this.maxHealth;
    }

    //sets the players health
    public void setHealth(int health)
    {
        if (this.health + health > maxHealth) //checks that the health is not going higher than max health if it is sets the health to max health
        {
            this.health = maxHealth;
        }
        else
        {
            if (this.health + health < 0) //if the health is going to be negative instead sets it to 0
            {
                this.health = 0;
            }
            else
            {
                this.health += health; //otherwise just adds the health
            }
        }
    }

    //returns the players current health
    public int getHealth()
    {
        return this.health;
    }

    //adds the damage reward to the players preexisting damage
    public void setDamage(int damage)
    {
        this.damage += damage;
    }

    //returns the players damage
    public int getDamage()
    {
        return damage;
    }

    //adds the armour class reward to the armour class of the player
    public void setArmourClass(int armourClass)
    {
        this.armourClass += armourClass;
    }

    //adds the roll modifier reward to the roll modifer of the player
    //checks that the roll modifer is not higher than 20 because if it is the player will hit every time
    public void setRollModifier(int rollModifier)
    {
        if (this.rollModifier + rollModifier > 20)
        {
            this.rollModifier = 20;
        }
        else
        {
            this.rollModifier += rollModifier;
        }
    }

    //returns the roll modifier of the player
    public int getRollModifier()
    {
        return rollModifier;
    }

    //returns the armour class of the player
    public int getArmourClass()
    {
        return armourClass;
    }

    //sets the used skill to a given boolean
    public void setUsedSkill(boolean usedSkill)
    {
        this.usedSkill = usedSkill;
    }

    //returns the used skills current value
    public boolean getUsedSkill()
    {
        return this.usedSkill;
    }

    //sets the reversed used skill to a given value
    public void setReversedSkill(boolean reversedSkill)
    {
        this.reversedSkill = reversedSkill;
    }

    //returns the reversed skills current value
    public boolean getReversedSkill()
    {
        return this.reversedSkill;
    }

    //string to print out if the player is in an encounter
    public String encounterToString()
    {
        return this.className + " " + this.name + " health: " + this.health + "\n";
    }

    //string to print out normally
    //shows the player their current stats
    @Override
    public String toString()
    {
        String output = "<html>";

        output += this.className + ": " + this.name + "<br>";
        output += "Health: " + this.health + "<br>";
        output += "Damage: " + this.damage + "<br>";
        output += "Armour Class: " + this.armourClass + "<br>";
        output += "rollModifier: " + this.rollModifier + "<br></html>";

        return output;
    }
}
