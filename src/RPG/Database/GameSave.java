/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.Database;

import RPG.GameSetup.Game;
import RPG.PlayerClasses.Barbarian;
import RPG.PlayerClasses.Paladin;
import RPG.PlayerClasses.Player;
import RPG.PlayerClasses.Wizard;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author alex
 * 
 * This class controls the saving and loading of the game
 */
public class GameSave
{

    private final DBManager dbManager; //instance of the DB manager to get the connection
    private final Connection conn; //instance of the connection
    private final Logger log; //logger class for any errors that occur
    private Statement statement; //statement for SQL updates

    private boolean overWrite; //is true if the user wants to overwrite
    private boolean askOverWrite; //is true if the user has been asked if they want to overwrite

    //isntantiating method for gamesave
    public GameSave(DBManager db, Logger log)
    {
        dbManager = db;
        conn = dbManager.getConnection();
        connectGameSaveDB();
        askOverWrite = false;
        this.log = log;
    }

    //sets the overwrite to an input value
    public void setOverrite(boolean input)
    {
        overWrite = input;
    }

    //sets the ask overwrite to an input value
    public void setAskOverWrite(boolean input)
    {
        askOverWrite = input;
    }

    //returns the over write
    public boolean getOverWrite()
    {
        return this.overWrite;
    }

    //returns the ask over write
    public boolean getAskOverWrite()
    {
        return this.askOverWrite;
    }

    //connects the table to the database
    private void connectGameSaveDB()
    {
        try
        {
            statement = conn.createStatement();
            if (!dbManager.checkTable("GAMESAVES")) //checks if the table has been created
            {
                statement.addBatch("CREATE TABLE GAMESAVES (USERNAME VARCHAR(20), TYPE INT, HEALTH INT, MAXHEALTH INT, DAMAGE INT, AC INT, RM INT, CRN INT, TRM INT)");
                statement.executeBatch();
            }
        }
        catch (SQLException ex)
        {
            log.log(ex + "");
        }
    }

    //checks if there is a save under the name name and returns a game if there is
    public Game findSavedGame(String name)
    {
        ResultSet rs = null;

        try
        {
            rs = this.statement.executeQuery("SELECT * FROM GAMESAVES"); //gets all the data from gavesaves

            while (rs.next())
            {
                String playerName = rs.getString("USERNAME");
                int playerType = rs.getInt("TYPE");
                int health = rs.getInt("HEALTH");
                int maxhealth = rs.getInt("MAXHEALTH");
                int damage = rs.getInt("DAMAGE");
                int armourClass = rs.getInt("AC");
                int rollModifier = rs.getInt("RM");
                int currentRoomNum = rs.getInt("CRN");
                int totalRoomNum = rs.getInt("TRM");

                if (playerName.equals(name)) //checks if the data is equal to the name given and if it is it returns the game
                {
                    rs.close();
                    statement.executeUpdate("DELETE FROM GAMESAVES WHERE USERNAME='" + playerName + "'");
                    return loadGame(playerName, playerType, health, maxhealth, damage, armourClass, rollModifier, currentRoomNum, totalRoomNum);
                }
            }
            
            rs.close();
            return null; //otherwise it returns null
        }
        catch (SQLException ex)
        {
            log.log(ex + "");
        }

        return null;
    }

    //loads the game after an existing player is found
    private Game loadGame(String name, int playerType, int health, int maxhealth, int damage, int armourClass, int rollModifier, int currentRoomNum, int totalRoomNum)
    {
        Player player;

        //first loads in the player
        switch (playerType)
        {
            case 1:
                player = new Paladin(health, maxhealth, damage, armourClass, rollModifier, currentRoomNum);
                break;
            case 2:
                player = new Wizard(health, maxhealth, damage, armourClass, rollModifier, currentRoomNum);
                break;
            case 3:
                player = new Barbarian(health, maxhealth, damage, armourClass, rollModifier, currentRoomNum);
                break;
            default:
                player = new Paladin(health, maxhealth, damage, armourClass, rollModifier, currentRoomNum);
                break;
        }

        //sets the players name
        player.setName(name);

        //the loads the game
        Game game = new Game(totalRoomNum, player, currentRoomNum);

        return game; //returns the game that has been loaded
    }

    //saves the game into a specific format
    //returns true if the game was succesfully saved and false if it was not
    public boolean saveGame(Game game)
    {
        //gets each part of the tokenized string
        String playerName = game.getPlayer().getName();
        int playerType = game.getPlayer().returnClass();
        int health = game.getPlayer().getHealth();
        int maxHealth = game.getPlayer().getMaxHealth();
        int damage = game.getPlayer().getDamage();
        int armourClass = game.getPlayer().getArmourClass();
        int rollModifier = game.getPlayer().getRollModifier();
        int currentRoomNum = game.getPlayer().getRoomNum();
        int totalRoomNum = game.getMapSize();

        if (!checkSave(playerName)) //if gameList is not instantiated due to a missing file it instantiates gameList and returns true
        {
            try
            {
                statement.executeUpdate("INSERT INTO GAMESAVES VALUES('" + playerName + "', " + playerType + "," + health + "," + maxHealth + "," + damage + ","
                        + armourClass + "," + rollModifier + "," + currentRoomNum + "," + totalRoomNum + ")");
            }
            catch (SQLException ex)
            {
                log.log(ex + "");
            }
            return true;
        }
        else
        {
            if (overWrite) //if it does exist it asks if they want to overwrite their save
            {
                try
                {
                    //updates the current save
                    statement.executeUpdate("UPDATE GAMESAVES "
                            + "SET TYPE=" + playerType + ",  HEALTH=" + health + ", MAXHEALTH=" + maxHealth + ", DAMAGE=" + damage 
                            + ", AC=" + armourClass + ", RM=" + rollModifier + ", CRN=" + currentRoomNum + ", TRM=" + totalRoomNum 
                            + " WHERE USERNAME='" + playerName + "'");
                }
                catch (SQLException ex)
                {
                    log.log(ex + "");
                }

                overWrite = false;
                askOverWrite = false;
                return true; //if they do overwrite it it returns true otherwise it returns false
            }
            else
            {
                return false;
            }
        }
    }

    //checks if a save has been made under the name inputted
    //returns true if a save is found and false if a save is not found
    public boolean checkSave(String name)
    {
        ResultSet rs = null;

        try
        {
            rs = this.statement.executeQuery("SELECT * FROM GAMESAVES");

            while (rs.next())
            {
                String playerName = rs.getString("USERNAME"); 

                if (playerName.equals(name)) //if  a matching name is found it returns true
                {
                    rs.close();
                    return true;
                }
            }
            
            rs.close();
            return false;
        }
        catch (SQLException ex)
        {
            log.log(ex + "");
        }

        return false;
    }
}
