/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.GUI;

import RPG.GameSetup.Game;
import RPG.RunGame.StartGame;

/**
 *
 * @author alex
 */
public class Model
{

    private View view;
    private StartGame startGame;

    public Model()
    {
        startGame = new StartGame();
    }

    public void setView(View view)
    {
        this.view = view;
    }

    public void newGame()
    {
        view.setNewGameScreen();
    }

    public void loadGame()
    {
        view.setLoadScreen();
    }

    public void createGameScreen()
    {
        
    }
    
    public void loadGameScreen()
    {
        Game game = startGame.validateLoadInput(view.getLoadTextField());
        if (game == null)
        {
            view.updateErrorLabel("Error: No save by the name " + view.getLoadTextField() + " found!");
        }
        else
        {
            view.setGameScreen();
        }
    }
    
    
}
