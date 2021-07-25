package DataStructures;

import java.util.HashSet;
import java.util.Set;

//First graph lecture
public abstract class AbstractGraph implements Graph {

    final private int nVertices;

    public AbstractGraph(int nVertices) {
        if (nVertices <= 0)
            throw new IllegalArgumentException("non-positive number of vertices");
        this.nVertices = nVertices;
    }

    public int numberOfVertices() {
        return nVertices;
    }

    public int numberOfEdges() {
        return edgeSet().size();
    }

    public int degree(int v) {
        if (!rangeCheck(v))
            throw new IllegalArgumentException();
        return neighborsOf(v).size();
    }

    public Set<Integer> neighborsOf(int v) {
        if (!rangeCheck(v))
            throw new IllegalArgumentException();
        Set<Integer> adj = new HashSet<>();
        for (int u = 0; u < numberOfVertices(); u = u + 1)
            if (u != v && containsEdge(u, v))
                adj.add(u);
        return adj;
    }

    public Set<Edge> edgeSet() {
        Set<Edge> edges = new HashSet<>();
        for (int i = 0; i < numberOfVertices(); i = i + 1)
            for (int j = 0; j < numberOfVertices(); j = j + 1)
                if (containsEdge(i, j))
                    edges.add(new Edge(i, j));
        return edges;
    }

    protected boolean rangeCheck(int i, int j) {
        return i >= 0 && i < numberOfVertices() &&
                j >= 0 && j < numberOfVertices() &&
                i != j;
    }

    protected boolean rangeCheck(int i) {
        return i >= 0 && i < numberOfVertices();
    }

    public String toString() {
        return "G=([" + nVertices + "], " + edgeSet() + ")";
    }

    public boolean equals(Object other) {

        if (!(other instanceof Graph))
            return false;

        Graph otherGraph = (Graph) other;
        return this.numberOfVertices() == otherGraph.numberOfEdges() &&
                this.edgeSet().equals(otherGraph.edgeSet());
    }

    public abstract boolean containsEdge(int i, int j);

    public abstract void addEdge(int i, int j);

    public abstract void removeEdge(int i, int j);


}