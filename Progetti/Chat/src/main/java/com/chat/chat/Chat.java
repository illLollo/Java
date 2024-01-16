/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chat.chat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gambaro.lorenzo
 */
public class Chat implements Closeable
{
    private DatagramSocket socket;
    
    public Chat(final int port) throws SocketException
    {
        this.socket = new DatagramSocket(port);
    }

    public void send(final String message, final String inetadd)
    {
        new Thread(() -> 
        {
            try 
            {
                DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, InetAddress.getByName(inetadd), this.socket.getLocalPort());
                this.socket.send(packet);
//
//                final byte[] buffer = new byte[512];
//                
//                packet = new DatagramPacket(buffer, buffer.length);
//                this.socket.receive(packet);

            } catch (final IOException ex) 
            {
                System.err.println("Socket exception: " + ex.getMessage());
                ex.printStackTrace();
            }
        }).start();
    }
    public void send(final Connection obj, final String inetadd)
    {
        new Thread(() -> 
        {
            ByteArrayOutputStream str = new ByteArrayOutputStream();
            try {
                
                new ObjectOutputStream(str).writeObject(obj);
                DatagramPacket packet = new DatagramPacket(str.toByteArray(), str.toByteArray().length, InetAddress.getByName(inetadd), this.socket.getLocalPort());
                
                this.socket.send(packet);
                
            } catch (IOException ex) {
                Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }).start();
    }
    public int getPort() { return this.socket.getLocalPort(); }
    public void recive()
    {   
        new Thread(() -> 
        {
            final byte[] buffer = new byte[512];
            while (true)
            {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                try 
                {
                    this.socket.receive(packet);
                    final Connection con = new Connection(packet.getAddress(), packet.getPort());

                    packet = new DatagramPacket(buffer, buffer.length, con.getAddress(), con.getPort());

//                    System.out.print(con.getAddress());
//                    System.out.print(" : "); 
                    final String message = new String(packet.getData(), 0, packet.getLength());
                    System.out.println(message);
                    
                    ByteArrayInputStream strIn = new ByteArrayInputStream(message.getBytes());
                    
                    final Connection recivedcon = (Connection) new ObjectInputStream(strIn).readObject();
               
                    System.out.println(recivedcon.getAddress());
                    System.out.println(recivedcon.getPort());

//                    socket.send(new DatagramPacket("Ciao".getBytes(), "Ciao".getBytes().length, con.getAddress(), con.getPort()));

                }
                catch (final IOException | ClassNotFoundException ex) 
                {
                    System.out.println();
                }
                
            }
        }).start();
    }

    @Override
    public void close() throws IOException 
    {
        this.socket.close();
    }
    
}
