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
	/** */
	private int position;
	
	/* Fields used by Dijkstra's algorithm for generating the shortest path */
	
	/** */
	private boolean found;
	/** */
	private double cumulativeCost;
	/** */
	private Vertex parent;
	/** */
	private Edge parentEdge;
	
	
	/**
	 * 
	 */
	public Vertex() {
		// TODO Auto-generated constructor stub
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
		
	}

}
