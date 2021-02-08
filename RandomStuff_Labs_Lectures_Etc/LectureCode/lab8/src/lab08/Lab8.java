package lab08;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Random;
import java.util.TreeMap;

public class Lab8 {
	
	 
	public static void main(String[] args) {
	generateRandomDotFile("src/lab08/Output", 12);	
	}
	
	public static void generateRandomDotFile(String filename, int vertexCount) {
	    PrintWriter out = null;
	    try {
	      out = new PrintWriter(filename);
	    } 
	    catch (IOException e) {
	      System.out.println(e);
	    }

	    Random rng = new Random();

	    // randomly construct either a graph or a digraph
	    String edgeOp = "--";
	    if (rng.nextBoolean()) {
	      out.print("di");
	      edgeOp = "->";
	    }
	    out.println("graph G {");

	    
	    HashMap<Integer,Integer> vertices = new HashMap<>();
	    TreeMap<Integer,Integer> verticess = new TreeMap<>();
	    
	    
	    
	    // generate a list of vertices
	    String[] vertex = new String[vertexCount];
	    for(int i = 0; i < vertexCount; i++)
	      vertex[i] = "v" + i;

	    for (int i = 0; i < 2 * vertexCount; i++) {
	    	int start = rng.nextInt(vertexCount);
	    	int end = rng.nextInt(vertexCount);
	    	
	    	//If end == start, use formula they showed in lab
	    	if (end == start) {
	    		end = (end + 1) % vertexCount;
	    	}
	    	vertices.put(start, end);

	    }
	    
	    
	    // randomly connect the vertices using 2 * |V| edges
	    out.println();
	   
	    for(int i = 0; i < vertices.size(); i++) {

	      out.println("\t" + vertices + edgeOp
	          + vertex[end]);
	    }
	    out.println("}");
	    out.close();
	  }
}
