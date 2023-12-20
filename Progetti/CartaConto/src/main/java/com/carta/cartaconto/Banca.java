/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carta.cartaconto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author gambaro.lorenzo
 */
public class Banca 
{
    private final String bic;
    private final List<Conto> conti;
    
    public Banca()
    {
        this.bic = generateBankCode();
        this.conti = new ArrayList<>();
    }
    public Conto newConto(final String beneficiaryCode)
    {
        final Conto c = new Conto();
    }
    private String generateIban()
    {
        
    }
    public static String generateBankCode()
    {
        final Random rnd = new Random();
        final StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < 6; i++)
            sb.append((char)rnd.nextInt(65, 90));
        
        int index = 0;
        while (index < 5)
        {
            char code = (char) rnd.nextInt(48, 90);
            if ((code >= '0' && code <= '9') || (code >= 'A' && code <= 'Z'))
            {
                sb.append(code);
                ++index;
            }
                
        }
        return sb.toString();
    }
}
