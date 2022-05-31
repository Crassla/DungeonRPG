/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author alex
 * 
 *  This class is used to log all user input and any errors that occur as log4j has proved to be bad
 */
public class Logger
{
    private final DBManager dbManager; //instance of the DB manager to get the connection
    private final Connection conn; //instance of the connection
    private Statement statement; //statement for SQL updates

    //instantiates a logger object
    public Logger(DBManager db)
    {
        dbManager = db;
        conn = dbManager.getConnection();
        connectLoggerDB();
    }
    
    //connects to the logger table
    private void connectLoggerDB()
    {
        try
        {
            statement = conn.createStatement();
            if (dbManager.checkTable("LOG")) //checks if the log table already exists
            {

            }
            else
            { //if it doesn't exist it creates the table
                statement.addBatch("CREATE TABLE LOG (LOG VARCHAR(100))"); 
                statement.executeBatch();
            }
        }
        catch (SQLException ex)
        {
        }
    }
    
    //logs a string
    public void log(String log)
    {
        if (log.length() < 100)
        {
            try
            {
                statement.executeUpdate("INSERT INTO LOG(LOG) VALUES ('" + log + "')");
            }
            catch (SQLException ex)
            {
                System.out.println(ex + "");
            }
        }
    }
    
    //gets the log from id
    //returns nothing if the log isn't found or if it is it returns the log
    public String getLog(int id)
    {
        ResultSet rs = null;
        
        try
        {
            rs = statement.executeQuery("SELECT * FROM LOG");
            int count = 0;
            
            while(rs.next())
            {
                String s = rs.getString("LOG");
                if (count == id)
                {
                    return s;
                }
                count++;
            }
            
        }
        catch (SQLException ex)
        {
        }
        
        return "";
    }
}
