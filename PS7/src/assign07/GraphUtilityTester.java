package assign07;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class GraphUtilityTester {
	
	
	@BeforeEach
	public void setUp() {
		ArrayList<String> sources = new ArrayList<>();
		ArrayList<String> dests = new ArrayList<>();
		
		
		GraphUtility.buildListsFromDot("src/graphs/graph1", sources, dests);
		
		Graph<String> g = new Graph<String>(sources,dests);
	}
	
	@Test
	void testisCyclicOnBranchedGraph() {
		ArrayList<String> sources = new ArrayList<>();
		ArrayList<String> dests = new ArrayList<>();
		GraphUtility.buildListsFromDot("src/graphs/graph1", sources, dests);
		assertFalse(GraphUtility.isCyclic(sources,dests));
	}
	
	@Test
	void testisCyclicOnBranchedGraphWithCycle() {
		ArrayList<String> sources = new ArrayList<>();
		ArrayList<String> dests = new ArrayList<>();
		GraphUtility.buildListsFromDot("src/graphs/graph2", sources, dests);
		assertTrue(GraphUtility.isCyclic(sources,dests));
	}
	

	@Test
	void testisCyclicOnSelfLoop() {
		ArrayList<String> sources = new ArrayList<>();
		ArrayList<String> dests = new ArrayList<>();
		GraphUtility.buildListsFromDot("src/graphs/graph3", sources, dests);
		assertTrue(GraphUtility.isCyclic(sources,dests));
	}
	
	@Test
	void testisCyclicOnSelfLoopOfOneVertex() {
		ArrayList<String> sources = new ArrayList<>();
		ArrayList<String> dests = new ArrayList<>();
		GraphUtility.buildListsFromDot("src/graphs/graph10", sources, dests);
		assertTrue(GraphUtility.isCyclic(sources,dests));
	}
	
	@Test
	void testisCyclicOnConvolutedGraph() {
		ArrayList<String> sources = new ArrayList<>();
		ArrayList<String> dests = new ArrayList<>();
		GraphUtility.buildListsFromDot("src/graphs/graph4", sources, dests);
		assertFalse(GraphUtility.isCyclic(sources,dests));
	}
	
	@Test
	void testisCyclicOnConvolutedGraphWithCycle() {
		ArrayList<String> sources = new ArrayList<>();
		ArrayList<String> dests = new ArrayList<>();
		GraphUtility.buildListsFromDot("src/graphs/graph5", sources, dests);
		assertTrue(GraphUtility.isCyclic(sources,dests));
	}
	
	@Test
	void testisCyclicOnREALLYConvolutedGraphWithCycle() {
		ArrayList<String> sources = new ArrayList<>();
		ArrayList<String> dests = new ArrayList<>();
		GraphUtility.buildListsFromDot("src/graphs/graph7", sources, dests);
		assertTrue(GraphUtility.isCyclic(sources,dests));
	}
	
	@Test
	void testSimpleDAGForConnected() {
		ArrayList<String> sources = new ArrayList<>();
		ArrayList<String> dests = new ArrayList<>();
		GraphUtility.buildListsFromDot("src/graphs/SimpleDag1", sources, dests);
		assertTrue(GraphUtility.areConnected(sources,dests,"1","3"));
	}
	
	@Test
	void testNotSoSimpleDAGForConnected() {
		ArrayList<String> sources = new ArrayList<>();
		ArrayList<String> dests = new ArrayList<>();
		GraphUtility.buildListsFromDot("src/graphs/NotSoSimpleDag1", sources, dests);
		assertFalse(GraphUtility.areConnected(sources,dests,"1","4"));
	}
	
	@Test
	void testareConnectedOnConvolutedGraph() {
		ArrayList<String> sources = new ArrayList<>();
		ArrayList<String> dests = new ArrayList<>();
		GraphUtility.buildListsFromDot("src/graphs/graph4", sources, dests);
		assertTrue(GraphUtility.areConnected(sources,dests,"1","17"));
	}
	
	@Test
	void testareConnectedOnREALLYConvolutedGraphDisconnected() {
		ArrayList<String> sources = new ArrayList<>();
		ArrayList<String> dests = new ArrayList<>();
		GraphUtility.buildListsFromDot("src/graphs/graph7", sources, dests);
		assertFalse(GraphUtility.areConnected(sources,dests,"1","38"));
	}
	
	@Test
	void testareConnectedOnREALLYConvolutedGraphConnected() {
		ArrayList<String> sources = new ArrayList<>();
		ArrayList<String> dests = new ArrayList<>();
		GraphUtility.buildListsFromDot("src/graphs/graph8", sources, dests);
		assertTrue(GraphUtility.areConnected(sources,dests,"1","38"));
	}
	
	@Test
	public void testOneVertexSelfLoopIsConnected() {
		ArrayList<String> sources = new ArrayList<>();
		ArrayList<String> dests = new ArrayList<>();
		GraphUtility.buildListsFromDot("src/graphs/graph10", sources, dests);
		assertTrue(GraphUtility.areConnected(sources,dests,"v1","v1"));
	}
	
	@Test
	public void testOneVertexNoEdgeThrowsIAE() {
		ArrayList<String> sources = new ArrayList<>();
		ArrayList<String> dests = new ArrayList<>();
		GraphUtility.buildListsFromDot("src/graphs/graph9", sources, dests);
		assertThrows(IllegalArgumentException.class, () -> {
			GraphUtility.areConnected(sources,dests,"v1","v1");
		});
	}
	
	@Test
	public void testGraphWithDisconnectedDestinationVertexThrowsIAE() {
		ArrayList<String> sources = new ArrayList<>();
		ArrayList<String> dests = new ArrayList<>();
		GraphUtility.buildListsFromDot("src/graphs/graph11", sources, dests);
		assertThrows(IllegalArgumentException.class, () -> {
			GraphUtility.areConnected(sources,dests,"v1","v5");
		});
	}
	
	@Test
	public void testDisconnectedCyclicGraphEdgeCase() {
		ArrayList<String> sources = new ArrayList<>();
		ArrayList<String> dests = new ArrayList<>();
		GraphUtility.buildListsFromDot("src/graphs/graph", sources, dests);
		assertFalse(GraphUtility.areConnected(sources,dests,"1","5"));
	}
	
}
