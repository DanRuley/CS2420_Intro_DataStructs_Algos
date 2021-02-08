package assign07;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Class that represents a generic graph. Uses the generic Vertex and Edge
 * classes to represent the vertices and edges of the graph.
 * 
 * @authors Erin Parker, Dan Ruley, Aric Woodliff
 * @version March 2019
 */
public class Graph<T> {

	// the graph -- a set of vertices (Type value mapped to Vertex instance)
	private HashMap<T, Vertex<T>> vertices;

	/**
	 * Constructs an empty graph.
	 */
	public Graph() {
		vertices = new HashMap<T, Vertex<T>>();
	}

	/**
	 * Constructs a generic graph from the given lists of sources and destinations.
	 */
	public Graph(List<T> sources, List<T> destinations) {

		vertices = new HashMap<T, Vertex<T>>();

		for (int i = 0; i < sources.size(); i++) {
			this.addEdge(sources.get(i), destinations.get(i));
		}
	}

	/**
	 * Adds to the graph an edge from the vertex with Type "val1" to the vertex with
	 * Type "val2". If either vertex does not already exist in the graph, it is
	 * added. Increments the indegree of the destination vertex as well.
	 */
	public void addEdge(T val1, T val2) {
		Vertex<T> vertex1;
		if (vertices.containsKey(val1))
			vertex1 = vertices.get(val1);
		else {
			vertex1 = new Vertex<T>(val1);
			vertices.put(val1, vertex1);
		}

		Vertex<T> vertex2;
		if (vertices.containsKey(val2))
			vertex2 = vertices.get(val2);
		else {
			vertex2 = new Vertex<T>(val2);
			vertices.put(val2, vertex2);
		}
		vertex2.setInDegree(vertex2.getInDegree() + 1);

		vertex1.addEdge(vertex2);
	}

	/**
	 * Returns a collection of the vertices contained in the graph.
	 */
	public Collection<Vertex<T>> getVerts() {
		return vertices.values();

	}

	/**
	 * Returns the backing hash map.
	 */
	public HashMap<T, Vertex<T>> getMap() {

		return this.vertices;
	}

}
