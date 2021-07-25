package SpecialDataStructures;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Stack;

public class MStack<E extends Comparable<E>> {

    private Stack<E> OS = new Stack<>();
    private Stack<E> AMS = new Stack<>();

    public E popMS(){
        if(OS.isEmpty()) throw new NoSuchElementException();
        E pop = OS.pop();
        if(pop.compareTo(AMS.peek()) == 0){
            AMS.pop();
        }
        return pop;
    }

    public void pushMS(E x){
        OS.push(x);
        if(AMS.isEmpty() || x.compareTo(AMS.peek()) >= 0){
            AMS.push(x);
        }
    }

    public E peekMS(){
        if(AMS.isEmpty()) throw new NoSuchElementException();
        return AMS.peek();
    }

    public E peek(){
        if(OS.isEmpty()) throw new NoSuchElementException();
        return OS.peek();
    }
}
