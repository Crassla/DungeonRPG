/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.GameSetup;

import RPG.FileIO.NameList;
import RPG.EnemyClasses.EnemyType;
import RPG.PlayerClasses.Player;
import RPG.PlayerClasses.Wizard;
import RPG.PlayerClasses.Barbarian;
import RPG.PlayerClasses.Paladin;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alex
 *
 * This class stores and controls all game data and manages data access
 */
public class Game
{

    //game variables
    private List<Room> map;
    private Player player;
    private int mapSize;

    //instaniates a new game object
    public Game(int gameLength, int playerType)
    {
        //instantiates the map list
        map = new ArrayList<>();
        NameList nameList = new NameList(); //gets a gamelist object for the random allocation of room and enemy names

        for (int i = 0; i < gameLength; i++) //creates a map based on the game length
        {
            if (i == 0)
            {
                map.add(new Room(nameList.getRandomRoomName(), EnemyType.BABY, nameList.getRandomEnemyName())); //first room is always a baby object
            }
            else
            {
                if (i % 5 == 0)
                {
                    map.add(new Room(nameList.getRandomRoomName(), EnemyType.MINIBOSS, nameList.getRandomEnemyName())); //every 5 rooms there is a miniboss
                }
                else
                {
                    if (i % 7 == 0)
                    {
                        map.add(new Room(nameList.getRandomRoomName(), EnemyType.BOSS, nameList.getRandomEnemyName())); //every 7 rooms there is a boss
                    }
                    else
                    {
                        map.add(new Room(nameList.getRandomRoomName(), EnemyType.NORMAL, nameList.getRandomEnemyName())); //every other room is a normal enemy
                    }
                }
            }

            //creates the player class based on the class they want
            switch (playerType)
            {
                case 1:
                    player = new Paladin();
                    break;
                case 2:
                    player = new Wizard();
                    break;
                case 3:
                    player = new Barbarian();
                    break;
                default: //for error prevention if an incorrect playertype is inputted a paladin is created
                    player = new Paladin();
                    break;
            }

            this.mapSize = gameLength; //maintains an object with the game length
        }

        player.setRoom(map.get(0)); //sets the players room to the first room in the map
    }

    //instaniates a preexisting game object
    public Game(int gameLength, Player player, int startIncrement)
    {
        //instantiates the map list
        map = new ArrayList<>();
        NameList nameList = new NameList(); //gets a gamelist object for the random allocation of room and enemy names

        for (int i = startIncrement; i < gameLength; i++)  //runs a loop between the room where the player saved and the games length 
        {
            if (i == 0)
            {
                map.add(new Room(nameList.getRandomRoomName(), EnemyType.BABY, nameList.getRandomEnemyName()));
            }
            else
            {
                if (i % 5 == 0)
                {
                    map.add(new Room(nameList.getRandomRoomName(), EnemyType.MINIBOSS, nameList.getRandomEnemyName()));
                }
                else
                {
                    if (i % 7 == 0)
                    {
                        map.add(new Room(nameList.getRandomRoomName(), EnemyType.BOSS, nameList.getRandomEnemyName()));
                    }
                    else
                    {
                        map.add(new Room(nameList.getRandomRoomName(), EnemyType.NORMAL, nameList.getRandomEnemyName()));
                    }
                }
            }

            this.player = player; //sets the player object

            this.mapSize = gameLength; //sets the map size
        }

        player.setRoom(map.get(0)); //sets the players room to the first room in the map
    }

    //returns the map size
    public int getMapSize()
    {
        return this.mapSize;
    }
    
    //returns the current map size (number of rooms left)
    public int getMapLength()
    {
        return this.map.size();
    }
    
    //returns if the map is empty
    public boolean mapEmpty()
    {
        return this.map.isEmpty();
    }

    //returns the player
    public Player getPlayer()
    {
        return this.player;
    }

    //switchs the plauers room to the next room and removes the current room from the map
    //returns true if the map is empty
    public boolean nextRoom()
    {
        if (map.isEmpty())
        {
            return true;
        }
        else
        {
            map.remove(0);

            if (map.isEmpty())
            {
                return true;
            }

            player.setRoom(map.get(0));
            return false;
        }
    }
    
    @Override
    public String toString()
    {
        String output = "";
        
        for (Room room : map)
        {
            output += room +"\n";
        }
        
        return output;
    }
}
