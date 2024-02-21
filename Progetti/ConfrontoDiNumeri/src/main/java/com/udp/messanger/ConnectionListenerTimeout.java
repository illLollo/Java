package com.udp.messanger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Objects;

/**
 *
 * @author Administrator
 */
public class ConnectionListenerTimeout implements Runnable
{
    private final DatagramSocket socket;
    private final Callback<DatagramPacket> callback;
   private final byte[] buffer;
    private Thread timeouter;
    private final int timeout;
    private final Runnable callbackOnFail;
    private final ConnectionManager c;
    
    public ConnectionListenerTimeout(final DatagramSocket socket, final Callback<DatagramPacket> callback, final Runnable callbackOnFail, final int timeout, final ConnectionManager c)
    {
        this.socket = Objects.requireNonNull(socket);
        this.callback = Objects.requireNonNull(callback);
        this.buffer = new byte[2056];
        this.callbackOnFail = Objects.requireNonNull(callbackOnFail);
        this.timeout = timeout;
        this.c = Objects.requireNonNull(c);
    }

    @Override
    public void run() 
    {
        final DatagramPacket packet = new DatagramPacket(this.buffer, this.buffer.length);
        
        try
        {        
            this.timeouter = new Thread(() -> 
            {
                try 
                {
                    Thread.sleep(this.timeout);
                    if (this.c.isReciving())
                        this.callbackOnFail.run();
                    this.c.stopReciving();
                } 
                catch (final InterruptedException ex) {}
            });
            
            this.timeouter.start();
            
            this.socket.receive(packet);
            this.timeouter.interrupt();
                        
            if (this.c.isReciving() && packet.getLength() > 0)
                this.callback.call(packet);
            this.c.stopReciving();
            
        } 
        catch (final IOException ex) 
        {
            System.err.println(ex.getMessage());
        }
    }
  
}
