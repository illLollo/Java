/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ms.game;

import java.util.Arrays;

/**
 *
 * @author Administrator
 */
public class Cell 
{
    private boolean isBomb;
    public boolean isFlag;
    private int adiacentBombs;
    private int x;
    private int y;
    private Field gamefield;
    private boolean isShowing;
    
    public Cell(int x, int y, boolean isBomb, final Field field)
    {
        this.x = x;
        this.y = y;
        this.isBomb = isBomb;
        this.adiacentBombs = 0;
        this.isFlag = false;
        this.gamefield = field;
        this.isShowing = false;
    }
    public void setFlag(final boolean value) { this.isFlag = value; }
    public boolean isFlag() { return this.isFlag; }
    public boolean isBomb() { return this.isBomb; }
    
    public void increaseAdiacent(int amount) { this.adiacentBombs += amount; }
    
    @Override 
    public boolean equals(Object other)
    {
        if (other == null)
            return false;
        
        if (other instanceof Cell c2)
            return c2.isBomb && this.isBomb && c2.isFlag && this.isFlag && c2.adiacentBombs == this.adiacentBombs && c2.x == this.x && c2.y == this.y;
        
        return false;
    }
    @Override
    public String toString()
    {
        
        if (!this.isShowing())
            return "X";
        
        if (this.isFlag)
            return "F";
        if (this.isBomb)
            return "B";
        
        return new StringBuilder().append(this.adiacentBombs).toString();
    }
    public int getAdiacentsBombsCounter() { return this.adiacentBombs; }
    public void setBomb(final boolean value) { this.isBomb = value; }
    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public Cell[] getAdiacents()
    {
//        System.out.println("INITIAL X: " + (x - 1));
//        System.out.println("FINAL X: " + (x + 1));
//        System.out.println("INITIAL Y: " + (y - 1));
//        System.out.println("FINAL Y: " + (y + 1));
        Cell[] adiacents = new Cell[8];
        int index = 0;
        
        for (int i = (x - 1) >= 0 ? (x - 1) : 0; i <= x + 1 && i < this.gamefield.getNCols(); i++)
        {
            for (int j = (y - 1) >= 0 ? (y - 1) : 0; j <= y + 1 && j < this.gamefield.getNRows(); j++)
            {
//                System.out.println("CELL: " + i + " - " + j);
                final Cell selected = this.gamefield.getCell(i, j);
                if (!selected.isBomb() && index < 8)
                    adiacents[index++] = this.gamefield.getCell(i, j);
            }
        }
        return adiacents;
    }
    public boolean isShowing() { return this.isShowing; }
    public void setVisibility(boolean value)
    {
        this.isShowing = value;
    }
}

