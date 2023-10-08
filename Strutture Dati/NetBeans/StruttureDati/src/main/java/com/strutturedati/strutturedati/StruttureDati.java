package com.strutturedati.strutturedati;

import java.util.Scanner;


public class StruttureDati 
{

    public static void main(String[] args)
    {

        Scanner sc = new Scanner(System.in);
        
        final Registro reg = new Registro(10);
        
        for (int i = 0; i < 6; i++) {
            reg.add(new Studente("asdasd", 10 + i, 18 + i));
        }
        
        reg.visual();
        
        
        System.out.println("\n\n");
        
        reg.findMinAge().view();
        
        System.out.println("\n\n");
        
        reg.findMaxAge().view();
    }
    
}
