/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chat.chat;

import com.chat.messanger.Messanger;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;
/**
 *
 * @author gambaro.lorenzo
 */
public class Main 
{
    public static void main(String[] args) throws IOException, InterruptedException 
    {
        try
        {          
            final Messanger c =  new Messanger(2233);
            c.startReciving((final DatagramPacket packet) -> 
            {
                System.out.println(new String(packet.getData(), 0, packet.getLength())); 
            });
//            c.recive(param -> System.out.println("asd"));
            final Scanner sc2 = new Scanner(System.in);
            
            StringBuilder sb = new StringBuilder();
//
            String line;
            while (sc2.hasNextLine() && !(line = sc2.nextLine()).equals("END"))
            {
                c.send(line, InetAddress.getByName("lollohomeserver.ddns.net"));
                Thread.sleep(1);
            }
            
//            c.recive(f -> System.out.println(f));
        } 
        catch (SocketException ex)
        {
            System.err.println("CPPP");
        } 
        catch (IOException ex) 
        {
            System.err.println("CPP");
        }
        
        
    }
}
