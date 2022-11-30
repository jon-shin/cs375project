import java.util.*;
public class Edge{
    private int weight;
    private boolean included = false;
    private int dest;

    public Edge(int weight, int dest){
        this.weight = weight;
        this.dest = dest;
        this.included = false;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isIncluded() {
        return included;
    }

    public void setIncluded(boolean included) {
        this.included = included;
    }

    public int getDest() {
        return dest;
    }

    public void setDest(int dest) {
        this.dest = dest;
    }
}