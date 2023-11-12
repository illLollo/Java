/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.crono.cronometro;

/**
 *
 * @author Administrator
 */
public class CronometroMain {

    public static void main(String[] args) throws InterruptedException 
    {
        final Chronometer ch = new Chronometer();
        ch.start();
        
        Thread.sleep(1000);
        ch.lapse();
        
        System.out.println(ch);
    }
}
