/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.RunGame;

import RPG.FileIO.GameSave;
import RPG.GUI.GUI;
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
    public void newGame()
    {
    }

    //load the game into a previous save
    public void loadGame()
    {
    }
    
    public Game validateLoadInput(String text)
    {
        GameSave gameSave = new GameSave();
        Game game;
        
        game = gameSave.findSavedGame(text);
        
        return game;
    }
}
