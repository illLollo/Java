public interface List<T> extends Iterable<T>
{
    boolean push(T element);
    boolean set(int index, T value) throws EmptyArrayException;
    T pop() throws EmptyArrayException;
    T get(int index) throws EmptyArrayException;
    T getHead() throws EmptyArrayException;
    T getLast() throws EmptyArrayException;
    int size();
    boolean isEmpty();
    int indexOf(T value) throws EmptyArrayException, ElementNotInArrayException;
    String toString();
    void print();
    void clear();
}
