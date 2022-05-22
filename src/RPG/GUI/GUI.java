/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.GUI;

/**
 *
 * @author alex
 */
public class GUI
{
    private final Model model;
    private final View view;
    private final Controller controller;
    
    public GUI() 
    {
        this.model = new Model();
        this.controller = new Controller(model);
        this.view = new View(controller);
        this.model.setView(view);
    }
}
