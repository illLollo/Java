/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.giocodel15;

/**
 *
 * @author gambaro.lorenzo
 */
public class Card 
{
    private final int row;
    private final int col;
    private final int value;
    
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
    public int getRow() { return this.row; }
    public int getCol() { return this.col; }
    public int getValue() { return this.value; }
}
