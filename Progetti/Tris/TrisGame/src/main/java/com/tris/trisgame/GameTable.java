package com.tris.trisgame;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Administrator
 */
public class GameTable
{
    private final Cell[][] matrix;
    private final int rows;
    private final int cols;
    
    public GameTable(int rows, int cols)
    {
        this.matrix = new Cell[rows][cols];
        this.rows = rows;
        this.cols = cols;
        
        for (Cell[] matrix1 : this.matrix)
            for (int j = 0; j < matrix1.length; j++)
                matrix1[j] = new Cell<>("-");
    }
    
//    @Override
//    public void render() {
//        if (this.render != null)
//            this.render.render();
//        else
//            for (int i = 0; i < this.matrix.length; i++)
//            {
//                int chars = 0;
//
//                for (int j = 0; j < this.matrix[i].length; j++) 
//                {
//                    chars += countDigits(this.matrix[i].length);
//                    System.out.print(this.matrix[i][j]);
//                    
//                    if (j < this.matrix.length - 1) 
//                    {
//                        System.out.print(" | ");
//                        chars += 3;
//                    }
//                }
//                System.out.println();
//
//                for (int j = 0; j < chars && i != this.matrix.length - 1; j++) 
//                    System.out.print("_");
//
//                System.out.println();
//            }
//    }

    private static int countDigits(int num)
    {
        if (num == 0) return 1;
        
        int count = 0;
        
        while (num != 0)
        {
            count++;
            if (num < 0) count++;
            num /= 10;
        }
            
        return count;
    }
    public Cell getCell(int cellNumber) throws InvalidMoveException
    {
        if (cellNumber < 0 || cellNumber > (this.rows * this.cols) - 1) throw new InvalidMoveException("Indice non compreso tra i limiti della tabella di gioco [0," + ((this.rows * this.cols) - 1) + "]");
        
        int row = cellNumber / this.matrix.length;
        int col = cellNumber - (this.matrix.length * row); 
        
        return this.matrix[row][col];
    }
    public int getNRows() { return this.rows; }
    public int getNCols() { return this.cols; }
    
}