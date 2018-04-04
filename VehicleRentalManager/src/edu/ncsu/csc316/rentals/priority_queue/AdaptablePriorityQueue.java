package edu.ncsu.csc316.rentals.priority_queue;

import edu.ncsu.csc316.rentals.graph.Vertex;

/**
 * An adaptable priority queue. Outputs elements with highest priority
 * first (low cumulative cost). Elements can also have their cumulative
 * costs updated at any time and PQ will adjust itself accordingly.
 * 
 * @author Noah Benveniste
 */
public class AdaptablePriorityQueue {
	/** Implemented using a min heap */
	private MinHeap heap;
	
	/**
	 * Constructs an empty APQ
	 */
	public AdaptablePriorityQueue() {
		
		this.heap = new MinHeap();
		
	}
	
	/**
	 * Inserts a vertex into the PQ
	 * 
	 * @param v the vertex to insert
	 */
	public void insert(Vertex v) {
		
		heap.insert(v);
		
	}
	
	/**
	 * Gets the highest priority vertex from the PQ
	 * 
	 * @return the highest priority vertex
	 */
	public Vertex deleteMin() {
		
		return heap.deleteMin();
		
	}
	
	/**
	 * Updates the priority of a given element in the PQ. Requires that elements in the PQ
	 * be location aware.
	 * 
	 * @param v the vertex whose priority is being updated
	 * @param newPriority the new priority value
	 */
	public void updatePriority(Vertex v, double newPriority) {
		
		// Update v's priority (its cumulative cost field)
		int pos = v.getPosition();
		heap.list.get(pos).setCumulativeCost(newPriority);
		
		// Check if upHeap or downHeap needs to be done to preserve heap ordering property
		Vertex parent = heap.list.get( (pos - 1) / 2 );
		if ( parent.getCumulativeCost() > newPriority ) {
			heap.upHeap(pos);
		} else {
			heap.downHeap(pos);
		}
		
	}
	
	/**
	 * Checks if the APQ is empty or not
	 * 
	 * @return true if it is, false otherwise
	 */
	public boolean isEmpty() {
		
		return this.heap.isEmpty();
		
	}

}
