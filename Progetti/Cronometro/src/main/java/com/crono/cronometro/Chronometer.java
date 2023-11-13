/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.crono.cronometro;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Administrator
 */
public final class Chronometer 
{
    private boolean isRunning;
    private boolean restarted;
    private List<Double> lapses;
    private long lastTime;
    
    public Chronometer()
    {
        this.isRunning = false;
        this.restarted = true;
        this.lapses = new ArrayList<>();
        this.lastTime = 0l;
    }
    public void start()
    {
        if (this.isRunning())
            throw new IllegalChronometerStateException("Chronometer already started!");
        
        this.isRunning = true;
        
        if (this.restarted)
            this.lastTime = System.nanoTime();
        
        this.restarted = false;
    }
    public void stop()
    {
        if (!this.isRunning())
            throw new IllegalChronometerStateException("Chronometer not started yet!");
        this.isRunning = false;
    }
    public void lapse()
    {
        if (!this.isRunning())
            throw new IllegalChronometerStateException("Chronometer not started yet!");
        
        this.lapses.add(this.getElapsed());
    }
    public double getLapse(int index)
    {
        if (!this.isRunning())
            throw new IllegalChronometerStateException("Chronometer not started yet!");
        
        if (index < 0 || index >= this.lapses.size())
            throw new ArrayIndexOutOfBoundsException("Given index out of Lapses list bounds!");
        
        return this.lapses.get(index);
    }
    public double getElapsed()
    {
        if (!this.isRunning())
            throw new IllegalChronometerStateException("Chronometer not started yet!");
        return Chronometer.convertToSeconds(System.nanoTime() - this.lastTime);
    }
    public double[] getLapses()
    {
        double[] lapses = new double[this.lapses.size()];
        
        for (int i = 0; i < this.lapses.size(); i++)
            lapses[i] = this.lapses.get(i);
        
        return lapses;
    }
    public boolean isRunning() { return this.isRunning; }
    
    @Override 
    public String toString()
    {
        final StringBuilder sb = new StringBuilder();
        
        sb.append("Chronometer (");
        sb.append(super.toString());
        sb.append("): { Current Elapsed = ");
        sb.append(this.getElapsed());
        sb.append(" } { Lapses: ");
        sb.append(this.lapses);
        sb.append(" }");
        
        return sb.toString();
    }
    public void reset()
    {
        if (this.isRunning())
            return;
        
        this.lapses = new ArrayList<>();
        this.lastTime = 0l;
        this.restarted = true;
    }
    private static double convertToSeconds(long millis) { return millis / 1000000000.0d; }
}