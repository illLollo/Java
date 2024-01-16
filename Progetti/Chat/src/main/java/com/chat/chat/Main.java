/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chat.chat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
/**
 *
 * @author gambaro.lorenzo
 */
public class Main 
{
    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException 
    {
//        ByteArrayOutputStream str = new ByteArrayOutputStream();
//        
//        new ObjectOutputStream(str).writeObject(new Connection(InetAddress.getByName("server632.ddns.net"), 1010));
//        
//        final String obser = new String(str.toByteArray(), 0, str.toByteArray().length);
//        System.out.println(obser);
//        
//        ByteArrayInputStream strIn = new ByteArrayInputStream(obser.getBytes());
//        
//        
//        
//        final Connection con = (Connection) new ObjectInputStream(strIn).readObject();
        

        try 
        {
            final Chat c = new Chat(95);
            c.recive();
            System.out.println("Listening on port: " + c.getPort());
            
            c.recive();
//            c.send("ciao sono lollo", "localhost");
//            c.send(new Connection(InetAddress.getLocalHost(), c.getPort()), "localhost");
        } 
        catch (SocketException ex) 
        {
            ex.printStackTrace();
        }
        
        
    }
}
