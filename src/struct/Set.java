package struct;

public interface Set<E> extends Iterable<E> {
    int size();
    boolean isEmpty();
    boolean contains(E e);
    boolean add(E e);
    boolean remove(E e);
    void clear();
    LinkedList<E> getLinkedListByIndex(E e);
}
