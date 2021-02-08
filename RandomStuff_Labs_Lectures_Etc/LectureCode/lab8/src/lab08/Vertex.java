package lab08;

import java.util.LinkedList;
import java.util.Iterator;

/**
 * This class represents a vertex (AKA node) in a directed graph. The vertex is
 * not generic, assumes that a string name is stored there.
 * 
 * @author Erin Parker
 * @version March 1, 2019
 */
public class Vertex implements Comparable<Vertex> {

	
	private double distanceFromStart;
	
	private boolean visited;
	
	private Vertex prev;
	
	// used to id the Vertex
	private String name;

	// adjacency list
	private LinkedList<Edge> adj;

	public Vertex(String name) {
		this.name = name;
		this.adj = new LinkedList<Edge>();
	}

	public String getName() {
		return name;
	}

	public void addEdge(Vertex otherVertex) {
		adj.add(new Edge(otherVertex));
	}
	
	public void addEdge(Vertex otherVertex, double weight) {
		adj.add(new Edge(otherVertex, weight));
	}

	public Iterator<Edge> edges() {
		return adj.iterator();
	}

	public String toString() {
		String s = "Vertex " + name + " adjacent to ";
		Iterator<Edge> itr = adj.iterator();
		while(itr.hasNext())
			s += itr.next() + "  ";
		return s;
	}

	public void setPrevious(Vertex x) {
		prev = x;
	}

	public double getDistanceFromStart() {
		return distanceFromStart;
	}

	public void setDistanceFromStart(double cost) {
		distanceFromStart = cost;
	}

	public boolean getVisited() {
		return visited;
	}

	public void setVisited(boolean b) {
		visited = b;
	}

	public Vertex getPrevious() {
		return prev;
	}

	public LinkedList<Edge> getEdges() {
		return adj;
	}

	@Override
	public int compareTo(Vertex v) {
		return Double.compare(this.distanceFromStart, v.distanceFromStart);
	}

	
}