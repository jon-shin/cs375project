package cs375Project;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

public class adjPrimAlg {
    public static void main(String args[]) throws IOException{
        BufferedReader fInput = null;
        FileWriter fOutputWriter = new FileWriter("output.txt");
        String sLineTrack = "";     // String to read each line of input file
        int iNumNodes = 0;          // Number of nodes as indicated by input file
        int iProblemtrack = 0;      // Tracks which problem we're on for the input file
        adjList lAdjList = null;    // Adjacency list object
        ArrayList<ArrayList<Edge>> minSpanningTree = null;
        ArrayList<Edge> eEdgesToAdd = new ArrayList<Edge>();
        adjMatrix mAdjMatrix = null; // Adjacency matrix object
        String sParseProblem[] = null;
        if (args.length != 1){
            System.out.println("Incorrect number of arguments passed in. Please try again.");
            System.exit(0);
        } else {
            fInput = new BufferedReader(new FileReader(args[0]));
            sLineTrack = fInput.readLine();

            while (sLineTrack != null){
                sParseProblem = sLineTrack.split(" ");
                if (Character.isDigit(sParseProblem[0].charAt(0)) & sParseProblem.length == 1){
                    iProblemtrack++;
                    // If first character is a digit and array is only length 1, we know a new problem is being sent in
                    iNumNodes = sLineTrack.charAt(0);
                    sLineTrack = fInput.readLine(); // Move to first given edge
                    sParseProblem = sLineTrack.split(" ");
                    mAdjMatrix = new adjMatrix(iNumNodes);
                    minSpanningTree = new ArrayList<ArrayList<Edge>>(iNumNodes);
                    for(int i = 0; i < iNumNodes; i++){
                        ArrayList<Edge> b = new ArrayList<Edge>();
                        minSpanningTree.add(b);
                    }
                    lAdjList = new adjList(minSpanningTree, iNumNodes);
                    while (!(sParseProblem.length == 1)){
                        mAdjMatrix.addEdge(Integer.parseInt(sParseProblem[0]), Integer.parseInt(sParseProblem[1]), Integer.parseInt(sParseProblem[2]));
                        eEdgesToAdd.add(new Edge(Integer.parseInt(sParseProblem[0]), Integer.parseInt(sParseProblem[1]), Integer.parseInt(sParseProblem[2])));
                        sLineTrack = fInput.readLine();
                    }
                    for(int i = 0; i < eEdgesToAdd.size(); i++){
                        lAdjList.addEdge(eEdgesToAdd.get(i).getSource(), eEdgesToAdd.get(i).getDest(), eEdgesToAdd.get(i).getWeight());
                    }
                    fOutputWriter.write("Problem " + iProblemtrack + "\n");
                    fOutputWriter.write("Adjancency List: \n");
                    lAdjList.primsMST(fOutputWriter);
                    fOutputWriter.write("\nAdjacency Matrix: \n");
                    mAdjMatrix.primsMST(fOutputWriter);
                    fOutputWriter.write("\n");
                } else {
                    sLineTrack = fInput.readLine();
                }
                sParseProblem = null;
                mAdjMatrix = null;
                lAdjList = null;
            }
            fOutputWriter.close();
        }
    }
}