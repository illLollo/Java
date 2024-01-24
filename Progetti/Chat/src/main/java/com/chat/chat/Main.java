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
            final Scanner sc2 = new Scanner(System.in);
            
            System.out.println("Inserisci la porta alla quale vuoi connetterti: ");
            
            final Messanger c =  new Messanger(sc2.nextInt());
            c.startReciving((final DatagramPacket packet) -> 
            {
                System.out.println(new String(packet.getData(), 0, packet.getLength())); 
            });
            sc2.nextLine();
            System.out.println("Inserisci l'indirizzo al quale vuoi connetterti: ");
            final String add = sc2.nextLine();
            
            System.out.println("Benvenuto nella chat, inserisci il tuo nome: ");
            
            
            StringBuilder sb = new StringBuilder();
//
            final String name = sc2.nextLine();
            System.out.println("Buona permanenza " + name + "!");
            
            String line;
            while (sc2.hasNextLine() && !(line = sc2.nextLine()).equals("END"))
            {
                c.send(name+ ": " + line, InetAddress.getByName(add));
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
