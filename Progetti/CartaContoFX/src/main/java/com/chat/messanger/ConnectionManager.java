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
public class ConnectionManager implements Closeable
{
    private final DatagramSocket sendSocket;
    private final DatagramSocket listenSocket;
    private volatile Boolean isReciving;
    private volatile Thread recivingThread;
    
    public ConnectionManager(final int port) throws SocketException
    {
        this.listenSocket = new DatagramSocket(port);
        this.sendSocket = new DatagramSocket();
        this.isReciving = false;
        this.recivingThread = null;
    }
    
    public void send(final String message, final InetAddress address, final int port) throws IOException
    {
        this.sendSocket.send(new DatagramPacket(message.getBytes(), message.getBytes().length, address, port));
    }
    public void send(final byte[] message, final InetAddress address, final int port) throws IOException
    {
        this.sendSocket.send(new DatagramPacket(message, message.length, address, port));
    }
    public void recive(final Callback<DatagramPacket> callbackResponse)
    {
        if (this.isReciving())
            return;
        this.isReciving = true;
        this.recivingThread = new Thread(new ConnectionListener(this.listenSocket, callbackResponse, this));
        this.recivingThread.start();
    }
    public void recive(final Callback<DatagramPacket> callbackResponse, final Runnable onTimeout, final int timeout)
    {
        if (this.isReciving())
            return;
        this.isReciving = true;
        this.recivingThread = new Thread(new ConnectionListenerTimeout(this.listenSocket, callbackResponse, onTimeout, timeout, this));
        this.recivingThread.start();
    }
    
    public void startReciving(final Callback<DatagramPacket> run)
    {
        if (this.isReciving())
            return;
        this.isReciving = true;
        
        this.recivingThread = new Thread(() -> 
        {
            while (this.isReciving())
                new ConnectionListener(this.listenSocket, run, this).run();
        });
        
        this.recivingThread.start();
    }
//    public void startReciving(final Callback<DatagramPacket> run, final Runnable onTimeout, final int timeout)
//    {
//        if (this.isReciving())
//            return;
//        this.isReciving = true;
//        
//        this.recivingThread = new Thread(() -> 
//        {
//            while (this.isReciving())
//                new ConnectionListenerTimeout(this.listenSocket, run, onTimeout, timeout, this).run();
//        });
//        
//        this.recivingThread.start();
//    }
    public void stopReciving()
    {
        if (!this.isReciving())
            return;
        this.isReciving = false;
        if (this.recivingThread != null)
        {
           this.recivingThread.interrupt();
           this.listenSocket.close();
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
    public Thread getListenThread()
    {
        return this.recivingThread;
    }
    @Override
    public void close() 
    {
        if (this.isReciving())
            this.stopReciving();
        this.listenSocket.close();
        this.sendSocket.close();
    }
}

