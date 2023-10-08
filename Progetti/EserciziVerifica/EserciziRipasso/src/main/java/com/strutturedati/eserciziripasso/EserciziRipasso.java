package com.strutturedati.eserciziripasso;


public class EserciziRipasso 
{

    public static void main(String[] args) 
    {
        final String[] daysOfWeek = {"Lunedì", "Martedì", "Mercoledì", "Giovedì", "Venerdì", "Sabato", "Domenica"};
        final double[] temps = new double[7];
        
        for (int i = 0; i < temps.length; i++)
            temps[i] = InputUtils.getValidDouble("Inserisci la temperatura di \"" + daysOfWeek[i] + "\": ");
        
        double min = findMin(temps);
        double max = findMax(temps);
        double middle = findMiddle(temps);
        
        
        System.out.println("La minima è: \"" + min + "\" nel giorno: \"" + daysOfWeek[findIndex(temps, min)] + "\"");
        System.out.println("La massima è: \"" + max + "\" nel giorno: \"" + daysOfWeek[findIndex(temps, max)] + "\"");
        System.out.println("La media è: \"" + middle + "\"");
    }
    public static double findMin(double[] arr)
    {
        double min = arr[0];
        
        for (double d : arr)
            if (d < min) min = d;
        
        return min;
    }
    public static double findMax(double[] arr)
    {
        double max = arr[0];
        
        for (double d : arr)
            if (d > max) max = d;
        
        return max;
    }
    public static double findMiddle(double[] arr)
    {
        double sum = 0;
        
        for (double d : arr)
            sum += d;
        
        return sum / arr.length;
    }
    public static int findIndex(double[] arr, double para)
    {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == para) return i;
        
        return -1;
    }
}
