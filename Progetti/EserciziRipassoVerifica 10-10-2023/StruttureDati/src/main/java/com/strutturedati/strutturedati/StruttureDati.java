package com.strutturedati.strutturedati;

<<<<<<< HEAD
import java.util.Scanner;

=======
import java.util.Arrays;
>>>>>>> 07e2cf5f59e40f5b686669867ad58e78e216a60a

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
