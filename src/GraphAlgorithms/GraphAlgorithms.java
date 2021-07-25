package GraphAlgorithms;

import DataStructures.*;
import SpecialDataStructures.NonDeterministicGraph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class GraphAlgorithms {


    private enum Color{White, Gray, Black};


    public static int[][] BFS(Graph g, int s){
        Color[] c = new Color[g.numberOfVertices()];
        int[] d = new int[c.length];
        int[] pi = new int[c.length];
        for (int i = 0;i < g.numberOfVertices(); i++){
            c[i] = Color.White;
            pi[i] = -1;
            d[i] = -1;
        }
        d[s] = 0;
        c[s] = Color.Gray;
        Queue<Integer> Q = new LinkedList<>();
        Q.add(s);
        while (!Q.isEmpty()){
            int u = Q.remove();
            Set<Integer> adj = g.neighborsOf(u);
            for (int v : adj){
                if(c[v] == Color.White){
                    c[v] = Color.Gray;
                    d[v] = d[u] + 1;
                    pi[v] = u;
                    Q.add(v);
                }
            }
            c[u] = Color.Black;
        }
        return new int[][]{pi, d};
    }

    public static Color[] ConnectedComponentsBFS(Graph g, int s){
        Color[] c = new Color[g.numberOfVertices()];
        int[] d = new int[c.length];
        int[] pi = new int[c.length];
        for (int i = 0;i < g.numberOfVertices(); i++){
            c[i] = Color.White;
            pi[i] = -1;
            d[i] = -1;
        }
        d[s] = 0;
        c[s] = Color.Gray;
        Queue<Integer> Q = new LinkedList<>();
        Q.add(s);
        while (!Q.isEmpty()){
            int u = Q.remove();
            Set<Integer> adj = g.neighborsOf(u);
            for (int v : adj){
                if(c[v] == Color.White){
                    c[v] = Color.Gray;
                    d[v] = d[u] + 1;
                    pi[v] = u;
                    Q.add(v);
                }
            }
            c[u] = Color.Black;
        }
        return c;
    }

    private static int time;

    public static int[][] DFS(Graph g){
        time = 0;
        Color[] c = new Color[g.numberOfVertices()];
        int[] pi = new int[c.length];
        int[] d = new int[c.length];
        int[] f = new int[c.length];
        for (int i = 0;i < g.numberOfVertices(); i++){
            c[i] = Color.White;
            pi[i] = -1;
        }
        for (int i = 0; i< g.numberOfVertices();i++){
            if(c[i] == Color.White) {
                DFS_VISIT(g, i, pi, c, d, f);
            }
        }
        return new int[][]{pi, d, f};
    }


    private static void DFS_VISIT(Graph g, int u, int[] pi, Color[] c, int[] d, int[] f){
        c[u] = Color.Gray;
        d[u] = ++time;
        Set<Integer> adj = g.neighborsOf(u);
        for (int v : adj){
            if(c[v] == Color.White){
                pi[v] = u;
                DFS_VISIT(g, v , pi, c, d, f);
            }
        }
        c[u] = Color.Black;
        f[u] = ++time;
    }

    public static List<Integer> TOPOLOGICAL_SORT(Graph g){
        time = 0;
        List<Integer> l = new LinkedList<>();
        Color[] c = new Color[g.numberOfVertices()];
        int[] pi = new int[c.length];
        int[] d = new int[c.length];
        int[] f = new int[c.length];
        for (int i = 0;i < g.numberOfVertices(); i++){
            c[i] = Color.White;
            pi[i] = -1;
        }
        for (int i = 0; i< g.numberOfVertices();i++){
            if(c[i] == Color.White) {
                DFS_VISIT(g, i, pi, c, d, f, l);
            }
        }
        return l;
    }


    private static void DFS_VISIT(Graph g, int u, int[] pi, Color[] c, int[] d, int[] f, List<Integer> l){
        c[u] = Color.Gray;
        d[u] = ++time;
        Set<Integer> adj = g.neighborsOf(u);
        for (int v : adj){
            if(c[v] == Color.White){
                pi[v] = u;
                DFS_VISIT(g, v , pi, c, d, f, l);
            }
        }
        c[u] = Color.Black;
        l.add(0, u);
        f[u] = ++time;
    }


    private static int counter;

    public static boolean abCircle(int numOfVertices, NonDeterministicGraph g, int a, int b){
        counter = 0;
        boolean flag = false;
        time = 0;
        List<Integer> l = new LinkedList<>();
        Color[] c = new Color[numOfVertices];
        int[] pi = new int[c.length];
        int[] d = new int[c.length];
        int[] f = new int[c.length];
        for (int i = 0;i < numOfVertices; i++){
            c[i] = Color.White;
            pi[i] = -1;
        }
        for (int i = 0; i< numOfVertices;i++){
            if(c[i] == Color.White) {
                flag = flag || DFS_VISIT(g, i, pi, c, d, f, a, b);
            }
            if(counter >= numOfVertices){
                return true;
            }
        }
        return flag;
    }

    private static boolean DFS_VISIT(NonDeterministicGraph g, int u, int[] pi, Color[] c, int[] d, int[] f, int a, int b){
        c[u] = Color.Gray;
        d[u] = ++time;
        Set<Integer> adj = g.NeighborsInRange(u, a, b);
        counter += (pi[u] != -1)? adj.size() - 1 : adj.size();
        for (int v : adj){
            if(c[v] == Color.White){
                pi[v] = u;
                DFS_VISIT(g, v , pi, c, d, f, a, b);
            }
            else{
                return true;
            }
        }
        c[u] = Color.Black;
        f[u] = ++time;
        return false;
    }

}
