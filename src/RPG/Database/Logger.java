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


/**
 *
 * @author alex
 */
public class Logger
{
    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;

    public Logger(DBManager db)
    {
        dbManager = db;
        conn = dbManager.getConnection();
        connectLoggerDB();
    }
    
    private void connectLoggerDB()
    {
        try
        {
            statement = conn.createStatement();
            if (checkTable("LOG"))
            {

            }
            else
            {
                statement.addBatch("CREATE TABLE LOG (ID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), LOG VARCHAR(100))");
                statement.executeBatch();
            }
        }
        catch (SQLException ex)
        {
        }
    }
    
    public void log(String log)
    {
        if (log.length() < 100)
        {
            try
            {
                statement.executeUpdate("INSERT INTO LOG VALUES ('" + log + "')");
            }
            catch (SQLException ex)
            {
            }
        }
    }
    
    public String getLog(int id)
    {
        ResultSet rs = null;
        String output = "";
        
        try
        {
            rs = statement.executeQuery("SELECT * FROM LOG");
            
            while(rs.next())
            {
                int thisId = rs.getInt("ID");
                String s = rs.getString("LOG");
                
                if (thisId == id)
                {
                    output = s;
                }
            }
            
        }
        catch (SQLException ex)
        {
        }
        
        return output;
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
