import java.util.Arrays;
public class Stack<T> implements List<T>
{
    private T[] buffer;
    private int size;
    private int capacity;

    Stack() { this.capacity = 20; this.size = 0; buffer = (T[]) new Object[this.capacity]; }
    Stack(int initialSize) { this.capacity = initialSize; this.size = 0; buffer = (T[]) new Object[this.capacity]; }

    /**
     *
     * @param element the object to push into the stack
     * @return true if it can push, false if can't
     */
    @Override
    public boolean push(T element)
    {
        if (this.size == this.capacity) this.expand();
        this.buffer[this.size] = element;
        this.size++;
        return true;
    }

    /**
     *
     * @param index the index of the element to modify
     * @param value the new value to reference
     * @return true if it can set, false if can't
     * @throws EmptyArrayException if the stack is empty
     * @throws ArrayIndexOutOfBoundsException if the index is < 0 || > size - 1
     */
    @Override
    public boolean set(int index, T value) throws EmptyArrayException
    {
        if (this.isEmpty()) throw new EmptyArrayException();
        if (index < 0 || index > this.size - 1) throw new ArrayIndexOutOfBoundsException("Indice fuori dai limiti dell'array!");

        this.buffer[this.size - 1 - index] = value;
        return true;
    }


    /**
     *
     * @return the last element been inserted (FIFO)
     * @throws EmptyArrayException if the stack is empty
     */
    @Override
    public T pop() throws EmptyArrayException
    {
        if (this.isEmpty()) throw new EmptyArrayException();
        if (this.size <= this.capacity / 2) this.shrink();
        this.size--;
        return this.buffer[this.size];
    }

    /**
     * @param index the index of the element to get
     * @return the element if founds it
     * @throws EmptyArrayException if the stack is empty
     * @throws ArrayIndexOutOfBoundsException if the index is < 0 or > of size - 1
     */
    @Override
    public T get(int index) throws EmptyArrayException, ArrayIndexOutOfBoundsException
    {
        if (this.isEmpty()) throw new EmptyArrayException("Array Vuoto!");
        if (index < 0 || index > this.size - 1) throw new ArrayIndexOutOfBoundsException("Indice fuori dai limiti della coda!");
        return this.buffer[this.size - 1 - index];
    }

    /**
     * @return the reference object to the head of the stack
     */
    @Override
    public T getHead() throws EmptyArrayException
    {
        if (this.isEmpty()) throw new EmptyArrayException();
        return this.buffer[this.size - 1];
    }

    /**
     * @return the reference object to the last element of the stack
     */
    @Override
    public T getLast() throws EmptyArrayException
    {
        if (this.isEmpty()) throw new EmptyArrayException();
        return this.buffer[0];
    }

    /**
     * @return the size of the stack
     */
    @Override
    public int size() { return this.size; }

    /**
     * @return true if the stack is empty, false if is not
     */
    @Override
    public boolean isEmpty() { return this.size == 0; }

    /**
     *
     * @param value the value to search into the stack
     * @return the value's index if present
     * @throws EmptyArrayException if the stack is empty
     * @throws ElementNotInArrayException if the element isn't into the stack
     */
    @Override
    public int indexOf(T value) throws EmptyArrayException, ElementNotInArrayException
    {
        if (this.isEmpty()) throw new EmptyArrayException();

        for (int i = 0; i < this.size; i++) if (this.buffer[i].equals(value)) return this.size - 1 - i;
        throw new ElementNotInArrayException();
    }

    /**
     *
     * @return the String rappresentation of the stack
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("[");

        for (int i = this.size - 1; i >= 0; i--)
        {
            sb.append(i == this.size - 1 ? "" : ", ");
            sb.append(this.buffer[i]);
        }
        sb.append("]");

        return sb.toString();
    }
    @Override
    public void print() { System.out.println(this); }

    @Override
    public void clear() { this.size = 0; }

    private void expand(int slots)
    {
        this.capacity += slots;
        buffer = Arrays.copyOf(buffer, this.capacity);
    }
    private void shrink()
    {
        this.capacity /= 2;
        buffer = Arrays.copyOf(buffer, this.capacity);
    }
    private void expand() { this.expand(10); }

}
