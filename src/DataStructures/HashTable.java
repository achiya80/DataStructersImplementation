package DataStructures;

import java.util.*;


public class HashTable<K , V> implements Iterable<HashTable<K, V>.Node<K, V>> {



    LinkedList<Node<K, V>>[] table;

    private static final int defaultCapacity = 19;

    private int size;

    HashTable(){
        this(defaultCapacity);
    }


    HashTable(int capacity){
        table = new LinkedList[capacity];
        for (int i = 0; i < table.length; i++){
            table[i] = new LinkedList<>();
        }
        size = 0;
    }

    public boolean contains(K key){
        return (search(key) == null) ? false : true;
    }

    public void insert(K key, V value){
        if(contains(key)) {search(key).key = key; search(key).value = value; return;}
        Node<K, V> node = new Node<>(key, value);
        insert(node);
    }

    private void insert(Node<K, V> toInsert){
        table[hash(toInsert.key)].add(toInsert);
        size++;
        if(loadFactor() > 1){
            rehashing();
        }
    }

    public Node<K, V> search(K key){
        LinkedList<Node<K, V>> list = table[hash(key)];
        for (Node<K, V> res : list){
            if(res.key.equals(key)){
                return res;
            }
        }
        return null;
    }

    public Node<K, V> delete(K key){
        Node<K ,V> deleted = search(key);
        if(deleted == null) throw  new NoSuchElementException();
        table[hash(key)].remove(deleted);
        size--;
        return deleted;
    }

    public int size(){
        return size;
    }

    private double loadFactor(){
        return (double) size()/(double) table.length;
    }

    private int hash(Object o){
        return o.hashCode()%table.length;
    }

    private void rehashing(){
        LinkedList<Node<K, V>>[] newTable = new LinkedList[table.length*2-1];
        for (int i = 0;i < newTable.length;i++){
            newTable[i] = new LinkedList<>();
        }
        LinkedList<Node<K, V>>[] temp = table;
        Iterator<Node<K, V>> it = new HashTableIterator<K,V>(table);
        table = newTable;
        size = 0;
        while(it.hasNext()){
            insert(it.next());
        }
    }

    public Iterator<Node<K, V>> iterator(){
        return new HashTableIterator<K, V>(table);
    }


    class HashTableIterator<K, V> implements Iterator<Node<K , V>>{

        private LinkedList<Node<K, V>>[] iterator;
        private int place;
        private Iterator<Node<K, V>> it;

        HashTableIterator(LinkedList<Node<K,V>>[] iterator){
            this.iterator = iterator;
            this.place = 0;
            while (place < iterator.length && iterator[place].isEmpty()){
                if(++place < iterator.length) {
                    it = iterator[place].iterator();
                }
            }
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public Node<K, V> next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            Node<K, V> val = it.next();
            while(!it.hasNext() && place < iterator.length){
                if(++place < iterator.length) {
                    it = iterator[place].iterator();
                }
            }
            return val;
        }
    }

    class Node<K, V>{
        K key;
        V value;

        Node(K key, V value){
            this.key = key;
            this.value = value;
        }

        public String toString(){
            return "Key: " + key + " Value: " + value + " hash: " + hash(key);
        }
    }

    public void print(){
        Iterator<Node<K, V>> it = iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }
}
