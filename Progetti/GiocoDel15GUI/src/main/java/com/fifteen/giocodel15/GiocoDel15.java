/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.fifteen.giocodel15;

import java.util.Scanner;


/**
 *
 * @author Administrator
 */
public class GiocoDel15 {

   public static void main(String[] args) 
    {
        final FifteenGameExecutor game = new FifteenGameExecutor();
        final Scanner sc = new Scanner(System.in);
        
//        printGameMatrix(game.getTable());
        
        printGameMatrix(game.getTable());
        
//        System.out.println("Mossa valida di cella: " + game.getTable().getCell(2, 3) + "? = " + game.tryMove(2, 3));


        while (game.isStarted())
        {
            System.out.println("Inserisci le coordinate della cella che vuoi selezionare: ");
            int row = sc.nextInt();
            int col = sc.nextInt();
            if (game.tryMove(row, col))
                game.makeMove(row, col);
            else
                System.err.println("Cella non spostabile, riprova!");
            
            System.out.println("\n\n\n\n\n");
            printGameMatrix(game.getTable());
        }
        System.out.println("hai finito!");
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
