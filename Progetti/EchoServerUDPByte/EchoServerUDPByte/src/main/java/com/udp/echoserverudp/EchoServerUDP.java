/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.udp.echoserverudp;

import com.udp.connectionmodel.ConnectionManager;
import java.net.DatagramPacket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Lorenzo
 */
public class EchoServerUDP 
{
    public static void main(String[] args)
    {
        try
        {          
            final Scanner sc = new Scanner(System.in);
            
            System.out.println("Inserisci la porta di ascolto: ");
            final int listenPort = sc.nextInt();
            
            final ConnectionManager c = new ConnectionManager(listenPort);
            
            sc.nextLine(); //clearing output
            
            c.startReciving((final DatagramPacket packet) -> 
            {
                byte[] value = Arrays.copyOf(packet.getData(), packet.getLength());
                
                System.out.println(value[0] < value[1] ? value[0] + " < " + value[1]: value[0] + " > " + value[1]);
            });
        }
        catch (final SocketException ex)
        {
            System.err.println("Errore nell' I/O del socket: " + ex.getMessage());
        } 
        catch (final InputMismatchException ex)
        {
            System.err.println("Errore nell'input!");
        }  
    }
}
