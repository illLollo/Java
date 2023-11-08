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
            t.getTable().render();
                
            int cell = InputUtils.getIntInRange(t.getTurn().getCurrent() + " fai la tua mossa: ", 0, 8);
            
            if (t.getTable().getCell(cell).getOwnership() != null)
                t.makeMove(cell);     
        }
        
        t.getTable().render();
        
        if (t.getWinner() == null)
            System.out.println("PAREGGIO");
        else 
            System.out.println("Il vincitore è: " + t.getWinner());
    }
}