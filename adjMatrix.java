import java.io.FileWriter;
import java.io.IOException;

class adjMatrix{
	private int matrix[][]; //matrix rep of graph
	private int numV; //number of vertices in the graph
	
	public adjMatrix(int n) {
		numV = n;
		matrix = new int[n][n];
	}
	
	public adjMatrix(int[][] matrix) {
		this.matrix = matrix;
		numV = matrix[0].length;
	}
	
	/*
	 * void addEdge
	 * adds edge with weight between v1 and v2
	 */
	public void addEdge(int v1, int v2, int weight) {
		matrix[v1][v2] = weight;
		matrix[v2][v1] = weight;
	}
	
	/*
	 * void removeEdge
	 * sets edge weight from v1 to v2 to 0
	 */
	public void removeEdge(int v1, int v2) {
		matrix[v1][v2] = 0;
		matrix[v2][v1] = 0;
	}
	
	/*
	 * String toString
	 * prints the matrix in the form:
	 * [vertex]: [edgeVertex]/[edgeValue]
	 * ...
	 */
	public String toString() {
		String s = "";
	    for (int i = 0; i < numV; i++) {
	      s = s + i + ": ";
	      for (int j = 0; j < numV; j++) {
	    	  int val = matrix[i][j];
	    	  s = s + (val != 0 ? j + "/" + val + " " : "");
	      }
	      s = s + "\n";
	    }
	    return s;
	}
	
	/*
	 * void primsMST
	 * creates a minimum spanning tree from this graph and prints it to console
	 */
	public void primsMST(FileWriter w) throws IOException {
		int numE = 0; 							//count number of edges
		boolean selected[] = new boolean[numV];	//keep track of selected vertices
		selected[0] = true; 					//arbitrarily select first vertex
		
		w.write("Prim's Algorithm using adjacency matrix representation:\n");
		w.write("Edge : Weight\n");
		
		while(numE < numV-1) {
			int min = Integer.MAX_VALUE;
			int r = 0, c = 0;	//keep track of row/col
			
			for(int i = 0; i < numV; i++) {
				if(selected[i]) {	//only continue if vertex i is in the mst already
					for(int j = 0; j < numV; j++) {
						if(!selected[j] && matrix[i][j] != 0) { //only continue if vertex j is not already in mst and the edge exists
							if(min > matrix[i][j]) {	//check min weight
								min = matrix[i][j];
								r = i;
								c = j;
							}
						}
					}
				}
			}
			w.write(r + " - " + c + " : " + matrix[r][c] + "\n");
			selected[c] = true;	//update selected to include vertex c
			numE++;
		}
		w.write("\n");
	}
	
	//examples for creating a graph and running prims
	public static void main(String[] args) throws IOException {
		FileWriter fOutput = new FileWriter("output.txt");
		adjMatrix a = new adjMatrix(6);
		a.addEdge(0, 1, 4);
		a.addEdge(0, 2, 4);
		a.addEdge(1, 2, 2);
		a.addEdge(2, 3, 3);
		a.addEdge(2, 4, 2);
		a.addEdge(2, 5, 4);
		a.addEdge(3, 5, 3);
		a.addEdge(4, 5, 3);
		System.out.println(a);
		a.primsMST(fOutput);
		System.out.println();
		int graph[][] = new int[][] { { 0, 2, 0, 6, 0 }, { 2, 0, 3, 8, 5 }, { 0, 3, 0, 0, 7 }, { 6, 8, 0, 0, 9 }, { 0, 5, 7, 9, 0 } };
		adjMatrix b = new adjMatrix(graph);
		System.out.println(b);
		b.primsMST(fOutput);
	}
};