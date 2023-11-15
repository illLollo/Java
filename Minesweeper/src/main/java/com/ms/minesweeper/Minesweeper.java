/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ms.minesweeper;

import com.ms.game.Field;


/**
 *
 * @author Administrator
 */
public class Minesweeper {

    public static void main(String[] args) 
    {
        final Field f = new Field(10, 10);
        printField(f);
    }
    public static void printField(Field f)
    {
        for (int x = 0; x < f.getNRows(); x++)
        {
            for (int y = 0; y < f.getNCols(); y++)
            {
                System.out.print(f.getCell(x, y));
                System.out.print(" | ");
            }
            System.out.println();
        }
    }
}
