/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udp.messanger;

import java.util.Objects;

/**
 *
 * @author Administrator
 */
public class Time 
{
    private Thread executingThread;
    
    public void setTimeout(final Runnable run, final int millis)
    {
        this.executingThread = new Thread(() ->
        {
            try 
            {
                Thread.sleep(millis);
                Objects.requireNonNull(run).run();
            } 
            catch (final InterruptedException ex) 
            {
                System.err.println("INTRTTUPTED BY ME");
            }
            this.executingThread = null;
            
        });
        
        this.executingThread.start();
    }
    public void stopTimeout()
    {
        if (this.executingThread != null)
        {
            this.executingThread.interrupt();
            this.executingThread = null;
        }
    }
}
