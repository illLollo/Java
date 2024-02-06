package com.udp.connectionmodel;

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
 * @author Lorenzo
 */
public class ConnectionListener implements Runnable
{
    private final DatagramSocket socket;
    private final Callback<DatagramPacket> callback;
    private byte[] buffer;
    private Boolean isRunning;
    
    public ConnectionListener(final DatagramSocket socket, final Callback<DatagramPacket> callback, final Boolean isRunning)
    {
        this.socket = Objects.requireNonNull(socket);
        this.callback = Objects.requireNonNull(callback);
        this.buffer = new byte[2056];
        this.isRunning = isRunning;
    }

    @Override
    public void run() 
    {
        final DatagramPacket packet = new DatagramPacket(this.buffer, this.buffer.length);
        
        try
        {            
            this.socket.receive(packet);
                        
            if (this.isRunning && packet.getLength() > 0)
                this.callback.call(packet);
            
        } catch (final IOException ex) 
        {
            System.err.println(ex.getMessage());
        }
    }
  
}
