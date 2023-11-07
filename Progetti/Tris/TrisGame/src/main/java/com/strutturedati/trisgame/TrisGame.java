/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.strutturedati.trisgame;

import java.util.Scanner;

/**
 *
 * @author Lorenzo
 */
public class TrisGame {

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Giocatore 1, inserisci il tuo nome: ");
        final Player p1 = new Player<>(sc.nextLine(), 'X');
        
        System.out.println("Giocatore 2, inserisci il tuo nome: ");
        final Player p2 = new Player<>(sc.nextLine(), 'O');
        
        final Tris t = new Tris(p1, p2);
        
        try
        {
            t.play();
        }
        catch (final NoPlayersException e)
        {
            System.err.println("Non puoi avviare una partita senza aver prima impostato i giocatori!");
        }
    }
}
