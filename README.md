# cs375project
This file is run by commandline by first compiling with the following:

javac adjPrimAlg.java adjList.java adjMatrix.java

OR

javac *.java

THEN running with the following:

java adjPrimAlg input.txt


The input file should have the following format:

NUMBER (number of nodes that are in the graph)
LIST (list of pairs of nodes with edges, along with their weight)

EXAMPLE INPUT FILE:

3
3 2 3
2 1 5
1 3 4

This input file represents a graph with 3 vertices, with an edge between 3 and 2 with weight 3, an edge between 2 and 1 with weight 5, and an edge between 1 and 3 with weight 4

This program produces an output.txt file that prints the minimum spanning tree for this graph along with the total computation time in this format:

Problem *
Adjacency List:
Vertex * with edge to vertex * and weight *
...
Total computation time: 0.0 seconds

Adjacency Matrix:
Vertex * with edge to vertex * and weight *
...
Total computation time: 0.0 seconds