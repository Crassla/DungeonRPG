/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.MainMethod;

import RPG.GUIMVC.Controller;
import RPG.GUIMVC.Model;
import RPG.GUIMVC.View;

/**
 *
 * @author alex
 * 
 * This is the GUI manager class which manages all of the instances of the MVC model
 * It maintains the GUI
 * 
 * It runs the game by creating a new GUI instance
 */
public class GUI
{
    //MVC final objects
    private final Model model;
    private final View view;
    private final Controller controller;
    
    //instantiates the GUI and the game
    public GUI() 
    {
        this.model = new Model();
        this.controller = new Controller(model);
        this.view = new View(controller);
        this.model.setView(view);
    }
    
    public static void main(String args[])
    {
        GUI gui = new GUI();
    }
}
