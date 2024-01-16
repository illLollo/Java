/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stack.stack;

import java.util.Arrays;
import java.util.Iterator;

/**
 *
 * @author Administrator
 */
public final class ArrayStackDLA<E> implements Stack<E>
{
    private int size;
    private E[] buffer;
    
    public ArrayStackDLA(final int initialCapacity)
    {
        this.size = 0;
        this.buffer = (E[]) new Object[initialCapacity];
    }
    public ArrayStackDLA() { this(16); }
    public ArrayStackDLA(final Stack<? extends E> st)
    {
        this(st.capacity());
        for (final var temp : st)
            this.push(temp);
    }
    
    @Override
    public int size() 
    {
        return this.size;
    }

    @Override
    public int capacity() 
    {
        return this.buffer.length;
    }

    @Override
    public void push(final E item)
    {
        if (this.size() == this.capacity())
            expand();
        this.buffer[this.size++] = item;
    }

    @Override
    public E pop()
    {
        if (this.size() == 0)
            throw new StackOperationException("Stack Empty, cannot remove!");
        
        final E temp = this.buffer[this.size - 1];
        this.buffer[this.size--] = null;
        
        if (this.size() == (this.capacity() / 2) - 10)
            shrink();
        
        return temp;
    }

    @Override
    public Iterator<E> iterator() 
    {
        return new Iterator<E>()
        {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                return buffer[index++];
            }
        };
    }
    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("[");
        
        for (int i = this.size() - 1; i >= 0; i--)
        {
            if (i < this.size() - 1)
                sb.append(", ");
            sb.append(this.buffer[i]);
        }
        return sb.append("]").toString();
    }
    private void expand()
    {
        this.buffer = Arrays.copyOf(this.buffer, this.buffer.length + 20);
    }
    private void shrink()
    {
        this.buffer = Arrays.copyOf(this.buffer, this.buffer.length + 20);
        
    }
    
}
