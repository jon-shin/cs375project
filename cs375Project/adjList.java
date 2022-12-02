//This is the implementation of an adjacency list using Prim's algorithm
package cs375Project;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class adjList{
    private static Comparator<Edge> minWeight;  //Used to sort priority queue by minimum weight first
    private ArrayList<ArrayList<Edge>> graph;   //Sets the adjacency list using a double ArrayList of Edges
    private int numV;   //Number of vertices in the graph

    //constructor for adjacency list
    public adjList(ArrayList<ArrayList<Edge>> graph, int numV){
        this.graph = graph;
        this.numV = numV;
    }

    //Sets the comparator to order priority queue of edges by minimum weight
    static{
        minWeight = new Comparator<Edge>(){
            @Override
            public int compare(Edge e1, Edge e2){
                return Integer.compare(e1.getWeight(), e2.getWeight());
            }
        };
    }

    //Creates edge objects from the input file and puts them into the adjacency list
    public void addEdge(int source, int destination, int weight){
        Edge e1 = new Edge(source, destination, weight);
        Edge e2 = new Edge(destination, source, weight);
        graph.get(source).add(e1);  
        graph.get(destination).add(e2);
    }

    //Prim's algorithm with adjacency list
    //Writes to a given output file
    public void primsMST(FileWriter w) throws IOException{
        double startTime = System.nanoTime();   //Starts the timer
        double endTime = 0;     //Signifies end of timer
        w.write("Prim's Algorithm using adjacency list representation:\n");
		w.write("Edge : Weight\n");
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>(minWeight);    //Create the min priority queue for edges
        ArrayList<Edge> mst = new ArrayList<Edge>();        //Creates the minimum spanning tree
        boolean selected[] = new boolean[numV];	//keep track of selected vertices
		selected[0] = true; 					//arbitrarily select first vertex
        int currentNode = 0;                    //Signifies which node is being added to the mst
        for(Edge e : graph.get(currentNode)){
            pq.add(e);  //Add edges adjacent to starting node to the min priority queue
        }
        while(!pq.isEmpty()){
            Edge minEdge = pq.peek();           //Takes the edge at the top of the queue
            currentNode = minEdge.getDest();    //Set the destination of the edge as the next node to be added
            pq.remove();                        //Remove the minimum edge from the priority queue
            //if the node is not already in the min spanning tree
            if (!selected[currentNode]) {
                selected[currentNode] = true;   //Signify that the node is added
                mst.add(minEdge);               //add to minimum spanning tree
                // Iterate through all the nodes adjacent to the node taken out of priority queue.
                for (Edge e : graph.get(currentNode)) {
                    int adj_node = e.getDest();
                    if (selected[adj_node] == false) {
                        // Push only those nodes that are not yet present in the minumum spanning tree.
                        pq.add(e);
                    }
                }
            }
        }
        //Print results of min spanning tree to output file
        for(int i = 0; i < mst.size(); i++){
            w.write(mst.get(i).getSource() + " - " + mst.get(i).getDest() + " : " + mst.get(i).getWeight() + "\n");
        }
        w.write("Total Computation Time: ");
        endTime = System.nanoTime();        //Stop timer
        w.write(Double.toString((endTime-startTime)/1000000) + " milliseconds");    //Get the time in milliseconds
    }

    public static void main(String args[]) throws IOException{
        FileWriter fOutput = new FileWriter("adjListOut.txt");
        ArrayList<Edge> a = new ArrayList<Edge>();
		a.add(new Edge(0, 1, 4));
		a.add(new Edge(0, 2, 4));
        a.add(new Edge(1, 2, 2));
        a.add(new Edge(2, 3, 3));
		a.add(new Edge(2, 4, 2));
		a.add(new Edge(2, 5, 4));
		a.add(new Edge(3, 5, 3));
        a.add(new Edge(4, 5, 3));
        ArrayList<ArrayList<Edge>> minSpanningTree = new ArrayList<ArrayList<Edge>>(6);
        for(int i = 0; i < 6; i++){
            ArrayList<Edge> b = new ArrayList<Edge>();
            minSpanningTree.add(b);
        }
        adjList aList = new adjList(minSpanningTree, 6);
        for(int i = 0; i < a.size(); i++){
            aList.addEdge(a.get(i).getSource(), a.get(i).getDest(), a.get(i).getWeight());
        }
        aList.primsMST(fOutput);
        fOutput.close();
    }
}