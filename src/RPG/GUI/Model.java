/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.GUI;

import RPG.Database.DBManager;
import RPG.Database.GameSave;
import RPG.Database.HighScore;
import RPG.Database.Leaderboard;
import RPG.GameSetup.Game;
import RPG.RunGame.Encounter;
import RPG.RunGame.Instructions;
import RPG.RunGame.StartGame;

/**
 *
 * @author alex
 */
public class Model
{

    private View view;
    private final Instructions instructions;
    private final StartGame startGame;
    private Game game;
    private Encounter encounter;
    private final GameSave gameSave;
    private final HighScore highScore;
    private final Leaderboard leaderBoard;
    private final DBManager db;

    public Model()
    {
        db = new DBManager();
        gameSave = new GameSave(db);
        startGame = new StartGame(gameSave);
        highScore = new HighScore(db);
        leaderBoard = new Leaderboard(db);
        instructions = new Instructions();
    }

    public void setView(View view)
    {
        this.view = view;
    }
    
    public void getInstructions()
    {
        view.popUp2(instructions.getOutput(), "Instructions");
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
        view.setRoomLabel("" + game.getMapLength());
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
            updateGameLabels();
        }
    }

    public void createEncounterScreen()
    {
        if (game.mapEmpty())
        {
            view.setBoardScreen();
            view.setEncounterPlayerHealth(game.getPlayer().getName());
            view.setRoomLabel(game.getMapLength() + 1 + "");
        }
        else
        {
            encounter = new Encounter(game.getPlayer(), game.getPlayer().getRoom().getEnemy(), view, game);

            encounter.runEncounter();
        }
    }

    public void attack()
    {
        view.disableEncounterButtons();
        encounter.attack();
    }

    public void block()
    {
        view.disableEncounterButtons();
        encounter.block();
    }

    public void skill()
    {
        view.disableEncounterButtons();
        encounter.skill();
    }

    public void exitGame()
    {
        view.setSaveScreen();
        updateGameLabels();
    }

    public void saveGame()
    {
        if (gameSave.getAskOverWrite() && !gameSave.getOverWrite())
        {
            gameSave.setOverrite(true);
        }

        if (!gameSave.saveGame(game))
        {
            view.updateErrorLabel("Save already exists, click save again to overwrite otherwise click don't save to cancel");
            gameSave.setAskOverWrite(true);
        }
        else
        {
            view.setEndGameScreen();
        }
    }
    
    public void saveHighScore()
    {
        if(highScore.addHighScore(game.getPlayer().getName(), game.getMapSize()))
        {
            view.updateMainLabel("Highscore succesfully added");
            view.disableScoreButton();
        }
        else
        {
            view.updateErrorLabel("Higher highscore already added. Play again to get a better one!");
        }
    }
    
    public void saveLeaderboard()
    {
        leaderBoard.addHighScore(game.getPlayer().getName(), game.getMapSize());
        view.updateMainLabel("Leaderboard succesfully added");
        view.disableLeaderButton();
    }

    public void quitGame()
    {
        view.enableSaveButtons();
        view.setEndGameScreen();
    }

    public void closeGame()
    {
        view.dispose();
    }

    public void restartGame()
    {
        view.setTitleScreen();
    }

    public void showLeaderboard()
    {
       view.popUp(leaderBoard.printLeaderboard(), "Top 10 Leader Board");
    }

    public void showScoreboard()
    {
        view.popUp(highScore.printHighScore(), "Top 10 High Scores");
    }
}
