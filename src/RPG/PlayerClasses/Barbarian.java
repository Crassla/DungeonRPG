/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.PlayerClasses;

/**
 *
 * @author Alex
 * 
 * Barbarian is one of the classes the player can chose
 */
public class Barbarian extends Player
{

    //instantiates the barbarian default class values using its super constructor
    public Barbarian()
    {
        super("Barbarian", 45, 45, 50, 10, 10, 1);
    }

    //instantiates the barbarian class from a previous save
    public Barbarian(int health, int maxHealth, int damage, int armourClass, int rollModifier, int currentRoomNum)
    {
        super("Barbarian", health, maxHealth, damage, armourClass, rollModifier, currentRoomNum);
    }
    
    //returns the class value
    @Override
    public int returnClass()
    {
        return 3;
    }

    //applies the barbarians skill
    @Override
    public void skill()
    {      
        this.setDamage(20);
        this.setUsedSkill(true);
    }
    
    @Override 
    public String skillString()
    {
        String output = "<html>";
        output +=("You feel a burst of rage. Your damage increases by 20 <br> </html>");
        return output;
    }

    //reverses the skill as the changes should not be permanent
    @Override
    public void reverseSkill()
    {
        this.setDamage(-20);
        System.out.println("You start to calm down, your damage reduces back to " + this.getDamage());
        this.setReversedSkill(true);
    }
}
