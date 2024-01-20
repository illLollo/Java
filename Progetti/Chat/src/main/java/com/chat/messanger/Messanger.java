package com.chat.messanger;

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
 * @author Administrator
 */
public class Messanger implements Closeable
{
    private DatagramSocket socket;
    private Boolean isReciving;
    private Thread recivingThread;
    private MessageReciver reciver;
    
    public Messanger(final int port) throws SocketException
    {
        this.socket = new DatagramSocket(port);
        this.isReciving = false;
        this.recivingThread = null;
        this.reciver = null;
    }
    
    public void send(final String message, final InetAddress address) throws IOException
    {
        new Thread(new MessageSender(this.socket, address, message)).start();
    }
    public void recive(final Callback<DatagramPacket> run)
    {
        if (this.isReciving())
            return;
        this.isReciving = true;
        new Thread(new MessageReciver(this.socket, run, this.isReciving)).start();
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
                this.reciver = new MessageReciver(this.socket, run, this.isReciving);
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
       
        try 
        {
            this.socket.send(new DatagramPacket(new byte[1], 0, InetAddress.getLocalHost(), this.socket.getLocalPort()));
        
        } catch (IOException ex) 
        {
        }
    }
    public boolean isReciving() 
    {
        return this.isReciving;
    }
    
    public int getPort()
    {
        return this.socket.getLocalPort();
    }

    @Override
    public void close() throws IOException 
    {
        if (this.isReciving())
            this.stopReciving();
        this.socket.close();
    }
}

