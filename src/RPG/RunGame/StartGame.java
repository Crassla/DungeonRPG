/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.RunGame;

import RPG.FileIO.GameSave;
import RPG.GameSetup.Game;

/**
 *
 * @author Alex
 *
 * This class controls the start of the game
 */
public class StartGame
{


    public StartGame()
    {
    }

    //creates a new game object for the user
    public Game newGame(int numRooms, int playerClass, String playerName)
    {
        
        if (playerClass != -1 || !playerName.contains(":"))
        {
            Game game = new Game(numRooms, playerClass);
            if (game != null)
            {
                game.getPlayer().setName(playerName);
            }
            
            return game;
        }
        
        return null;
    }

    //load the game into a previous save
    public Game loadGame(String text)
    {
        return validateLoadInput(text);
    }
    
    private Game validateLoadInput(String text)
    {
        GameSave gameSave = new GameSave();
        Game game;
        
        game = gameSave.findSavedGame(text);
        
        return game;
    }
}
