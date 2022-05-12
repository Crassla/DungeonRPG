/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.FileIO;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Alex
 *
 * This class maintains the list of roomNames and enemyNames contained in their
 * corresponding txt files
 */
public class NameList
{

    //instaintiated the set lists and reader writer
    //sets are used for the naming lists as each name should be unique and this is not checked
    //when reading from the files
    private final Set<String> roomName = new HashSet<>();
    private final Set<String> enemyName = new HashSet<>();
    private final FileReaderWriter readerWriter = new FileReaderWriter();

    //instantiates both sets
    public NameList()
    {
        instantiateSets();
    }

    //this method reads each file into a temperary list then iterates through the list adding 
    //each entry into the set
    private void instantiateSets()
    {
        List<String> tempList = readerWriter.readFile("./resources/enemyNames.txt");

        for (String entry : tempList)
        {
            enemyName.add(entry);
        }

        tempList = readerWriter.readFile("./resources/roomNames.txt");

        for (String entry : tempList)
        {
            roomName.add(entry);
        }
    }

    //this method returns a string picked randomly from the enemy list
    //it removes the string from the list to ensure no duplicate returns
    public String getRandomEnemyName()
    {
        Random rand = new Random();

        int randNumber = rand.nextInt(enemyName.size()); //get a random number
        int i = 0;
        String output = null;

        for (String name : enemyName) //iterate through the list until random number is found and add name at this number
        {
            if (i == randNumber)
            {
                output = name;
            }
            i++;
        }

        enemyName.remove(output); //remove the output found

        return output; //return the output
    }

    //this method returns a string picked randomly from the room list
    //it removes the string from the list to ensure no duplicate returns
    public String getRandomRoomName()
    {
        Random rand = new Random();

        int randNumber = rand.nextInt(roomName.size()); //get a random number
        int i = 0;
        String output = null;

        for (String name : roomName) //iterate through the list until random number is found and add name at this number
        {
            if (i == randNumber)
            {
                output = name;
            }
            i++;
        }

        roomName.remove(output); //remove the output found

        return output;//return the output
    }
}
