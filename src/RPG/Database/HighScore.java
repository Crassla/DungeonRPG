/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alex
 * 
 * This class controls the table of all scores users have saved It
 * allows for duplicates and prints out from highest score to lowest score
 */
public class HighScore
{

    private final DBManager dbManager; //instance of the DB manager to get the connection
    private final Connection conn; //instance of the connection
    private final Logger log; //logger class for any errors that occur
    private Statement statement; //statement for SQL updates

    //instaitates a HighScore object
    public HighScore(DBManager db, Logger log)
    {
        dbManager = db;
        conn = dbManager.getConnection();
        connectHighScoreDB();
        this.log = log;
    }

    //connects the high score table to the database if it is not already
    private void connectHighScoreDB()
    {
        try
        {
            statement = conn.createStatement();
            if (dbManager.checkTable("HIGHSCORES")) //checks if the table highscores exists
            {
                //highscore doesn't exist
            }
            else
            {
                statement.addBatch("CREATE TABLE HIGHSCORES (USERNAME VARCHAR(20), SCORE INT)"); //creates the table if it doesn't exist
                statement.executeBatch();
            }
        }
        catch (SQLException ex)
        {
            log.log(ex + "");
        }
    }

    //returns a list of the top 10 highscores
    public List<String> printHighScore()
    {
        int count = 1;
        List<String> highScores = new ArrayList<>();

        ResultSet rs = null;

        try
        {
            rs = this.statement.executeQuery("SELECT * FROM HIGHSCORES ORDER BY SCORE DESC"); //selects all from the highscores sorting from largest score

            while (rs.next())
            {
                String playerName = rs.getString("USERNAME");
                int score = rs.getInt("SCORE");

                highScores.add(count + ": " + (playerName + " [" + score + "]")); //adds the score to the list
                count++;

                if (count > 10)
                {
                    break; //breaks the for loop when 10 scores have been printed
                }
            }

            rs.close();
        }
        catch (SQLException ex)
        {
            log.log(ex + "");
        }

        return highScores;
    }

    //adds a high score to the database
    //returns true if the score was added or false if it was not
    public boolean addHighScore(String playerName, int score)
    {
        int previousScore = checkScore(playerName);
        if (previousScore != -1)
        {
            if (score > previousScore)
            {
                try
                {
                    statement.executeUpdate("DELETE FROM HIGHSCORES WHERE USERNAME='" + playerName + "'"); //if the score is higher it is deleted
                    statement.executeUpdate("INSERT INTO HIGHSCORES VALUES ('" + playerName + "'," + score + ")"); //then the higher one is inserted
                    statement.executeUpdate("UPDATE HIGHSCORES"
                            + "SET SCORE="+score+ " WHERE USERNAME='" + playerName + "'");
                }
                catch (SQLException ex)
                {
                    log.log(ex + "");
                }
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            try
            {
                statement.executeUpdate("INSERT INTO HIGHSCORES VALUES ('" + playerName + "'," + score + ")"); //if the score doesn't exist a new one is inserted
            }
            catch (SQLException ex)
            {
                log.log(ex + "");
            }
            return true;
        }
    }

    //checks to see if the users score already exists
    //if it does it returns the score otherwise it returns -1 
    private int checkScore(String name)
    {
        ResultSet rs = null;

        try
        {
            rs = this.statement.executeQuery("SELECT * FROM HIGHSCORES"); //gets all data in highscores

            while (rs.next())
            {
                String playerName = rs.getString("USERNAME");
                int score = rs.getInt("SCORE");

                if (playerName.equals(name)) //returns true if the name matches and returns the score
                {
                    rs.close();
                    return score;
                }
            }

            rs.close();
            return -1;
        }
        catch (SQLException ex)
        {
            log.log(ex + "");
        }

        return -1;
    }
}
