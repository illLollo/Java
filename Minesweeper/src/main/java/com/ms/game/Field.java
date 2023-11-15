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
    
    public Field(int rows, int cols)
    {
        this.gametable = new Cell[rows][cols];
        this.rows = rows;
        this.cols = cols;
        
        
        for (int x = 0; x < rows; x++)
            for (int y = 0; y < cols; y++)
                this.gametable[x][y] = new Cell(x, y, false);
        
        for (int i = 0; i < 10; i++)
        {
            final Cell c = this.spawnRandomBomb();
//            System.out.println("XY: " + c.getX() + " - " + c.getY());
            this.incementAdiacents(c.getX(), c.getY());
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
    public Cell getCell(int x, int y)
    {
        if (x < 0 || x >= this.rows || y < 0 || y >= this.cols)
            throw new ArrayIndexOutOfBoundsException("Indici al di fuori dei limiti del campo di gioco!");
        
        return this.gametable[x][y];
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
    private void incementAdiacents(int x, int y)
    {
//        System.out.println("INITIAL X: " + (x - 1));
//        System.out.println("FINAL X: " + (x + 1));
//        System.out.println("INITIAL Y: " + (y - 1));
//        System.out.println("FINAL Y: " + (y + 1));
        
        for (int i = (x - 1) >= 0 ? (x - 1) : 0; i <= x + 1 && i < cols; i++)
        {
            for (int j = (y - 1) >= 0 ? (y - 1) : 0; j <= y + 1 && j < rows; j++)
            {
//                System.out.println("CELL: " + i + " - " + j);
                this.getCell(i, j).increaseAdiacent(1);
            }
        }
    }
    
}
