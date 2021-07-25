package CompressionAlgorithms;

import java.util.LinkedList;
import java.util.Queue;

public class HuffmanCode {
    Queue<Node> Q1;
    Queue<Node> Q2;
    int n;

    public HuffmanCode(int[] freqArr, char[] letterArr){
        Q1 = new LinkedList<>();
        Q2 = new LinkedList<>();
        n = freqArr.length;
        for(int i = 0;i < n;i++){
            Q1.add(new Node(letterArr[i],freqArr[i]));
        }
    }

    public Node HuffmanTree(){
        for(int i = 1; i< n;i++){
            Node z = new Node();
            z.left = createNode();
            z.right = createNode();
            z.freq = z.left.freq + z.right.freq;
            Q2.add(z);
        }
        return Q2.remove();
    }

    public Node createNode(){
        if(Q1.isEmpty()){
            return Q2.remove();
        }
        if(Q2.isEmpty()){
            return Q1.remove();
        }
        if(Q1.peek().freq < Q2.peek().freq){
            return Q1.remove();
        }
        else{
            return Q2.remove();
        }
    }

    class Node{
        char letter;
        int freq;
        Node left;
        Node right;
        Node(char letter, int freq){
            this.letter = letter;
            this.freq = freq;
        }
        Node(){
            letter = '-';
        }
    }

    public void printInOrder(Node n){
        if(n != null){
            printInOrder(n.left);
            System.out.println("" + ((n.letter == '-') ? "" + n.freq : "" + n.letter + " " + n.freq));
            printInOrder(n.right);
        }
    }


}
