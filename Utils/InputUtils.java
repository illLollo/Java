package com.esercizi.calcolatrice;

import java.io.InputStream;
import java.util.Scanner;

public final class InputUtils
{
    private final Scanner input;
    
    InputUtils() { input = new Scanner(System.in); }
    InputUtils(InputStream is) { input = new Scanner(is); }
    
    public long getLongInRange(long min, long max) { return this.getLongInRange("", min, max); }
    public long getLongInRange(String prompt, long min, long max) 
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

    public long getValidLong() { return this.getValidLong(""); } 
    public long getValidLong(String prompt) 
    {
        System.out.print(prompt);
        while (!input.hasNextLong()) 
        {
            System.out.print("Valore non corretto, riprova: ");
            input.nextLine();
        }
        return input.nextLong();
    }
    public int getIntInRange(int min, int max) { return this.getIntInRange("", min, max); }
    public int getIntInRange(String prompt, int min, int max) 
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

    public int getValidInt() { return this.getValidInt(""); }
    public int getValidInt(String prompt) 
    {
        System.out.print(prompt);
        while (!input.hasNextInt()) 
        {
            System.out.print("Valore non corretto, riprova: ");
            input.nextLine();
        }
        return input.nextInt();
    }
    
    public short getShortInRange(short min, short max) { return this.getShortInRange("", min, max); }
    public short getShortInRange(String prompt, short min, short max) 
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

    public short getValidShort() { return this.getValidShort(""); }
    public short getValidShort(String prompt) 
    {
        System.out.print(prompt);
        
        while (!input.hasNextShort()) 
        {
            System.out.print("Valore non corretto, riprova: ");
            input.nextLine();
        }
        return input.nextShort();
    }
    public double getDoubleInRange(double min, double max) { return this.getDoubleInRange("", min, max); }
    public double getDoubleInRange(String prompt, double min, double max) 
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

    public double getValidDouble() { return this.getValidDouble(""); } 
    public double getValidDouble(String prompt) 
    {
        System.out.print(prompt);
        while (!input.hasNextDouble()) 
        {
            System.out.print("Valore non corretto, riprova: ");
            input.nextLine();
        }
        return input.nextDouble();
    }
    public double getFloatInRange(float min, float max) { return this.getFloatInRange("", min, max); } 
    public double getFloatInRange(String prompt, float min, float max) 
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

    public float getValidFloat() { return this.getValidFloat(""); } 
    public float getValidFloat(String prompt) 
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
