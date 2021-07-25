package DataStructures;

public class Edge {
    private int left;
    private int right;

    public Edge(int left, int right) {
        this.left = left;
        this.right = right;
    }


    public int getLeft() {
        return left;
    }
    public int getRight() {
        return right;
    }

    public String toString(){
        return "(" + left + ", " + right+ ") ";
    }
}