/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.giocodel15;

/**
 *
 * @author gambaro.lorenzo
 */
public class GameTable 
{
    private final Card[][] matrix;
    private int row;
    private int col;
    
    public GameTable(int rows, int cols)
    {
        this.row = rows;
        this.col = cols;
        this.matrix = new Card[rows][cols];
        
        for (int row = 0; row < this.row; row++)
            for (int col = 0; col < this.col; col++)
                if (row != rows - 1 || col != cols - 1)
                    this.matrix[row][col] = new Card(row, col, col + (row * this.col) + 1);
    }
    public GameTable()
    {
        this(4, 4);
    }
    
    public Card getCell(int index)
    {
        int row = index / this.row;
        int col = index - (this.row * row); 
        
        return getCell(row, col);
    }
    public Card getCell(int row, int col)
    {
        return this.matrix[row][col];
    }
    public int getNRows() { return this.row; }
    public int getNCols() { return this.col; }
    public Card[] getCellAdiacents(final Card c)
    {
        final Card[] adiacents = new Card[4];
        
        int index = 0;
        
        adiacents[index++] = this.getCell(c.getRow() - 1, c.getCol() - 1);
        
       
        
        return null;
    }
}
