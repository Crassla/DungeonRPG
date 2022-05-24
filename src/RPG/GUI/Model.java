/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.GUI;

import RPG.GameSetup.Game;
import RPG.RunGame.Encounter;
import RPG.RunGame.StartGame;

/**
 *
 * @author alex
 */
public class Model
{

    private View view;
    private StartGame startGame;
    private Game game;
    private Encounter encounter;

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
        int numRooms = view.getNumRooms();
        int playerClass = view.getPlayerClass() + 1;
        String playerName = view.getPlayerName().toLowerCase();

        game = startGame.newGame(numRooms, playerClass, playerName);

        view.setGameScreen();
        updateGameLabels();
    }
    
    public void updateGameLabels()
    {
        view.setPlayerHealthLabel(game.getPlayer().getName(), game.getPlayer().getPlayerClass(), "" + game.getPlayer().getHealth());
        view.setPlayerDamageLabel("" + game.getPlayer().getDamage());
        view.setPlayerArmourLabel("" + game.getPlayer().getArmourClass());
        view.setPlayerRollLabel("" + game.getPlayer().getRollModifier());
    }

    public void loadGameScreen()
    {
        game = startGame.loadGame(view.getLoadTextField());
        if (game == null)
        {
            view.updateErrorLabel("Error: No save by the name " + view.getLoadTextField() + " found!");
        }
        else
        {
            view.setGameScreen();
            view.setPlayerHealthLabel(game.getPlayer().getName(), game.getPlayer().getPlayerClass(), "" + game.getPlayer().getHealth());
            view.setPlayerDamageLabel("" + game.getPlayer().getDamage());
            view.setPlayerArmourLabel("" + game.getPlayer().getArmourClass());
            view.setPlayerRollLabel("" + game.getPlayer().getRollModifier());
        }
    }

    public void createEncounterScreen()
    {
        encounter = new Encounter(game.getPlayer(), game.getPlayer().getRoom().getEnemy(), view);
        
        encounter.runEncounter();
    }
    
    public void attack()
    {
        if (encounter.attack() == 0)
        {
            game.nextRoom();
        }
    }
}
