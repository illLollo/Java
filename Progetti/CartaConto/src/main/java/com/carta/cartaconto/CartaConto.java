/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.carta.cartaconto;

import com.carta.model.Banca;
import com.carta.model.Iban;
import com.carta.model.Indirizzo;
import com.carta.model.Intestatario;
import com.carta.model.TipoIntestatario;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

/**
 *
 * @author Administrator
 */
public class CartaConto {

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException 
    {        
        final Banca b = new Banca("Intesa Sanpaolo", "IT", "03069", "01783");   
        b.registerUser(new Intestatario("Lorenzo", "12345", TipoIntestatario.DEFAULT_USER, "GMBLNZ06T16F241E", "Gambaro", "Lorenzo", LocalDate.parse("12-16-2006"), new Indirizzo("Via Accoppè Fratte", "85 int. 2", "30035", "Mirano", "VE"),"3899369940", "lorygamba06@gmail.com"));
        
        System.out.println(b.login("lorenzo", "12345"));

        final Intestatario logged = b.login("lorenzo", "12345");
        
        
        
        
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