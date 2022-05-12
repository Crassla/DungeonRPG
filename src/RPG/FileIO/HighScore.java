/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.FileIO;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author alex
 *
 * This class maintains a sorted file/list of all scores users have saved It
 * allows for duplicates and prints out from highest score to lowest score
 */
public class HighScore
{

    //instantiates the variables
    //as highscores allows for duplicated list is the best data structure for this data structure
    private List<String> highScores = new ArrayList<>();
    private final FileReaderWriter readerWriter = new FileReaderWriter();

    //instantiates the highscores array from the file
    public HighScore()
    {
        instantiateArray();
    }

    //prints the top 10 highscores
    public void printHighScore()
    {
        int count = 1;

        if (highScores == null)
        {
            highScores = new ArrayList<>();
        }

        for (String entry : highScores) //tokenziers each string then prints it
        {
            StringTokenizer st = new StringTokenizer(entry, " ");
            String playerName = st.nextToken();
            int score = Integer.parseInt(st.nextToken());

            System.out.println(count + ": " + (playerName + " [" + score + "]"));
            count++;

            if (count > 10)
            {
                break; //breaks the for loop when 10 scores have been printed
            }
        }
    }

    //adds a highscore in order into the file
    //returns true if the highscore is added into a top 10 position
    public boolean addHighScore(String playerName, int score)
    {
        int count = 0;

        System.out.println("Adding " + playerName + " [" + score + "]\n");

        if (highScores == null)
        {
            highScores = new ArrayList<>();
        }

        if (highScores.isEmpty()) //if the array is empty it adds in the 
        {
            highScores.add(playerName + " " + score);
            this.writeHighScore();
            return true;
        }
        else
        {
            for (String entry : highScores) //loops through the high score list 
            {
                StringTokenizer st = new StringTokenizer(entry, " ");
                st.nextToken();

                int currentScore = Integer.parseInt(st.nextToken());

                if (score >= currentScore) //adds the high score into the correct position in the array
                {
                    highScores.add(count, (playerName + " " + score));

                    this.writeHighScore();

                    return (count < 9); //returns true if the user made the top 10 highscores
                }

                count++;
            }

            highScores.add(playerName + " " + score);
            this.writeHighScore();

            return (count < 9);  //returns true if the user made the top 10 highscores
        }
    }

    //writes the list to highscores.txt
    private void writeHighScore()
    {
        readerWriter.writeFile(highScores, "./resources/highScores.txt");
    }

    //reads the file into the highscores list
    private void instantiateArray()
    {
        highScores = readerWriter.readFile("./resources/highScores.txt");
    }
}
