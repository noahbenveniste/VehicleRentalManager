package edu.ncsu.csc316.rentals.graph;

import java.util.Formatter;

/**
 * An object representing an edge/arc in a directed, weighted graph
 * 
 * @author Noah Benveniste
 */
public class Edge implements Comparable<Edge> {

	/** The starting vertex of the edge */
	private Vertex startDay;
	/** The ending vertex of the edge */
	private Vertex endDay;
	/** The cost to traverse the edge (rental cost) */
	private double cost;
	/** Rental car make */
	private String make;
	/** Rental car model */
	private String model;
	
	/**
	 * Builds an edge object corresponding to a rental between two days
	 * 
	 * @param startDay the starting vertex
	 * @param endDay the ending vertex
	 * @param cost the cost of the rental
	 * @param make the rental car make
	 * @param model the rental car model
	 */
	public Edge(Vertex startDay, Vertex endDay, double cost, String make, String model) {
		
		this.startDay = startDay;
		this.endDay = endDay;
		this.cost = cost;
		this.make = make;
		this.model = model;
		
	}

	/**
	 * Gets the cost
	 * 
	 * @return cost
	 */
	public double getCost() {
		
		return this.cost;
		
	}
	
	/**
	 * Given an integer corresponding to one vertex on the edge,
	 * returns the other vertex. Or, if the edge doesn't contain
	 * a vertex with the given integer, returns null
	 * 
	 * @param n corresponds to the vertex that is known
	 * 
	 * @return the vertex opposite the one corresponding to n
	 */
	public Vertex getOpposite(int n) {
		
		if (n == startDay.getDay()) {
			return endDay;
		} else if (n == endDay.getDay()) {
			return startDay;
		} else {
			return null;
		}
		
	}
	
	/**
	 * Generates a string representation of the edge; used by getRentals()
	 * 
	 * @return string for the edge whose format is dictated by getRentals()
	 */
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("From day ")
		  .append(startDay.getDay())
		  .append(" to day ")
		  .append(endDay.getDay())
		  .append(": $");
		Formatter f = new Formatter(sb);
		f.format("%.2f", cost);
		f.close();
		sb.append(", ")
		  .append(make)
		  .append(" ")
		  .append(model);
		return sb.toString();
		
	}
	
	/**
	 * Generates a string representation of the edge; used by getRentalsForDay()
	 * 
	 * @return string for the edge whose format is dictated by getRentalsForDay()
	 */
	public String toStringAlt() {
		
		StringBuilder sb = new StringBuilder("$");
		Formatter f = new Formatter(sb);
		f.format("%.2f", cost);
		f.close();
		sb.append(" ")
		  .append(make).append(" ")
		  .append(model).append(" for day ")
		  .append(startDay.getDay())
		  .append(" to day ")
		  .append(endDay.getDay());
		return sb.toString();
		
	}
	
	/**
	 * Used for sorting edges for outputting in order of decreasing cost
	 * 
	 * @param o the other edge to compare to
	 * 
	 * @return -1 if this has a smaller cost than o, 1 if o has a smaller cost
	 * 		   than this, and 0 if they have the same cost
	 */
	@Override
	public int compareTo(Edge o) {
		double result = this.cost - o.getCost();
		if (result < 0) {
			return -1;
		} else if (result > 0) {
			return 1;
		} else {
			return 0;
		}
		
	}

}
