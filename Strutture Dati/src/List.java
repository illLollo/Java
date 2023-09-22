public interface List<T>
{
    boolean push(T element);
    boolean set(int index, T value);
    boolean insert(int index, T value);
    T pop();
    T pop(int index);
    T popByValue(T value);
    T get(int index);
    T getHead();
    T getLast();
    int size();
    boolean isEmpty();
    int indexOf(T value);
    String toString();
    void print();
}
