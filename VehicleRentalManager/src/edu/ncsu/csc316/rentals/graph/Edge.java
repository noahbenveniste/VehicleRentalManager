package edu.ncsu.csc316.rentals.graph;

import java.util.Formatter;

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
	 * 
	 * @return
	 */
	public double getCost() {
		return this.cost;
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
		if (n == startDay.getDay()) {
			return endDay;
		} else if (n == endDay.getDay()) {
			return startDay;
		} else {
			return null;
		}
	}
	
	/**
	 * 
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
	 * 
	 * @return
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
