/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.FileIO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author alex
 *
 * This class maintains control over access to the leaderboard data and
 * writes/reads from the leaderboard file.
 *
 * The leaderboard is for users to store their personal highscore for a
 * character if they wish to
 */
public class GameWinnerLeaderboard
{

    //instantiates empty hashmap and file reader
    //map is used to store leaderboard data as it has a unique key (players username)
    //and a value which may be repeated (the players score) 
    private final Map<String, Integer> leaderboard = new HashMap<>();
    private final FileReaderWriter readerWriter = new FileReaderWriter();

    //instantiates the map which stores users and their score
    public GameWinnerLeaderboard()
    {
        instantiateMap();
    }

    //This method attempts to add a players score to the leaderboard
    //if their score is added it returns true
    //otherwise it returns false
    public boolean addLeaderboard(String playerName, int score)
    {
        System.out.println("Adding: " + playerName + " [" + score + "]\n");
        
        if (leaderboard.containsKey(playerName)) //checks if they have a preexisting score
        {
            if (leaderboard.get(playerName) < score) //checks that their new score is higher
            {
                System.out.println("Congratulations!");
                System.out.println("Your new score of " + score + "is larger than your old score" + leaderboard.get(playerName));
                leaderboard.replace(playerName, score);
                writeLeaderboard();
                return true;//if score is higher it adds it and returns true
            }
        }
        else
        {
            leaderboard.put(playerName, score);
            writeLeaderboard();
            return true;//if they do not have a previous score it adds the score and returns true
        }

        return false; //otherwise if they have a previous score which is higher than their current one it returns false
    }

    //This method iterates through the hashmap and prints out the map in alphabetical order
    public void printLeaderboard()
    {
        List<String> tempList = new ArrayList<>(); //temp array to use for sorting

        for (Map.Entry<String, Integer> entry : leaderboard.entrySet()) //adds each value of the map to the array
        {
            tempList.add(entry.getKey() + " [" + entry.getValue() + "]");
        }

        Collections.sort(tempList); //sorts the map alphabetically

        int count = 1;
        for (int i = tempList.size() - 1; i >= 0; i--)//prints out the user and their score
        {
            System.out.println(count + ": " + tempList.get(i));
            count++;
        }
    }

    //This method adds each key and value into the map to a list in the format it is meant to be saved in
    private void writeLeaderboard()
    {
        List<String> tempList = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : leaderboard.entrySet())
        {
            tempList.add(entry.getKey() + " " + entry.getValue());
        }

        readerWriter.writeFile(tempList, "./resources/leaderboard.txt"); //writes the created list to the file
    }

    //read the file into a list then splits the line into its key and value using a scanner
    //it adds this formatted line into the map
    private void instantiateMap()
    {
        List<String> tempList = readerWriter.readFile("./resources/leaderboard.txt"); //reads list from leaderboard file

        if (tempList != null)
        {
            for (String line : tempList)
            {
                Scanner sc = new Scanner(line);
                String name = sc.next();
                int score = sc.nextInt();
                leaderboard.put(name, score);
            }
        }
    }
}
