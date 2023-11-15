/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-defaulthis.istance.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tris.trisgame.cui;

import com.tris.trisai.TrisAI;
import com.tris.trisgame.GameTable;
import com.tris.trisgame.InputUtils;
import com.tris.trisgame.InvalidMoveException;
import com.tris.trisgame.Player;
import com.tris.trisgame.Tris;
import java.io.InputStream;
import java.util.Scanner;

/**
 *
 * @author Lorenzo
 */
public class TrisCUI
{
    private final InputStream is;
    private final Tris istance;
    
    public TrisCUI(Tris tg)
    {
        this(tg, System.in);
    }
    public TrisCUI(Tris tg, InputStream is)
    {
        this.istance = tg;
        this.is = is;
    }
    private static void printGameTable(GameTable t)
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
    public void play2Players()
    {
        Scanner sc = new Scanner(this.is);
        
        System.out.println("\t\tGIOCO DEL TRIS");
        
        System.out.println("Giocatore 1, inserisci il tuo nome: ");
        this.istance.setP1(new Player<>(sc.nextLine(), 'X', this.istance));
        
        System.out.println("Giocatore 2, inserisci il tuo nome: ");
        this.istance.setP2(new Player<>(sc.nextLine(), 'O',  this.istance));
        
        this.istance.startGame();
        System.out.println("Inizia " + this.istance.getTurn().getCurrent());
        
        while (this.istance.isStarted() && this.istance.getWinner() == null)
        {
            printGameTable(this.istance.getTable());
            
            int cell = InputUtils.getIntInRange(this.istance.getTurn().getCurrent() + " fai la tua mossa: ", 0, 8);
            
            try
            {
                if (!((Player)this.istance.getTurn().getCurrent()).chooseMove(cell))
                    System.err.println("Cella occupata da un altro giocatore: " + this.istance.getTable().getCell(cell).getOwnership());
            }
            catch (final InvalidMoveException e)
            {
                System.err.println("L'indice della cella che hai inserito è all'esteno dei limiti: [0," + ((this.istance.getTable().getNCols() * this.istance.getTable().getNRows()) - 1) + "]"); 
            }
        }
        
        printGameTable(this.istance.getTable());
        
        if (this.istance.getWinner() == null)
            System.out.println("PAREGGIO");
        else 
            System.out.println("Il vincitore è " + this.istance.getWinner());
    }
    public void playVSAI()
    {
        Scanner sc = new Scanner(this.is);
        
        System.out.println("\t\tGIOCO DEL TRIS");
        
        System.out.println("Giocatore 1, inserisci il tuo nome: ");
        this.istance.setP1(new Player<>(sc.nextLine(), 'X', this.istance));
        
        final TrisAI bot = new TrisAI<>(this.istance, "Tris BOT", 'O');
        
        System.out.println("Giocatore 2, inserisci il tuo nome: " + bot.getName());
        this.istance.setP2(bot);
        
        this.istance.startGame();
        System.out.println("Inizia " + this.istance.getTurn().getCurrent());
        
        while (this.istance.isStarted() && this.istance.getWinner() == null)
        {
            printGameTable(this.istance.getTable());
                
            int cell = 0;
            
            if (this.istance.getTurn().getCurrent() instanceof TrisAI ta)
            {
                cell = ta.chooseNumber();
                System.out.println(this.istance.getTurn().getCurrent() + " fai la tua mossa: " + cell);
            }
            else
                cell = InputUtils.getIntInRange(this.istance.getTurn().getCurrent() + " fai la tua mossa: ", 0, 8);
            
            try
            {
                if (!((Player)this.istance.getTurn().getCurrent()).chooseMove(cell))
                    System.err.println("Cella occupata da un altro giocatore: " + this.istance.getTable().getCell(cell).getOwnership());
            }
            catch (final InvalidMoveException e)
            {
                System.err.println("L'indice della cella che hai inserito è all'esteno dei limiti: [0," + ((this.istance.getTable().getNCols() * this.istance.getTable().getNRows()) - 1) + "]"); 
            }
        }
        
        printGameTable(this.istance.getTable());
        
        if (this.istance.getWinner() == null)
            System.out.println("PAREGGIO");
        else 
            System.out.println("Il vincitore è " + this.istance.getWinner());
    }
}
