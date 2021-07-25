package DataStructures;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

public class MaxHeap<E> {


    private Node[] A;
    int size;

    public class Node<E>{
        int id;
        E data;

        Node(int id, E data){
            this.id = id;
            this.data = data;
        }

        public int getId(){
            return id;
        }

        public E getData(){
            return data;
        }
    }

    public Node<E> createNode(int id, E data){
        return new Node<>(id, data);
    }

    public MaxHeap(){

    }
    public MaxHeap(Node<E>[] arr, int size){
        this.size = size;
        A = new Node[arr.length];
        for(int i = 0; i < A.length; i++){
            A[i] = arr[i];
        }
        for(int i = size/2-1; i >=0;i--){
            MaxHeapIFI(A,i);
        }
    }

    public Node<E> Maximum(){
        return A[0];
    }

    public Node<E> ExtractMax(){
        if(size == 0) throw new NoSuchElementException();
        Node<E> max = Maximum();
        A[0] = A[size-1];
        size--;
        MaxHeapIFI(A,0);
        return max;

    }

    public void IncreaseKey(int i, int key){
        if(key < A[i].id) throw new NoSuchElementException();
        A[i].id = key;
        while(i > 0 && A[parent(i)].id < A[i].id){
            Node temp = A[i];
            A[i] = A[parent(i)];
            A[parent(i)] = temp;
            i = parent(i);
        }
    }

    public void Insert(int key, E data){
        if(size == A.length) throw new NoSuchElementException();
        A[size] = new Node(Integer.MIN_VALUE, data);
        size++;
        IncreaseKey(size-1,key);
    }


    public void MaxHeapIFI(Node[] arr,int i){
        int l = left(i);
        int r = right(i);
        int largest = i;
        if(l < size && arr[l].id > arr[largest].id){
            largest = l;
        }
        if(r < size && arr[r].id > arr[largest].id){
            largest = r;
        }
        if(largest != i){
            Node<E> temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            MaxHeapIFI(arr,largest);
        }

    }

    public Node<E>[] heapSort(){
        for(int i = A.length-1;i >= 0;i--){
            A[i] = ExtractMax();
        }
        return A;
    }

    public int parent(int i){
        return (i-1)/2;
    }

    public int left(int i){
        return  2*i+1;
    }
    public int right(int i){
        return 2*i+2;
    }

    public List<Node<E>> returnTopC(int c){//O(c)
        List<Node<E>> list = new LinkedList<>();
        List<Node<E>> sorted = new LinkedList<>();
        sorted.add(Maximum());
        while (list.size() < c){
            Node<E> temp = sorted.remove(0);
        }
        return list;
    }
}
