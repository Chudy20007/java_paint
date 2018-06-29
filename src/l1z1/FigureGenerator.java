/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package l1z1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.*;

/**
 *
 * @author Kacper-PC
 */
public class FigureGenerator extends JFrame {

    JButton generateButton;
    JComboBox figures;
    JTextArea figureHeight;
    JTextArea figureWidth;

    public FigureGenerator() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        setSize(screenWidth / 4 + 150, screenHeight / 2 + 100);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Generator");
        setVisible(true);
    }

    public void generateComponents() {
        figureHeight = new JTextArea(1, 8);
        figureWidth = new JTextArea(1, 8);
        figures = new JComboBox();

    }

}
