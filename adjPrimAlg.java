import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class adjPrimAlg {
    public static void main(String args[]) throws IOException{
        BufferedReader fInput = null;
        FileWriter fOutputWriter = new FileWriter("output.txt");
        String sLineTrack = "";     // String to read each line of input file
        int iNumNodes = 0;          // Number of nodes as indicated by input file
        int iLineTrack = 0;         // Integer to act as index for array when reading in the edges and weights for the problem
        int iProblemtrack = 0;      // Tracks which problem we're on for the input file
        adjList lAdjList = null;    // Adjacency list object
        adjMatrix mAdjMatrix = null; // Adjacency matrix object
        String sEdgesWeights[] = null;  // Each edge-weight read in from input file. Stored in array that is passed into prim's algorithms
        double startTime = 0.0;   // Idicates starting time of algorithms
        double endTime = 0.0;     // Indicates ending time of algorithms
        double durationToCompute = 0;           // Holds the total computation time of the algorithms

        startTime = System.nanoTime();
        if (args.length != 1){
            System.out.println("Incorrect number of arguments passed in. Please try again.");
            System.exit(0);
        } else {
            fInput = new BufferedReader(new FileReader(args[0]));
            sLineTrack = fInput.readLine();

            while (sLineTrack != null){
                if (Character.isDigit(sLineTrack.charAt(0))){
                    iProblemtrack++;
                    // If first character is a digit, we know a new problem is being sent in
                    iNumNodes = sLineTrack.charAt(0);
                    sLineTrack = fInput.readLine(); // Move to first given edge
                    sEdgesWeights = new String[iNumNodes*((iNumNodes - 1)/2)];  // Max num of edges is equal to (n(n-1))/2
                    while (!(Character.isDigit(sLineTrack.charAt(0)))){
                        sEdgesWeights[iLineTrack] = sLineTrack;
                        iLineTrack++;
                        sLineTrack = fInput.readLine();
                    }
                    lAdjList = adjList(iNumNodes, sEdgesWeights);
                    lAdjMatrix = adjMatrix(iNumNodes, sEdgesWeights);
                    fOutputWriter.write("Problem " + iProblemtrack + "\n");
                    fOutputWriter.write("Adjancency List: \n");
                    lAdjList.Prims(fOutputWriter);
                    fOutputWriter.write("\nAdjacency Matrix: \n");
                    lAdjMatrix.Prims(fOutputWriter);
                    fOutputWriter.write("\n");
                } else {
                    sLineTrack = fInput.readLine();
                }
            }
            
        }
    }
}