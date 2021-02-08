package assign07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * Contains several methods for solving problems on generic, directed,
 * unweighted, sparse graphs.
 * 
 * @author Erin Parker & Aric Woodliff & Dan Ruley
 * @version March, 2019
 */
public class GraphUtility {

	/**
	 * Utilizes a recursive depth first search to determine if a the graph is
	 * cyclic. Throws IllegalArgumentException if the input source and destination
	 * lists are of unequal size.
	 */
	public static <Type> boolean isCyclic(List<Type> sources, List<Type> destinations) throws IllegalArgumentException {
		if (sources.size() != destinations.size())
			throw new IllegalArgumentException();
		Graph<Type> g = new Graph<Type>(sources, destinations);

		boolean isCyclic = false;

		for (Vertex<Type> v : g.getVerts()) {
			// Each DFS is independent, so reset the flags each at the beginning of each new
			// search.
			setUnvisited(g);
			isCyclic = depthFirstSearch(v, v);
			// Break out early if we find a cycle, no need to keep doing extra work.
			if (isCyclic)
				break;
		}

		return isCyclic;
	}

	/**
	 * Private helper method that performs the actual DFS.
	 */
	private static <Type> boolean depthFirstSearch(Vertex<Type> v, Vertex<Type> start) {
		boolean isCyclic = false;

		// case where we've reached a sink
		if (v.getEdges().size() == 0) {
			return false;
		}
		// case where we've come back to a visited node, but it's not the start
		if (!v.equals(start) && v.getVisited()) {
			return false;
		}
		// case where we've returned to the start: then it is cyclic.
		if (v.equals(start) && v.getVisited()) {
			isCyclic = true;
			return isCyclic;
		}

		v.setVisited(true);

		// Iterate through adjacency list and keep recursively calling DFS. This
		// utilizes the "implicit" stack via recursion.
		for (Edge<Type> e : v.getEdges()) {
			Vertex<Type> w = e.getOtherVertex();
			isCyclic = depthFirstSearch(w, start) || isCyclic;
		}

		return isCyclic;
	}

	/**
	 * Helper method that resets the flags for each vertex in the graph.
	 */
	private static <Type> void setUnvisited(Graph<Type> g) {
		for (Vertex<Type> v : g.getVerts()) {
			v.setVisited(false);
		}
	}

	/**
	 * Utilizes a breadth first search to determine if a path exists between a
	 * vertex containing srcData and a vertex containing dstData. Throws
	 * IllegalArgumentException if the input source list and destination list are of
	 * unequal size, or if the graph does not contain a vertex with srcData and
	 * dstData.
	 */
	public static <Type> boolean areConnected(List<Type> sources, List<Type> destinations, Type srcData, Type dstData)
			throws IllegalArgumentException {

		// check if sources and destinations are same size
		if (sources.size() != destinations.size())
			throw new IllegalArgumentException();

		Graph<Type> g = new Graph<Type>(sources, destinations);

		// check that srcData and dstData are both actually in the graph
		if (g.getMap().keySet().contains(srcData) == false || g.getMap().keySet().contains(dstData) == false) {
			throw new IllegalArgumentException();
		}

		return breadthFirstSearch(srcData, dstData, g);
	}

	/**
	 * Performs a breadth first search on the graph. Determines if a path exists
	 * between a vertex src and a vertex dst.
	 */
	private static <Type> boolean breadthFirstSearch(Type src, Type dst, Graph<Type> g) {

		// reset the flags
		setUnvisited(g);

		Queue<Type> vertexQueue = new LinkedList<Type>();

		vertexQueue.offer(src);

		while (vertexQueue.size() != 0) {

			Type x = vertexQueue.poll();

			if (x.equals(dst)) {
				return true;
			}

			Vertex<Type> v = g.getMap().get(x);
			v.setVisited(true);

			// Keep exploring and enqueueing adjacent vertices by iterating through
			// adjacency list.
			for (Edge<Type> e : v.getEdges()) {
				Type w = e.getOtherVertex().getValue();
				if (!g.getMap().get(w).getVisited())
					vertexQueue.offer(w);
			}
		}

		return false;
	}

	/**
	 * Performs a topological sort on the input graph. Throws
	 * IllegalArgumentException if the input source list and destination list are of
	 * unequal size, or if the graph contains a cycle.
	 */
	public static <Type> List<Type> sort(List<Type> sources, List<Type> destinations) throws IllegalArgumentException {
		if (sources.size() != destinations.size())
			throw new IllegalArgumentException();
		Graph<Type> g = new Graph<Type>(sources, destinations);

		return topoSort(g);
	}

	/**
	 * Helper method that performs the actual topo sort. Takes vertices from the
	 * graph with an indegree of zero (the "first layer"), and adds them to a queue.
	 * It then pops them from the queue, adds them to the sorted list, and explores
	 * adjacent vertices and decrements their indegree (in effect this "strips" off
	 * a layer of the graph). Keeps repeating this process until all vertices are
	 * added to the sorted list. If one or more vertices are missing from the final
	 * list, then there must be a cycle, so an IAE is thrown.
	 */
	private static <Type> List<Type> topoSort(Graph<Type> g) {

		Queue<Vertex<Type>> vertexQueue = new LinkedList<>();
		ArrayList<Type> sorted = new ArrayList<>();
		int expectedSize = 0;

		for (Vertex<Type> v : g.getVerts()) {
			// expected size is used at the end of the sort to determine if we are missing
			// vertices. If the sorted list size != expected size, there must be a cycle.
			expectedSize++;
			v.setTempInDegree(v.getInDegree());
			if (v.getTempInDegree() == 0) {
				vertexQueue.offer(v);
			}

		}

		while (!vertexQueue.isEmpty()) {

			Vertex<Type> x = vertexQueue.poll();
			sorted.add(x.getValue());

			for (Edge<Type> e : x.getEdges()) {

				Vertex<Type> w = e.getOtherVertex();
				w.setTempInDegree(w.getTempInDegree() - 1);

				if (w.getTempInDegree() == 0) {
					vertexQueue.offer(w);
				}
			}
		}

		// Case where graph is cyclic: there will be some nodes that were never put into
		// the queue and the sorted list. So if the size of the sorted list != the
		// number of vertices, the graph must be cyclic.
		if (sorted.size() != expectedSize) {
			throw new IllegalArgumentException("Error: Graph is cyclic.");
		}

		return sorted;

	}

	/**
	 * Builds "sources" and "destinations" lists according to the edges specified in
	 * the given DOT file (e.g., "a -> b"). Assumes that the vertex data type is
	 * String.
	 * 
	 * Accepts many valid "digraph" DOT files (see examples posted on Canvas).
	 * --accepts \\-style comments --accepts one edge per line or edges terminated
	 * with ; --does not accept attributes in [] (e.g., [label = "a label"])
	 * 
	 * @param filename     - name of the DOT file
	 * @param sources      - empty ArrayList, when method returns it is a valid
	 *                     "sources" list that can be passed to the public methods
	 *                     in this class
	 * @param destinations - empty ArrayList, when method returns it is a valid
	 *                     "destinations" list that can be passed to the public
	 *                     methods in this class
	 */
	public static void buildListsFromDot(String filename, ArrayList<String> sources, ArrayList<String> destinations) {

		Scanner scan = null;
		try {
			scan = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

		scan.useDelimiter(";|\n");

		// Determine if graph is directed (i.e., look for "digraph id {").
		String line = "", edgeOp = "";
		while (scan.hasNext()) {
			line = scan.next();

			// Skip //-style comments.
			line = line.replaceFirst("//.*", "");

			if (line.indexOf("digraph") >= 0) {
				edgeOp = "->";
				line = line.replaceFirst(".*\\{", "");
				break;
			}
		}
		if (edgeOp.equals("")) {
			System.out.println("DOT graph must be directed (i.e., digraph).");
			scan.close();
			System.exit(0);

		}

		// Look for edge operator -> and determine the source and destination
		// vertices for each edge.
		while (scan.hasNext()) {
			String[] substring = line.split(edgeOp);

			for (int i = 0; i < substring.length - 1; i += 2) {
				// remove " and trim whitespace from node string on the left
				String vertex1 = substring[0].replace("\"", "").trim();
				// if string is empty, try again
				if (vertex1.equals(""))
					continue;

				// do the same for the node string on the right
				String vertex2 = substring[1].replace("\"", "").trim();
				if (vertex2.equals(""))
					continue;

				// indicate edge between vertex1 and vertex2
				sources.add(vertex1);
				destinations.add(vertex2);
			}

			// do until the "}" has been read
			if (substring[substring.length - 1].indexOf("}") >= 0)
				break;

			line = scan.next();

			// Skip //-style comments.
			line = line.replaceFirst("//.*", "");
		}

		scan.close();
	}
}