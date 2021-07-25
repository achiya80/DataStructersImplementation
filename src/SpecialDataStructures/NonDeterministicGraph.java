package SpecialDataStructures;

import DataStructures.Edge;
import DataStructures.SkipList;
import DataStructures.*;
import java.util.*;

public class NonDeterministicGraph {


    private SkipList<WeightVertical>[] skipList;
    private SkipList<WeightVertical>.Node<WeightVertical>[][] edges;

    public NonDeterministicGraph(Graph adjacencyListGraph, int[][] weightFunction){//O(log(|V|)*|V|^2) expected
        skipList = new SkipList[adjacencyListGraph.numberOfVertices()];
        for (int i = 0;i < adjacencyListGraph.numberOfVertices(); i++){
            skipList[i] = new SkipList<>();
        }
        edges = new SkipList.Node[weightFunction.length][weightFunction.length];
        Set<Edge> set = adjacencyListGraph.edgeSet();
        for (Edge e : set){
            insert(e, weightFunction[e.getLeft()][e.getRight()]);
        }
    }
    private class WeightVertical {
        private int weight;

        private int neighbor;
        private WeightVertical(int weight, int neighbor){
            this.weight = weight;
            this.neighbor = neighbor;
        }

        public String toString(){
            return String.format("N: %d, W: %d", neighbor, weight);
        }
    }

    public void remove(int i, int j){//O(1) expected
        if(edges[i][j] != null) {
            remove(new Edge(i, j));
            remove(new Edge(j, i));
        }
    }

    private void remove(Edge e){
        skipList[e.getLeft()].delete(edges[e.getLeft()][e.getRight()]);
        edges[e.getLeft()][e.getRight()] = null;
    }

    public void addEdge(int u, int v, int k){//O(log(|V|)) expected
        insert(new Edge(u, v), k);
        insert(new Edge(v, u), k);
    }

    private void insert(Edge e, int weight){
        SkipList<WeightVertical>.Node<WeightVertical> node = skipList[e.getLeft()].find(weight);
        insert(node.next, e, weight);
    }

    private void insert(SkipList<WeightVertical>.Node<WeightVertical> node, Edge e, int weight){
        SkipList<WeightVertical>.Node<WeightVertical> newNode = skipList[e.getLeft()].insert(node, skipList[e.getLeft()].randomHeight(), weight, new WeightVertical(weight, e.getRight()));
        edges[e.getLeft()][ e.getRight()] = newNode;
    }

    public Set<Integer> NeighborsInRange(int v, int a, int b){//O(log(|V|) + |Nab(v)|) expected
        Set<Integer> list = new HashSet<>();
        SkipList<WeightVertical>.Node<WeightVertical> node = skipList[v].find(a);
        while (node.down != null){
            node = node.down;
        }
        while(node != null && node.key <= b){
            if(node.key >= a){
                list.add(node.data.neighbor);
            }
            node = node.next;
        }
        return list;
    }

    public void Union(int u, int v){//O(|N(v)| + |N(u)|) expected
        SkipList<WeightVertical>.Node<WeightVertical> headU = skipList[u].getBottomHead();
        SkipList<WeightVertical>.Node<WeightVertical> headV = skipList[v].getBottomHead();
        while(headU.key < Integer.MAX_VALUE || headV.key < Integer.MAX_VALUE){
            if(headU.key == Integer.MAX_VALUE){
                if(headV.data.neighbor != u) {
                    insert(headU, new Edge(u, headV.data.neighbor), headV.data.weight);
                    insert(edges[headV.data.neighbor][v], new Edge(headV.data.neighbor, u), headV.data.weight);
                }
                headV = headV.next;
            }
            else if(headV.key == Integer.MAX_VALUE){
                if(headU.data.neighbor != v) {
                    insert(headV, new Edge(v, headU.data.neighbor), headU.data.weight);
                    insert(edges[headU.data.neighbor][u], new Edge(headU.data.neighbor, v), headU.data.weight);
                }
                headU = headU.next;
            }
            else if(headU.key < headV.key){
                if(headU.data.neighbor != v) {
                    addWithSuccessor(u, v, headU, headV);
                    if (headV.next.prev != headV) {
                        headV = headV.next;
                    }
                }
                headU = headU.next;
            }
            else{
                if(headV.data.neighbor != u) {
                    addWithSuccessor(v, u, headV, headU);
                    if (headU.next.prev != headU) {
                        headU = headU.next;
                    }
                }
                headV = headV.next;
            }
        }
    }

    private void addWithSuccessor(int u, int v,SkipList<WeightVertical>.Node<WeightVertical> U, SkipList<WeightVertical>.Node<WeightVertical> V){
        int i = U.data.neighbor;
        if(i == V.data.neighbor){
            V = V.next;
        }
        remove(v, i);
        insert(V, new Edge(v, i), U.data.weight);
        insert(edges[i][u], new Edge(i, v), U.data.weight);

    }
    public void print(){
        for (int i = 0;i < edges.length;i++){
            System.out.print("[");
            for (int j = 0;j< edges.length;j++){
                System.out.print((edges[i][j] == null) ? edges[i][j] + ((j == edges.length-1) ? "]" : ", ") : edges[i][j].data + ((j == edges.length-1) ? "]" : ", "));
            }
            System.out.println();
        }
    }
}
