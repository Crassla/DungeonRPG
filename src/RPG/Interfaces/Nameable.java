/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.Interfaces;

/**
 *
 * @author Alex
 *
 * This class is implemented for all classes which require a name E.g Room,
 * Enemy and Player
 */
public interface Nameable
{
    //sets the name
    public void setName(String name);

    //returns the name
    public String getName();
}
