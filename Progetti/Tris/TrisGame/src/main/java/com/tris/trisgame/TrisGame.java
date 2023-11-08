/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.tris.trisgame;

import java.util.Scanner;

/**
 *
 * @author gambaro.lorenzo
 */
public class TrisGame {

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("\t\tGIOCO DEL TRIS");
        
        System.out.println("Giocatore 1, inserisci il tuo nome: ");
        final Player p1 = new Player<>(sc.nextLine(), 'X');
        
        System.out.println("Giocatore 2, inserisci il tuo nome: ");
        final Player p2 = new Player<>(sc.nextLine(), 'O');
        
        final Tris t = new Tris(p1, p2);
        
        t.startGame();
        System.out.println("Inizia " + t.getTurn().getCurrent());
        
        while (t.isStarted() && t.getWinner() == null)
        {
            printTable(t.getTable());
                
            int cell = InputUtils.getIntInRange(t.getTurn().getCurrent() + " fai la tua mossa: ", 0, 8);
            
            try
            {
                if (t.tryMove(cell))
                    t.makeMove(cell);
                else
                    System.err.println("Cella occupata da un altro giocatore: " + t.getTable().getCell(cell).getOwnership());
            }
            catch (final InvalidMoveException e)
            {
                System.err.println("L'indice della cella che hai inserito è all'esteno dei limiti: [0," + ((t.getTable().getNCols() * t.getTable().getNRows()) - 1) + "]"); 
            }
        }
        
        printTable(t.getTable());
        
        if (t.getWinner() == null)
            System.out.println("PAREGGIO");
        else 
            System.out.println("Il vincitore è " + t.getWinner());
    }
    private static void printTable(GameTable t)
    {
        int sum = 0;
        System.out.println();
        
        for (int i = 0; i < t.getNRows() * t.getNCols(); i++)
        {
            if (i > 0 && i % t.getNCols() == 0) 
            {
                System.out.println();
                for (int j = 0; j < sum - 1; j++)
                    System.out.print('_');
                
                System.out.println("\n");
                sum = 0;
            }
            
            String s = t.getCell(i) + " ";
            
            System.out.print(s);
            sum += s.length();
        }
        
        System.out.println();
        for (int i = 0; i < sum - 1; i++)
            System.out.print('_');
        
        System.out.println("\n");
    }
}

