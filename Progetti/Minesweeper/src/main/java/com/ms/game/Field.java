/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ms.game;
import java.util.Random;

/**
 *
 * @author Administrator
 */
public class Field 
{
    private Cell[][] gametable;
    private int rows;
    private int cols;
    private static Random rnd = new Random();
    
    public Cell[][] getTable() { return this.gametable; }
    
    public Field(int rows, int cols)
    {
        this.gametable = new Cell[rows][cols];
        this.rows = rows;
        this.cols = cols;
        
        
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++)
                this.gametable[row][col] = new Cell(row, col, false, this);
        
        for (int i = 0; i < Math.min(rows, cols); i++)
        {
            final Cell c = this.spawnRandomBomb();
//            System.out.println("XY: " + c.getX() + " - " + c.getY());
            for (Cell cell : c.getAdiacents())
                cell.increaseAdiacent(1);
        }
    }
    public int getNRows() { return this.rows; }
    public int getNCols() { return this.cols; }
    public Cell getCell(int index)
    {
        if (index < 0 || index >= this.rows * this.cols)
            throw new ArrayIndexOutOfBoundsException("Indici al di fuori dei limti del campo di gioco!");
        
        int row = index / this.gametable.length;
        int col = index - (this.gametable.length * row); 
        
        return this.gametable[row][col];
    }
    public Cell getCell(int row, int col)
    {
        if (row < 0 || row >= this.rows || col < 0 || col >= this.cols)
            throw new ArrayIndexOutOfBoundsException("Indici al di fuori dei limiti del campo di gioco!");
        
        return this.gametable[row][col];
    }
    private Cell spawnRandomBomb()
    {        
        int x = rnd.nextInt(0,this.cols);
        int y = rnd.nextInt(0,this.rows);

        while (this.getCell(x, y).isBomb())
        {
            x = rnd.nextInt(0 ,this.cols);
            y = rnd.nextInt(0 ,this.rows);
        }
        
        this.getCell(x, y).setBomb(true);
        
        return this.getCell(x, y);
    }
    
    
}
