package struct;

public interface LinkedList<T> extends Iterable<T> {
    int getSize();
    T getFirst();
    T getLast();
    boolean isEmpty();
    void addFirst(T object);
    void addLast(T object);
    T popFirst();
    T popLast();
    void replacement(T before, T after);
    void clear();
    boolean remove(T object);
    boolean contains(T object);
}