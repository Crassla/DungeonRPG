/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.RunGame;

/**
 *
 * @author alex
 * 
 * This class prints out the game instructions
 */
public class Instructions
{

    //has a string to save the entire output
    private String output;
    
    //when created makes the string
    public Instructions()
    {
        output = "<html>";
        this.printGameDetails();
        this.printClassInformation();
        output += "</html>";
    }

    //prints the class information
    private void printClassInformation()
    {
        output += ("<br><br>=====================CLASS DETAILS====================<br>");
        output += ("There are 3 classes you can chose from:<br>");
        this.printPaladinStats();
        this.printWizardStats();
        this.printBarbarianStats();
    }

    //adds the wizard information
    private void printWizardStats()
    {
        output +=("<br><br>========================WIZARD======================<br>");
        output +=("Wizards have the following starting stats:<br>");
        output +=("Health: 40<br>");
        output +=("Damage: 30<br>");
        output +=("Armour Class: 16<br>");
        output +=("Roll Modifier: 10<br>");
        
        output +=("Once per encounter a Wizard can use their skill to gain +8 roll modifier.<br>");
    }

    //adds the paladin information
    private void printPaladinStats()
    {
        output +=("<br><br>========================PALADIN======================<br>");
        output += ("Paladins have the following starting stats:<br>");
        output += ("Health: 50<br>");
        output += ("Damage: 22<br>");
        output += ("Armour Class: 22<br>");
        output += ("Roll Modifier: 8<br>");
        
        output +=("Once per encounter a Paladin can use their skill to heal 20 health.<br>");
    }

    //adds the barbarian information
    private void printBarbarianStats()
    {
        output += ("<br><br>========================BARBARIAN======================<br>");
        output += ("Barbarians have the following starting stats: <br>");
        output += ("Health: 45<br>");
        output += ("Damage: 50<br>");
        output += ("Armour Class: 10<br>");
        output += ("Roll Modifier: 10<br>");
        
        output += ("Once per encounter a Barbarian can use their skill to gain +20 damage.<br>");
    }

    //adds all of the game details
    private void printGameDetails()
    {
        output += ("<br><br>=====================GAME DETAILS====================<br>");
        output += ("Dungeon RPG is a room based RPG where each room contains a reward<br>");
        output += ("and an enemy to fight.<br>");
        output += ("Use the command move to walk into the next room. Use the command<br>");
        output += ("exit to leave the game. You can save your current stats to use in the<br>");
        output += ("future.<br>");
        output += ("When you walk into a room an encounter will start. In each encounter<br>");
        output += ("there will be an enemy to defeat. Depending on your current room number<br>");
        output += ("the enemy will either be a Baby, a normal enemy, a miniboss or a boss.<br>");
        output += ("If the enemy is defeated you will gain randomized stats and heal 20 health.<br>");
        output += ("When in an encounter you can attack, block or use your class based skill.<br>");
        output += ("Attack deals your damage value to the enemy if you roll higher than the<br>");
        output += ("enemies AC (Armour Class).<br>");
        output += ("Block negates enemy attack damage by your AC. Block rolls over between<br>");
        output += ("turns and stacks.<br>");
        output += ("Skill can be used once every encounter and you can use other actions after<br>");
        output += ("using your skill. (E.g you can use your skill to increase your damage then<br>");
        output += ("attack). Your skill is based on your class.<br>");
        output += ("Each class and enemy has a different roll modifier. When either you or the<br>");
        output += ("enemy attacks a D20 is rolled. To succesfully attack the roll must be higher<br>");
        output += ("than their opponents AC<br>");
    }
    
    //returns output
    public String getOutput()
    {
        return this.output;
    }
}
