//This class creates an edge object, which is used by the class adjList.java
package cs375Project;
import java.util.*;
public class Edge{
    private int weight;     //Edge's weight
    private int source;     //The vertex which the edge is coming from
    private int dest;       //The vertex which the edge is going to

    //constructor for edge
    public Edge(int source, int dest, int weight){
        this.source = source;
        this.dest = dest;
        this.weight = weight;
    }

    //getter for source
    public int getSource() {
        return source;
    }

    //setter for source
    public void setSource(int source) {
        this.source = source;
    }

    //getter for weight
    public int getWeight() {
        return weight;
    }

    //setter for weight
    public void setWeight(int weight) {
        this.weight = weight;
    }

    //getter for destination
    public int getDest() {
        return dest;
    }

    //setter for destination
    public void setDest(int dest) {
        this.dest = dest;
    }
}