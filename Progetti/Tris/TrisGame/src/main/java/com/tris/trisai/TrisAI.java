package com.tris.trisai;
import com.tris.trisgame.GameNotStartedException;
import com.tris.trisgame.Player;
import com.tris.trisgame.Tris;
import java.util.Random;

/**
 *
 * @author gambaro.lorenzo
 * @param <T>
 */
public class TrisAI<T> extends Player<T>
{
    private final Tris game;
    private static final Random rnd = new Random();
    
    public TrisAI(final Tris t, final String name, final T code)
    {
        super(name, code, t);
        this.game = t;
    }
    public int chooseNumber()
    {
        if (!this.game.isStarted())
            throw new GameNotStartedException("Game not started yet!");
        
//        if (this.game.getNMoves() <= 1)
        return rnd.nextInt(0, this.game.getTable().getNCols() * this.game.getTable().getNRows());
        
    }

}
