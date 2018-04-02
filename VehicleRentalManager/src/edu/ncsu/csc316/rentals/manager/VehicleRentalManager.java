package edu.ncsu.csc316.rentals.manager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc316.rentals.graph.Edge;
import edu.ncsu.csc316.rentals.graph.Vertex;
import edu.ncsu.csc316.rentals.list.ArrayList;

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
	    // TODO Add your code here
		return null;
	}
	
	/**
	 * Returns the String representation of the rentals 
	 * that are available for the requested day.
	 * 
	 * @param day - the day for which to retrieve available rentals
	 * @return the String representation of the rentals
	 */
	public String getRentalsForDay(int day) {
	    // List the incident edges for the specified day
		return null;
	}
	
	/**
	 * 
	 * @param fileName
	 */
	private void buildGraph(String fileName) {
		/* 
		   Each line in the csv file is of the following format:
		   <start (day) vertex> <end (day) vertex> <edge weight (cost)> <make> <model>
		   
		   TODO: Make the following changes to the arraylist class:
		       i. Update the add(index, E) method to allow the index to be out of bounds
		          for the array's SIZE; instead, check that the index isn't out of the
		          array's CAPACITY
		      ii. If an element to add is at an index outside of the array's CAPACITY, call
		          the ensureCapacity() method
		     iii. Also add this check to the get(index) method (i.e. call ensureCapacity if out of capacity)
		   
		   SETUP: Initialize the adjacency list to some arbitrarily large size (should have
		          all elements NULL)
		       
		       1. Read in the next line of input
		       2. Check if the adjacency list already has vertex objects corresponding to the START_DAY
		          and END_DAY values read in from the line
		              -these values correspond to the index + 1 in the list where the vertex object should be placed
		       		  i. The index has a vertex already: grab its reference and use it to construct the edge
		       		 ii. The index DOESN'T have a vertex: create a new Vertex object, add it to the list, use it
		       		     to create the edge object
		       3. Add the newly create edge object to the incident edge list for each vertex (just look them up in the list
		          by index)
		       
		 */
		
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
				
				// Add the newly created edge to the start day and end day's incident edge lists
				adjList.get(startDay - 1).addIncidentEdge(e);
				adjList.get(endDay - 1).addIncidentEdge(e);

				// Close the scanner for the line
				currEdge.close();
				
			} catch (NoSuchElementException e) {
				// Do nothing
			}
			
		}
		
		// Close the file scanner
		edges.close();
		
	}

}
