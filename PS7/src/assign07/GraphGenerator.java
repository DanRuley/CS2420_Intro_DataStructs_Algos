package assign07;

import java.awt.Point;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Random;

public class GraphGenerator {
	
	public static void main(String[] args) {
		generateRandomDotFile("src/assign07/Output", 25000);
		
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
		    if (true) {//rng.nextBoolean()) {
		      out.print("di");
		      edgeOp = "->";
		    }
		    out.println("graph G {");

		    HashMap<String, String> verts = new HashMap<>();
		    
		    // generate a list of vertices
		    String[] vertex = new String[vertexCount];
		    for(int i = 0; i < vertexCount; i++)
		      vertex[i] = "v" + i;

		    // randomly connect the vertices using 2 * |V| edges
		    for(int i = 0; i < 3 * vertexCount; i++) {
		    	
		    	int start = rng.nextInt(vertexCount);
		    	int end = rng.nextInt(vertexCount);
		    	
		    	//If end == start, use formula they showed in lab
		    	if (end == start) {
		    		end = (end + 1) % vertexCount;
		    	}
		    	
		    	verts.put(vertex[start], vertex[end]);
		    	
		    	
		    	
		      out.println("\t" + vertex[start] + edgeOp
		          + vertex[end]);
		    }
		  /*  for (String v: verts.keySet()) {
		    	out.println("\t" + v + " " + edgeOp + " " + verts.get(v));
		    }
		    */
		    out.println("}");
		    out.close();
		    }
		  
}
