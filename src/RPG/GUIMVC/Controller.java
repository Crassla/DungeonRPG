/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.GUIMVC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author alex
 *
 * This class is the controller in the MVC design It controls what happens when
 * the player updates the view
 */
public class Controller
{

    //model is stored for reference so controller can act on it
    private final Model model;

    //instates a controller object
    public Controller(Model model)
    {
        this.model = model;
    }

    //the new game handler handles what happens when the user clicks on the new Game button
    //in the title menu
    public ActionListener newGameHandler()
    {
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                //the new game function is run
                model.newGame();
            }
        };

        return listener;
    }

    //the load game handler handles what happens when the user clicks on the load Game button
    //in the title menu
    public ActionListener loadGameHandler()
    {
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                //the load game function is run
                model.loadGame();
            }
        };

        return listener;
    }

    //the load game text field handler handles what happens when the user clicks on the load game button
    //in the load game
    public ActionListener loadGameTextFieldHandler()
    {
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                //the load game function is run
                model.loadGameScreen();
            }
        };

        return listener;
    }

    //the create game handler handles what happens when the user clicks on the create Game button
    //in the create game menu
    public ActionListener createGameHandler()
    {
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                //the create game function is run
                model.createGameScreen();
            }
        };

        return listener;
    }

    //the move handler handles what happens when the user clicks on the move button
    //in the what will you do next
    public ActionListener moveHandler()
    {
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                //runs the create encounter screen button
                model.createEncounterScreen();
            }
        };

        return listener;
    }

    //the attack handler handles what happens when the user clicks on the attack button
    //in the encounter menu
    public ActionListener attackHandler()
    {
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                //runs the attack function
                model.attack();
            }
        };

        return listener;
    }

    //the block handler handles what happens when the user clicks on the block button
    //in the encounter menu
    public ActionListener blockHandler()
    {
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                //runs the block function
                model.block();
            }
        };

        return listener;
    }

    //the skill handler handles what happens when the user clicks on the skill button
    //in the encounter menu
    public ActionListener skillHandler()
    {
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                //runs the skill function
                model.skill();
            }
        };

        return listener;
    }

    //the exit handler handles what happens when the user clicks on the exit button
    //in the what will you do next menu
    public ActionListener exitGameHandler()
    {
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                //runs the exit game function
                model.exitGame();
            }
        };

        return listener;
    }

    //the save game handler handles what happens when the user clicks on the save game button
    //in the save menu
    public ActionListener saveGameHandler()
    {
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                //runs the save game function
                model.saveGame();
            }
        };

        return listener;
    }

    //the exit game handler handles what happens when the user clicks on the exit game button
    //in the what will you do next menu
    public ActionListener quitGameHandler()
    {
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                //runs the quit game function
                model.quitGame();
            }
        };

        return listener;
    }

    //quits the game and ends the connection with the database
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

    //starts the game over again from the start
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

    //runs the save score function
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

    //shows the socreboard in a popup
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

    //saves the leaderboard score
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

    //shows the leaderboard in a popup
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

    //shows the instructions in a popup
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
