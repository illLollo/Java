/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.carta.cartaconto;

import com.carta.model.Banca;
import com.carta.model.Iban;
import com.carta.model.TipoUtente;
import com.carta.model.User;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Administrator
 */
public class CartaConto {

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException 
    {        
        final Banca b = new Banca("Intesa Sanpaolo", "IT", "03069", "01783");   
        b.registerUser(new User("lorenzo", "12345", TipoUtente.DEFAULT_USER));
        
        System.out.println(b.login("lorenzo", "12345"));

        final User logged = b.login("lorenzo", "12345");
        
        if (logged != null)
        {
            
        }
        
        System.out.println(b.findConto(new Iban("IT30K1231123123123112123123")));
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