/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 *
 * @author alex
 */
public class View extends JFrame
{

    private final ContentGenerator cg;
    private final Controller controller;
    private final Container container;
    private JPanel titleScreen, loadGameScreen, newGameScreen, mainGameScreen, encounterScreen;
    private JTextField loadTextField, createGameTextField;
    private JLabel errorLabel, mainLabel, playerHealth, playerDamage, playerRollModifier, playerArmourClass, enemyHealth;
    private JList classList;
    private JSpinner numRoomSpinner;

    public View(Controller controller)
    {
        this.controller = controller;
        this.cg = new ContentGenerator(controller);

        this.setSize(1000, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.black);
        this.setLocationRelativeTo(null);

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
            container.remove(titleScreen);
            titleScreen.setVisible(false);
        }
        if (mainGameScreen != null)
        {
            container.remove(mainGameScreen);
            mainGameScreen.setVisible(false);
        }
        if (loadGameScreen != null)
        {
            container.remove(loadGameScreen);
            loadGameScreen.setVisible(false);
        }
        if (newGameScreen != null)
        {
            container.remove(newGameScreen);
            newGameScreen.setVisible(false);
        }
        if (mainGameScreen != null)
        {
            container.remove(mainGameScreen);
            mainGameScreen.setVisible(false);
        }
        if (encounterScreen != null)
        {
            container.remove(encounterScreen);
            encounterScreen.setVisible(true);
        }
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
        createGameTextField = cg.createGameTextField();

        classList = cg.classList();
        numRoomSpinner = cg.NumRoomsSpinner();

        newGameScreen.add(Box.createVerticalStrut(50));
        newGameScreen.add(cg.newGamePanel());
        newGameScreen.add(cg.newGameListPanel().add(classList));
        newGameScreen.add(Box.createVerticalStrut(50));
        newGameScreen.add(cg.newGameNumRoomsPanel().add(numRoomSpinner));
        newGameScreen.add(Box.createVerticalStrut(10));
        newGameScreen.add(createGameTextField);
        newGameScreen.add(Box.createVerticalStrut(10));
        newGameScreen.add(cg.newGameButton());

        container.add(newGameScreen);

        newGameScreen.setVisible(true);

        this.validate();
        this.repaint();
    }

    public void setGameScreen()
    {
        removeCurrentScreen();

        mainGameScreen = initPanel(mainGameScreen);

        JPanel panel = new JPanel();
        panel.setBackground(Color.black);

        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.black);

        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.black);
        panel3.setBorder(null);
        
        Font font = new Font("Times New Roman", Font.BOLD, 20);
        
        Border border1 = BorderFactory.createEmptyBorder();
        TitledBorder border2 = BorderFactory.createTitledBorder(border1, "What will you do: >", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, font, Color.white);

        panel3.setBorder(border2);
        
        playerHealth = new JLabel("        Health:        ");
        playerHealth.setForeground(Color.white);
        playerHealth.setFont(new Font("Times New Roman", Font.PLAIN, 22));

        playerDamage = new JLabel("        Damage:        ");
        playerDamage.setForeground(Color.white);
        playerDamage.setFont(new Font("Times New Roman", Font.PLAIN, 22));

        playerArmourClass = new JLabel("        Armour:        ");
        playerArmourClass.setForeground(Color.white);
        playerArmourClass.setFont(new Font("Times New Roman", Font.PLAIN, 22));

        playerRollModifier = new JLabel("        Roll Modifer:        ");
        playerRollModifier.setForeground(Color.white);
        playerRollModifier.setFont(new Font("Times New Roman", Font.PLAIN, 22));

        JButton move = new JButton("MOVE");
        move.setBackground(Color.black);
        move.setForeground(Color.white);
        move.setFont(new Font("Times New Roman", Font.BOLD, 20));
        move.addActionListener(controller.moveHandler());

        JButton exit = new JButton("EXIT");
        exit.setBackground(Color.black);
        exit.setForeground(Color.white);
        exit.setFont(new Font("Times New Roman", Font.BOLD, 20));

        panel3.add(move);
        panel3.add(exit);

        panel.add(playerHealth);
        panel.add(playerDamage);
        panel.add(playerArmourClass);
        panel.add(playerRollModifier);

        mainLabel = cg.mainLabel();
        panel2.add(mainLabel);

        mainGameScreen.add(Box.createVerticalStrut(50));
        mainGameScreen.add(panel);
        mainGameScreen.add(Box.createVerticalStrut(70));
        mainGameScreen.add(panel2);
        mainGameScreen.add(Box.createVerticalStrut(70));
        mainGameScreen.add(panel3);
        mainGameScreen.add(Box.createVerticalStrut(90));

        container.add(mainGameScreen);
        
        mainGameScreen.setVisible(true);

        this.validate();
        this.repaint();
    }
    
    public void setEncounterScreen()
    {
        removeCurrentScreen();
                
        Font font = new Font("Times New Roman", Font.BOLD, 20);
        encounterScreen = initPanel(encounterScreen);

        JPanel panel = new JPanel();
        panel.setBackground(Color.black);
        
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.black);
        panel1.setBounds(0, 0, 50, 0);
        
        Border border3 = BorderFactory.createEmptyBorder();
        TitledBorder border4 = BorderFactory.createTitledBorder(border3, "Player Health:", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, font, Color.white);

        panel1.setBorder(border4);
        
        JPanel panel4 = new JPanel();
        panel4.setBackground(Color.black);
        panel4.setBounds(0, 0, 50, 0);
        
        Border border5 = BorderFactory.createEmptyBorder();
        TitledBorder border6 = BorderFactory.createTitledBorder(border5, "Enemy Health:", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, font, Color.white);
        
        panel.add(panel1);
        panel.add(panel4);

        panel4.setBorder(border6);

        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.black);

        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.black);
        panel3.setBorder(null);
        
        Border border1 = BorderFactory.createEmptyBorder();
        TitledBorder border2 = BorderFactory.createTitledBorder(border1, "What will you do: >", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, font, Color.white);

        panel3.setBorder(border2);
        
        playerHealth = new JLabel();
        playerHealth.setForeground(Color.white);
        playerHealth.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        
        enemyHealth = new JLabel();
        enemyHealth.setForeground(Color.white);
        enemyHealth.setFont(new Font("Times New Roman", Font.PLAIN, 22));

        JButton attack = new JButton("ATTACK");
        attack.setBackground(Color.black);
        attack.setForeground(Color.white);
        attack.setFont(new Font("Times New Roman", Font.BOLD, 20));
        attack.addActionListener(controller.attackHandler());

        JButton block = new JButton("BLOCK");
        block.setBackground(Color.black);
        block.setForeground(Color.white);
        block.setFont(new Font("Times New Roman", Font.BOLD, 20));
        
        JButton skill = new JButton("USE SKILL");
        skill.setBackground(Color.black);
        skill.setForeground(Color.white);
        skill.setFont(new Font("Times New Roman", Font.BOLD, 20));

        panel3.add(attack);
        panel3.add(block);
        panel3.add(skill);

        panel1.add(playerHealth);   
        panel4.add(enemyHealth);

        mainLabel = cg.mainLabel();
        panel2.add(mainLabel);

        encounterScreen.add(Box.createVerticalStrut(50));
        encounterScreen.add(panel);
        encounterScreen.add(Box.createVerticalStrut(70));
        encounterScreen.add(panel2);
        encounterScreen.add(Box.createVerticalStrut(70));
        encounterScreen.add(panel3);
        encounterScreen.add(Box.createVerticalStrut(90));

        mainLabel.setText("LOADED INTO ENCOUNTER");

        container.add(encounterScreen);

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

    public int getNumRooms()
    {
        JComponent editor = numRoomSpinner.getEditor();
        JFormattedTextField tf = ((JSpinner.DefaultEditor) editor).getTextField();
        if (tf.getText() == null)
        {
            return -1;
        }
        return Integer.parseInt(tf.getText());
    }

    public int getPlayerClass()
    {
        return classList.getSelectedIndex();
    }

    public String getPlayerName()
    {
        return createGameTextField.getText();
    }

    public void setPlayerHealthLabel(String name, String playerClass, String text)
    {
        for (int i = 10; i > text.length(); i--)
        {
            text += " ";
        }

        text = playerClass + " " + name + " Health:   " + text;

        for (int i = 10; i > text.length(); i--)
        {
            text += " ";
        }

        playerHealth.setText(text);
    }

    public void setPlayerDamageLabel(String text)
    {
        for (int i = 10; i > text.length(); i--)
        {
            text += " ";
        }

        text = "Damage:   " + text;

        for (int i = 10; i > text.length(); i--)
        {
            text += " ";
        }

        playerDamage.setText(text);
    }

    public void setPlayerArmourLabel(String text)
    {
        for (int i = 10; i > text.length(); i--)
        {
            text += " ";
        }

        text = "Armour:   " + text;

        for (int i = 10; i > text.length(); i--)
        {
            text += " ";
        }

        playerArmourClass.setText(text);
    }

    public void setPlayerRollLabel(String text)
    {
        for (int i = 10; i > text.length(); i--)
        {
            text += " ";
        }

        text = "Roll Modifer:   " + text;

        for (int i = 10; i > text.length(); i--)
        {
            text += " ";
        }

        playerRollModifier.setText(text);
    }
    
    public void setEncounterPlayerHealth(String text)
    {
        text = "            " + text + "            ";
        playerHealth.setText(text);
    }
    
    public void setEnemyHealth(String text)
    {
        text = "            " + text + "            ";
        enemyHealth.setText(text);
    }
}
