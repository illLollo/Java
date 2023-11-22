/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.giocodel15;

/**
 *
 * @author gambaro.lorenzo
 */

import java.util.Arrays;
public class GameTable 
{
    private final Card[][] matrix;
    private int rows;
    private int cols;
    
    public GameTable(int rows, int cols)
    {
        this.rows = rows;
        this.cols = cols;
        this.matrix = new Card[rows][cols];
        
        for (int row = 0; row < this.rows; row++)
            for (int col = 0; col < this.cols; col++)
                if (row != rows - 1 || col != cols - 1)
                    this.matrix[row][col] = new Card(row, col, col + (row * this.cols) + 1);
    }
    public GameTable()
    {
        this(4, 4);
    }
    
    public Card getCell(int index)
    {
        int row = index / this.rows;
        int col = index - (this.rows * row); 
        
        return getCell(row, col);
    }
    public Card getCell(int row, int col)
    {
        return this.matrix[row][col];
    }
    public int getNRows() { return this.rows; }
    public int getNCols() { return this.cols; }
    public Card[] getCellAdiacents(final Card c)
    {
        final Card[] adiacents = new Card[4];
        
        int index = 0;
        
        if (c.getRow() - 1 > 0)
            adiacents[index++] = this.getCell(c.getRow() -1, c.getCol());
        if (c.getCol() - 1 > 0)
            adiacents[index++] = this.getCell(c.getRow(), c.getCol() - 1);
        if (c.getCol() + 1 < this.cols)
            adiacents[index++] = this.getCell(c.getRow(), c.getCol() + 1);
        if (c.getRow() + 1 < this.rows)
            adiacents[index++] = this.getCell(c.getRow() + 1, c.getCol());
                  
        return index < adiacents.length ? Arrays.copyOf(adiacents, index) : adiacents;
    }
}
