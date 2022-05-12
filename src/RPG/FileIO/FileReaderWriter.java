/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.FileIO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alex
 * 
 * This class is this programs main File I/O component
 * It contains the methods for reading and writing which is used in the other File I/O
 * classes
 */
public class FileReaderWriter
{
    
    //add every line from a file specified by the file path into a list and return the list to the caller
    public List<String> readFile(String filePath)
    {
        List<String> tempList = new ArrayList<>();
        
        try
        {
            BufferedReader input = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = input.readLine()) != null) //checks to see if line is at the end of the file
            {
                tempList.add(line);
            }

            input.close();
        }
        catch (FileNotFoundException e)  //returns null if it throws an exception
        {
            return null;
        }
        catch (IOException e)
        {
            return null;
        }
        
        return tempList;
    }

    //writes a single string line to the end of a file specified by the filepath
    public void writeLineToFile(String line, String filePath)
    {
        try 
        {
            PrintWriter pw = new PrintWriter(new FileOutputStream(filePath, true)); //write to the end of a file
            
            pw.println(line);

            pw.close();
        }
        catch (FileNotFoundException e) //does nothing if the file is not found
        {
        }
    }
    
    //writes a list to a specified file line by line
    //this overwrites the file to reduce the chances of errors
    public void writeFile(List<String> list, String filePath)
    {
        try
        {
            PrintWriter pw = new PrintWriter(new FileOutputStream(filePath)); //write to a file

            for (String entry : list) //adds each line of the list to the file
            {
                pw.println(entry);
            }

            pw.close();
        }
        catch (FileNotFoundException e) //does nothing if the file is not found
        {
        }
    }
}
