package com.strutturedati.strutturedati;

import java.util.Arrays;
import java.util.Iterator;

public final class VectorList<T extends Comparable<T>> implements List<T> {

    private T[] buffer;
    private int size;
    private int capacity;

    VectorList()
    {
        System.out.println("default costruttore chiamato");
        this.capacity = 10;
        this.buffer = (T[]) new Object[capacity];
    }
    VectorList(int initialSize)
    {
        System.out.println("costruttore 2 chiamato");
        this.capacity = (initialSize == 0 ? 10 : initialSize);
        this.buffer = (T[]) new Object[capacity];
    }
    VectorList(T[] initializerList) { this.size = initializerList.length; this.capacity = initializerList.length; this.buffer = initializerList; }

    @Override
    public boolean push(T element)
    {
        if (this.size == this.capacity) this.expand(10);
        this.buffer[this.size] = element;
        this.size++;
        return true;
    }
    @Override
    public boolean set(int index, T value)
    {
        if (index < 0 || index >= this.size) throw new ArrayIndexOutOfBoundsException("Indice fuori dai limiti dell'array!");

        this.buffer[index] = value;
        return true;
    }

    public boolean insert(int index, T value)
    {
        if (index < 0 || index > this.size) throw new ArrayIndexOutOfBoundsException("Indice Negativo o Troppo Grande");
        if (this.size == this.capacity) this.expand(10);

        T temp = this.buffer[index];
        this.buffer[index] = value;

        for (int i = index + 1; i <= this.size; i++)
        {
            final T cell = this.buffer[i];
            this.buffer[i] = temp;
            temp = cell;
        }

        this.size++;
        return true;
    }

    @Override
    public T pop() throws ArrayIndexOutOfBoundsException { return this.pop(this.indexOf(this.getLast())); }

    public T pop(int index)
    {
        if (this.isEmpty()) throw new ArrayIndexOutOfBoundsException("Array Vuoto!");
        if (index < 0 || index >= this.size) throw new ArrayIndexOutOfBoundsException("Indice Negativo o Troppo Grande!");

        final T temp = buffer[index];

        for (int i = index; i < this.size - 1; i++) buffer[i] = buffer[i + 1];

        this.size--;

        if (this.size <= this.capacity / 2) this.shrink();

        return temp;
    }

    public T popByValue(T value) throws ArrayIndexOutOfBoundsException { return this.pop(this.indexOf(value)); }
    @Override
    public T get(int index)
    {
        if (this.isEmpty()) throw new ArrayIndexOutOfBoundsException("Array Vuoto!");
        if (index < 0 || index >= this.size) throw new ArrayIndexOutOfBoundsException("Indice Negativo o Troppo Grande!");

        return this.buffer[index];
    }
    @Override
    public T getHead()
    {
        if (this.isEmpty()) throw new ArrayIndexOutOfBoundsException("Array Vuoto!");
        return this.buffer[0];
    }
    @Override
    public T getLast()
    {
        if (this.isEmpty()) throw new ArrayIndexOutOfBoundsException("Array Vuoto!");
        return this.buffer[this.size - 1];
    }
    @Override
    public int size() { return this.size; }
    @Override
    public boolean isEmpty() { return this.size == 0; }
    @Override
    public int indexOf(T value)
    {
        if (this.isEmpty()) throw new ArrayIndexOutOfBoundsException("Array Vuoto!");

        for (int i = 0; i < this.size; i++) if (buffer[i].equals(value)) return i;

        throw new ArrayIndexOutOfBoundsException("Elemento inesistente nell'array!");
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < this.size; i++)
        {
            sb.append(" ");
            sb.append(this.buffer[i]);
            sb.append(i != this.size - 1 ? ", " : " ");
        }
        sb.append("]");

        return sb.toString();
    }
    @Override
    public void print() { System.out.println(this); }

    /**
     *
     */
    @Override
    public void clear() { this.size = 0; }

    private void shrink()
    {
        this.capacity /= 2;
        buffer = Arrays.copyOf(this.buffer, this.capacity);
    }
    private void expand(int slots)
    {
        this.capacity += slots;
        buffer = Arrays.copyOf(this.buffer, this.capacity);
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
            public boolean hasNext()
            {
                return currentIndex < size;
            }

            @Override
            public T next() {
                return buffer[currentIndex++];
            }
        };
    }
    @Override
    public void sort()
    {
        Arrays.sort(this.buffer);
    }
    
    @Override
    public void parallelSort()
    {
        Arrays.parallelSort(this.buffer);
    }
}
