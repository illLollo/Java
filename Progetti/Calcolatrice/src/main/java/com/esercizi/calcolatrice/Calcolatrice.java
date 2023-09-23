package com.esercizi.calcolatrice;

import java.util.Scanner;

public class Calcolatrice
{  
    public void start()
    {  
        try
        {
            switch (getOperationType())
            {
                case ADDIZIONE -> System.out.println(new Addizione(getValidDouble("Inserisci il primo addendo: "), getValidDouble("Inserisci il secondo addendo: ")).calcola());
                case SOTTRAZIONE -> System.out.println(new Sottrazione(getValidDouble("Inserisci il sottraendo: "), getValidDouble("Inserisci il minuendo: ")).calcola());
                case MOLTIPLICAZIONE -> System.out.println(new Moltiplicazione(getValidDouble("Inserisci il primo fattore: "), getValidDouble("Inserisci il secondo fattore: ")).calcola());
                case DIVISIONE -> System.out.println(new Divisione(getValidDouble("Inserisci il dividendo: "), getValidDouble("Inserisci il divisore: ")).calcola());
                case POTENZA -> System.out.println(new Potenza(getValidDouble("Inserisci la base: "), getValidDouble("Inserisci l'esponente: ")).calcola());
                case RADICEQUADRATA -> System.out.println(new Radice(getValidDouble("Inserisci il valore da calcolare la radice quadrata: ")).calcola());
            }
        } 
        catch (ArithmeticException e) { System.err.println(e.getMessage()); }
    }
    private static Operazioni getOperationType() { return getOperationType("Inserisci il tipo di operazione (+, -, *, /, ^, |): "); }
    private static Operazioni getOperationType(String prompt)
    {
        final Scanner sc = new Scanner(System.in);
                
        System.out.println(prompt);
               
        do 
        {
            final String result = sc.nextLine();
                                   
            if (result.contains(Operazioni.ADDIZIONE.toString())) return Operazioni.ADDIZIONE;
            else if (result.contains(Operazioni.SOTTRAZIONE.toString())) return Operazioni.SOTTRAZIONE;
            else if (result.contains(Operazioni.MOLTIPLICAZIONE.toString())) return Operazioni.MOLTIPLICAZIONE;
            else if (result.contains(Operazioni.DIVISIONE.toString())) return Operazioni.DIVISIONE;
            else if (result.contains(Operazioni.POTENZA.toString())) return Operazioni.POTENZA;
            else if (result.contains(Operazioni.RADICEQUADRATA.toString())) return Operazioni.RADICEQUADRATA;
                        
            System.out.print("Input sbagliato, riprova: ");
            
        } while (true); 
    }
    
    private static double getValidDouble(String prompt)
    {
        final Scanner sc = new Scanner(System.in);
        
        System.out.println(prompt);
        
        while (!sc.hasNextDouble())
        {
            System.out.print("Input sbagliato, riprova: ");
            sc.nextLine();
        }
        
        return sc.nextDouble();
    }

}
