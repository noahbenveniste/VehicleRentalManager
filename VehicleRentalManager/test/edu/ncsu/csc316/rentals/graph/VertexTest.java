package edu.ncsu.csc316.rentals.graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit tests for the Vertex class
 * 
 * @author Noah Benveniste
 */
public class VertexTest {

	/**
	 * Tests the compareTo() method
	 */
	@Test
	public void testCompareTo() {
		Vertex v1 = new Vertex(1);
		Vertex v2 = new Vertex(2);
		assertEquals(0, v1.compareTo(v2));
	}

}
