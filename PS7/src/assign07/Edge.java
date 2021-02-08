package assign07;

/**
 * This class represents an edge between a source vertex and a destination
 * vertex in a directed graph.
 * 
 * The source of this edge is the Vertex whose object has an adjacency list
 * containing this edge.
 * 
 * This class is generic because it contains a generic vertex of type T.
 * 
 * @author Erin Parker, Aric Woodliff, & Dan Ruley
 * @version March, 2019
 */
public class Edge<T> {

	// destination of this directed edge
	private Vertex<T> dst;

	/**
	 * Constructs this edge with the given generic vertex as its destination.
	 */
	public Edge(Vertex<T> dst) {
		this.dst = dst;
	}

	/**
	 * Getter method for destination vertex.
	 */
	public Vertex<T> getOtherVertex() {
		return this.dst;
	}

	/**
	 * Returns a string representation of the destination vertex this edge points
	 * to.
	 */
	public String toString() {
		return this.dst.getValue().toString();
	}
}