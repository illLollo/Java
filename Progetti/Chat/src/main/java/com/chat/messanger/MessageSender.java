package com.chat.messanger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Objects;
/**
 *
 * @author Administrator
 */
public class MessageSender implements Runnable
{
    private final DatagramSocket socket;
    private final InetAddress address;
    private final String message;
    
    public MessageSender(final DatagramSocket socket, final InetAddress address, final String message)
    {
        this.socket = Objects.requireNonNull(socket);
        this.address = Objects.requireNonNull(address);
        this.message = Objects.requireNonNull(message);
    }
    
    @Override
    public void run() 
    {
        try 
        {
            this.socket.send(new DatagramPacket(this.message.getBytes(), this.message.getBytes().length, this.address, this.socket.getLocalPort()));
            
        } catch (final IOException ex) 
        {
            System.err.println(ex.getMessage());
        }
    }
    
}
