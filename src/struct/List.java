package struct;
import java.util.Comparator;

public interface List<E> extends Iterable<E> {
    int size();
    E getLast();
    E getFirst();
    boolean add(E e);
    boolean remove(int index);
    E get(int index);
    void clear();
    void sort(Comparator<E> comparator);
    boolean contains(E e);
    boolean isEmpty();
}
