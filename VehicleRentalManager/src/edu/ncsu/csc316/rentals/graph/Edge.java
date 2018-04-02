package edu.ncsu.csc316.rentals.graph;

/**
 * 
 * @author Noah Benveniste
 */
public class Edge implements Comparable<Edge> {

	/** */
	private Vertex startDay;
	/** */
	private Vertex endDay;
	/** */
	private double cost;
	/** */
	private String make;
	/** */
	private String model;
	
	/**
	 * 
	 */
	public Edge(Vertex startDay, Vertex endDay, double cost, String make, String model) {
		this.startDay = startDay;
		this.endDay = endDay;
		this.cost = cost;
		this.make = make;
		this.model = model;
	}

	/**
	 * Given an integer corresponding to one vertex on the edge,
	 * returns the other vertex. Or, if the edge doesn't contain
	 * a vertex with the given integer, returns null
	 * 
	 * @param n
	 * @return
	 */
	public Vertex getOpposite(int n) {
		return null;
	}
	
	/**
	 * 
	 */
	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
