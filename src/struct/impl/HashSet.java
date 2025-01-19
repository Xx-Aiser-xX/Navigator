package struct.impl;

import struct.LinkedList;
import struct.Set;
import java.util.Iterator;

public class HashSet<E> implements Set<E>, Iterable<E> {
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.80d;
    private LinkedList<E>[] slots;
    private int size;
    private int capacity;

    public HashSet() {
        capacity = INITIAL_CAPACITY;
        initMassive(capacity);
        this.size = 0;
    }

    public HashSet(int capacity) {
        this.capacity = capacity;
        initMassive(capacity);
        this.size = 0;
    }

    private void initMassive(int capacity){
        slots =  new LinkedListImpl[capacity];
        for (int i = 0; i < capacity; i++) {
            slots[i] = new LinkedListImpl<>();
        }
    }

    @Override
    public LinkedList<E> getLinkedListByIndex(E e){
        int number = findSlotNumber(e);
        LinkedList<E> slot = slots[number];
        return slot;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E e) {
        int number = findSlotNumber(e);
        LinkedList<E> slot = slots[number];
        boolean contains = slot.contains(e);
        return contains;
    }

    @Override
    public boolean add(E e) {
        growIfNeeded();
        if(contains(e))
            return false;

        int number = findSlotNumber(e);
        slots[number].addLast(e);
        size++;
        return true;
    }

    private void growIfNeeded() {
        if((double) (size + 1) / capacity > LOAD_FACTOR){
            grow();
        }
    }

    private void grow() {
        LinkedList<E>[] temp = slots;
        capacity *= 2;
        initMassive(capacity);
        size = 0;
        for (LinkedList<E> slot : temp) {
            for (E e : slot) {
                add(e);
            }
        }
    }

    private int findSlotNumber(E e) {
        return Math.abs(e.hashCode()) % capacity;
    }

    @Override
    public boolean remove(E e) {
        int number = findSlotNumber(e);
        LinkedList<E> slot = slots[number];

        if (slot.remove(e)) {
            size--;
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        for(int i = 0; i < capacity; i++){
            slots[i].clear();
        }
        size = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new HashSetIterator();
    }

    private class HashSetIterator implements Iterator<E> {
        private int index;
        private Iterator<E> listIterator = slots[0].iterator();

        @Override
        public boolean hasNext() {
            while (!listIterator.hasNext() && index < capacity) {
                if (++index < capacity)
                    listIterator = slots[index].iterator();
            }
            return index < capacity && listIterator.hasNext();
        }

        @Override
        public E next() {
            return listIterator.next();
        }
    }
}
