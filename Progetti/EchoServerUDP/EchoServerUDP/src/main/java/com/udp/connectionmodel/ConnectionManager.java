package com.udp.connectionmodel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.Closeable;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author Lorenzo
 */
public final class ConnectionManager implements Closeable
{
    private DatagramSocket sendSocket;
    private DatagramSocket listenSocket;
    private Boolean isReciving;
    private Thread recivingThread;
    private ConnectionListener reciver;
    
    public ConnectionManager(final int port) throws SocketException
    {
        this.listenSocket = new DatagramSocket(port);
        this.sendSocket = new DatagramSocket();
        this.isReciving = false;
        this.recivingThread = null;
        this.reciver = null;
    }
    
    public void send(final String message, final InetAddress address, final int port) throws IOException
    {
        this.sendSocket.send(new DatagramPacket(message.getBytes(), message.getBytes().length, address, port));
    }
    public void recive(final Callback<DatagramPacket> run)
    {
        if (this.isReciving())
            return;
        this.isReciving = true;
        new Thread(new ConnectionListener(this.listenSocket, run, this.isReciving)).start();
    }
    public void startReciving(final Callback<DatagramPacket> run)
    {
        if (this.isReciving())
            return;
        this.isReciving = true;
        
        this.recivingThread = new Thread(() -> 
        {
            while (this.isReciving())
            {
                this.reciver = new ConnectionListener(this.listenSocket, run, this.isReciving);
                this.reciver.run();
            }   
            
        });
        
        this.recivingThread.start();
    }
    public void stopReciving()
    {
        if (!this.isReciving())
            return;
        this.isReciving = false;
       if (this.recivingThread != null)
           this.recivingThread.interrupt();

        this.listenSocket.close();
        try
        {
            this.listenSocket = new DatagramSocket(this.listenSocket.getLocalPort());
            
        }
        catch (final SocketException ex)
        {
            ex.printStackTrace();
        }
    }
    public boolean isReciving() 
    {
        return this.isReciving;
    }
    
    public int getListenPort()
    {
        return this.listenSocket.getLocalPort();
    }

    @Override
    public void close() throws IOException 
    {
        if (this.isReciving())
            this.stopReciving();
        this.listenSocket.close();
        this.sendSocket.close();
    }
}

