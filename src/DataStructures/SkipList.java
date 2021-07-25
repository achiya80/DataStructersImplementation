package DataStructures;

import java.util.LinkedList;
import java.util.List;

public class SkipList<E>{


    private List<Node<E>> leftSentinels;
    private List<Node<E>> rightSentinels;
    private static final double probability = 0.5;
    private int size;

    public SkipList(){
        size = 0;
        leftSentinels = new LinkedList<>();
        rightSentinels = new LinkedList<>();
        Node topLeftSentinel = new Node(Integer.MIN_VALUE,  0);
        Node topRightSentinel = new Node(Integer.MAX_VALUE, 0);
        topRightSentinel.prev = topLeftSentinel;
        topLeftSentinel.next = topRightSentinel;
        leftSentinels.add(topLeftSentinel);
        rightSentinels.add(topRightSentinel);
    }

    private Node<E> findOnList(int key, Node<E> p){
        while (p.next != null && p.next.key <= key) {
            p = p.next;
        }
        return p;
    }

    public Node<E> find(int key){
        Node<E> p = leftSentinels.get(0);
        while(p.down != null){
            p = findOnList(key, p);
            p = p.down;
        }
        p = findOnList(key, p);
        return p;
    }

    public List<Node<E>> findAll(int key){
        List<Node<E>> res = new LinkedList<>();
        Node<E> p = leftSentinels.get(0);
        while (p != null){
            p = findOnList(key, p);
            res.add(0, p);
            p = p.down;
        }
        return res;
    }

    public Node<E> search(int key){
        Node<E> p = find(key);
        return (p.key == key) ? p : null;
    }
    public void insert(int key, E data){
        insert(find(key).next, randomHeight(), key, data);
    }

    public int randomHeight(){
        int h = 0;
        while (Math.random() < probability){
            h++;
        }
        return h;
    }

    public Node<E> getBottomHead(){
        return leftSentinels.get(leftSentinels.size()-1).next;
    }

    public Node<E> insert(Node<E> successor, int h, int key, E value){
        resizeSentinels(h);
        Node<E> inserted = null;
        successor = (successor.key != Integer.MIN_VALUE) ? successor.prev : successor;
        Node<E> down = null;
        for (int i = 0;i <= h;i++){
            Node<E> ToAdd = new Node<E>(key, i, value);
            ToAdd.down = down;
            if(down != null) {
                down.up = ToAdd;
            }
            else{
                inserted = ToAdd;
            }
            Node<E> n = successor.next;
            successor.next = ToAdd;
            ToAdd.prev = successor;
            ToAdd.next = n;
            n.prev = ToAdd;
            down = ToAdd;
            while(successor.height < leftSentinels.get(0).height && successor.up == null){
                successor = successor.prev;
            }
            successor = successor.up;
        }
        size++;
        return  inserted;
    }

    private void resizeSentinels(int h){
        while (leftSentinels.get(0).height < h) {
            Node<E> ls = new Node(Integer.MIN_VALUE, leftSentinels.get(0).height + 1);
            Node<E> rs = new Node(Integer.MAX_VALUE, rightSentinels.get(0).height + 1);
            ls.down = leftSentinels.get(0);
            leftSentinels.get(0).up = ls;
            ls.next = rs;
            rs.prev = ls;
            rs.down = rightSentinels.get(0);
            rightSentinels.get(0).up = rs;
            leftSentinels.add(0, ls);
            rightSentinels.add(0, rs);
        }
    }

    private void resizeSentinels(){
        while (leftSentinels.get(0).down != null && leftSentinels.get(0).next == rightSentinels.get(0)){
            leftSentinels.remove(0);
            leftSentinels.get(0).up = null;
            rightSentinels.remove(0);
            rightSentinels.get(0).up = null;
        }
    }
    public void delete(int key){
        Node<E> x = search(key);
        delete(x);
    }
    public void delete(Node<E> x){
        while(x != null){
            Node<E> e = x.prev;
            e.next = x.next;
            x.next.prev = e;
            x = x.up;
        }
        resizeSentinels();
    }


    public void print(){
        for (Node<E> n : leftSentinels){
            while (n!=null) {
                System.out.print(n + " ");
                n = n.next;
            }
            System.out.println();
        }
    }

    public class Node<E>{
        public Node prev;
        public Node next;
        public Node down;
        public Node up;
        public int key;
        public int height;
        public E data;
        public Node(int key, int height, E data){
            this.key = key;
            this.height = height;
            this.data = data;
        }

        public Node(int key, int height){
            this.key = key;
            this.height = height;
            this.data = null;
        }

        public Node(Node n){
            this.key = n.key;
            this.height = n.height;
            this.data = (E) n.data;
        }

        public String toString(){
            return String.format("[key= %d] [data= %s]", key, data);
        }
    }

    public static void main(String[] args) {
        SkipList<String> skipList = new SkipList<>();
        for (int i = 0; i < 10; i++) {
            skipList.insert(i, String.valueOf(i));
        }
        skipList.print();
        System.out.println();
        skipList.delete(3);
        skipList.print();
    }

}
