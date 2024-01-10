/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.carta.cartaconto;

import java.time.LocalDate;
import java.util.Arrays;

/**
 *
 * @author Administrator
 */
public class CartaConto {

    public static void main(String[] args) 
    {
        final Banca b = new Banca("Centromarca Banca", "IT", 'X', "30", "36190", "08749");
        
        
        final Intestatario l = new Intestatario("GMBLNZ06T16F241E", "Gambaro", "Lorenzo", LocalDate.now(), new Indirizzo("Accoppè Fratte", "85 int.2", "30035", "Mirano", "VE"), "+393899369940", "lorygamba06@gmail.com");
        final Intestatario s = new Intestatario("MSCSLV07B50F241I", "Maschio", "Silvia", LocalDate.now(), new Indirizzo("Cognaro", "85 int.2", "30035", "Mirano", "VE"), "+393899369940", "lorygamba06@gmail.com");
        
        final Intestatario[] i = {l, s};
        
        
        final Conto c = b.newConto(i, "171804");
        
        
        final Movimento m = new Movimento(LocalDate.now(), LocalDate.now(), "Primo movimento di prova", c.getIban(), 1000, 1.0);
        final Movimento m2 = new Movimento(LocalDate.now(), LocalDate.now(), "Primo movimento di prova", c.getIban(), -1000, 0.0);
        
        c.newOperazione(m);
        c.newOperazione(m2);
//        
        
        System.out.println(c);
        System.out.println(c.saldo());
        System.out.println(c.getOperazioni());
        
    }
}
