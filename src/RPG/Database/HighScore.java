/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.Database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alex
 */
public class HighScore
{

    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;

    public HighScore(DBManager db)
    {
        dbManager = db;
        conn = dbManager.getConnection();
        connectHighScoreDB();
    }

    private void connectHighScoreDB()
    {
        try
        {
            statement = conn.createStatement();
            if (checkTable("HIGHSCORES"))
            {

            }
            else
            {
                statement.addBatch("CREATE TABLE HIGHSCORES (USERNAME VARCHAR(20), SCORE INT)");
                statement.executeBatch();
            }
        }
        catch (SQLException ex)
        {
            java.util.logging.Logger.getLogger(GameSave.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //prints the top 10 highscores
    public List<String> printHighScore()
    {
        int count = 1;
        List<String> highScores = new ArrayList<>();

        ResultSet rs = null;

        try
        {
            rs = this.statement.executeQuery("SELECT * FROM HIGHSCORES");

            while (rs.next())
            {
                String playerName = rs.getString("USERNAME");
                int score = rs.getInt("SCORE");

                highScores.add(count + ": " + (playerName + " [" + score + "]"));
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
            java.util.logging.Logger.getLogger(GameSave.class.getName()).log(Level.SEVERE, null, ex);
        }

        return highScores;
    }

    public boolean addHighScore(String playerName, int score)
    {
        int previousScore = checkScore(playerName);
        if (previousScore != -1)
        {
            if (score > previousScore)
            {
                try
                {
                    statement.executeUpdate("DELETE FROM HIGHSCORES WHERE USERNAME='" + playerName + "'");
                    statement.executeUpdate("INSERT INTO HIGHSCORES VALUES ('" + playerName + "'," + score + ")");
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(HighScore.class.getName()).log(Level.SEVERE, null, ex);
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
                statement.executeUpdate("INSERT INTO HIGHSCORES VALUES ('" + playerName + "'," + score + ")");
            }
            catch (SQLException ex)
            {
                Logger.getLogger(HighScore.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }
    }

    private int checkScore(String name)
    {
        ResultSet rs = null;

        try
        {
            rs = this.statement.executeQuery("SELECT * FROM HIGHSCORES");

            while (rs.next())
            {
                String playerName = rs.getString("USERNAME");
                int score = rs.getInt("SCORE");

                if (playerName.equals(name))
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
            java.util.logging.Logger.getLogger(GameSave.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

    public boolean checkTable(String name)
    {
        try
        {
            DatabaseMetaData dbmd = this.conn.getMetaData();
            String[] types =
            {
                "TABLE"
            };
            statement = this.conn.createStatement();
            ResultSet rs = dbmd.getTables(null, null, null, types);

            while (rs.next())
            {
                String table_name = rs.getString("TABLE_NAME");
                if (table_name.equalsIgnoreCase(name))
                {
                    return true;
                }
            }
            rs.close();
        }
        catch (SQLException ex)
        {
        }

        return false;
    }

    public void closeConnection()
    {
        this.dbManager.closeConnections();
    }
}
