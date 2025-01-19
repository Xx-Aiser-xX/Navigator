package struct.impl;

import struct.List;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class ArrayList<E> implements Iterable<E>, List<E> {
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTORY = 0.80d;

    private E[] slots;
    private int size;
    private int capacity;

    public ArrayList() {
        capacity = INITIAL_CAPACITY;
        slots = (E[]) new Object[capacity];
        this.size = 0;
    }

    public ArrayList(int capacity) {
        this.capacity = capacity;
        slots = (E[]) new Object[capacity];
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E getLast() {
        if(size == 0)
            return null;
        return slots[size - 1];
    }

    @Override
    public E getFirst() {
        if(size == 0)
            return null;
        return slots[0];
    }

    @Override
    public boolean add(E e) {
        growIfNeeded();
        if(e == null)
            return false;
        slots[size] = e;
        size++;
        return true;
    }

    @Override
    public E get(int index) {
        if(index >= size || index < 0)
            throw new RuntimeException("Вы вышли за размер листа");
        return slots[index];
    }

    public boolean remove(int index){
        if(index >= size || index < 0)
            return false;
        for (int i = index; i < size - 1; i++) {
            slots[i] = slots[i + 1];
        }
        slots[size - 1] = null;
        size--;
        return true;
    }

    @Override
    public void clear() {
        for(int i = 0; i < capacity; i++){
            slots[i] = null;
        }
        size = 0;
    }

    @Override
    public void sort(Comparator<E> comparator) {
        Arrays.sort(slots, 0, size, comparator);
    }
    @Override
    public boolean contains(E e){
        for (int i = 0; i < size; i++) {
            if (slots[i].equals(e))
                return true;
        }
        return false;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    private void growIfNeeded() {
        if((double) (size + 1) / capacity > LOAD_FACTORY){
            grow();
        }
    }

    private void grow() {
        E[] temp = slots;
        capacity *= 2;
        slots = (E[]) new Object[capacity];
        size = 0;
        for (E slot : temp) {
            add(slot);
        }
    }

    @Override
    public String toString() {
        String list = "";
        int count = 0;
        for (E slot : slots) {
            if (slot != null) {
                list += slot;
                if (size > 1 && count < size - 1) {
                    list += ", ";
                    count++;
                }
            }
        }
        return "elements: " + list + "\n";
    }


    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<E> {
        private int count = 0;

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public E next() {
            return slots[count++];
        }
    }

}
