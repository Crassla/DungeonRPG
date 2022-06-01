/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.GUIMVC;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 *
 * @author alex
 * 
 * This class is the view in the MVC design It controls what the user sees
 */
public class View extends JFrame
{

    //instantiates all of the variables
    private final Controller controller;
    private Container container;
    private JPanel titleScreen, loadGameScreen, newGameScreen, mainGameScreen, encounterScreen, saveScreen, endGameScreen, boardScreen;
    private JTextField loadTextField, createGameTextField;
    private JLabel errorLabel, mainLabel, playerHealth, playerDamage, playerRollModifier, playerArmourClass, enemyHealth, roomsLeft;
    private JList classList;
    private JSpinner numRoomSpinner;
    private JButton attack, block, skill, saveScoreButton, saveLeaderButton, exit;

    //instantiates a new view object
    public View(Controller controller)
    {
        this.controller = controller;

        initView(); //runs the initview function
    }
    
    //initializes the view panel
    private void initView()
    {
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.black);
        setLocationRelativeTo(null);
        setTitle("DUNGEON RPG");
        
        this.container = getContentPane();

        setTitleScreen();
    }

    //intialises a game panel pased through to it
    private JPanel initPanel(JPanel panel)
    {
        panel = new JPanel();

        panel.setBackground(Color.black);
        panel.setBounds(0, 0, 800, 600);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        return panel;
    }

    //removes all current screens to prevent the overloading of the jFrame
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
            encounterScreen.setVisible(false);
        }
        if (saveScreen != null)
        {
            container.remove(saveScreen);
            saveScreen.setVisible(false);
        }
        if (endGameScreen != null)
        {
            container.remove(endGameScreen);
            endGameScreen.setVisible(false);
        }
        if (boardScreen != null)
        {
            container.remove(boardScreen);
            boardScreen.setVisible(false);
        }

        this.validate();
        this.repaint();
    }

    //sets the title screen of the game
    public void setTitleScreen()
    {
        removeCurrentScreen();

        titleScreen = initPanel(titleScreen);

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.black);
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.black);
        JLabel label;

        label = new JLabel("DUNGEON RPG");
        label.setForeground(Color.white);
        label.setFont(new Font("Times New Roman", Font.PLAIN, 80));

        JButton startButton = new JButton("NEW GAME");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.white);
        startButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        startButton.setFocusPainted(false);

        JButton loadButton = new JButton("LOAD GAME");
        loadButton.setBackground(Color.black);
        loadButton.setForeground(Color.white);
        loadButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        loadButton.setFocusPainted(false);

        JButton lButton = new JButton("VIEW INSTRUCTIONS");
        lButton.setBackground(Color.black);
        lButton.setForeground(Color.white);
        lButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lButton.setFocusPainted(false);
        
        JButton exitButton = new JButton("QUIT GAME");
        exitButton.setBackground(Color.black);
        exitButton.setForeground(Color.white);
        exitButton.setFont(new Font("Times New Roman", Font.BOLD, 20));

        startButton.addActionListener(controller.newGameHandler());
        loadButton.addActionListener(controller.loadGameHandler());
        lButton.addActionListener(controller.showInstructionsHandler());
        exitButton.addActionListener(controller.closeGameHandler());

        panel1.add(label);
        panel2.add(startButton);
        panel2.add(loadButton);
        panel2.add(lButton);
        panel2.add(exitButton);

        titleScreen.add(Box.createVerticalStrut(50));
        titleScreen.add(panel1);
        titleScreen.add(panel2);

        container.add(titleScreen);

        this.setVisible(true);
    }

    //sets the load screen of the game
    public void setLoadScreen()
    {
        removeCurrentScreen();

        loadGameScreen = initPanel(loadGameScreen);

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.black);
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.black);
        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.black);

        JLabel label = new JLabel("LOAD PREVIOUS GAME");
        label.setForeground(Color.white);
        label.setFont(new Font("Times New Roman", Font.PLAIN, 50));

        loadTextField = new JTextField(20);
        loadTextField.setText("Enter Previous Name Here");
        loadTextField.setBackground(Color.black);
        loadTextField.setForeground(Color.white);
        loadTextField.setFont(new Font("Times New Roman", Font.BOLD, 20));

        errorLabel = new JLabel();
        errorLabel.setBackground(Color.black);
        errorLabel.setForeground(Color.red);
        errorLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        JButton textButton = new JButton("ENTER");
        textButton.setBackground(Color.black);
        textButton.setForeground(Color.white);
        textButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        textButton.setFocusPainted(false);
        textButton.addActionListener(controller.loadGameTextFieldHandler());

        JButton textButton2 = new JButton("EXIT");
        textButton2.setBackground(Color.black);
        textButton2.setForeground(Color.white);
        textButton2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        textButton2.setFocusPainted(false);
        textButton2.addActionListener(controller.restartGameHandler());

        panel1.add(label);
        panel2.add(errorLabel);
        panel3.add(textButton);
        panel3.add(loadTextField);
        panel3.add(textButton2);

        loadGameScreen.add(Box.createVerticalStrut(50));
        loadGameScreen.add(panel1);
        loadGameScreen.add(Box.createVerticalStrut(50));
        loadGameScreen.add(panel2);
        loadGameScreen.add(Box.createVerticalStrut(50));
        loadGameScreen.add(panel3);

        container.add(loadGameScreen);

        loadGameScreen.setVisible(true);

        this.validate();
        this.repaint();
    }

    //sets the new game screen of the game
    public void setNewGameScreen()
    {
        removeCurrentScreen();

        newGameScreen = initPanel(newGameScreen);

        Font font = new Font("Times New Roman", Font.PLAIN, 20);
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.black);
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.black);
        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.black);
        JPanel panel4 = new JPanel();
        panel4.setBackground(Color.black);
        JPanel panel5 = new JPanel();
        panel5.setBackground(Color.black);
        JPanel panel6 = new JPanel();
        panel6.setBackground(Color.black);
        JLabel label;

        label = new JLabel("CREATE NEW GAME");
        label.setForeground(Color.white);
        label.setFont(new Font("Times New Roman", Font.PLAIN, 50));

        createGameTextField = new JTextField(20);
        createGameTextField.setText("");
        createGameTextField.setBackground(Color.black);
        createGameTextField.setForeground(Color.white);
        createGameTextField.setFont(new Font("Times New Roman", Font.BOLD, 20));

        Border border1 = BorderFactory.createLineBorder(Color.white);
        TitledBorder border2 = BorderFactory.createTitledBorder(border1, "Enter Characters Name", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, font, Color.white);

        createGameTextField.setBorder(border2);
        
        errorLabel = new JLabel();
        errorLabel.setBackground(Color.black);
        errorLabel.setForeground(Color.red);
        errorLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        String[] classLists =
        {
            "Paladin", "Wizard", "Barbarian"
        };

        classList = new JList(classLists);
        classList.setFixedCellWidth(250);
        classList.setBackground(Color.black);
        classList.setForeground(Color.white);
        classList.setFont(font);

        DefaultListCellRenderer renderer = (DefaultListCellRenderer) classList.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        TitledBorder border3 = BorderFactory.createTitledBorder(border1, "Choose Class", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, font, Color.white);

        classList.setBorder(border3);

        numRoomSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));

        JComponent editor = numRoomSpinner.getEditor();
        JFormattedTextField tf = ((JSpinner.DefaultEditor) editor).getTextField();
        tf.setFont(font);
        tf.setColumns(20);
        tf.setBackground(Color.black);
        tf.setForeground(Color.white);
        tf.setHorizontalAlignment(SwingConstants.CENTER);
        tf.setEditable(false);

        TitledBorder border4 = BorderFactory.createTitledBorder(border1, "Chose Number of Rooms", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, font, Color.white);

        tf.setBorder(border4);

        numRoomSpinner.setBackground(Color.black);
        numRoomSpinner.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        JButton textButton = new JButton("ENTER");
        textButton.setBackground(Color.black);
        textButton.setForeground(Color.white);
        textButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        textButton.setFocusPainted(false);
        textButton.addActionListener(controller.createGameHandler());

        panel1.add(label);
        panel2.add(errorLabel);
        panel3.add(classList);
        panel4.add(numRoomSpinner);
        panel5.add(createGameTextField);
        panel6.add(textButton);

        newGameScreen.add(Box.createVerticalStrut(50));
        newGameScreen.add(panel1);
        newGameScreen.add(Box.createVerticalStrut(50));
        newGameScreen.add(panel2);
        newGameScreen.add(panel3);
        newGameScreen.add(Box.createVerticalStrut(50));
        newGameScreen.add(panel4);
        newGameScreen.add(Box.createVerticalStrut(10));
        newGameScreen.add(panel5);
        newGameScreen.add(Box.createVerticalStrut(10));
        newGameScreen.add(panel6);
        newGameScreen.add(Box.createVerticalStrut(50));

        container.add(newGameScreen);

        newGameScreen.setVisible(true);

        this.validate();
        this.repaint();
    }

    //sets the main game screen
    public void setGameScreen()
    {
        removeCurrentScreen();

        mainGameScreen = initPanel(mainGameScreen);

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.black);
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

        roomsLeft = new JLabel();
        roomsLeft.setForeground(Color.white);
        roomsLeft.setFont(new Font("Times New Roman", Font.PLAIN, 22));

        mainLabel = new JLabel("", SwingConstants.CENTER);
        mainLabel.setBackground(Color.black);
        mainLabel.setForeground(Color.white);
        mainLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        mainLabel.setBorder(null);
        mainLabel.setText("<html>You walk into a dark dungeon, on your left you see an entrance to a room<br><br> What do you want to do?</html>");

        JButton move = new JButton("MOVE");
        move.setBackground(Color.black);
        move.setForeground(Color.white);
        move.setFont(new Font("Times New Roman", Font.BOLD, 20));
        move.addActionListener(controller.moveHandler());

        exit = new JButton("EXIT");
        exit.setBackground(Color.black);
        exit.setForeground(Color.white);
        exit.setFont(new Font("Times New Roman", Font.BOLD, 20));
        exit.addActionListener(controller.exitGameHandler());

        panel1.add(playerHealth);
        panel1.add(playerDamage);
        panel1.add(playerArmourClass);
        panel1.add(playerRollModifier);
        panel1.add(roomsLeft);
        panel2.add(mainLabel);
        panel3.add(move);
        panel3.add(exit);

        mainGameScreen.add(Box.createVerticalStrut(50));
        mainGameScreen.add(panel1);
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

    //sets the encounter screen
    public void setEncounterScreen()
    {
        removeCurrentScreen();

        Font font = new Font("Times New Roman", Font.BOLD, 20);
        encounterScreen = initPanel(encounterScreen);

        JPanel panel = new JPanel();
        panel.setBackground(Color.black);
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.black);
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.black);
        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.black);
        JPanel panel4 = new JPanel();
        panel4.setBackground(Color.black);

        Border border1 = BorderFactory.createEmptyBorder();
        TitledBorder border2 = BorderFactory.createTitledBorder(border1, "Player Health:", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, font, Color.white);

        panel1.setBorder(border2);

        TitledBorder border3 = BorderFactory.createTitledBorder(border1, "Enemy Health:", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, font, Color.white);

        panel4.setBorder(border3);

        TitledBorder border4 = BorderFactory.createTitledBorder(border1, "What will you do: >", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, font, Color.white);

        panel3.setBorder(border4);

        playerHealth = new JLabel();
        playerHealth.setForeground(Color.white);
        playerHealth.setFont(new Font("Times New Roman", Font.PLAIN, 22));

        enemyHealth = new JLabel();
        enemyHealth.setForeground(Color.white);
        enemyHealth.setFont(new Font("Times New Roman", Font.PLAIN, 22));

        attack = new JButton("ATTACK");
        attack.setBackground(Color.black);
        attack.setForeground(Color.white);
        attack.setFont(new Font("Times New Roman", Font.BOLD, 20));
        attack.addActionListener(controller.attackHandler());

        block = new JButton("BLOCK");
        block.setBackground(Color.black);
        block.setForeground(Color.white);
        block.setFont(new Font("Times New Roman", Font.BOLD, 20));
        block.addActionListener(controller.blockHandler());

        skill = new JButton("USE SKILL");
        skill.setBackground(Color.black);
        skill.setForeground(Color.white);
        skill.setFont(new Font("Times New Roman", Font.BOLD, 20));
        skill.addActionListener(controller.skillHandler());

        mainLabel = new JLabel("", SwingConstants.CENTER);
        mainLabel.setBackground(Color.black);
        mainLabel.setForeground(Color.white);
        mainLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        mainLabel.setBorder(null);

        panel.add(panel1);
        panel.add(panel4);
        panel1.add(playerHealth);
        panel2.add(mainLabel);
        panel3.add(attack);
        panel3.add(block);
        panel3.add(skill);
        panel4.add(enemyHealth);

        encounterScreen.add(Box.createVerticalStrut(50));
        encounterScreen.add(panel);
        encounterScreen.add(Box.createVerticalStrut(70));
        encounterScreen.add(panel2);
        encounterScreen.add(Box.createVerticalStrut(70));
        encounterScreen.add(panel3);
        encounterScreen.add(Box.createVerticalStrut(90));

        container.add(encounterScreen);

        this.validate();
        this.repaint();
    }

    //sets the save screen
    public void setSaveScreen()
    {
        removeCurrentScreen();

        Font font = new Font("Times New Roman", Font.BOLD, 20);
        saveScreen = initPanel(saveScreen);

        JLabel label;
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.black);
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.black);
        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.black);
        JPanel panel4 = new JPanel();
        panel4.setBackground(Color.black);

        Border border1 = BorderFactory.createEmptyBorder();
        TitledBorder border2 = BorderFactory.createTitledBorder(border1, "Your current stats:", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, font, Color.white);

        panel2.setBorder(border2);

        label = new JLabel("SAVE CURRENT GAME");
        label.setForeground(Color.white);
        label.setFont(new Font("Times New Roman", Font.PLAIN, 80));

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

        roomsLeft = new JLabel();
        roomsLeft.setForeground(Color.white);
        roomsLeft.setFont(new Font("Times New Roman", Font.PLAIN, 22));

        errorLabel = new JLabel();
        errorLabel.setBackground(Color.black);
        errorLabel.setForeground(Color.red);
        errorLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        errorLabel.setText("");

        JButton saveButton = new JButton("SAVE");
        saveButton.setBackground(Color.black);
        saveButton.setForeground(Color.white);
        saveButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        saveButton.addActionListener(controller.saveGameHandler());

        JButton exitButton = new JButton("DON'T SAVE");
        exitButton.setBackground(Color.black);
        exitButton.setForeground(Color.white);
        exitButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        exitButton.addActionListener(controller.quitGameHandler());

        panel1.add(label);
        panel2.add(playerHealth);
        panel2.add(playerDamage);
        panel2.add(playerArmourClass);
        panel2.add(playerRollModifier);
        panel2.add(roomsLeft);
        panel3.add(errorLabel);
        panel4.add(saveButton);
        panel4.add(exitButton);

        saveScreen.add(panel1);
        saveScreen.add(panel2);
        saveScreen.add(panel3);
        saveScreen.add(panel4);

        container.add(saveScreen);

        this.validate();
        this.repaint();
    }

    //sets the end game screen
    public void setEndGameScreen()
    {
        removeCurrentScreen();

        endGameScreen = initPanel(endGameScreen);

        JLabel label;
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.black);
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.black);
        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.black);

        label = new JLabel("EXIT DUNGEON RPG");
        label.setForeground(Color.white);
        label.setFont(new Font("Times New Roman", Font.PLAIN, 80));

        JButton scoreBoardButton = new JButton("SHOW SCOREBOARD");
        scoreBoardButton.setBackground(Color.black);
        scoreBoardButton.setForeground(Color.white);
        scoreBoardButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        scoreBoardButton.addActionListener(controller.showScoreBoardHandler());

        JButton leaderBoardButton = new JButton("SHOW LEADERBOARD");
        leaderBoardButton.setBackground(Color.black);
        leaderBoardButton.setForeground(Color.white);
        leaderBoardButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        leaderBoardButton.addActionListener(controller.showLeaderBoardHandler());

        JButton newButton = new JButton("NEW GAME");
        newButton.setBackground(Color.black);
        newButton.setForeground(Color.white);
        newButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        newButton.addActionListener(controller.restartGameHandler());

        JButton exitButton = new JButton("QUIT GAME");
        exitButton.setBackground(Color.black);
        exitButton.setForeground(Color.white);
        exitButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        exitButton.addActionListener(controller.closeGameHandler());

        mainLabel.setText("<html>Thank you for playing Dungeon RPG!<br><br>If you want to view the current "
                + "scoreboard or leaderboard please click the button below. <br><br> If you wish to play again please click the play again button!</html>");

        panel1.add(label);
        panel2.add(mainLabel);
        panel3.add(scoreBoardButton);
        panel3.add(leaderBoardButton);
        panel3.add(newButton);
        panel3.add(exitButton);

        endGameScreen.add(panel1);
        endGameScreen.add(panel2);
        endGameScreen.add(panel3);

        container.add(endGameScreen);

        this.validate();
        this.repaint();
    }

    //sets the board screen
    public void setBoardScreen()
    {
        removeCurrentScreen();

        Font font = new Font("Times New Roman", Font.BOLD, 20);
        boardScreen = initPanel(boardScreen);

        JLabel label;
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.black);
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.black);
        JPanel panel4 = new JPanel();
        panel4.setBackground(Color.black);
        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.black);
        panel3.add(errorLabel);
        JPanel panel5 = new JPanel();
        panel5.setBackground(Color.black);

        Border border1 = BorderFactory.createEmptyBorder();
        TitledBorder border2 = BorderFactory.createTitledBorder(border1, "Your final score:", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, font, Color.white);

        panel2.setBorder(border2);

        mainLabel.setText("<html>Congratualtions for beating Dungeon RPG!<br><br>If you want to save your top score to the "
                + "scoreboard or leaderboard please click the button below. <br><br> Once done click exit game to exit!</html>");

        label = new JLabel("SAVE CURRENT GAME");
        label.setForeground(Color.white);
        label.setFont(new Font("Times New Roman", Font.PLAIN, 80));

        playerHealth = new JLabel("");
        playerHealth.setForeground(Color.white);
        playerHealth.setFont(new Font("Times New Roman", Font.PLAIN, 22));

        roomsLeft = new JLabel();
        roomsLeft.setForeground(Color.white);
        roomsLeft.setFont(new Font("Times New Roman", Font.PLAIN, 22));

        errorLabel = new JLabel();
        errorLabel.setBackground(Color.black);
        errorLabel.setForeground(Color.red);
        errorLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        errorLabel.setText("");
        errorLabel.setVisible(true);

        saveScoreButton = new JButton("SAVE SCORE TO SCOREBOARD");
        saveScoreButton.setBackground(Color.black);
        saveScoreButton.setForeground(Color.white);
        saveScoreButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        saveScoreButton.addActionListener(controller.scoreBoardHandler());

        saveLeaderButton = new JButton("SAVE SCORE TO LEADERBOARD");
        saveLeaderButton.setBackground(Color.black);
        saveLeaderButton.setForeground(Color.white);
        saveLeaderButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        saveLeaderButton.addActionListener(controller.saveLeaderBoardHandler());

        JButton quitButton = new JButton("EXIT GAME");
        quitButton.setBackground(Color.black);
        quitButton.setForeground(Color.white);
        quitButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        quitButton.addActionListener(controller.quitGameHandler());

        panel1.add(label);
        panel2.add(playerHealth);
        panel2.add(roomsLeft);
        panel3.add(errorLabel);
        panel4.add(mainLabel);
        panel5.add(saveScoreButton);
        panel5.add(saveLeaderButton);
        panel5.add(quitButton);

        boardScreen.add(panel1);
        boardScreen.add(panel2);
        boardScreen.add(panel3);
        boardScreen.add(panel4);
        boardScreen.add(panel5); 

        container.add(boardScreen);

        this.validate();
        this.repaint();
    }

    //opens a popup displaying the list given
    public void popUp(List<String> list, String name)
    {
        JFrame frame = new JFrame(name);
        frame.getContentPane().setBackground(Color.black);
        frame.setMinimumSize(new Dimension(50, 100));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        if (name.equals("Top 10 High Scores"))
        {
            frame.setLocation(100, 100);
        }
        else
        {
            frame.setLocation(100, 500);
        }

        JLabel label = new JLabel();
        label.setBackground(Color.black);
        label.setForeground(Color.white);
        label.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        String output = ("<html>" + name + "<br><br>");

        for (String s : list)
        {
            output += s + "<br>";
        }

        output += "</html>";
        label.setText(output);
        frame.add(label);

        frame.setVisible(true);
        frame.pack();
    }

    //displays a popup for a string given
    public void popUp2(String popUp, String name)
    {
        JFrame frame = new JFrame(name);
        frame.getContentPane().setBackground(Color.black);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.black);
        JScrollPane scroll = new JScrollPane(panel);
        scroll.setBackground(Color.black);

        JLabel label = new JLabel();
        label.setBackground(Color.black);
        label.setForeground(Color.white);
        label.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        label.setText(popUp);
        panel.add(label);

        frame.add(scroll);

        frame.setSize(700, 600);
        frame.setVisible(true);
    }

    //disables all enocunter buttons
    public void disableEncounterButtons()
    {
        attack.setEnabled(false);
        block.setEnabled(false);
        skill.setEnabled(false);
    }

    //reenables all encounter buttons
    public void enableEncounterButtons()
    {
        attack.setEnabled(true);
        block.setEnabled(true);
        skill.setEnabled(true);
    }

    //disables the scoreboard button
    public void disableScoreButton()
    {
        if (saveScoreButton != null)
        {
            saveScoreButton.setEnabled(false);
        }
    }

    //disables the leaderboard button
    public void disableLeaderButton()
    {
        if (saveLeaderButton != null)
        {
            saveLeaderButton.setEnabled(false);
        }
    }

    //enables the save buttons
    public void enableSaveButtons()
    {
        if (saveLeaderButton != null && saveScoreButton != null)
        {
            saveScoreButton.setEnabled(true);
            saveLeaderButton.setEnabled(true);
        }
    }
    
    //disables the exit button
    public void disableExitButton()
    {
        if (exit != null)
        {
            exit.setEnabled(false);
        }
    }

    //updates the main label with the text given
    public void updateMainLabel(String text)
    {
        mainLabel.setText(text);
    }

    //returns the text in loadfield
    public String getLoadTextField()
    {
        return loadTextField.getText();
    }

    //updates the error label with the text given
    public void updateErrorLabel(String text)
    {
        errorLabel.setText(text);
    }

    //gets the number of rooms, returns -1 if there are no rooms
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

    //returns the class selected, by default returns paladin
    public int getPlayerClass()
    {
        return classList.getSelectedIndex();
    }

    //returns the playername from the text field
    public String getPlayerName()
    {
        return createGameTextField.getText();
    }

    //sets the player helath label to the current health
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

    //sets the room label to the room given
    public void setRoomLabel(String text)
    {
        for (int i = 10; i > text.length(); i--)
        {
            text += " ";
        }

        text = "Rooms Left:   " + text;

        for (int i = 10; i > text.length(); i--)
        {
            text += " ";
        }

        this.roomsLeft.setText(text);
    }

    //sets the damage to the deamage given
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

    //sets the armour class label to the armour class given
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

    //sets the roll label to the roll number given
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

    //sets the encounter health to the health given
    public void setEncounterPlayerHealth(String text)
    {
        text = "            " + text + "            ";
        playerHealth.setText(text);
    }

    //sets the enemy health to the health given
    public void setEnemyHealth(String text)
    {
        text = "            " + text + "            ";
        enemyHealth.setText(text);
    }
}
