package com.strutturedati.esericiripassoverifica_10102023;

import java.time.LocalDate;
import java.util.Scanner;

public class EsericiRipassoVerifica_10102023
{
    private static final String[] dow = {"Lunedi", "Martedi", "Mercoledi", "Giovedi", "Venerdi", "Sabato", "Domenica"}; 
    
    public static void main(String[] args) 
    {
        final Scanner sc = new Scanner(System.in);
        
        System.out.println("Inserisci una stringa: ");
        printCharArrayAsWord(stringToCharArray(sc.nextLine()));
        
        int today = (LocalDate.now().getDayOfWeek().getValue()) - 1;
          
        while (!sc.nextLine().equals(dow[today]))
            System.out.print("Hai inserito un giorno sbagliato, riprova: ");
        
        System.out.println("Giorno corretto, oggi è " + dow[today]);
    }
    
    public static char[] stringToCharArray(String s)
    {
        char[] buffer = new char[s.length() + 1];
        
        for (int i = 0; i < s.length(); i++) buffer[i] = s.charAt(i);
        
        return buffer;
    }

    public static void printCharArrayAsWord(char[] buffer)
    {
        System.out.print("Array di caratteri: \"");
        for (int i = 0; i < buffer.length; i++) 
            System.out.print(buffer[i]);
        System.out.println("\"");
    }
}
