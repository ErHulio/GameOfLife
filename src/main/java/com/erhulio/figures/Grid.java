package com.erhulio.figures;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

public class Grid extends JComponent{
    private int n;
    private int m;
    private boolean grid[][];
    private Color black;
    private Color white;

    public Grid(boolean[][] grid) {
        this.black = new Color(0, 0, 0);
        this.white = new Color(255, 255, 255);
        this.n = grid.length;
        this.m = grid[0].length;
        setPreferredSize(new Dimension(n*10, m*10));
        this.grid = grid;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for(int i = 0; i<this.n; i++) {
            for(int j = 0; j<this.m; j++) {
                if(this.grid[i][j]) {
                    g.setColor(black);
                    g.fillRect(i*10, j*10, 10, 10);
                }
                else {
                    g.setColor(white);
                    g.fillRect(i*10, j*10, 10, 10);
                }
            }
        }
    }

    public void setGrid(boolean[][] grid) {
        this.grid = grid;
    }
}
