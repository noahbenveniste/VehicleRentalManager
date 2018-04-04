package edu.ncsu.csc316.rentals.manager;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.rentals.graph.Vertex;

/**
 * A suite of unit tests that test the functionality of the
 * VehicleRentalManager class, including construction of a graph
 * given a csv input file, querying for rentals available for a
 * specific day, and finding the cheapest sequence of rentals
 * between two specified days.
 * 
 * @author Noah Benveniste
 */
public class VehicleRentalManagerTest {

	/** Path to the test file to use */
	private static final String TEST_FILE = "input/sample.csv";
	
	/**
	 * Tests construction of the manager object, including initialization of the
	 * adjacency list graph representation
	 */
	@Test
	public void testVehicleRentalManager() {
		
		// Build a new manager with the given input file
		VehicleRentalManager vrm = new VehicleRentalManager(TEST_FILE);
		
		// Check that the adjacency list is the correct size
		assertEquals(5, vrm.getGraph().size());
		
		// Check that each vertex has the correct day that corresponds to its index in the adj list
		for (int i = 0; i < vrm.getGraph().size(); i++) {
			assertEquals(i + 1, vrm.getGraph().get(i).getDay());
		}
		
		// Check that each vertex has the correct adjacent edge list
		Vertex day1 = vrm.getGraph().get(0);
		assertEquals(4, day1.getAdjacentEdges().size());
		assertEquals("Available rentals for day 1\n" + 
				"   $85.00 Chevrolet Tahoe for day 1 to day 2\n" + 
				"   $180.00 Chevrolet Silverado for day 1 to day 3\n" + 
				"   $255.00 Toyota Prius for day 1 to day 4\n" + 
				"   $500.00 Honda CRV for day 1 to day 5\n" + 
				"]", vrm.getRentalsForDay(1));
		
		Vertex day2 = vrm.getGraph().get(1);
		assertEquals(3, day2.getAdjacentEdges().size());
		assertEquals("Available rentals for day 2\n" + 
				"   $65.00 Jeep Compass for day 2 to day 3\n" + 
				"   $90.00 Jeep Cherokee for day 2 to day 4\n" + 
				"   $220.00 Ford Explorer for day 2 to day 5\n" + 
				"]", vrm.getRentalsForDay(2));
		
		Vertex day3 = vrm.getGraph().get(2);
		assertEquals(2, day3.getAdjacentEdges().size());
		assertEquals("Available rentals for day 3\n" +
				"   $55.00 Kia Soul for day 3 to day 4\n" +
				"   $90.00 Ford Explorer for day 3 to day 5\n" +
				"]", vrm.getRentalsForDay(3));
		
		Vertex day4 = vrm.getGraph().get(3);
		assertEquals(1, day4.getAdjacentEdges().size());
		assertEquals("Available rentals for day 4\n" +
				"   $50.00 Honda Accord for day 4 to day 5\n" +
				"]", vrm.getRentalsForDay(4));
		
		Vertex day5 = vrm.getGraph().get(4);
		assertEquals(0, day5.getAdjacentEdges().size());
		assertEquals("Available rentals for day 5\n" +
				"   No rentals available.\n" +
				"]", vrm.getRentalsForDay(5));
		
	}
	
	/**
	 * Tests the getRentals() method
	 */
	@Test
	public void testGetRentals() {
		
		// Build a new manager with the given input file
		VehicleRentalManager vrm = new VehicleRentalManager(TEST_FILE);
		
		String actual1 = vrm.getRentals(1, 5);
		assertEquals("Rental total is $225.00\n" + 
				"[\n" + 
				"   From day 1 to day 2: $85.00, Chevrolet Tahoe\n" + 
				"   From day 2 to day 4: $90.00, Jeep Cherokee\n" + 
				"   From day 4 to day 5: $50.00, Honda Accord\n" + 
				"]", actual1);
		
		String actual2 = vrm.getRentals(1, 6);
		assertEquals("Rental total is $225.00\n" + 
				"[\n" + 
				"   From day 1 to day 2: $85.00, Chevrolet Tahoe\n" + 
				"   From day 2 to day 4: $90.00, Jeep Cherokee\n" + 
				"   From day 4 to day 5: $50.00, Honda Accord\n" + 
				"   No rentals available on day 5\n" +
				"]", actual2);
		String actual3 = vrm.getRentals(2, 7);
		assertEquals("Rental total is $140.00\n" + 
				"[\n" + 
				"   From day 2 to day 4: $90.00, Jeep Cherokee\n" + 
				"   From day 4 to day 5: $50.00, Honda Accord\n" + 
				"   No rentals available on day 5\n" +
				"]", actual3);
		
		// Test with days that span two disjoint trees
		vrm = new VehicleRentalManager("input/disjoint.csv");
		String actual4 = vrm.getRentals(1, 100);
		assertEquals("Rental total is $205.00\n" + 
				"[\n" + 
				"   From day 1 to day 2: $85.00, Chevrolet Tahoe\n" + 
				"   From day 2 to day 3: $65.00, Jeep Compass\n" + 
				"   From day 3 to day 4: $55.00, Kia Soul\n" + 
				"   No rentals available on day 4\n" + 
				"]", actual4);
		
	}

}
