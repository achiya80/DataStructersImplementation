package DataStructures;

import java.util.List;
import java.util.NoSuchElementException;
public class DoubleLinkedList<T> {

    private Link<T> head;
    private Link<T> tail;

    private int size;
    public DoubleLinkedList(){ size = 0;}

    public void addFirst(T value){
        Link<T> added = new Link<T>(value);
        if(isEmpty()){
            head = added;
            head.prev = head;
            head.next = head;
            tail = head;
        }
        else{
            added.next = head;
            added.prev = head.prev;
            head.prev = added;
            tail.next = added;
            head = added;
        }
        size++;
    }

    public boolean isEmpty(){
        return head == null;
    }

    public T removeFirst() {
        if(isEmpty())
            throw new NoSuchElementException("no items");
        T pop = head.value;
        if(head == tail){
            head = null;
            tail = null;
        }
        else {
            head.next.prev = tail;
            tail.next = head.next;
            head = head.next;
        }
        size--;
        return pop;
    }

    public void add(int index, T value){
        if(index > size || index < 0) throw new IllegalArgumentException();
        if(index == 0){
            addFirst(value);
            return;
        }
        if(index == size){
            add(value);
            return;
        }
        Link<T> h = head;
        for (int i = 0; i< index;i++){
            h = h.next;
        }
        add(h, value);
    }

    public void add(Link<T> successor, T value){
        if(successor == head) {
            addFirst(value);
            return;
        }
        if(successor == tail){
            add(value);
            return;
        }
        Link<T> added = new Link<T>(value);
        added.next = successor;
        added.prev = successor.prev;
        successor.prev.next = added;
        successor.prev = added;
        size++;
    }
    public void add(T value) {
        Link<T> added = new Link<T>(value);
        if(isEmpty()){
            head = added;
            head.prev = head;
            head.next = head;
            tail = head;
        }
        else{
            added.next = head;
            added.prev = tail;
            head.prev = added;
            tail.next = added;
            tail = added;
        }
        size++;
    }

    public T remove() {
        if(isEmpty())
            throw new NoSuchElementException("no items");
        T dequeue = tail.value;
        if(head == tail){
            head = null;
            tail = null;
        }
        else {
            tail.prev.next = head;
            head.prev = tail.prev;
            tail = tail.prev;
        }
        size--;
        return dequeue;
    }


    private class Link<T>{
        private T value;
        private Link<T> next;
        private Link<T> prev;

        private Link(T value){
            this.value = value;
        }
    }
}
