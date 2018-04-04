package edu.ncsu.csc316.rentals.priority_queue;

import edu.ncsu.csc316.rentals.graph.Vertex;
import edu.ncsu.csc316.rentals.list.ArrayList;

/**
 * A min heap data structure; used to implement an adaptable priority queue
 * i.e. has location-aware elements.
 * 
 * @author Noah Benveniste
 */
public class MinHeap {
	
	ArrayList<Vertex> list;
	private int size;

	/**
	 * Constructs an empty minheap
	 */
	public MinHeap() {
		this.list = new ArrayList<Vertex>();
		this.size = 0;
	}
	
	/**
	 * Inserts a vertex into the minheap
	 * 
	 * @param v the vertex to insert
	 */
	public void insert(Vertex v) {
		/*
		  1. Insert the vertex at the end of the array
		  2. Set the vertex's position field (for the adaptable priority queue to use)
		  3. Update the size
		  4. upheap
		 */
		
		// 1.
		this.list.add(this.size, v);
		// 2.
		v.setPosition(this.size);
		// 3.
		this.size++;
		// 4.
		this.upHeap(this.size - 1);
	}
	
	/**
	 * Performs up heap operation to preserve heap ordering property when necessary
	 * 
	 * @param i the position of the new element in the heap
	 */
	public void upHeap(int i) {
		/*
		  1. Check if i > 0
		  2. Check if the key at the i-1/2 index is greater than the key at the i index
		      i. If this is true, swap the vertex objects at these positions. Also, update
		         the position field of each vertex for the adaptable priority queue. Then, 
		         recursive call upheap, passing i-1/2
		     ii. If either of the above checks are false, return
		 */
		
		if ( (i > 0) && (list.get( ( i - 1 ) / 2 ).getCumulativeCost() > list.get( i ).getCumulativeCost())) {
			Vertex temp = list.get( i );
			list.set( i, list.get( ( i - 1 ) / 2 ));
			list.set(( i - 1 ) / 2, temp);
			
			// Don't forget to update each vertex's position field
			list.get( i ).setPosition(i);
			list.get( ( i - 1 ) / 2 ).setPosition( ( i - 1 ) / 2 );
			
			upHeap( ( i - 1 ) / 2 );
		}
		
	}
	
	/**
	 * Gets the vertex with the highest priority from the queue
	 * 
	 * @return the vertex with the highest priority (lowest cumulative cost)
	 */
	public Vertex deleteMin() {
		
		/*
		  1. Get the element at index 0, store in x
		  2. Set the index 0 to be the last element in the array
		  3. Remove the last element in the array (a duplicate now)
		  4. downheap
		  5. return x
		 */
		
		// 1.
		Vertex out = list.get(0);
		// 2.
		list.set(0, list.get(size - 1));
		list.get(0).setPosition(0);
		// 3.
		list.remove(size - 1);
		this.size--;
		// 4.
		this.downHeap(0);
		// 5.
		return out;
		
	}
	
	/**
	 * Performs the downHeap operation to preserve heap ordering property when necessary
	 * 
	 * @param m the position of the new element in the heap
	 */
	public void downHeap(int m) {
		/* DOWNHEAP ALGORITHM */
//		i is m’s smallest child, if one exists
//		i <- 0
//		if 2m + 2 < size(H) then (both children exist)
//			if key(H[2m+2] <= key(H[2m+1]) then
//				i <- 2m + 2
//			else
//				i <- 2m + 1
//		else if 2m + 1 < size(H) then (only left child exists)
//			i <- 2m + 1
//		(at this stage, if i = 0, then the node has no children)
//		if i > 0 and key(H[m]) > key(H[i]) then
//			swap(m,i)
//			downHeap(H,i)
		int i = 0;
		
		if ( 2 * m  + 2 < this.size ) {
			if ( list.get( 2 * m + 2 ).getCumulativeCost() <= list.get( 2 * m + 1 ).getCumulativeCost() ) {
				i = 2 * m + 2;
			} else {
				i = 2 * m + 1;
			}
		} else if ( 2 * m + 1 < this.size ) {
			i = 2 * m + 1;
		}
		
		if ( (i > 0) && (list.get(m).getCumulativeCost() > list.get(i).getCumulativeCost()) ) {
			Vertex temp = list.get(i);
			list.set(i, list.get(m));
			list.set(m, temp);
			
			// Don't forget to update each vertex's position field
			list.get(i).setPosition(i);
			list.get(m).setPosition(m);
			
			// Downheap
			downHeap(i);
		}
		
	}
	
	/**
	 * Checks if the heap is empty
	 * 
	 * @return true if it is, false otherwise
	 */
	public boolean isEmpty() {
		
		return this.size == 0;
		
	}

}
