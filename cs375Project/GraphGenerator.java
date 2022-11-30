import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;

public class GraphGenerator {
	private final int MAX_WEIGHT = 10;
	private Random random;
	
	public GraphGenerator() {
		random = new Random();
	}
	
	/*
	 * void generateGraph
	 * generates graph with minV <= |V| <= maxV
	 * |E| is dependent on if the graph is sparse (true) or not (false)
	 * writes to file specified by fileName
	 */
	public void generateGraph(String fileName, int minV, int maxV, boolean sparse) throws IOException{
		FileWriter fOutput = new FileWriter(fileName);
		
		int numV = random.nextInt(maxV - minV) + minV;
		double density = sparse ? random.nextDouble() * 0.5 : (random.nextDouble() * 0.25 + 0.75);
		int numE = (int)(density / 2.0 * numV * (numV - 1));
		
		fOutput.write(numV + "\n");
		
		Set<String> edges = new HashSet<String>();
		int count = 0;
		for(int i = 0; i < numV; i++) {		//this new for loop ensures connectivity
			int v2 = random.nextInt(numV);
			while(i == v2) {v2 = random.nextInt(numV);}
			String e = i + " " + v2;
			String e2 = v2 + " " + i;
			if(!edges.contains(e) && !edges.contains(e2)) {
				edges.add(e);
				edges.add(e2);
				count++;
				int weight = random.nextInt(MAX_WEIGHT-1) + 1;
				fOutput.write(i + " " + v2 + " " + weight + "\n");
			}
		}
		while(count < numE) {
			int v1 = random.nextInt(numV);
			int v2 = random.nextInt(numV);
			while(v1 == v2) {
				v2 = random.nextInt(numV);
			}
			String e = v1 + " " + v2;
			String e2 = v2 + " " + v1;
			if(!edges.contains(e) && !edges.contains(e2)) {
				edges.add(e);
				edges.add(e2);
				count++;
				int weight = random.nextInt(MAX_WEIGHT-1) + 1;
				fOutput.write(v1 + " " + v2 + " " + weight + "\n");
			}
		}
		fOutput.close();
	}
	
	/*
	 * Examples of generating a graph
	 */
	public static void main(String[] args) throws IOException{
		GraphGenerator g = new GraphGenerator();
		
		//g.generateGraph("gOutput.txt", 5, 10, false);
		//g.generateGraph("gOutputSparse.txt", 5, 10, true);
		g.generateGraph("gOutputBig.txt", 20, 40, true);
		
		//g.generateGraph("gOutputSmallSparse.txt", 4, 6, true);
	}
}
