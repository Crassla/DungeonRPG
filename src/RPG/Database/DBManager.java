/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author alex
 */
public final class DBManager
{

    private static final String USER_NAME = "pdc"; //your DB username
    private static final String PASSWORD = "pdc"; //your DB password
    private static final String URL = "jdbc:derby:DungeonRPG; create=true";  //url of the DB host

    Connection conn;

    public DBManager()
    {
        establishConnection();
    }

    public Connection getConnection()
    {
        return this.conn;
    }

    //Establish connection
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

    public void closeConnections()
    {
        if (conn != null)
        {
            try
            {
                conn.close();
            }
            catch (SQLException ex)
            {
            }
        }
    }
}
