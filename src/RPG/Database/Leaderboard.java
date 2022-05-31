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
 * This class maintains control over access to the leaderboard data and
 * writes/reads from the leaderboard table.
 *
 * The leaderboard is for users to store their personal highscore for a
 * character if they wish to
 */
public class Leaderboard
{

    private final DBManager dbManager; //instance of the DB manager to get the connection
    private final Connection conn; //instance of the connection
    private final Logger log; //logger class for any errors that occur
    private Statement statement; //statement for SQL updates

    //instantiates a leaderboard object
    public Leaderboard(DBManager db, Logger log)
    {
        dbManager = db;
        conn = dbManager.getConnection();
        connectLeaderBoardDB();
        this.log = log;
    }

    //connects the leaderboard table up to the database
    private void connectLeaderBoardDB()
    {
        try
        {
            statement = conn.createStatement();
            if (dbManager.checkTable("LEADERBOARD")) //checks if the table exists
            {

            }
            else
            {
                statement.addBatch("CREATE TABLE LEADERBOARD (USERNAME VARCHAR(20), SCORE INT)"); //if it doesn't the table is created
                statement.executeBatch();
            }
        }
        catch (SQLException ex)
        {
            log.log(ex + "");
        }
    }

    //returns a list of the top 10 leaderboard
    public List<String> printLeaderboard()
    {
        int count = 1;
        List<String> highScores = new ArrayList<>();

        ResultSet rs = null;

        try
        {
            rs = this.statement.executeQuery("SELECT * FROM LEADERBOARD ORDER BY SCORE DESC"); //gets the list in order of greatest score

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
    public void addHighScore(String playerName, int score)
    {
        try
        {
            statement.executeUpdate("INSERT INTO LEADERBOARD VALUES ('" + playerName + "'," + score + ")");
        }
        catch (SQLException ex)
        {
            log.log(ex + "");
        }
    }
}
