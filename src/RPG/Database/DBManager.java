/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.Database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author alex
 * 
 * This class is the DBManager for this project. It manages the database connection
 */
public final class DBManager
{

    private static final String USER_NAME = "pdc"; //your DB username
    private static final String PASSWORD = "pdc"; //your DB password
    private static final String URL = "jdbc:derby:DungeonRPG; create=true";  //url of the DB host

    //the connection to the database
    Connection conn;

    //constructor automatically creates the connection
    public DBManager()
    {
        establishConnection();
    }

    //returns the connection so other classes can use it
    public Connection getConnection()
    {
        return this.conn;
    }

    //Establish the connection
    public void establishConnection()
    {
        if (this.conn == null)
        {
            try
            {
                conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                System.out.println("connected");
            }
            catch (SQLException ex)
            {
                System.out.println("not connected");
            }
        }
    }

    //close the connection to the database
    public void closeConnections()
    {
        if (conn != null)
        {
            try
            {
                conn.close();
                System.out.println("connection succesfully closed");
            }
            catch (SQLException ex)
            {
                System.out.println("connection failed to close");
            }
        }
    }
    
    //checks to see if a table exists
    //returns true if it exists or false if it does not
    public boolean checkTable(String name)
    {
        try
        {
            DatabaseMetaData dbmd = this.conn.getMetaData();
            String[] types =
            {
                "TABLE"
            };
            Statement statement = this.conn.createStatement();
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
}
