/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.carta.cartaconto;

import com.carta.model.Banca;
import com.carta.model.User;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author Administrator
 */
public class CartaConto {

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException 
    {        
        final Banca b = new Banca("Intesa Sanpaolo", "IT", "03069", "01783");   
        
        try (final FileInputStream fis = new FileInputStream("users.ser"))
        {
            final ObjectInputStream ois = new ObjectInputStream(fis);
            
            for (final User u : (User[]) ois.readObject())
            {
                if (b.login(u.getUsername(), u.getPassword()))
                {
//                    final Conto c =  b.newConto(new Intestatario(u.getUsername(), u.getPassword(), ));
//                    c.newOperazione(TipoMovimento.BONIFICO_RICEVUTO, 1700, LocalDate.now(), "Stipendio Mensile");
//                    c.newOperazione(TipoMovimento.PRELIEVO_CONTANTE, 130, LocalDate.now().plusDays(2), "Perlievio per spese");
                }
            }
//                public Intestatario(final String username, final String password, final String cf, final String cognome, final String nome, final LocalDate birthDate, final Indirizzo address, final String phoneNumber, final String email)

        }
        catch (final ClassNotFoundException ex)
        {
            System.err.println("CANNOT CAST READED OBJECT!");
        }
        catch (final FileNotFoundException ex)
        {
            System.err.println("FILE NOT FOUND!");
        }
        catch (final IOException ex)
        {
            System.err.println("ERROR IN I/O FILE OPERATION!");
            ex.printStackTrace();
        }
        
        System.out.println(b);
    }
}

    //        User[] users = new User[10];
    //        users[0] = new User("verdefrancesca", "vf123");
    //        users[1] = new User("blupaolo", "bp123");
    //        users[2] = new User("rosasilvia", "rs123");
    //        users[3] = new User("celestemarco", "cm123");
    //        users[4] = new User("giallilaura", "gl123");
    //        users[5] = new User("marronegiovanni", "mg123");
    //        users[6] = new User("nerielena", "ne123");
    //        users[7] = new User("verdiluca", "vl123");
    //        users[8] = new User("bianchianna", "ba123");
    //        users[9] = new User("rossimario", "rm123");
    //        
    //        new ObjectOutputStream(new FileOutputStream("users.ser")).writeObject(users);