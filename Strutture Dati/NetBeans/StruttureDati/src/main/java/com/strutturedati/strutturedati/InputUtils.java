package com.strutturedati.strutturedati;

import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

public final class InputUtils
{
    private static Scanner input = new Scanner(System.in);
    
    public void setScanner(Scanner newScanner) { input = newScanner; }
    public Scanner getScanner() { return input; }
  
    public static long getLongInRange(long min, long max) { return getLongInRange("", min, max); }
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

    public static long getValidLong() { return getValidLong(""); } 
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
    public static int getIntInRange(int min, int max) { return getIntInRange("", min, max); }
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

    public static int getValidInt() { return getValidInt(""); }
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
    
    public static short getShortInRange(short min, short max) { return getShortInRange("", min, max); }
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

    public static short getValidShort() { return getValidShort(""); }
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
    public static double getDoubleInRange(double min, double max) { return getDoubleInRange("", min, max); }
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

    public static double getValidDouble() { return getValidDouble(""); } 
    public static double getValidDouble(String prompt) 
    {
        System.out.print(prompt);
        while (!input.hasNextDouble()) 
        {
            System.out.print("Valore non corretto, riprova: ");
            input.nextLine();
        }
        return input.nextDouble();
    }
    public static float getFloatInRange(float min, float max) { return getFloatInRange("", min, max); } 
    public static float getFloatInRange(String prompt, float min, float max) 
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

    public static float getValidFloat() { return getValidFloat(""); } 
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
    public static int getRandIntInRange(int min, int max)
    {
        return min + (new Random().nextInt(max - min));
    }
 
}
