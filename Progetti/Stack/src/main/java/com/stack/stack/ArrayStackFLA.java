/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stack.stack;

import java.util.Iterator;

/**
 *
 * @author Administrator
 * @param <E>
 */
public final class ArrayStackFLA<E> implements Stack<E>
{
    private int size;
    private E[] buffer;
    
    public ArrayStackFLA(final int dim)
    {
        this.size = 0;
        this.buffer = (E[]) new Object[dim];
    }
    public ArrayStackFLA()
    {
        this(16);
    }
    public ArrayStackFLA(final Stack<? extends E> st)
    {
        this(st.size());
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
            throw new StackOperationException("Stack Full, cannot add!");
        this.buffer[this.size++] = item;
    }

    @Override
    public E pop() 
    {
        if (this.size == 0)
            throw new StackOperationException("Stack Empty, cannot remove!");

        final E temp = this.buffer[this.size - 1];
        this.buffer[this.size--] = null;
        
        return temp;
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

    @Override
    public Iterator<E> iterator() 
    {
        return new Iterator<E>() 
        {
            int index = 0;
            @Override
            public boolean hasNext() 
            {
                return index < size;
            }

            @Override
            public E next() 
            {
                return buffer[index++];
            }
        };
    }
    
}
