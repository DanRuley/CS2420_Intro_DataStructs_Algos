package assign07;

import java.util.LinkedList;
import java.util.Iterator;

/**
 * This class represents a vertex (AKA node) in a directed graph. The vertex is
 * generic: each vertex stores a value of type T which is used to identify the
 * vertex.
 * 
 * @author Erin Parker, Dan Ruley, Aric Woodliff
 * @version March, 2019
 */
public class Vertex<T> {

	//inDegree represents the number of edges pointing to this vertex.
	private int inDegree = 0;
	//tempInDegree is utilized in topological sort so that the original inDegree is preserved.
	private int tempInDegree = 0;
	//flag used in various methods to determine if the vertex has been "visited" previously or not.
	private boolean visited;

	// used to id the Vertex
	private T value;

	// adjacency list
	private LinkedList<Edge<T>> adj;

	/**
	 * Constructs a vertex with a given value of type T.  Also initializes the adjacency list of edges.
	 */
	public Vertex(T value) {
		this.value = value;
		this.adj = new LinkedList<Edge<T>>();
	}

	/**
	 * Returns the value of type T contained in this vertex.
	 */
	public T getValue() {
		return value;
	}

	/**
	 * Adds a new edge to this vertices' adjacency list.
	 */
	public void addEdge(Vertex<T> otherVertex) {
		adj.add(new Edge<T>(otherVertex));
	}

	/**
	 * Returns an iterator for the adjacency list.
	 */
	public Iterator<Edge<T>> edges() {
		return adj.iterator();
	}

	/**
	 * Returns a String representation of this vertex.
	 */
	public String toString() {
		String s = "Vertex " + value + " adjacent to ";
		Iterator<Edge<T>> itr = adj.iterator();
		while (itr.hasNext())
			s += itr.next() + "  ";
		return s;
	}

	/**
	 * Getter method for visited flag.
	 */
	public boolean getVisited() {
		return visited;
	}

	/**
	 * Setter method for visited flag.
	 */
	public void setVisited(boolean b) {
		visited = b;
	}

	/**
	 * Getter method for the adjacency list of edges.
	 */
	public LinkedList<Edge<T>> getEdges() {
		return adj;
	}

	/*
	 * Getter method for indegree
	 */
	public int getInDegree() {
		return inDegree;
	}

	/**
	 * Setter method for indegree.
	 */
	public void setInDegree(int inDegree) {
		this.inDegree = inDegree;
	}

	/**
	 * Getter method for tempindegree.
	 */
	public int getTempInDegree() {
		return tempInDegree;
	}

	/**
	 * Setter method for tempindegree.
	 */
	public void setTempInDegree(int tempInDegree) {
		this.tempInDegree = tempInDegree;
	}

}