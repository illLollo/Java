/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.carta.cartaconto;

/**
 *
 * @author Administrator
 */
public class CartaConto {

    public static void main(String[] args) 
    {
        final Banca b = new Banca("Centromarca Banca", "IT", 'X', "30", "36190", "08749");
        
        final Conto c = b.newConto("171804");
        
        System.out.println(c.getIban());
    }
}
