/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.FileIO;

import RPG.GameSetup.Game;
import RPG.PlayerClasses.Barbarian;
import RPG.PlayerClasses.Paladin;
import RPG.PlayerClasses.Player;
import RPG.PlayerClasses.Wizard;
import RPG.UserInput.ParseInput;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author alex
 * 
 * This class controls the saving and loading of the game
 */
public class GameSave
{
    //string list containing each save, as duplicates are checked for in the add method set is not nessesary
    private List<String> gameList;
    //main file i/o componene to control saving and loading of specific files
    private final FileReaderWriter readerWriter = new FileReaderWriter();
    
    //instantiates the array which stores the list of saves
    public GameSave()
    {
        instantiateArray();
    }
   
    //saves player data in the format:
    //name:playerType:health:MAXHEALTH:damage:armourClass:rollModifier:currentRoomNum:totalRoomNum
    //returns null if playerName not found;
    public Game findSavedGame(String playerName)
    {
        playerName = playerName.toLowerCase();

        if (gameList == null || gameList.isEmpty()) //checks to see if the list is empty or null and if it is throws an error
        {
            return null;
        }
        else
        {
            for (String line : gameList) //iterates through gameList to see if any names match the one inputted
            {
                StringTokenizer st = new StringTokenizer(line, ":"); //tokenzies the string so it is in its proper format
                String name = st.nextToken();
                int playerType = Integer.parseInt(st.nextToken());
                int health = Integer.parseInt(st.nextToken());
                int maxhealth = Integer.parseInt(st.nextToken());
                int damage = Integer.parseInt(st.nextToken());
                int armourClass = Integer.parseInt(st.nextToken());
                int rollModifier = Integer.parseInt(st.nextToken());
                int currentRoomNum = Integer.parseInt(st.nextToken());
                int totalRoomNum = Integer.parseInt(st.nextToken());

                if (name.equals(playerName)) //if the player to load into is found it returns the loaded game
                {
                    gameList.remove(line);
                    writeSave();
                    return loadGame(name, playerType, health, maxhealth, damage, armourClass, rollModifier, currentRoomNum, totalRoomNum);
                }
            }
        }

        return null;
    }
    
    //loads the game after an existing player is found
    private Game loadGame(String name, int playerType, int health, int maxhealth, int damage, int armourClass, int rollModifier, int currentRoomNum, int totalRoomNum)
    {
        Player player;

        //first loads in the player
        switch (playerType)
        {
            case 1:
                player = new Paladin(health, maxhealth, damage, armourClass, rollModifier, currentRoomNum);
                break;
            case 2:
                player = new Wizard(health, maxhealth, damage, armourClass, rollModifier, currentRoomNum);
                break;
            case 3:
                player = new Barbarian(health, maxhealth, damage, armourClass, rollModifier, currentRoomNum);
                break;
            default:
                player = new Paladin(health, maxhealth, damage, armourClass, rollModifier, currentRoomNum);
                break;
        }
        
        //sets the players name
        player.setName(name);

        //the loads the game
        Game game = new Game(totalRoomNum, player, currentRoomNum);

        return game; //returns the game that has been loaded
    }

    //saves the game into a specific format
    //returns true if the game was succesfully saved
    public boolean saveGame(Game game)
    {
        //gets each part of the tokenized string
        String playerName = game.getPlayer().getName();
        int playerType = game.getPlayer().returnClass();
        int health = game.getPlayer().getHealth();
        int maxhealth = game.getPlayer().getMaxHealth();
        int damage = game.getPlayer().getDamage();
        int armourClass = game.getPlayer().getArmourClass();
        int rollModifier = game.getPlayer().getRollModifier();
        int currentRoomNum = game.getPlayer().getRoomNum();
        int totalRoomNum = game.getMapSize();

        //tokenizes the string
        String tokenizedString = playerName + ":" + playerType + ":" + health + ":" + maxhealth + ":" + damage + ":" + armourClass + ":" + rollModifier + ":" + currentRoomNum + ":" + totalRoomNum;

        if (gameList == null) //if gameList is not instantiated due to a missing file it instantiates gameList and returns true
        {
            gameList = new ArrayList<>();
            gameList.add(tokenizedString);
            writeSave();
            return true;
        }
        else
        {
            for (String line : gameList) //otherwise it loops through the gameList to see if the users name already exists
            {
                StringTokenizer st = new StringTokenizer(line, ":");
                String name = st.nextToken();

                if (name.equals(playerName))
                {
                    if (ParseInput.getYNInput("Save already exists. Do you want to overwrite the save (y/n): ")) //if it does exist it asks if they want to overwrite their save
                    {
                        gameList.remove(line);
                        gameList.add(tokenizedString);
                        writeSave();
                        return true; //if they do overwrite it it returns true otherwise it returns false
                    }
                    else
                    {
                        return false;
                    }
                }
            }
        }

        gameList.add(tokenizedString); //if gameList has been instantiated but is empty or they are trying to save a name which doesn't exist it saves and returns true
        writeSave();
        return true;
    }   
    
    private void instantiateArray()
    {
        this.gameList = readerWriter.readFile("./resources/GameSaves.txt"); //gets the list of saves from GameSaves
    }

    private void writeSave()
    {
        readerWriter.writeFile(gameList, "./resources/GameSaves.txt"); //overwrites GameSaves with the new list
    }
}
