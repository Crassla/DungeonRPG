/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.RunGame;

import RPG.FileIO.GameSave;
import RPG.FileIO.GameWinnerLeaderboard;
import RPG.FileIO.HighScore;
import RPG.GameSetup.Game;
import RPG.UserInput.ParseInput;

/**
 *
 * @author alex
 *
 * This class controls the end of the game
 */
public class EndGame
{

    //variables to end the game
    private final Game game;

    //instantiates the endgame object
    public EndGame(Game game)
    {
        this.game = game;
    }

    //runs the main endgame
    //returns true if the player wants to play again
    public boolean endGame()
    {
        if (game.getPlayer().getHealth() == 0)
        {
            lostGame();
        }
        else
        {
            if (game.getMapSize() > game.getPlayer().getRoomNum())
            {
                exitGame();
            }
            else
            {
                wonGame();
            }
        }

        return playAgain();
    }

    //returns true if the player wants to play again
    private boolean playAgain()
    {
        if (ParseInput.getYNInput("Do you wish to play again (y/n): "))
        {
            return true;
        }
        else
        {
            System.out.println("Goodbye!");
            System.out.println("=======================RPG EXITING======================");
            return false;
        }
    }

    //method controlling the lost game
    //only prints out the highscore if the user has added their number as some players do not want to see the highscore
    private void lostGame()
    {
        System.out.println("Unfortunately you have lost");
        if (ParseInput.getYNInput("Do you want to save your room number to the highscore (y/n): "))
        {
            HighScore highScore = new HighScore();
            if (!highScore.addHighScore(game.getPlayer().getName(), game.getPlayer().getRoomNum() - 1))
            {
                System.out.println("Unfortunately you did not make it onto the top 10 highscores, better luck next time!\n");
            }
            System.out.println("====================TOP 10 HIGHSCORES====================");
            highScore.printHighScore();
        }
    }

    //method controlling the won game
    //asks if the user wants to update their stats on the leaderboard
    //asks if the user wants to add their score to the highscore
    //only asks if they want to see the leaderboard and highscore if they have added to it for fairness
    //this is to make sure that users who don't want to see the leaderboard or high score don't have to
    private void wonGame()
    {
        System.out.println("You have won!");

        if (ParseInput.getYNInput("Do you want to add your stats onto the leaderboard (y/n): "))
        {
            GameWinnerLeaderboard leaderboard = new GameWinnerLeaderboard();

            if (!leaderboard.addLeaderboard(game.getPlayer().getName(), game.getPlayer().getRoomNum() - 1))
            {
                System.out.println("You have a score higher than or equal to " + (game.getPlayer().getRoomNum() - 1) + " on the leaderboard\n");
            }

            if (ParseInput.getYNInput("Do you want to view the leaderboard (y/n): "))
            {
                System.out.println("=======================LEADERBOARD======================");
                leaderboard.printLeaderboard();
            }
        }

        if (ParseInput.getYNInput("Do you want to save your room number to the highscore (y/n): "))
        {
            HighScore highScore = new HighScore();

            if (!highScore.addHighScore(game.getPlayer().getName(), game.getPlayer().getRoomNum() - 1))
            {
                System.out.println("Unfortunately you did not make it onto the top 10 highscores, better luck next time!\n");
            }

            if (ParseInput.getYNInput("Do you want to view the top 10 highscores (y/n): "))
            {
                System.out.println("====================TOP 10 HIGHSCORES====================");
                highScore.printHighScore();
            }
        }
    }

    //runs if the user exited the game
    //asks if they want to save
    //if they do saves the game
    private void exitGame()
    {
        System.out.println("You have exited the game.");
        System.out.println("\nYou have " + (game.getMapSize() + 1 - game.getPlayer().getRoomNum()) + " rooms left");
        if (ParseInput.getYNInput("Do you want to save (y/n): "))
        {
            GameSave gameSave = new GameSave();
            gameSave.saveGame(game);
        }
    }
}
