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

/**
 *
 * @author alex
 */
public class Leaderboard
{

    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;
    private final Logger log;

    public Leaderboard(DBManager db, Logger log)
    {
        dbManager = db;
        conn = dbManager.getConnection();
        connectLeaderBoardDB();
        this.log = log;
    }

    private void connectLeaderBoardDB()
    {
        try
        {
            statement = conn.createStatement();
            if (checkTable("LEADERBOARD"))
            {

            }
            else
            {
                statement.addBatch("CREATE TABLE LEADERBOARD (USERNAME VARCHAR(20), SCORE INT)");
                statement.executeBatch();
            }
        }
        catch (SQLException ex)
        {
            log.log(ex + "");
        }
    }

    //prints the top 10 leaderboard
    public List<String> printLeaderboard()
    {
        int count = 1;
        List<String> highScores = new ArrayList<>();

        ResultSet rs = null;

        try
        {
            rs = this.statement.executeQuery("SELECT * FROM LEADERBOARD ORDER BY SCORE DESC");

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
            log.log(ex + "");
        }

        return highScores;
    }

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
            log.log(ex + "");
        }

        return false;
    }
}
