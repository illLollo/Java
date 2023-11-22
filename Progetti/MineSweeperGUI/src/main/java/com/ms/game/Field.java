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
    private final Cell[][] gametable;
    private final int rows;
    private final int cols;
    private static final Random rnd = new Random();
    
    public Cell[][] getTable() { return this.gametable; }
    
    public Field(int rows, int cols)
    {
        this.gametable = new Cell[rows][cols];
        this.rows = rows;
        this.cols = cols;
        
        
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++)
                this.gametable[row][col] = new Cell(row, col, false, this);
        
        for (int i = 0; i < Math.ceil((rows * cols) / 5); i++)
        {
            final Cell c = this.spawnRandomBomb();
//            System.out.println("XY: " + c.getX() + " - " + c.getY());
            for (Cell cell : c.getAdiacents())
                cell.increaseAdiacent(1);
        }
        System.out.println("TOTALBOMBS: " + Math.ceil((rows * cols) / 5));
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
        int x = rnd.nextInt(this.cols);
        int y = rnd.nextInt(this.rows);

        while (this.getCell(x, y).isBomb())
        {
            x = rnd.nextInt(this.cols);
            y = rnd.nextInt(this.rows);
        }
        
        this.getCell(x, y).setBomb(true);
        
        return this.getCell(x, y);
    }
    private static int getRandIntInRange(int min, int max)
    {
        return min + rnd.nextInt(max - min);
    }
    
    
}
