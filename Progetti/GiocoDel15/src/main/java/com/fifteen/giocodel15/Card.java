/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fifteen.giocodel15;

/**
 *
 * @author gambaro.lorenzo
 */
public class Card 
{
    private final int row;
    private final int col;
    private int value;
    
    public Card(final int x, final int y, final int value)
    {
        this.row = x;
        this.col = y;
        this.value = value;
    }
    @Override
    public boolean equals(Object other)
    {
        if (other == null)
            return false;
        
        if (other instanceof Card c)
            return this.row == c.row && this.col == c.col && this.value == c.value;
        
        return false;
    }
    @Override 
    public String toString()
    {
        return value + "";
    }
    public boolean isEmpty() { return this.value == 0; }
    public void setValue(int value) { this.value = value; }
    public int getRow() { return this.row; }
    public int getCol() { return this.col; }
    public int getValue() { return this.value; }
}
