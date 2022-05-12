/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.FileIO;

import java.util.Date;

/**
 *
 * @author alex
 * 
 * This class is used to log all user input and any errors that occur as log4j has proved to be bad
 */
public class Logger
{
    //instantiates a filereaderwriter
    private final FileReaderWriter readerWriter = new FileReaderWriter();
    
    //writes the string to be logged into the log.txt with the date and time the user inputted it
    public void logInput(String input)
    {
        input = new Date() + " (log): " + input;
        readerWriter.writeLineToFile(input, "./resources/log.txt");
    }
}
