/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPG.GUI;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 *
 * @author alex
 */
public class ContentGenerator
{

    private final Controller handlerGenerator;

    public ContentGenerator(Controller controller)
    {
        handlerGenerator = controller;
    }

    public JPanel titalPanel()
    {
        JPanel panel = new JPanel();
        JLabel label;

        panel.setBackground(Color.black);

        label = new JLabel("DUNGEON RPG");
        label.setForeground(Color.white);
        label.setFont(new Font("Times New Roman", Font.PLAIN, 80));

        panel.add(label);

        return panel;
    }

    public JPanel startButtonPanel()
    {
        JPanel panel = new JPanel();

        panel.setBackground(Color.black);

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

        startButton.addActionListener(handlerGenerator.newGameHandler());
        loadButton.addActionListener(handlerGenerator.loadGameHandler());
        panel.add(startButton);
        panel.add(loadButton);

        return panel;
    }



    public JPanel newGamePanel()
    {
        JPanel panel = new JPanel();
        JLabel label;

        panel.setBackground(Color.black);

        label = new JLabel("CREATE NEW GAME");
        label.setForeground(Color.white);
        label.setFont(new Font("Times New Roman", Font.PLAIN, 50));

        panel.add(label);

        return panel;
    }

    public JPanel newGameListPanel()
    {
        JPanel panel = new JPanel();
        panel.setBackground(Color.black);

        return panel;
    }

    public JPanel newGameNumRoomsPanel()
    {
        JPanel panel = new JPanel();
        panel.setBackground(Color.black);

        return panel;
    }

    public JPanel loadGameTextPanel()
    {
        JPanel panel = new JPanel();

        JButton textButton = new JButton("ENTER");
        textButton.setBackground(Color.black);
        textButton.setForeground(Color.white);
        textButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        textButton.setFocusPainted(false);

        textButton.addActionListener(handlerGenerator.loadGameTextFieldHandler());

        panel.setBackground(Color.black);

        panel.add(textButton);

        textButton.setVisible(true);
        panel.setVisible(true);

        return panel;
    }

    public JTextField loadTextField()
    {
        JTextField textField = new JTextField(20);
        textField.setText("Enter Previous Name Here");
        textField.setBackground(Color.black);
        textField.setForeground(Color.white);
        textField.setFont(new Font("Times New Roman", Font.BOLD, 20));

        return textField;
    }

    public JPanel gamePanel()
    {
        JPanel gamePanel = new JPanel();
        gamePanel.setBackground(Color.black);

        return gamePanel;
    }

    public JPanel errorPanel()
    {
        JPanel gamePanel = new JPanel();
        gamePanel.setBackground(Color.black);

        return gamePanel;
    }

    public JLabel errorLabel()
    {
        JLabel label = new JLabel();
        
        label.setBackground(Color.black);
        label.setForeground(Color.red);
        label.setFont(new Font("Times New Roman", Font.BOLD, 20));

        return label;
    }

    public JLabel mainLabel()
    {
        JLabel label = new JLabel();
        label.setBackground(Color.black);
        label.setForeground(Color.white);
        label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        label.setBorder(null);

        return label;
    }

    public JList classList()
    {
        Font font = new Font("Times New Roman", Font.PLAIN, 20);

        String[] classList =
        {
            "Paladin", "Wizard", "Barbarian"
        };

        JList list = new JList(classList);
        list.setFixedCellWidth(250);
        list.setBackground(Color.black);
        list.setForeground(Color.white);
        list.setFont(font);

        DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        Border border1 = BorderFactory.createLineBorder(Color.white);
        TitledBorder border2 = BorderFactory.createTitledBorder(border1, "Choose Class", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, font, Color.white);

        list.setBorder(border2);

        list.setVisible(true);

        return list;
    }

    public JSpinner NumRoomsSpinner()
    {
        Font font = new Font("Times New Roman", Font.PLAIN, 20);

        JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));
        
        JComponent editor = spinner.getEditor();
        JFormattedTextField tf = ((JSpinner.DefaultEditor) editor).getTextField();
        tf.setFont(font);
        tf.setBackground(Color.black);
        tf.setForeground(Color.white);
        tf.setHorizontalAlignment(SwingConstants.CENTER);
        tf.setEditable(false);

        Border border1 = BorderFactory.createLineBorder(Color.white);
        TitledBorder border2 = BorderFactory.createTitledBorder(border1, "Chose Number of Rooms", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, font, Color.white);

        tf.setBorder(border2);
        spinner.setBackground(Color.black);
        spinner.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        return spinner;
    }

    public JPanel newGameButton()
    {
        JPanel panel = new JPanel();

        JButton textButton = new JButton("ENTER");
        textButton.setBackground(Color.black);
        textButton.setForeground(Color.white);
        textButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        textButton.setFocusPainted(false);

        textButton.addActionListener(handlerGenerator.createGameHandler());

        panel.setBackground(Color.black);

        panel.add(textButton);

        textButton.setVisible(true);
        panel.setVisible(true);

        return panel;
    }
}
