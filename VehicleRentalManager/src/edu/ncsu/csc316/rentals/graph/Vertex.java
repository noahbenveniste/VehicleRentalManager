package edu.ncsu.csc316.rentals.graph;

import edu.ncsu.csc316.rentals.list.ArrayList;

/**
 * 
 * @author Noah Benveniste
 */
public class Vertex implements Comparable<Vertex> {
    
	/* Fields used for Graph structure */
	
	/** */
	private int day;
	/** */
	private ArrayList<Edge> adjacentEdges;
	
	/* Fields used by Dijkstra's algorithm for generating the shortest path */
	
	/** */
	private double cumulativeCost;
	/** */
	private int position;
	/** */
	private boolean found;
	/** */
	private Vertex parent;
	/** */
	private Edge parentEdge;
	
	
	/**
	 * 
	 */
	public Vertex(int day) {
		this.day = day;
		this.adjacentEdges = new ArrayList<Edge>();
		this.resetVertex();
	}
	
	/**
	 * 
	 * @return
	 */
	public int getDay() {
		return this.day;
	}

	/**
	 * 
	 * @param pos
	 */
	public void setPosition(int pos) {
		this.position = pos;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getPosition() {
		return this.position;
	}
	
	/**
	 * 
	 * @param cost
	 */
	public void setCumulativeCost(double cost) {
		this.cumulativeCost = cost;
	}
	
	/**
	 * 
	 * @return
	 */
	public double getCumulativeCost() {
		return this.cumulativeCost;
	}
	
	/**
	 * 
	 */
	public void setFound() {
		this.found = true;
	}
	
	/**
	 * 
	 * @param p
	 */
	public void setParent(Vertex p) {
		this.parent = p;
	}
	
	/**
	 * 
	 * @return
	 */
	public Vertex getParent() {
		return this.parent;
	}
	
	/**
	 * 
	 * @param p
	 */
	public void setParentEdge(Edge p) {
		this.parentEdge = p;
	}
	
	/**
	 * 
	 * @return
	 */
	public Edge getParentEdge() {
		return this.parentEdge;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Edge> getAdjacentEdges() {
		return this.adjacentEdges;
	}
	
	/**
	 * 
	 * @param e
	 */
	public void addAdjacentEdge(Edge e) {
		this.adjacentEdges.add(this.adjacentEdges.size(), e);
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
