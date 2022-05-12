/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.GUI;

import RPG.GameSetup.Game;
import RPG.RunGame.StartGame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author alex
 */
public class Controller
{

    private GUI gui;

    public Controller(GUI gui)
    {
        this.gui = gui;
    }

    public ActionListener newGameHandler()
    {
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                StartGame startGame = new StartGame(gui);
                startGame.newGame();
            }
        };

        return listener;
    }

    public ActionListener loadGameHandler()
    {
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                StartGame startGame = new StartGame(gui);
                startGame.loadGame();
            }
        };

        return listener;
    }
    
    public ActionListener loadGameTextFieldHandler()
    {
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                StartGame startGame = new StartGame(gui);
                Game game = startGame.validateLoadInput(gui.getLoadTextField());
                if (game == null)
                {
                    gui.updateErrorLabel("Error: No save by the name " + gui.getLoadTextField() + " found!");
                }
                else
                {
                    gui.setGameScreen();
                }
            }
        };

        return listener;
    }
    
    public ActionListener createGameHandler()
    {
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                StartGame startGame = new StartGame(gui);
                startGame.loadGame();
            }
        };

        return listener;
    }
}
