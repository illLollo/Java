public class LinkedVector <T> implements List<T>  {

    private transient Node<T> first = null;
    private transient Node<T> last = null;

    private int size = 0;

    @Override
    public boolean push(T element)
    {
       if (this.isEmpty())
       {
           this.first = new Node<>(element, null, this.last);
           this.last = this.first;
       }
       else
       {
           this.last.setNext(new Node<>(element, last, null));
           last = last.getNext();
       }

       this.size++;
       return true;
    }

    @Override
    public boolean set(int index, T value) throws ArrayIndexOutOfBoundsException { this.search(index).setData(value); return true; }

    @Override
    public boolean insert(int index, T value)
    {
        if (index == 0)
        {
            Node<T> newCell = new Node<>(value, null, this.first);
            this.first = newCell;
            return true;
        }
        Node<T> iterator = this.search(index);

        System.out.println(iterator);

        Node<T> newCell = new Node<>(value, iterator.getPrevious(), iterator);

        iterator.getPrevious().setNext(newCell);

        return true;
    }

    @Override
    public T pop()
    {
        if (this.isEmpty()) throw new RuntimeException("Array Vuoto!");

        final T temp = this.last.getData();

        if (this.size == 1)
        {
            this.first = null;
            this.last = null;
        }
        else
        {
            this.last.getPrevious().setNext(null);
            this.last = this.last.getPrevious();
        }

        this.size--;
        return temp;
    }

    @Override
    public T pop(int index) throws ArrayIndexOutOfBoundsException
    {
        Node<T> iterator = this.search(index);

        if (index == 0)
            this.first = iterator.getNext();
        else
            iterator.getPrevious().setNext(iterator.getNext());

        this.size--;

        return iterator.getData();
    }

    @Override
    public T popByValue(T value) { return this.pop(this.indexOf(value)); }

    private Node<T> search(int index)
    {
        if (index < 0 || index >= this.size) throw new ArrayIndexOutOfBoundsException("Indice fuori dai limiti dell'array!");

        Node<T> iterator;

        if (index > this.size / 2) //scan all'indietro
        {
            iterator = this.last;

            for (int i = this.size - 1; i > index; i--)
                iterator = iterator.getPrevious();
        }
        else //scan in avanti
        {
            iterator = this.first;

            for (int i = 0; i < index; i++)
                iterator = iterator.getNext();
        }

        return iterator;
    }
    @Override
    public T get(int index) throws ArrayIndexOutOfBoundsException { return this.search(index).getData(); }

    @Override
    public T getHead()
    {
        if (this.isEmpty()) throw new ArrayIndexOutOfBoundsException("Array Vuoto!");
        return this.first.getData();
    }

    @Override
    public T getLast()
    {
        if (this.isEmpty()) throw new ArrayIndexOutOfBoundsException("Array Vuoto!");
        return this.last.getData();
    }

    @Override
    public int size() { return this.size; }

    @Override
    public boolean isEmpty() { return this.size == 0; }

    @Override
    public int indexOf(T value)
    {
        Node<T> iterator = this.first;

        for (int i = 0; iterator != null; i++, iterator = iterator.getNext()) if (iterator.getData().equals(value)) return i;

        throw new ArrayIndexOutOfBoundsException("Elemento non presente nell'array");
    }
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("[");

        Node<T> iterator = first;

        while (iterator != null)
        {
            sb.append(" ");
            sb.append(iterator.getData());
            sb.append(iterator.getNext() == null ? " " : ", ");

            iterator = iterator.getNext();
        }
        sb.append("]");

        return sb.toString();
    }


    @Override
    public void print() { System.out.println(this); }

}
