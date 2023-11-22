/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.giocodel15;

/**
 *
 * @author gambaro.lorenzo
 */
public class GiocoDel15 {

    public static void main(String[] args) 
    {
        final FifteenGame game = new FifteenGame();
        printGameMatrix(game.getTable());
    }
    private static void printGameMatrix(final GameTable g)
    {
        for (int row = 0; row < g.getNRows(); row++)
        {
            for (int col = 0; col < g.getNCols(); col++)
                if (g.getCell(row, col) != null)
                    System.out.print(" | " + g.getCell(row, col).getValue());
            System.out.print(" | ");
            System.out.println("\n");
        }
    }
}
