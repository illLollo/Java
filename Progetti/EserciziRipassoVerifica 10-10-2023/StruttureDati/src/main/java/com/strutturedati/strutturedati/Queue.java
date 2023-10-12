package com.strutturedati.strutturedati;

import java.util.Arrays;
import java.util.Iterator;

public final class Queue<T> implements List<T>
{
    private T[] buffer;
    private int size;
    private int capacity;
    Queue() { this.size = 0; this.capacity = 10; this.buffer = (T[]) new Object[this.capacity]; }
    Queue(int initialCapacity) { this.size = 0; this.capacity = initialCapacity; this.buffer = (T[]) new Object[this.capacity]; }
    /**
     * @param element
     * @return
     */
    @Override
    public boolean push(T element)
    {
        this.buffer[size++] = element;
        return true;
    }

    /**
     * @param index
     * @param value
     * @return
     * @throws EmptyArrayException
     */
    @Override
    public boolean set(int index, T value) throws EmptyArrayException
    {
        if (this.isEmpty()) throw new EmptyArrayException();
        if (index < 0 || index > this.size - 1) throw new ArrayIndexOutOfBoundsException("Indice fuori dai limiti della coda!");

        this.buffer[index] = value;
        return true;
    }

    /**
     * @return
     * @throws EmptyArrayException
     */
    @Override
    public T pop() throws EmptyArrayException
    {
        if (this.isEmpty()) throw new EmptyArrayException();

        final T temp = this.buffer[0];

        for (int i = 0; i < this.size - 1; i++)
            this.buffer[i] = this.buffer[i + 1];

        this.size--;
        return temp;
    }

    /**
     * @param index
     * @return
     * @throws EmptyArrayException
     */
    @Override
    public T get(int index) throws EmptyArrayException
    {
        if (this.isEmpty()) throw new EmptyArrayException();
        if (index < 0 || index > this.size - 1) throw new ArrayIndexOutOfBoundsException("Indice fuori dai limiti della coda!");

        return this.buffer[index];
    }

    /**
     * @return
     * @throws EmptyArrayException
     */
    @Override
    public T getHead() throws EmptyArrayException
    {
        if (this.isEmpty()) throw new EmptyArrayException();
        return this.buffer[0];
    }

    /**
     * @return
     * @throws EmptyArrayException
     */
    @Override
    public T getLast() throws EmptyArrayException
    {
        if (this.isEmpty()) throw new EmptyArrayException();
        return this.buffer[this.size - 1];
    }

    /**
     * @return
     */
    @Override
    public int size() { return this.size; }

    /**
     * @return
     */
    @Override
    public boolean isEmpty() { return this.size == 0; }

    /**
     * @param value
     * @return
     * @throws EmptyArrayException
     * @throws ElementNotInArrayException
     */
    @Override
    public int indexOf(T value) throws EmptyArrayException, ElementNotInArrayException
    {
        if (this.isEmpty()) throw new EmptyArrayException();

        for (int i = 0; i < this.size; i++)
            if (this.buffer[i].equals(value)) return i;

        throw new ElementNotInArrayException();
    }

    /**
     *
     */
    @Override
    public void print() { System.out.println(this); }

    /**
     *
     */
    @Override
    public void clear() { this.size = 0; }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("[");

        for (int i = 0; i < this.size; i++)
        {
            sb.append(i == 0 ? "" : ", ");
            sb.append(this.buffer[i]);
        }
        sb.append("]");

        return sb.toString();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>()
        {
            private int currentIndex = 0;
            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                return buffer[currentIndex++];
            }
        };
    }
    @Override
    public void sort() { Arrays.sort(this.buffer); }
    
    @Override 
    public void parallelSort() { /*Arrays.parallelSort(this.buffer); */ }
}

