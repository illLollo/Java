package com.strutturedati.trisgame;
import java.util.Scanner;

/**
 *
 * @author Administrator
 */
public class Tris implements Runnable
{
    private Player p1;
    private Player p2;
    private final GameTable table;
    private Turn turn;
    private static final Scanner sc = new Scanner(System.in);
    
    public Tris()
    {
        this.table = new GameTable(3, 3);
        this.p1 = null;
        this.p2 = null;
        this.turn = null;
    }
    public Tris(Player p1, Player p2)
    {
        this.table = new GameTable(3, 3);
        this.p1 = p1;
        this.p2 = p2;
        this.turn = new Turn<>(this.p1, this.p2);
    }
    
    public void play() throws NoPlayersException
    {
        if (this.p1 == null || this.p2 == null) throw new NoPlayersException();
        
        InputUtils.setScanner(sc);
        
        System.out.println("\t\tGIOCO DEL TRIS");
        
        System.out.println(this.turn.getCurrent() + " comincerai tu!");
        System.out.println("Quando sarà il tuo turno dovrai dirmi il numero della casella che vorrai marcare, inserendo il numero in input!");
        
        for (int i = 0; i < 9; i++)
        {
            this.table.render();
            
            final Player current = (Player) this.turn.getCurrent();
            final Cell selected = this.askCell(current);
            
            selected.setOwnership(current);
            selected.setSymbol(current.getCode());
          
            final Player winner = this.checkWinner();
            
            if (winner != null)
            {
                System.out.println("Il vincitore è " + winner);
                return;
            }
            this.turn.switchTurn();
            System.out.println(this.turn.getCurrent() + " ora è il tuo turno!");
        }
        System.out.println("PAREGGIO!");
    }
    public void setP1(Player p)
    {
        this.p1 = p;
    }
    public void setP2(Player p)
    {
        this.p2 = p;
    }
    
    private Cell askCell(Player p)
    {       
        boolean validCell = true;
        
        Cell cell = null;
        
        do
        {
            cell = this.table.getCell(InputUtils.getIntInRange(p.getName() + " inserisci il numero della casella che vuoi rendere tua 0-8: ", 0, 8));
            
            if (!cell.isEmpty()) 
            {
                validCell = false;
                System.out.println("La cella è già occupata dal giocatore: " + cell.getOwnership());
            }
            else validCell = true;
        }
        while (!validCell);
        
        return cell;
    }
    public Player checkWinner()
    { 
        for (int i = 0; i < this.table.getNRows(); i++)
        {
            Cell cc1 = this.table.getCell(0 + (i * 3));
            Cell cc2 = this.table.getCell(1 + (i * 3));
            Cell cc3 = this.table.getCell(2 + (i * 3));
            
            if (cc1.equals(cc2) && cc1.equals(cc3)) return cc1.getOwnership();
            
            Cell cr1 = this.table.getCell(i);
            Cell cr2 = this.table.getCell(i + 3);
            Cell cr3 = this.table.getCell(i + 6);
        
            if (cr1.equals(cr2) && cr1.equals(cr3)) return cr1.getOwnership();
            
            
            if  (this.table.getCell(0).equals(this.table.getCell(4)) && this.table.getCell(0).equals(this.table.getCell(8))) 
                    return this.table.getCell(0).getOwnership();
            
            if  (this.table.getCell(2).equals(this.table.getCell(4)) && this.table.getCell(2).equals(this.table.getCell(6))) 
                    return this.table.getCell(2).getOwnership();
        }
        return null;
    }

    @Override
    public void run() 
    {
        try 
        {
            this.play();
            
        }
        catch (final NoPlayersException e)
        {
            System.err.println("Non puoi giocare senza aver prima definito i giocatori!");
        }
    }
}
