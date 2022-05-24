/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.MainMethod;

import RPG.GUI.GUI;
import javax.swing.SwingUtilities;

/**
 *
 * @author alex
 *
 * This class contains the main method for the game
 */
public class RunGame
{

    public static void main(String args[])
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                GUI gui = new GUI();
            }
        });
    }
}
