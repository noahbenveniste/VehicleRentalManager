package edu.ncsu.csc316.rentals.manager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc316.rentals.graph.Edge;
import edu.ncsu.csc316.rentals.graph.Vertex;
import edu.ncsu.csc316.rentals.list.ArrayList;
import edu.ncsu.csc316.rentals.priority_queue.AdaptablePriorityQueue;
import edu.ncsu.csc316.rentals.stack.LinkedStack;

/**
 * 
 * @author Noah Benveniste
 */
public class VehicleRentalManager {
	
	/** */
	private ArrayList<Vertex> adjList;

	/**
	 * Constructs a new Rental manager with the given input files
	 * 
	 * @param pathToFile
	 *            - the path to the employee input file
	 */
	public VehicleRentalManager(String pathToFile) {
		
		this.adjList = new ArrayList<Vertex>();
		buildGraph(pathToFile);
		
	}
	
	/**
	 * Returns the String representation of the rentals that
	 * minimize the total cost from the start day to the end day
	 * (or for as many days from the start day while rentals are possible).
	 * 
	 * @param start - the start day as an integer
	 * @param end - the end day as an integer
	 * @return the String representation of the rentals
	 */
	public String getRentals(int start, int end) {
		
		// Call dijkstra's algorithm to handle getting the shortest path
	    dijkstra(start, end);
	    
	    // Initialize a stack and cost counter for generating output
	    LinkedStack<String> s = new LinkedStack<String>();
	    double totalCost = 0;
	    StringBuilder sb = null;
	    
	    // Handles the case where the end day is out of range for the graph
	    while (end > adjList.size()) {
	    	sb = new StringBuilder();
	    	s.push(sb.append("No rentals available on day ").append(end).toString());
	    	end--;
	    }
	    
	    // After the end day is decremented properly, get the last node covered
	    // in the range
	    Vertex curr = adjList.get(end - 1);
	    
	    do {
	    	sb = new StringBuilder();
	    	totalCost += curr.getParentEdge().getCost();
	    	s.push(curr.getParentEdge().toString());
	    	curr = curr.getParent();
	    } while (curr.getParent() != null);

	    sb = new StringBuilder("Rental total is $");
	    Formatter f = new Formatter(sb);
	    f.format("%.2f", totalCost);
	    f.close();
	    sb.append("\n[\n");
	    
	    while (!s.isEmpty()) {
	    	sb.append("   ").append(s.pop()).append("\n");
	    }
	    
	    sb.append("]");
	    return sb.toString();
	    
	}
	
	/**
	 * Returns the String representation of the rentals 
	 * that are available for the requested day.
	 * 
	 * @param day - the day for which to retrieve available rentals
	 * @return the String representation of the rentals
	 */
	public String getRentalsForDay(int day) {
		
	    // List the incident edges for the specified day sorted in ascending order by cost
		ArrayList<Edge> edges = adjList.get(day - 1).getAdjacentEdges();
		edges.quickSort();
		
		// Build output string
		StringBuilder sb = new StringBuilder("Available rentals for day ");
		sb.append(day).append("\n");
		if (edges.size() == 0) {
			sb.append("   No rentals available.\n");
		} else {
			for (int i = 0; i < edges.size(); i++) {
				sb.append("   ").append(edges.get(i).toStringAlt()).append("\n");
			}
		}
		sb.append("]");
		return sb.toString();
		
	}
	
	/**
	 * 
	 * @param startDay
	 * @param endDay
	 */
	private void dijkstra(int startDay, int endDay) {
		
		// Initialize PQ
		AdaptablePriorityQueue q = new AdaptablePriorityQueue();
		
		// For all vertices in the adjacency list, initialize for dijkstra's
		for (int i = 0; i < this.adjList.size(); i++) {
			adjList.get(i).resetVertex();
			if (adjList.get(i).getDay() == startDay) {
				adjList.get(i).setCumulativeCost(0);
			} else {
				adjList.get(i).setCumulativeCost(Integer.MAX_VALUE);
			}
			q.insert(adjList.get(i));
		}
		
		// Main loop for determining shortest path
		while (!q.isEmpty()) {
			Vertex u = q.deleteMin();
			u.setFound();
			
			// Loop through all of u's incident edges
			for (int i = 0; i < u.getAdjacentEdges().size(); i++) {
				Edge e = u.getAdjacentEdges().get(i);
				Vertex z = e.getOpposite(u.getDay());
				double r = e.getCost() + u.getCumulativeCost();
				if (r < z.getCumulativeCost()) {
					z.setCumulativeCost(r);
					z.setParent(u);
					z.setParentEdge(e);
					q.updatePriority(z, r);
				}
				
			}
			
		}
		
	}
	
	/**
	 * 
	 * @param fileName
	 */
	private void buildGraph(String fileName) {
		
		// Try to create a file scanner for the input file
		Scanner edges = null;
		try {
			edges = new Scanner(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
		
		// Skip the header line
		edges.nextLine();
		
		// Loop while the file has lines to read
		while (edges.hasNextLine()) {
			
			// Make a scanner for the current line
			String curr = edges.nextLine();
			Scanner currEdge = new Scanner(curr);
			currEdge.useDelimiter(",");
			
			// Try to parse the edge data from the current line
			try {
				
				int startDay = currEdge.nextInt();
				int endDay = currEdge.nextInt();
				double cost = currEdge.nextDouble();
				String make = currEdge.next();
				String model = currEdge.next();
				
				/* 
				  1. Check if the adjacency list already has vertex objects corresponding to the START_DAY
		             and END_DAY values read in from the line
		              -these values correspond to the index + 1 in the list where the vertex object should be placed
		       		  i. The index has a vertex already: grab its reference and use it to construct the edge
		       		 ii. The index DOESN'T have a vertex: create a new Vertex object, add it to the list, use it
		       		     to create the edge object
		       	  2. Add the newly create edge object to the incident edge list for each vertex (just look them up in the list
		             by index)
				 */
				
				// If the adjList doesn't already contain a vertex for the start day
				if ( adjList.get(startDay - 1) == null ) {
					adjList.set(startDay - 1, new Vertex(startDay));
				}
				
				// If the adjList doesn't already contain a vertex for the end day
				if ( adjList.get(endDay - 1) == null) {
					adjList.set(endDay - 1, new Vertex(endDay));
				}
				
				// Create the edge object using the data parsed from the current line in the file
				Edge e = new Edge(adjList.get(startDay - 1), adjList.get(endDay - 1), cost, make, model);
				
				// Add the newly created edge to the start day's adjacent edge list
				adjList.get(startDay - 1).addAdjacentEdge(e);

				// Close the scanner for the line
				currEdge.close();
				
			} catch (NoSuchElementException e) {
				// Do nothing
			}
			
		}
		
		// Close the file scanner
		edges.close();
		
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Vertex> getGraph() {
		
		return this.adjList;
		
	}

}
