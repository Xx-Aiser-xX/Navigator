package struct.impl;

import struct.LinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListImpl<T> implements LinkedList<T>, Iterable<T> {

    private Node<T> first;
    private Node<T> last;
    private int size;

    public LinkedListImpl(){
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    public T getFirst() {
        return first.getObject();
    }

    @Override
    public T getLast() {
        return last.getObject();
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty(){
        return (first == null && last == null);
    }

    @Override
    public void addFirst(T object){
        Node<T> node = new Node<>(object);
        if (isEmpty()) {
            last = node;
        }
        else {
            node.setNext(first);
            first.setPrev(node);
        }
        first = node;
        size++;
    }

    @Override
    public void addLast(T object) {
        Node<T> node = new Node<>(object);
        if (isEmpty()){
            first = node;
        }
        else {
            node.setPrev(last);
            last.setNext(node);
        }
        last = node;
        size++;
    }

    @Override
    public T popFirst(){
        Node<T> node = first;
        if(first.getNext().getPrev() != null)
            first.getNext().setPrev(null);
        first = first.getNext();
        size--;
        return node.getObject();
    }

    @Override
    public T popLast(){
        Node<T> node = last;
        if(last.getPrev().getNext() != null)
            last.getPrev().setNext(null);
        last = last.getPrev();
        size--;
        return node.getObject();
    }

    @Override
    public void replacement(T before, T after){
        Node<T> temp = first;
        while (temp != null){
            if(temp.getObject().equals(before)){
                temp.setObject(after);
                return;
            }
            temp = temp.getNext();
        }
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public boolean remove(T object) {
        Node<T> temp = first;
        if (temp == null) {
            return false;
        }
        if (object.equals(first.getObject())){
            if (first.getNext() != null) {
                first = first.getNext();
                first.setPrev(null);
            } else {
                first = null;
                last = null;
            }
            size--;
            return true;
        }
        else if (object.equals(last.getObject())) {
            if (last.getPrev() != null) {
                last = last.getPrev();
                last.setNext(null);
            } else {
                last = null;
                first = null;
            }
            size--;
            return true;
        }
        while (temp.getNext() != null && !temp.getObject().equals(object)) {
            temp = temp.getNext();
        }
        if(temp.getObject().equals(object)){
            if (temp.getPrev() != null) {
                temp.getPrev().setNext(temp.getNext());
            }
            if (temp.getNext() != null) {
                temp.getNext().setPrev(temp.getPrev());
            }
            size--;
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(T object) {
        Node<T> temp = first;
        while (temp != null){
            if(object.equals(temp.getObject()))
                return true;
            temp = temp.getNext();
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> node = first;

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = node.getObject();
            node = node.getNext();
            return data;
        }
    }

}
