/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tris.trisgame;

/**
 *
 * @author Administrator
 * @param <T>
 */
public class Player<T>
{
    private String name;
    private T code;
    
    public Player() {}
    
    public Player(String name, T code)
    {
        this.name = name;
        this.code = code;
    }
    
    public String getName() { return this.name; }
    public T getCode() { return this.code; }
    public void setCode(T code) { this.code = code; } 
    public void setName(String name) { this.name = name; } 
    
    @Override 
    public String toString() { return this.name;}
    @Override
    public boolean equals(Object other)
    {
        if (other == null) return false;
        
        if (other instanceof Player p)
            return p.getCode() == this.getCode() && p.getName().equals(this.getName());
        
        return false;
    }
}
