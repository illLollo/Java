/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.crono.cronometro;

import java.util.Arrays;

/**
 *
 * @author Administrator
 */
public class CronometroMain {

    public static void main(String[] args) throws InterruptedException 
    {
        final Chronometer ch = new Chronometer();
        ch.start();
        
        for (int i = 0; i < 10; i++)
        {
            Thread.sleep(1000);
            ch.lapse();
            System.out.println(ch.getElapsed());
        }
        
        System.out.println(ch);
        
        System.out.println(Arrays.toString(ch.getLapses()));
    }
}
