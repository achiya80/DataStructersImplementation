package DataStructures;

import DataStructures.AbstractGraph;
import DataStructures.Graph;

import java.util.Arrays;

public class AdjacencyMatrixGraph extends AbstractGraph {
    private boolean[][] edges;

    public AdjacencyMatrixGraph(int nVertices) {
        super(nVertices);
        edges = new boolean[nVertices][nVertices];
        for (int i = 0; i < nVertices; i = i + 1)
            for (int j = 0; j < nVertices; j = j + 1)
                edges[i][j] = false;
    }

    //copy constructor
    public AdjacencyMatrixGraph(Graph other) {
        this(other.numberOfVertices());
        for(int i = 0; i < other.numberOfVertices(); i = i+1)
            for(int j = i+1; j < other.numberOfVertices(); j = j+1)
                if(other.containsEdge(i,j))
                    addEdge(i,j);
    }

    public boolean containsEdge(int i, int j) {
        if(!rangeCheck(i, j))
            throw new IllegalArgumentException();
        return edges[i][j];
    }
    public void addEdge(int i, int j) {
        if(!rangeCheck(i, j))
            throw new IllegalArgumentException();
        edges[i][j] = true;
        edges[j][i] = true;
    }
    public void removeEdge(int i, int j) {
        if(!rangeCheck(i, j))
            throw new IllegalArgumentException();
        edges[i][j] = false;
        edges[j][i] = false;
    }

    @Override
    public String toString() {
        String output = "";
        for(int i = 0; i < numberOfVertices(); i = i+1)
            output = output + Arrays.toString(edges[i]) + "\n";
        return output;
    }
}
