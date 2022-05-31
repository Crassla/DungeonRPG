/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.PlayerClasses;

/**
 *
 * @author Alex
 */
public class Wizard extends Player
{
    
    //instantiates the wizard default class values using its super constructor
    public Wizard()
    {
        super("Wizard", 40, 40, 30, 16, 10, 0);
    }
    
    //instantiates the wizard class from a previous save
    public Wizard(int health, int maxHealth, int damage, int armourClass, int rollModifier, int currentRoomNum)
    {
        super("Wizard", health, maxHealth, damage, armourClass, rollModifier, currentRoomNum);
    }
    
    //returns the wizards class value
    @Override
    public int returnClass()
    {
        return 2;
    }
    
    //runs the wizards skill
    @Override
    public void skill()
    {
        this.setRollModifier(8);
        this.setUsedSkill(true);
    }
    
    @Override 
    public String skillString()
    {
        String output = "<html>";
        output += ("You gain sudden inspiration. You get +8 to your next roll<br></html>");
        return output;
    }
    
    //reverses the wizards skill
    @Override
    public void reverseSkill()
    {
        System.out.println("Your inspiration leaves you");
        this.setRollModifier(-8);
        this.setReversedSkill(true);
    }
}
