package DataStructures;

import java.util.Set;

//First graph lecture
public interface Graph {

    // Returns the number of vertices in the graph.
    public int numberOfVertices();

    // Returns the number of edges in the graph.
    public int numberOfEdges();

    // Returns true if the graph contains the edge (i, j).
    public boolean containsEdge(int i, int j);

    // Adds the edge (i, j) to this graph.
    public void addEdge(int i, int j);

    // Removes the edge (i, j) from this graph.
    public void removeEdge(int i, int j);

    // Returns the number of edges connected to vertex v.
    public int degree(int v);

    // Returns the set of vertices connected to vertex v.
    public Set<Integer> neighborsOf(int v);

    // Returns the set of edges of this graph.
    public Set<Edge> edgeSet();

}
