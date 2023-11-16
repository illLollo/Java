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

    public static void main(String[] args) 
    {
        final MineSweeper ms = new MineSweeper(10,10, new Player());
        printField(ms.getField());
        
        ms.start();
        Scanner sc = new Scanner(System.in);
        while (ms.isStarted())
        {
            System.out.println("Inserisci X: ");
            int x = sc.nextInt();
            System.out.println("Inserisci Y: ");
            int y = sc.nextInt();

            sc.nextLine();
            try 
            {
                System.out.println("Inserisci Operazione: ");
                switch (sc.nextLine().charAt(0))
                {
                    case 'F' -> ms.setFlag(x, y);
                    case 'S' -> 
                    {
                        if (ms.tryCell(x, y))
                            ms.viewCell(x, y );
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
    }
    public static void printField(Field f)
    {
        for (int x = 0; x < f.getNRows(); x++)
        {
            for (int y = 0; y < f.getNCols(); y++)
            {
                System.out.print(f.getCell(x, y));
                System.out.print("  |  ");
            }
            System.out.println("\n----------------------------------------------------------");
        }
    }
}
