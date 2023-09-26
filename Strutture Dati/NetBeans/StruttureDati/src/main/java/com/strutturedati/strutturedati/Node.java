package com.strutturedati.strutturedati;

public final class Node<T>
{
    private T data;
    private Node<T> previous;
    private Node<T> next;


    Node(T data, Node<T> prev, Node<T> next)
    {
        this.data = data;
        this.previous = prev;
        this.next = next;
    }

    public void setData(T data) { this.data = data; }
    public T getData() { return this.data; }
    public Node<T> getPrevious() { return this.previous; }
    public void setNext(Node<T> next) { this.next = next; }
    public Node<T> getNext() { return this.next; }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getData());
        return sb.toString();
    }
}
