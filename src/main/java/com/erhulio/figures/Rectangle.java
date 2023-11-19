package com.erhulio.figures;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;

public class Rectangle extends JComponent{
    private int width;
    private int height;
    private int x;
    private int y;

    public Rectangle(int x, int y, int width, int height) {
        setPreferredSize(new Dimension(800, 600));
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawRect(x, y, width, height);
        g.drawRect(x+20, y+20, width, height);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
