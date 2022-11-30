import java.util.*;
import Edge.java;

public class adjList{
    private ArrayList<ArrayList<Edge>> adjacencyList;

    public adjList(ArrayList<ArrayList<Edge>> adjacencyList){
        this.adjacencyList = adjacencyList;
    }

    public void addEdge(int source, int destination, int weight){
        adjacencyList.get(source).add(Edge(weight, destination));
        adjacencyList.get(destination).add(Edge(weight, source));
    }
}