/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ms.minesweeper;

import com.ms.game.BombFoundException;
import com.ms.game.Field;
import com.ms.game.IllegalMoveException;
import com.ms.game.MineSweeper;
import com.ms.game.Player;
import java.util.Scanner;


/**
 *
 * @author Administrator
 */
public class Minesweeper {
//    public static void main(String[] args) {
//        final Field f = new Field(5, 5);
//        
//        f.getCell(2, 2).setFlag(true);
//        printField(f); 
//                
//        for (Cell c : f.getCell(2, 2).getAdiacents())
//        {
//            System.out.println("CELL: " + c.getX() + "  " + c.getY());
//        }
////        
//    }
    public static void main(String[] args) 
    {
        final MineSweeper ms = new MineSweeper(10,10, new Player());
        printField(ms.getField());
        
        ms.start();
        Scanner sc = new Scanner(System.in);
        while (ms.isStarted())
        {
            System.out.println("Inserisci Riga: ");
            int row = sc.nextInt();
            System.out.println("Inserisci Colonna: ");
            int col = sc.nextInt();

            sc.nextLine();
            try 
            {
                System.out.println("Inserisci Operazione: ");
                switch (sc.nextLine().charAt(0))
                {
                    case 'F' -> ms.setFlag(row, col);
                    case 'S' -> 
                    {
                        if (ms.tryCell(row, col))
                            ms.viewCell(row, col);
                        else 
                            System.err.println("Non posso utilizzare quella cella, riprova!");
                    }
                }
                printField(ms.getField());
            } 
            catch (IllegalMoveException | IllegalStateException e)
            {
                System.err.println(e.getMessage());
            }
            catch (BombFoundException e)
            {
                System.out.println("Hai trovato la bomba e quindi hai perso!");
            }
            
        }
//        printField(ms.getField());
        System.out.println("Hai vinto");
    }
    public static void printField(Field f)
    {
        for (int row = 0; row < f.getNRows(); row++)
        {
            for (int col = 0; col < f.getNCols(); col++)
            {
                System.out.print(f.getCell(row, col));
                System.out.print("  |  ");
            }
            System.out.println("\n----------------------------------------------------------");
        }
    }
}
