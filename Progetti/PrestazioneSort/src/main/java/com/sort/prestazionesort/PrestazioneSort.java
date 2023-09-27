/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.sort.prestazionesort;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author gambaro.lorenzo
 */
public class PrestazioneSort {

    public static void main(String[] args)
    {        

        
        for (int i = 1; i < 9; i++)
        {
            int[] a = new int[(int)Math.pow(10, i)];
            
            for (int j = 0; j < a.length; j++) { a[j] = getRandIntInRange(10, 90000); }
            int[] b = Arrays.copyOf(a, a.length);
            
            long timeA = System.nanoTime();
            
            Arrays.sort(a);
            
            double timeDiffA = System.nanoTime() - timeA;
            
            long timeB = System.nanoTime();
            
            Arrays.parallelSort(b);
            
            double timeDiffB = System.nanoTime() - timeB;
            
            System.out.println("Tempo arr[" + a.length + "] (static sort) Rapporto: " + (timeDiffA / timeDiffB));    
        }
        System.out.println("");
    }
        public static int getRandIntInRange(int min, int max)
    {
        return min + (new Random().nextInt(max - min));
    }
}
