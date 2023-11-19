package com.erhulio;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.erhulio.figures.Grid;
import com.erhulio.logic.GridLogic;

public class Main {
    private static JFrame my_window;
    private static Grid my_grid;
    private static boolean[][] previous_grid;

    private static int[] getNMFromPanel() {
        JTextField n_rows = new JTextField();
        JTextField m_columns = new JTextField();
        JLabel n_label = new JLabel("number of rows");
        JLabel m_label = new JLabel("number of columns");
        JButton submit_buttom = new JButton();
        JPanel init_n_m_pane = new JPanel(new GridBagLayout());
        GridBagConstraints grid_constraints = new GridBagConstraints();
        Insets padding = new Insets(10,10,10,10);
        int n=50;
        int m=50;
        
        grid_constraints.insets = padding;  //top, bottom, left and right padding
        grid_constraints.fill = GridBagConstraints.HORIZONTAL;
        grid_constraints.gridx = 0;
        grid_constraints.gridwidth = 2;   //2 columns wide
        grid_constraints.gridy = 0;       //first row
        init_n_m_pane.add(n_rows, grid_constraints);

        grid_constraints.fill = GridBagConstraints.HORIZONTAL;
        grid_constraints.gridx = 2;
        grid_constraints.gridwidth = 1;   //2 columns wide
        grid_constraints.gridy = 0;       //first row
        init_n_m_pane.add(n_label, grid_constraints);

        grid_constraints.fill = GridBagConstraints.HORIZONTAL;
        grid_constraints.gridx = 0;
        grid_constraints.gridwidth = 2;   //2 columns wide
        grid_constraints.gridy = 1;       //second row
        init_n_m_pane.add(m_columns, grid_constraints);

        grid_constraints.fill = GridBagConstraints.HORIZONTAL;
        grid_constraints.gridx = 2;
        grid_constraints.gridwidth = 1;   //2 columns wide
        grid_constraints.gridy = 1;       //second row
        init_n_m_pane.add(m_label, grid_constraints);

        grid_constraints.fill = GridBagConstraints.HORIZONTAL;
        grid_constraints.gridx = 1;
        grid_constraints.gridwidth = 1;   //2 columns wide
        grid_constraints.gridy = 2;       //third row
        init_n_m_pane.add(submit_buttom, grid_constraints);

        my_window.add(init_n_m_pane);

        submit_buttom.setAction(new AbstractAction() {

            public void actionPerformed(ActionEvent arg0) {
                synchronized(submit_buttom) {
                    submit_buttom.notify();
                }

            }

        });

        submit_buttom.setText("Submit");
        my_window.pack();
        my_window.setLocationRelativeTo(null);
        my_window.setVisible(true);

        synchronized(submit_buttom) {
            try {
                submit_buttom.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        try {
            n = Integer.parseInt(n_rows.getText());
            m = Integer.parseInt(m_columns.getText());
        }
        catch(NumberFormatException e) {
            e.printStackTrace();
        }

        my_window.remove(init_n_m_pane);

        return new int[] {n, m};
    }

    private static void init() {
        int n;
        int m;

        my_window = new JFrame("Game of Life");
        my_window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        int[] aux = getNMFromPanel();
        n = aux[0];
        m = aux[1];

        previous_grid = GridLogic.startGrid(n, m);

        my_grid = new Grid(previous_grid);
        my_window.add(my_grid);
        my_window.pack();
        my_window.setLocationRelativeTo(null);
        my_window.setVisible(true);
        my_window.setAlwaysOnTop(true); 
        my_window.setAlwaysOnTop(false);
    }

    private static void gameLoop() {
        previous_grid = GridLogic.updateGrid(previous_grid);
        my_grid.setGrid(previous_grid);
    }

    public static void main(String[] args) {
        init();
        while(true) {
            gameLoop();
            try {
            TimeUnit.MILLISECONDS.sleep(75);
            }
            catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
            my_window.repaint();
        }
    }
}