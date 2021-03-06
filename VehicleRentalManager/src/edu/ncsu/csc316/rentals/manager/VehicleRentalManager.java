package edu.ncsu.csc316.rentals.manager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;
import java.util.StringTokenizer;

import edu.ncsu.csc316.rentals.graph.Edge;
import edu.ncsu.csc316.rentals.graph.Vertex;
import edu.ncsu.csc316.rentals.list.ArrayList;
import edu.ncsu.csc316.rentals.priority_queue.AdaptablePriorityQueue;
import edu.ncsu.csc316.rentals.stack.LinkedStack;

/**
 * Manager class that handles construction of the graph as well as
 * functionality relating to generating the shortest path or querying
 * adjacent edges for a given vertex (day)
 * 
 * @author Noah Benveniste
 */
public class VehicleRentalManager {
	
	/** An adjacency list representation of a graph */
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
	    boolean flag = false;
	    while (end - 1 >= adjList.size()) {
	    	flag = true;
	    	end--;
	    }
	    // After this loop, end is guaranteed to be at most size - 1
	    
	    // Handles the case of a disjoint tree: loop until adjList[idx - 1].getCumulativeCost is not Integer.MAX_VALUE
	    while ( adjList.get(end - 1).getCumulativeCost() == Integer.MAX_VALUE ) {
	    	flag = true;
	    	end--;
	    }
	    if (flag) {
	    	sb = new StringBuilder();
	    	s.push(sb.append("No rentals available on day ").append(end).toString());
	    }
	    
	    Vertex curr = adjList.get(end - 1);
	    
	    while (curr.getParentEdge() != null && curr.getParent() != null) {
	    	totalCost += curr.getParentEdge().getCost();
	    	s.push(curr.getParentEdge().toString());
	    	curr = curr.getParent();
	    }

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
	 * Generates the shortest path between the vertices corresponding to
	 * startDay and endDay
	 * 
	 * @param startDay the starting vertex
	 * @param endDay the ending vertex
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
	 * Builds the adjacency list from a file of input data
	 * 
	 * @param fileName path to the file containing the graph data
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
			
			// Make a StringTokenizer for the current line
			String curr = edges.nextLine();
			StringTokenizer st = new StringTokenizer(curr, ",", false);
			
			int startDay = Integer.parseInt(st.nextToken());
			int endDay = Integer.parseInt(st.nextToken());
			double cost = (double) Integer.parseInt(st.nextToken());
			String make = st.nextToken();
			String model = st.nextToken();
				
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
			
		}
		
		// Close the file scanner
		edges.close();
		
	}
	
	/**
	 * Gets the adjacency list
	 * 
	 * @return the graph represented as an adjacency list
	 */
	public ArrayList<Vertex> getGraph() {
		
		return this.adjList;
		
	}

}
