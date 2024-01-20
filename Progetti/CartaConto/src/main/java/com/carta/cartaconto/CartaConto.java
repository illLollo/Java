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
        final Intestatario t = new Intestatario("MSTTMS06C28F241W", "Mistron", "Tommaso", LocalDate.now(), new Indirizzo("Eugenio Montale", "52", "30030", "Pianiga", "VE"), "+393515441701", "tommaso.mistron@gmail.com");
        
        
        final Conto c = b.newConto(l, t);
//        final Conto c2 = b.newConto(t);
        
        c.newOperazione(TipoMovimento.VERSAMENTO_CONTANTE, 150, LocalDate.now().plusDays(2), "Prelievo per spesa");
        c.newOperazione(TipoMovimento.PRELIEVO_CONTANTE, 100, LocalDate.now().plusDays(2), "Prelievo per spesa");
        
        
        System.out.println(c);
//        System.out.println(c.saldo());
        
        
//        System.out.println(c);

//        System.out.println(c.saldo());
//        System.out.println(c.getOperazioni());
//
//        System.out.println(b.findConto(c.getIban()));      
    }
}
