/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.GUIMVC;

import RPG.Database.DBManager;
import RPG.Database.GameSave;
import RPG.Database.HighScore;
import RPG.Database.Leaderboard;
import RPG.Database.Logger;
import RPG.GameSetup.Game;
import RPG.RunGame.Encounter;
import RPG.RunGame.Instructions;
import RPG.RunGame.StartGame;

/**
 *
 * @author alex
 * 
 * This class is the model in the MVC design It controls what happens when
 * a controller has called it
 */
public class Model
{

    //final variables to be used by the mode
    private final Instructions instructions;
    private final StartGame startGame;
    private final GameSave gameSave;
    private final HighScore highScore;
    private final Leaderboard leaderBoard;
    private final Logger log;
    private final DBManager db;
    
    //varibles to be used by the model
    private Game game;
    private Encounter encounter;
    private View view;

    public Model()
    {
        db = new DBManager();
        log = new Logger(db);
        gameSave = new GameSave(db, log);
        startGame = new StartGame(gameSave);
        highScore = new HighScore(db, log);
        leaderBoard = new Leaderboard(db, log);
        instructions = new Instructions();
    }

    public void setView(View view)
    {
        log.log("set view");
        this.view = view;
    }

    public void getInstructions()
    {
        log.log("get instructions");
        view.popUp2(instructions.getOutput(), "Instructions");
    }

    public void newGame()
    {
        log.log("new game");
        view.setNewGameScreen();
    }

    public void loadGame()
    {
        log.log("load game");
        view.setLoadScreen();
    }

    public void createGameScreen()
    {
        log.log("create game screen");
        int numRooms = view.getNumRooms();
        int playerClass = view.getPlayerClass() + 1;
        String playerName = view.getPlayerName().toLowerCase();

        if (playerName.length() == 0)
        {
            view.updateErrorLabel("You must input a username");
        }
        else if (playerName.length() < 20)
        {
            game = startGame.newGame(numRooms, playerClass, playerName);
            view.setGameScreen();
            updateGameLabels();
        }
        else
        {
            view.updateErrorLabel("Your username can be no larger than 20 characters");
        }
    }

    public void updateGameLabels()
    {
        log.log("update game labels");
        view.setPlayerHealthLabel(game.getPlayer().getName(), game.getPlayer().getPlayerClass(), "" + game.getPlayer().getHealth());
        view.setPlayerDamageLabel("" + game.getPlayer().getDamage());
        view.setPlayerArmourLabel("" + game.getPlayer().getArmourClass());
        view.setPlayerRollLabel("" + game.getPlayer().getRollModifier());
        view.setRoomLabel("" + game.getMapLength());
    }

    public void loadGameScreen()
    {
        log.log("load game screen");
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
        log.log("create encounter screen");
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
        log.log("attack");
        view.disableEncounterButtons();
        encounter.attack();
    }

    public void block()
    {
        log.log("block");
        view.disableEncounterButtons();
        encounter.block();
    }

    public void skill()
    {
        log.log("skill");
        view.disableEncounterButtons();
        encounter.skill();
    }

    public void exitGame()
    {
        log.log("exit game");
        view.setSaveScreen();
        updateGameLabels();
    }

    public void saveGame()
    {
        log.log("save game");
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
        log.log("save high score");
        if (highScore.addHighScore(game.getPlayer().getName(), game.getMapSize()))
        {
            view.updateMainLabel("Highscore succesfully added");
            view.disableScoreButton();
        }
        else
        {
            System.out.println("Higher highscore already added. Play again to get a better one!");
            view.updateErrorLabel("Higher highscore already added. Play again to get a better one!");
        }
    }

    public void saveLeaderboard()
    {
        log.log("save leaderboard");
        leaderBoard.addHighScore(game.getPlayer().getName(), game.getMapSize());
        view.updateMainLabel("Leaderboard succesfully added");
        view.updateErrorLabel("");
        view.disableLeaderButton();
    }

    public void quitGame()
    {
        log.log("quit game");
        view.enableSaveButtons();
        view.setEndGameScreen();
    }

    public void closeGame()
    {
        log.log("close game");
        db.closeConnections();
        System.exit(0);
    }

    public void restartGame()
    {
        log.log("restart game");
        view.setTitleScreen();
    }

    public void showLeaderboard()
    {
        log.log("show leaderboard");
        view.popUp(leaderBoard.printLeaderboard(), "Top 10 Leader Board");
    }

    public void showScoreboard()
    {
        log.log("show leaderboard");
        view.popUp(highScore.printHighScore(), "Top 10 High Scores");
    }
}
