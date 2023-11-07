/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.strutturedati.trisgame;

import java.io.InputStream;
import java.util.Scanner;

public abstract class InputUtils 
{
    private static Scanner input = new Scanner(System.in);
    
    private InputUtils() {}
    
    public static void setSource(InputStream source) { input = new Scanner(source); }
    public static void setScanner(Scanner sc) { input = sc; }
        
    public static long getLongInRange() { return getLongInRange("Inserisci un long: ", Long.MIN_VALUE, Long.MAX_VALUE); }
    public static long getLongInRange(String prompt, long min, long max) 
    {
        System.out.print(prompt);

        long v = getValidLong("");

        while (v < min || v > max) 
        {
            System.out.print("Valore al di fuori dei limiti: ");
            input.nextLine();
            v = getValidLong("");
        }
        input.nextLine();

        return v;
    }

    public static long getValidLong() { return getValidLong("Inserisci un long: "); }
    public static long getValidLong(String prompt) 
    {
        System.out.print(prompt);
        while (!input.hasNextLong()) 
        {
            System.out.print("Valore non corretto, riprova: ");
            input.nextLine();
        }
        return input.nextLong();
    }
    public static int getIntInRange() { return getIntInRange("Inserisci un int: ", Integer.MIN_VALUE, Integer.MAX_VALUE); }
    public static int getIntInRange(String prompt, int min, int max) 
    {
        System.out.print(prompt);

        int v = getValidInt("");

        while (v < min || v > max) 
        {
            System.out.print("Valore al di fuori dei limiti: ");
            input.nextLine();
            v = getValidInt("");
        }
        input.nextLine();

        return v;
    }
    
    public static int getValidInt() { return getValidInt("Inserisci un int: "); }
    public static int getValidInt(String prompt) 
    {
        System.out.print(prompt);
        while (!input.hasNextInt()) 
        {
            System.out.print("Valore non corretto, riprova: ");
            input.nextLine();
        }
        return input.nextInt();
    }
    
    public static short getShortInRange() { return getShortInRange("Inserisci uno short: ", Short.MIN_VALUE, Short.MAX_VALUE); } 
    public static short getShortInRange(String prompt, short min, short max) 
    {
        System.out.print(prompt);

        short v = getValidShort("");

        while (v < min || v > max) 
        {
            System.out.print("Valore al di fuori dei limiti: ");
            input.nextLine();
            v = getValidShort("");
        }
        input.nextLine();

        return v;
    }

    public static short getValidShort() { return getValidShort("Inserisci uno short: "); } 
    public static short getValidShort(String prompt) 
    {
        System.out.print(prompt);
        
        while (!input.hasNextShort()) 
        {
            System.out.print("Valore non corretto, riprova: ");
            input.nextLine();
        }
        return input.nextShort();
    }
    public static double getDoubleInRange() { return getDoubleInRange("Inserisci un double: ", Double.MIN_VALUE, Double.MAX_VALUE); }
    public static double getDoubleInRange(String prompt, double min, double max) 
    {
        System.out.print(prompt);

        double v = getValidDouble("");

        while (v < min || v > max) 
        {
            System.out.print("Valore al di fuori dei limiti: ");
            input.nextLine();
            v = getValidDouble("");
        }
        input.nextLine();

        return v;
    }

    public static double getValidDouble() { return getValidDouble("Inserisci un double: "); } 
    public static double getValidDouble(String prompt) 
    {
        System.out.print(prompt);
        while (!input.hasNextDouble()) 
        {
            input.nextLine();
            System.out.print("Valore non corretto, riprova: ");
            input.nextLine();
        }
        return input.nextDouble();
    }
    public static double getFloatInRange() { return getFloatInRange("Inserisci un float: ", Float.MIN_VALUE, Float.MAX_VALUE); }
    public static double getFloatInRange(String prompt, float min, float max) 
    {
        System.out.print(prompt);

        float v = getValidFloat("");

        while (v < min || v > max) 
        {
            System.out.print("Valore al di fuori dei limiti: ");
            input.nextLine();
            v = getValidFloat("");
        }
        input.nextLine();

        return v;
    }

    public static float getValidFloat() { return getValidFloat("Inserisci un float: "); };
    public static float getValidFloat(String prompt) 
    {
        System.out.print(prompt);
        while (!input.hasNextFloat()) 
        {
            System.out.print("Valore non corretto, riprova: ");
            input.nextLine();
        }
        return input.nextFloat();
    }
 
}
