package com.tris.trisgame;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author Administrator
 */
public class Tris
{
    private Player p1;
    private Player p2;
    private GameTable table;
    private Turn turn;
    private static final Scanner sc = new Scanner(System.in);
    private boolean started;
    private Player winner;
    private int nMoves;
    
    public Tris(Player p1, Player p2)
    {
        this.table = new GameTable(3, 3);
        this.p1 = Objects.requireNonNull(p1);
        this.p2 = Objects.requireNonNull(p2);
        this.turn = new Turn<>(this.p1, this.p2);
        this.started = false;
        this.winner = null;
        this.nMoves = 0;
    }
    public Tris()
    {
        this.table = new GameTable(3,3);
        this.p1 = null;
        this.p2 = null;
        this.turn = null;
        this.started = false;
        this.winner = null;
        this.nMoves = 0;
    }
    public void startGame() throws NoPlayersException
    {
        if (this.p1 == null || this.p2 == null || this.turn == null) throw new NoPlayersException();
        this.started = true;
    }
    public boolean isStarted()
    {
        return this.started;
    }
    public void makeMove(int cellNumber)
    {
        if (!this.isStarted())
            throw new GameNotStartedException("Gioco non ancora iniziato!");
        
        final Cell selected = this.getTable().getCell(cellNumber);
        final Player current = (Player) this.getTurn().getCurrent();
        
        if (!selected.isEmpty()) 
            throw new InvalidMoveException("Cella occupata dal giocatore: " + current);
       
        this.nMoves++;
        
        selected.setOwnership(current);
        this.getTurn().switchTurn();
        this.checkWinner();
        
        if (this.getWinner() != null || this.nMoves >= 9)
            this.started = false;
    }
    public boolean tryMove(int cellNumber) throws InvalidMoveException 
    {
        if (!this.isStarted())
            throw new GameNotStartedException("Gioco non ancora iniziato!");
        
        try
        {
            final Cell selected = this.getTable().getCell(cellNumber);
            return selected.isEmpty();
        }
        catch (final InvalidMoveException e)
        {
            return false;
        }
    }
    //aggiungere un trymove
    public void stopGame()
    {
        if (!this.isStarted())
            throw new GameNotStartedException("Gioco non ancora iniziato!");
        
        this.started = false;
        this.table = new GameTable(3,3);
        this.turn = new Turn<>(this.p1, this.p2);
    }
    public Turn getTurn()
    {
        return this.turn;
    }
    public GameTable getTable()
    {
        return this.table;
    }
    private void checkWinner()
    {
        for (int i = 0; i < this.table.getNRows(); i++)
        {
            Cell cc1 = this.table.getCell(0 + (i * 3));
            Cell cc2 = this.table.getCell(1 + (i * 3));
            Cell cc3 = this.table.getCell(2 + (i * 3));
            
            if (cc1.equals(cc2) && cc1.equals(cc3)) this.winner = cc1.getOwnership();
            
            Cell cr1 = this.table.getCell(i);
            Cell cr2 = this.table.getCell(i + 3);
            Cell cr3 = this.table.getCell(i + 6);
        
            if (cr1.equals(cr2) && cr1.equals(cr3)) this.winner = cr1.getOwnership();
            
            
            if  (this.table.getCell(0).equals(this.table.getCell(4)) && this.table.getCell(0).equals(this.table.getCell(8))) 
                    this.winner = this.table.getCell(0).getOwnership();
            
            if  (this.table.getCell(2).equals(this.table.getCell(4)) && this.table.getCell(2).equals(this.table.getCell(6))) 
                    this.winner = this.table.getCell(2).getOwnership();
        }    
    }
    public Player getWinner()
    { 
        return this.winner;
    }
    public void setP1(Player p)
    {
        if (p != null && !p.equals(this.p2))
            this.p1 = p;
        this.turn = new Turn<>(this.p1, this.p2);
    }
    public void setP2(Player p)
    {
        if (p != null && !p.equals(this.p1))
            this.p2 = p;
        this.turn = new Turn<>(this.p1, this.p2);
    }
}
