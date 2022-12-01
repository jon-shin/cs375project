import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

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
		
		int numV;
		if(minV == maxV) { numV = minV; }
		else{ numV = random.nextInt(maxV - minV) + minV; }
		double density = sparse ? random.nextDouble() * 0.5 : (random.nextDouble() * 0.25 + 0.75);
		int numE = (int)(density / 2.0 * numV * (numV - 1));
		
		fOutput.write(numV + "\n");
		
		int count = 0;
		Set<String> edges = new HashSet<String>();
		List<Integer> unconnected = new ArrayList<Integer>(); //create list of all vertices-- need to be connected
		for(int i = 0; i < numV; i++) {
			unconnected.add(i);
		}
		
		List<Integer> init_mst = new ArrayList<Integer>(); //create list of all already connected vertices-- initialize with random vertex
		
		int posUnconnected = random.nextInt(unconnected.size()-1);
		int v_connect = unconnected.remove(posUnconnected);
		init_mst.add(v_connect);
		
		while(unconnected.size() > 0) {
			int posMST;
			if(init_mst.size() == 1) { posMST = 0; }
			else{ posMST = random.nextInt(init_mst.size()-1); }
			int v_mst = init_mst.get(posMST);
			
			if( unconnected.size() == 1) { posUnconnected = 0; }
			else{ posUnconnected = random.nextInt(unconnected.size()-1); }
			v_connect = unconnected.remove(posUnconnected);
			init_mst.add(v_connect);
			
			int weight = random.nextInt(MAX_WEIGHT - 1) + 1;
			String e = v_mst + " " + v_connect;
			String e2 = v_connect + " " + v_mst;
			edges.add(e);
			edges.add(e2);
			count++;
			fOutput.write(v_mst + " " + v_connect + " " + weight + "\n");
		}
		
//		init_mst.add(connect.remove(random.nextInt(connect.size()-1)));
//		while(connect.size() != 0) {
//			int v2 = init_mst.get(random.nextInt(init_mst.size()));
//			int v1 = connect.remove(random.nextInt(connect.size()));
//			while(v1 != v2 && connect.size() > 0) {
//				v1 = connect.remove(random.nextInt(connect.size()));
//			}
//			int weight = random.nextInt(MAX_WEIGHT-1) + 1;
//			String e = v1 + " " + v2;
//			String e2 = v2 + " " + v1;
//			edges.add(e);
//			edges.add(e2);
//			count++;
//			fOutput.write(v1 + " " + v2 + " " + weight + "\n");
//		}
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
		g.generateGraph("gOutputBigSparse.txt", 20, 20, true);
		
		//g.generateGraph("gOutputSmallSparse.txt", 10, 10, true);
	}
}
