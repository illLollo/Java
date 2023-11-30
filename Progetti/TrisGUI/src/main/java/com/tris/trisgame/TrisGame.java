package com.tris.trisgame;
import java.util.Objects;
/**
 *
 * @author Administrator
 */
public class TrisGame implements Tris
{
    private GameTable table = new GameTable(Tris.ROWS, Tris.COLS);
    private Player p1;
    private Player p2;
    private Turn turn;
    private boolean started;
    private Player winner;
    private int nMoves;
    
    public TrisGame(final Player p1, final Player p2)
    {
        this.p1 = Objects.requireNonNull(p1);
        this.p2 = Objects.requireNonNull(p2);
        this.turn = new Turn<>(this.p1, this.p2);
        this.started = false;
        this.winner = null;
        this.nMoves = 0;
    }
    public TrisGame()
    {
        this.p1 = null;
        this.p2 = null;
        this.turn = null;
        this.started = false;
        this.winner = null;
        this.nMoves = 0;
    }
    public int getNMoves()
    {
        return this.nMoves;
    }
    public GameTable getTable()
    {
        return this.table;
    }
    @Override
    public boolean isStarted()
    {
        return this.started;
    }
    @Override
    public boolean tryMove(int cellRow, int cellCol)
    {
        if (!this.isStarted())
            throw new GameNotStartedException("Gioco non ancora iniziato!");
        
        try
        {
            final Cell selected = this.getTable().getCell(cellRow, cellCol);
            return selected.isEmpty();
        }
        catch (final InvalidMoveException e)
        {
            return false;
        }
    }
    @Override
    public void reset()
    {
        this.started = false;
        this.table = new GameTable(3, 3);
        this.turn = new Turn<>(this.p1, this.p2);
        this.winner = null;
        this.nMoves = 0;
    }
    @Override
    public Turn getTurn()
    {
        return this.turn;
    }
    @Override
    public Player getWinner()
    { 
        return this.winner;
    }

    @Override
    public boolean isOver()
    {
        return !this.isStarted() && this.getWinner() != null;
    }

    @Override
    public void move(int row, int col) 
    {
        if (!this.isStarted())
            throw new GameNotStartedException("Gioco non ancora iniziato!");
        
        final Cell selected = this.getTable().getCell(row, col);
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

    @Override
    public void start() 
    {
        if (this.p1 == null || this.p2 == null || this.turn == null) 
            throw new NoPlayersException();
        
        this.started = true;
    }
    @Override
    public void stop() 
    {
        if (!this.isStarted())
            throw new GameNotStartedException("Gioco non ancora iniziato!");
        
        this.reset();
    }

    @Override
    public Cell getCell(int row, int col) 
    {
        return this.getTable().getCell(row, col);
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
}
