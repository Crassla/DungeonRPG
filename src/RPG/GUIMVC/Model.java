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

    //instantiates a new model object
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

    //adds the view to the model 
    public void setView(View view)
    {
        log.log("set view");
        this.view = view;
    }

    //logs getinstructions and shows the popup instructions
    public void getInstructions()
    {
        log.log("get instructions");
        view.popUp2(instructions.getOutput(), "Instructions");
    }

    //logs newGame and changes the screen to the new game screen
    public void newGame()
    {
        log.log("new game");
        view.setNewGameScreen();
    }

    //loads the game and changes the screen to the load game screen
    public void loadGame()
    {
        log.log("load game");
        view.setLoadScreen();
    }

    //logs the create game screen and checks if the user input is correct
    //if it is creates a new game object otherwise returns an error
    public void createGameScreen()
    {
        log.log("create game screen");
        int numRooms = view.getNumRooms();
        int playerClass = view.getPlayerClass() + 1; //fixes -1 error in playerclass
        String playerName = view.getPlayerName().toLowerCase(); //changes the name to lowercase so game ignores case

        if (playerName.length() == 0) //checks if the player has entered a name
        {
            view.updateErrorLabel("You must input a username");
        }
        else if (playerName.length() < 20) //checks if the playerName is less than 20 (to prevent database error)
        {
            game = startGame.newGame(numRooms, playerClass, playerName);
            view.setGameScreen();
            updateGameLabels();
        }
        else
        {
            view.updateErrorLabel("Your username can be no larger than 20 characters"); //outputs error
        }
    }

    //updates all of the game details to their most current value
    public void updateGameLabels()
    {
        log.log("update game labels");
        view.setPlayerHealthLabel(game.getPlayer().getName(), game.getPlayer().getPlayerClass(), "" + game.getPlayer().getHealth());
        view.setPlayerDamageLabel("" + game.getPlayer().getDamage());
        view.setPlayerArmourLabel("" + game.getPlayer().getArmourClass());
        view.setPlayerRollLabel("" + game.getPlayer().getRollModifier());
        view.setRoomLabel("Rooms Left: " + game.getMapLength());
    }

    //gets an old game object from the database
    //checks if the game has returned null (no save) and if it is outputs an error message
    //otherwise changes the screen to the game screen and updates its labels
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

    //checks if the map is empty, if it is changes the screen to the game win screen
    //if not it creates a new encounter from the room and runs the encounter
    public void createEncounterScreen()
    {
        log.log("create encounter screen");
        if (game.mapEmpty())
        {
            view.setBoardScreen();
            view.setEncounterPlayerHealth(game.getPlayer().getPlayerClass() + ": " + game.getPlayer().getName());
            view.setRoomLabel("Score: " + game.getMapSize() + "");
        }
        else
        {
            encounter = new Encounter(game.getPlayer(), game.getPlayer().getRoom().getEnemy(), view, game);

            encounter.runEncounter();
        }
    }

    //runs the attack function in the encounter
    public void attack()
    {
        log.log("attack");
        view.disableEncounterButtons();
        encounter.attack();
    }

    //runs the block function in the encounter
    public void block()
    {
        log.log("block");
        view.disableEncounterButtons();
        encounter.block();
    }

    //runs the skill function in the encounter
    public void skill()
    {
        log.log("skill");
        view.disableEncounterButtons();
        encounter.skill();
    }

    //changes the game screen to the save screen
    public void exitGame()
    {
        log.log("exit game");
        view.setSaveScreen();
        updateGameLabels();
    }

    //saves the game to the database
    //if the saves exists asks to overwrite
    //if askedtooverwrite then overwrites the save and sets the screen to the end game screen
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

    //saves the highscore to the database
    //if the highscore is already added it only adds if the current score is greater than the stored one
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
            view.updateErrorLabel("Higher highscore already added. Play again to get a better one!"); //shows an error if the score can't be added
        }
    }

    //saves the users score to the leaderboard
    public void saveLeaderboard()
    {
        log.log("save leaderboard");
        leaderBoard.addHighScore(game.getPlayer().getName(), game.getMapSize());
        view.updateMainLabel("Leaderboard succesfully added");
        view.updateErrorLabel("");
        view.disableLeaderButton();
    }

    //changes the game ti the end game screen
    public void quitGame()
    {
        log.log("quit game");
        view.enableSaveButtons();
        view.setEndGameScreen();
    }

    //exits the game
    public void closeGame()
    {
        log.log("close game");
        db.closeConnections();
        System.exit(0);
    }

    //retarts the game from the beginning
    public void restartGame()
    {
        log.log("restart game");
        view.setTitleScreen();
    }

    //shows a popup with the leaderboard information
    public void showLeaderboard()
    {
        log.log("show leaderboard");
        view.popUp(leaderBoard.printLeaderboard(), "Top 10 Leader Board");
    }

    //shows the scoreboard with the scoreboard information
    public void showScoreboard()
    {
        log.log("show leaderboard");
        view.popUp(highScore.printHighScore(), "Top 10 High Scores");
    }
}
