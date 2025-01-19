package struct.impl;

public class Node <T> {
    private T object;
    private Node <T> next;
    private Node <T> prev;

    public Node(T object){
        this.object = object;
    }

    public T getObject() {
        return object;
    }
    public void setObject(T object) {
        this.object = object;
    }

    public Node<T> getNext() {
        return next;
    }
    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getPrev() {
        return prev;
    }
    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }
}
