package DataStructures;

import java.util.List;
import java.util.NoSuchElementException;
public class Deque<T> implements IDeque<T> {

    private Link<T> head;
    private Link<T> tail;

    public Deque(){
    }

    public void push(T value){
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
    }

    public boolean isEmpty(){
        return head == null;
    }

    public T pop() {
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
        return pop;
    }

    @Override
    public void enqueue(T value) {
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
    }

    @Override
    public T dequeue() {
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
