/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

/**
 *
 * @author alex
 */
public class View extends JFrame
{

    private final ContentGenerator cg;
    private final Controller controller;
    private final Container container;
    private JPanel titleScreen, loadGameScreen, newGameScreen, mainGameScreen;
    private JTextField loadTextField;
    private JLabel errorLabel, mainLabel;
    private JList classList;
    private JSpinner numRoomSpinner;

    public View(Controller controller)
    {
        this.controller = controller;
        this.cg = new ContentGenerator(controller);

        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.black);

        container = this.getContentPane();

        setTitleScreen();
    }

    private JPanel initPanel(JPanel panel)
    {
        panel = new JPanel();

        panel.setBackground(Color.black);
        panel.setBounds(0, 0, 800, 600);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        return panel;
    }

    public void setTitleScreen()
    {
        removeCurrentScreen();

        titleScreen = initPanel(titleScreen);

        titleScreen.add(Box.createVerticalStrut(50));
        titleScreen.add(cg.titalPanel());
        titleScreen.add(cg.startButtonPanel());

        container.add(titleScreen);

        this.setVisible(true);
    }

    private void removeCurrentScreen()
    {
        if (titleScreen != null)
        {
            titleScreen.setVisible(false);
        }
        if (mainGameScreen != null)
        {
            mainGameScreen.setVisible(false);
        }
        if (loadGameScreen != null)
        {
            loadGameScreen.setVisible(false);
        }
        if (newGameScreen != null)
        {
            newGameScreen.setVisible(false);
        }
        this.validate();
        this.repaint();
    }

    public void setGameScreen()
    {
        removeCurrentScreen();

        mainGameScreen = initPanel(mainGameScreen);

        mainLabel = cg.mainLabel();

        mainGameScreen.add(cg.gamePanel().add(mainLabel));

        mainLabel.setText("LOADED INTO GAME");

        container.add(mainGameScreen);

        this.validate();
        this.repaint();
    }

    public void setLoadScreen()
    {
        removeCurrentScreen();

        loadGameScreen = initPanel(loadGameScreen);

        loadTextField = cg.loadTextField();

        errorLabel = cg.errorLabel();

        JPanel loadTextPanel = cg.loadGameTextPanel();
        loadTextPanel.add(loadTextField);
        
        JPanel errorPanel = new JPanel();
        errorPanel.add(errorLabel);
        errorPanel.setBackground(Color.black);

        JPanel labelPanel = new JPanel();
        JLabel label = new JLabel("LOAD PREVIOUS GAME");
        label.setForeground(Color.white);
        label.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        labelPanel.add(label);
        labelPanel.setBackground(Color.black);

        loadGameScreen.add(Box.createVerticalStrut(50));
        loadGameScreen.add(labelPanel);
        loadGameScreen.add(Box.createVerticalStrut(50));
        loadGameScreen.add(errorPanel);
        loadGameScreen.add(Box.createVerticalStrut(50));
        loadGameScreen.add(loadTextPanel);

        container.add(loadGameScreen);

        loadGameScreen.setVisible(true);

        this.validate();
        this.repaint();
    }

    public void setNewGameScreen()
    {
        removeCurrentScreen();

        newGameScreen = initPanel(newGameScreen);

        classList = cg.classList();
        numRoomSpinner = cg.NumRoomsSpinner();

        newGameScreen.add(Box.createVerticalStrut(50));
        newGameScreen.add(cg.newGamePanel());
        newGameScreen.add(cg.newGameListPanel().add(classList));
        newGameScreen.add(Box.createVerticalStrut(50));
        newGameScreen.add(cg.newGameNumRoomsPanel().add(numRoomSpinner));
        newGameScreen.add(Box.createVerticalStrut(10));
        newGameScreen.add(cg.newGameButton());

        container.add(newGameScreen);

        newGameScreen.setVisible(true);

        this.validate();
        this.repaint();
    }

    public void updateMainLabel(String text)
    {
        mainLabel.setText(text);
    }

    public String getLoadTextField()
    {
        return loadTextField.getText();
    }

    public void updateErrorLabel(String text)
    {
        errorLabel.setText(text);
    }
}
