package DataStructures;

public class WeightEdge extends Edge {


    private int weight;
    public WeightEdge(int left, int right, int weight) {
        super(left, right);
        this.weight = weight;
    }

    public int getWeight(){
        return weight;
    }

    public boolean equals(Object o){
        return ((WeightEdge)o).getLeft() == getLeft() && ((WeightEdge)o).getRight() == getRight();
    }
}
