/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.PlayerClasses;

/**
 *
 * @author Alex
 *
 * Paladin is one of the classes the player can chose
 */
public class Paladin extends Player
{

    //instantiates the paladin default class values using its super constructor
    public Paladin()
    {
        super("Paladin", 50, 50, 22, 22, 8, 1);
    }

     //instantiates the paladin class from a previous save
    public Paladin(int health, int maxHealth, int damage, int armourClass, int rollModifier, int currentRoomNum)
    {
        super("Paladin", health, maxHealth, damage, armourClass, rollModifier, currentRoomNum);
    }

    //returns this classes value
    @Override
    public int returnClass()
    {
        return 1;
    }

    //runs the paladins skill which is healing 20 health
    @Override
    public void skill()
    {
        System.out.println("You feel a Gods blessing fall upon you. You heal 20 health");
        this.setHealth(20);
        this.setUsedSkill(true);
    }

    //no need to reverse the skill for paladin 
    @Override
    public void reverseSkill()
    {
        this.setReversedSkill(true);
    }
}
