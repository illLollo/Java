/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.carta.cartaconto;

import java.time.LocalDate;

/**
 *
 * @author Administrator
 */
public class CartaConto {

    public static void main(String[] args) 
    {
        final Banca b = new Banca("Centromarca Banca", "IT", 'X', "30", "36190", "08749");
        
        
        final Intestatario l = new Intestatario("GMBLNZ06T16F241E", "Gambaro", "Lorenzo", LocalDate.now(), new Indirizzo("Accoppè Fratte", "85 int.2", "30035", "Mirano", "VE"), "+393899369940", "lorygamba06@gmail.com");
        final Intestatario t = new Intestatario("MSTTMS06C28F241W", "Mistron", "Tommaso", LocalDate.now(), new Indirizzo("Eugenio Montale", "35", "30030", "Pianiga", "VE"), "+393515441701", "tommaso.mistron@gmail.com");
        
        final Intestatario[] i = {l, t};
        
        
        final Conto c = b.newConto(i, "171804");
        final Conto c2 = b.newConto(t, "171884");
        
        
        final Movimento m = new Movimento(LocalDate.now(), LocalDate.now(), "Primo movimento di prova", c.getIban(), 1000, 1.0);
        final Movimento m2 = new Movimento(LocalDate.now(), LocalDate.now(), "Primo movimento di prova", c.getIban(), -1000, 0.0);
        
        c.newOperazione(m);
        c.newOperazione(m2);
        
        
        System.out.println(c);
        System.out.println(c.saldo());
        System.out.println(c.getOperazioni());

        System.out.println(b.findConto(c.getIban()));      
    }
}
