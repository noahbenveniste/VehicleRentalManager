package edu.ncsu.csc316.rentals.priority_queue;

import edu.ncsu.csc316.rentals.graph.Vertex;
import edu.ncsu.csc316.rentals.list.ArrayList;

/**
 * 
 * @author Noah Benveniste
 */
public class AdaptablePriorityQueue {
	
	private MinHeap heap;
	
	/**
	 * 
	 */
	public AdaptablePriorityQueue() {
		this.heap = new MinHeap();
	}
	
	/**
	 * 
	 * @param e
	 */
	public void insert(Vertex v) {
		heap.insert(v);
	}
	
	/**
	 * 
	 * @return
	 */
	public Vertex deleteMin() {
		return heap.deleteMin();
	}
	
	/**
	 * 
	 * @param e
	 * @param value
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
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return false;
	}

}
