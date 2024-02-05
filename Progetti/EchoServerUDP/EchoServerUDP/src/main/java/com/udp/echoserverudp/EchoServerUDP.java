/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.udp.echoserverudp;

import com.udp.connectionmodel.ConnectionManager;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
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
            
            System.out.println("Inserisci l'indirizzo al quale vuoi connetterti: ");
            final InetAddress address = InetAddress.getByName(sc.nextLine());
            
            System.out.println("Inserisci la porta dell'indirizzo alla quale vuoi spedire i messaggi: ");
            final int destPort = sc.nextInt();
            
            c.startReciving((final DatagramPacket packet) -> 
            {
                System.out.println(new String(packet.getData(), 0, packet.getLength())); 
            });
            
            String line; 
            while (!(line = sc.nextLine()).equals("END"))
            {
                c.send(line, address, destPort);
                Thread.sleep(1);  // per avere un flusso di dati semi-ordinato
            }
            
            System.out.println("Smesso di inviare, rimango in ascolto!");
        } 
        catch (final UnknownHostException ex)
        {
            System.err.println("Host fornito sconosciuto");
        }
        catch (final SocketException ex)
        {
            System.err.println("Errore nell' I/O del socket: " + ex.getMessage());
        } 
        catch (final IOException ex)
        {
            System.err.println("Errore nell' I/0!");
        }
        catch (final InputMismatchException ex)
        {
            System.err.println("Errore nell'input!");
        }
        catch (final InterruptedException ex)
        {
            System.err.println("Errore nell'interruzione del Thread!");
        }   
    }
}
