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
    
    public ActionListener blockHandler()
    {
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                model.block();
            }
        };

        return listener;
    }
    
    public ActionListener skillHandler()
    {
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                model.skill();
            }
        };

        return listener;
    }
    
    public ActionListener exitGameHandler()
    {
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                model.exitGame();
            }
        };

        return listener;
    }
    
    public ActionListener saveGameHandler()
    {
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                model.saveGame();
            }
        };

        return listener;
    }
    
    public ActionListener quitGameHandler()
    {
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                model.quitGame();
            }
        };

        return listener;
    }
    
    public ActionListener closeGameHandler()
    {
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                model.closeGame();
            }
        };

        return listener;
    }
    
    public ActionListener restartGameHandler()
    {
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                model.restartGame();
            }
        };

        return listener;
    }
    
    public ActionListener scoreBoardHandler()
    {
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                model.saveHighScore();
            }
        };

        return listener;
    }
    
    public ActionListener showScoreBoardHandler()
    {
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                model.showScoreboard();
            }
        };

        return listener;
    }
    
    public ActionListener saveLeaderBoardHandler()
    {
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                model.saveLeaderboard();
            }
        };

        return listener;
    }
    
    public ActionListener showLeaderBoardHandler()
    {
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                model.showLeaderboard();
            }
        };

        return listener;
    }
    
    public ActionListener showInstructionsHandler()
    {
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                model.getInstructions();
            }
        };

        return listener;
    }
}
