/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.stack.stack;

/**
 *
 * @author Administrator
 * @param <E>
 */
public interface Stack<E> extends Iterable<E>
{
    int size();
    int capacity();
    void push(final E item);
    E pop();
    
}
