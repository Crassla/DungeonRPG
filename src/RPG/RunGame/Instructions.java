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

    public void printInstructions()
    {
        this.printGameDetails();
        this.printClassInformation();
    }

    public void printClassInformation()
    {
        System.out.println("========================CLASS DETAILS=======================\n");
        System.out.println("There are 3 classes you can chose from:\n");
        this.printPaladinStats();
        this.printWizardStats();
        this.printBarbarianStats();
    }

    public void printWizardStats()
    {
        System.out.println("===========================WIZARD=========================\n");
        System.out.println("Wizards have the following starting stats:");
        System.out.println("Health: 40");
        System.out.println("Damage: 30");
        System.out.println("Armour Class: 16");
        System.out.println("Roll Modifier: 10\n");
        
        System.out.println("Once per encounter a Wizard can use their skill to gain +8 roll modifier.\n");
    }

    public void printPaladinStats()
    {
        System.out.println("===========================PALADIN=========================\n");
        System.out.println("Paladins have the following starting stats:");
        System.out.println("Health: 50");
        System.out.println("Damage: 22");
        System.out.println("Armour Class: 22");
        System.out.println("Roll Modifier: 8\n");
        
        System.out.println("Once per encounter a Paladin can use their skill to heal 20 health.\n");
    }

    public void printBarbarianStats()
    {
        System.out.println("===========================BARBARIAN=========================\n");
        System.out.println("Barbarians have the following starting stats:");
        System.out.println("Health: 45");
        System.out.println("Damage: 50");
        System.out.println("Armour Class: 10");
        System.out.println("Roll Modifier: 10\n");
        
        System.out.println("Once per encounter a Paladin can use their skill to heal 20 health.\n");
    }

    public void printGameDetails()
    {
        System.out.println("========================GAME DETAILS=======================\n");
        System.out.println("Dungeon RPG is a room based RPG where each room contains a reward");
        System.out.println("and an enemy to fight.\n");
        System.out.println("Use the command move to walk into the next room. Use the command");
        System.out.println("exit to leave the game. You can save your current stats to use in the");
        System.out.println("future.\n");
        System.out.println("When you walk into a room an encounter will start. In each encounter");
        System.out.println("there will be an enemy to defeat. Depending on your current room number");
        System.out.println("the enemy will either be a Baby, a normal enemy, a miniboss or a boss.");
        System.out.println("If the enemy is defeated you will gain randomized stats and heal 20 health.\n");
        System.out.println("When in an encounter you can attack, block or use your class based skill.\n");
        System.out.println("Attack deals your damage value to the enemy if you roll higher than the");
        System.out.println("enemies AC (Armour Class).\n");
        System.out.println("Block negates enemy attack damage by your AC. Block rolls over between");
        System.out.println("turns and stacks.\n");
        System.out.println("Skill can be used once every encounter and you can use other actions after");
        System.out.println("using your skill. (E.g you can use your skill to increase your damage then");
        System.out.println("attack). Your skill is based on your class.\n");
        System.out.println("Each class and enemy has a different roll modifier. When either you or the");
        System.out.println("enemy attacks a D20 is rolled. To succesfully attack the roll must be higher");
        System.out.println("than their opponents AC\n");
    }
}
