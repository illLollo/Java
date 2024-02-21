/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.network.confrontodinumeri;

import com.udp.messanger.ConnectionManager;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author gambaro.lorenzo
 */
public class EchoClientUDP 
{
    public static void main(String[] args)
    {
        try
        {          
            final Scanner sc = new Scanner(System.in);
            
            final ConnectionManager c = new ConnectionManager();
            
            System.out.println("Inserisci l'indirizzo al quale vuoi connetterti: ");
            final InetAddress address = InetAddress.getByName(sc.nextLine());
            
            System.out.println("Inserisci la porta dell'indirizzo alla quale vuoi spedire i messaggi: ");
            final int destPort = sc.nextInt();
            
            sc.nextLine();
            
            String line; 
            while (!(line = sc.nextLine()).equals("END"))
            {
                String[] bytes = line.split(" ");
                                
                if (bytes.length == 2)
                {
                    byte[] data = new byte[2];
                    
                    try 
                    {
                        data[0] = Byte.parseByte(bytes[0]);
                        data[1] = Byte.parseByte(bytes[1]);

                        c.send(data, address, destPort);
                        Thread.sleep(1);  // per avere una corrispondenza semi-ordinata tra client e server
                        
                    }
                    catch (final NumberFormatException ex)
                    {
                        System.err.println("Uno dei due o entrambi i valori inseriti non validi!");
                    }
                } 
                else
                    System.err.println("Formato dell'input non corretto!");
            }    
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
