package edu.ncsu.csc316.rentals.graph;

import edu.ncsu.csc316.rentals.list.ArrayList;

/**
 * An object representing a vertex in a directed, weighted graph.
 * Corresponds to one of the days that a rental spans.
 * 
 * @author Noah Benveniste
 */
public class Vertex implements Comparable<Vertex> {
    
	/* Fields used for Graph structure */
	
	/** The rental day */
	private int day;
	/** The edges that leave this vertex */
	private ArrayList<Edge> adjacentEdges;
	
	/* Fields used by Dijkstra's algorithm for generating the shortest path */
	
	/** The cost so far to reach this node in generating the shortest path */
	private double cumulativeCost;
	/** The node's position in the PQ */
	private int position;
	/** Whether or not this vertex has been visited by dijkstra */
	@SuppressWarnings("unused")
	private boolean found;
	/** The vertex that precedes this one in the shortest path */
	private Vertex parent;
	/** The edge that connects this vertex to its parent */
	private Edge parentEdge;
	
	
	/**
	 * Constructs a vertex object
	 * 
	 * @param day the day that the vertex corresponds to
	 */
	public Vertex(int day) {
		this.day = day;
		this.adjacentEdges = new ArrayList<Edge>();
		this.resetVertex();
	}
	
	/**
	 * Gets the day
	 * 
	 * @return the day
	 */
	public int getDay() {
		return this.day;
	}

	/**
	 * Sets the position
	 * 
	 * @param pos the position
	 */
	public void setPosition(int pos) {
		this.position = pos;
	}
	
	/**
	 * Gets the position
	 * 
	 * @return the position
	 */
	public int getPosition() {
		return this.position;
	}
	
	/**
	 * Sets the cumulative cost
	 * 
	 * @param cost the cumulative cost
	 */
	public void setCumulativeCost(double cost) {
		this.cumulativeCost = cost;
	}
	
	/**
	 * Gets the cumulative cost
	 * 
	 * @return the cumulative cost
	 */
	public double getCumulativeCost() {
		return this.cumulativeCost;
	}
	
	/**
	 * Sets the vertex to found i.e. it was visited by dijkstra
	 */
	public void setFound() {
		this.found = true;
	}
	
	/**
	 * Sets the preceding vertex
	 * 
	 * @param p the vertex that comes before this one in the shortest path
	 */
	public void setParent(Vertex p) {
		this.parent = p;
	}
	
	/**
	 * Gets the parent vertex
	 * 
	 * @return the parent vertex
	 */
	public Vertex getParent() {
		return this.parent;
	}
	
	/**
	 * Sets the edge that connects this vertex to its parent
	 * 
	 * @param p the parent edge
	 */
	public void setParentEdge(Edge p) {
		this.parentEdge = p;
	}
	
	/**
	 * Gets the edge that connects this vertex to its parent
	 * 
	 * @return the parent edge
	 */
	public Edge getParentEdge() {
		return this.parentEdge;
	}
	
	/**
	 * Gets the list of adjacent edges
	 * 
	 * @return the list of adjacent edges
	 */
	public ArrayList<Edge> getAdjacentEdges() {
		return this.adjacentEdges;
	}
	
	/**
	 * Adds an edge to the list
	 * 
	 * @param e the edge to add
	 */
	public void addAdjacentEdge(Edge e) {
		this.adjacentEdges.add(e);
	}
	
	/**
	 * This method isn't used; just here to satisfy the array list
	 * 
	 * @return 0 always
	 */
	@Override
	public int compareTo(Vertex o) {
		return 0;
	}
	
	/**
	 * Resets the vertex in the adjacency list for each run
	 * of Dijkstra's algorithm. Sets the cumulative cost to 0,
	 * found to false, and nulls both parent and parentEdge.
	 * Because these fields are only used for producing the
	 * shortest path, resetting them won't affect the structure
	 * of the graph.
	 */
	public void resetVertex() {
		this.found = false;
		this.cumulativeCost = 0;
		this.parent = null;
		this.parentEdge = null;
		this.position = -1;
	}

}
