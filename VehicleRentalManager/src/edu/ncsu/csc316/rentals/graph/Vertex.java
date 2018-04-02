package edu.ncsu.csc316.rentals.graph;

import edu.ncsu.csc316.rentals.list.LinkedAbstractList;

/**
 * 
 * @author Noah Benveniste
 */
public class Vertex implements Comparable<Vertex> {
    
	/* Fields used for Graph structure */
	
	/** */
	private int day;
	/** */
	private LinkedAbstractList<Edge> incidentEdges;
	
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
		this.incidentEdges = new LinkedAbstractList<Edge>();
		this.resetVertex();
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
	 * @param e
	 */
	public void addIncidentEdge(Edge e) {
		this.incidentEdges.add(this.incidentEdges.size(), e);
	}
	
	/**
	 * 
	 */
	@Override
	public int compareTo(Vertex o) {
		// TODO Auto-generated method stub
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
