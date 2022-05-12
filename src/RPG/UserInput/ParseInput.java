/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.UserInput;

import RPG.FileIO.Logger;
import RPG.RunGame.Encounter;
import RPG.GameSetup.Game;
import java.util.Scanner;

/**
 *
 * @author Alex
 *
 * This class is a static class which is used throughout the program to get user
 * input from the cli
 */
public class ParseInput
{

    //create final variables
    private static final Scanner scan = new Scanner(System.in);
    private static final Logger logger = new Logger();

    //this method return int input
    public static int getIntInput(String question)
    {
        Scanner sc = new Scanner(System.in);
        boolean invalid = false;
        int output = 0;
        String input = "";

        do
        {
            try
            {
                input = sc.nextLine();
                output = Integer.parseInt(input);
                invalid = true;
            }
            catch (NumberFormatException e) //reask the question while they are inputting invalid ints
            {
                System.out.println(input + " is not a valid int");
                System.out.println(question);
            }
        }
        while (invalid == false);

        logger.logInput(input); //log the input

        return output;
    }

    //This function gets string input ensuring no spaces or : are inputted as that would break the code
    public static String getStringInput()
    {
        String input = "";

        do
        {
            input = scan.nextLine();
            
            if (input.equals("") || input.contains(":") || input.contains(" "))
            {
                System.out.print("Please don't input nothing, : or space: ");
            }
        }
        while (input.equals("") || input.contains(":") || input.contains(" "));

        logger.logInput(input); //logs the input

        return input.toLowerCase();
    }

    //Parses a command
    //Returns false if the player inputed exit
    public static boolean parseCommandInput(Game game)
    {
        boolean validInput = false;

        String input = "";

        while (!validInput) //while the user has not entered in a valid input run the command accosiated with the input
        {
            System.out.print("What do you want to do? (move/exit)> ");
            input = scan.nextLine().toLowerCase();
            switch (input)
            {
                case "move":
                    boolean end = game.nextRoom();
                    if (end)
                    {
                        return false;
                    }
                    System.out.println(game.getPlayer().getRoom());
                    validInput = true;
                    break;
                case "exit":
                    return false;
                case "attack":
                case "block":
                case "skill":
                    System.out.println("You look around but can't find any monsters to fight");
                    break;
                default:
                    System.out.println("Command not valid. Please enter move or exit\n"); //if the command is not valid input the command
                    break;
            }

            logger.logInput(input); //logs the users input
        }

        return true;
    }

    //Parses a command while in an encounter
    //Returns false if the player inputed exit
    public static void parseEncounterCommandInput(Encounter encounter)
    {
        boolean validInput = false;

        String input = "";

        while (!validInput) //while the user has not entered in a valid input run the command accosiated with the input
        {
            System.out.print("What do you want to do? (attack/block/skill)> ");
            input = scan.nextLine().toLowerCase();
            switch (input)
            {
                case "move":
                case "exit":
                    System.out.println("You try to run to the door but " + encounter.getEnemy() + " blocks your way");
                    break;
                case "attack":
                    encounter.attack();
                    validInput = true;
                    break;
                case "block":
                    validInput = true;
                    encounter.block();
                    break;
                case "skill":
                    encounter.skill();
                    break;
                default:
                    System.out.println("Command not valid. Please enter attack, block or skill\n"); //if the command is not valid input the command
                    break;
            }
            
            logger.logInput(input); //log the user input
        }
    }

    //gets yes or no input from user
    //returns true if the user inputted y
    public static boolean getYNInput(String question)
    {
        String input = "";
        do
        {
            System.out.println(question);
            input = scan.nextLine().toLowerCase();

            if (!input.equals("y") && !input.equals("n")) //checks if its yes or no
            {
                System.out.println("Please input either y (yes) or n (no)\n"); 
            }
        }
        while (!input.equals("y") && !input.equals("n"));

        logger.logInput(question + input); //logs the user input

        return (input.equals("y")); //returns true if the user inputted y
    }
}
