package DataStructures;

import DataStructures.AbstractGraph;
import DataStructures.Edge;
import DataStructures.Graph;

import java.util.LinkedList;
import java.util.List;

public class AdjacencyListGraph extends AbstractGraph {

    protected LinkedList<Integer>[] adj ;

    public AdjacencyListGraph(int nVertices) {
        super(nVertices);
        this.adj = new LinkedList[nVertices];
        for(int i = 0; i < nVertices; i = i+1)
            adj[i] = new LinkedList<Integer>();
    }

    //copy constructor
    public AdjacencyListGraph(Graph other) {
        this(other.numberOfVertices());
        for(int i = 0; i < other.numberOfVertices(); i = i+1)
            for(int j = 0; j < other.numberOfVertices(); j = j+1)
                if(other.containsEdge(i,j))
                    addEdge(i,j);
    }

    protected Edge toEdge(int i, int j) {
        return new Edge(i, j);
    }

    public boolean containsEdge(int i, int j) {
        Edge e = toEdge(i,j);
        List<Integer> leftNeighbors = adj[e.getLeft()];
        return leftNeighbors.contains(e.getRight());
    }

    public void addEdge(int i, int j) {
        Edge e = toEdge(i, j);
        if(!containsEdge(e.getLeft(), e.getRight())){
            List<Integer> leftNeighbors = adj[e.getLeft()];
            leftNeighbors.add(e.getRight());
        }
    }

    public void removeEdge(int i, int j) {
        Edge e = toEdge(i, j);
        if(containsEdge(e.getLeft(), e.getRight())){
            List<Integer> leftNeighbors = adj[e.getLeft()];
            leftNeighbors.remove(e.getRight());
        }
    }

    public String toString() {
        String output = "";
        for(int i = 0 ; i < numberOfVertices(); i = i+1)
            output = output + i + "-" + adj[i] + "\n";
        return output;
    }
}
