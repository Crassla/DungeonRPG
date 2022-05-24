/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author alex
 */
public class Controller
{

    private Model model;

    public Controller(Model model)
    {
        this.model = model;
    }

    public ActionListener newGameHandler()
    {
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                model.newGame();
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
                model.loadGame();
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
                model.loadGameScreen();
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
                model.createGameScreen();
            }
        };

        return listener;
    }
    
    public ActionListener moveHandler()
    {
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                model.createEncounterScreen();
            }
        };

        return listener;
    }
    
    public ActionListener attackHandler()
    {
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                model.attack();
            }
        };

        return listener;
    }
}
