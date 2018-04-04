package edu.ncsu.csc316.rentals.stack;

import java.util.EmptyStackException;

import edu.ncsu.csc316.rentals.list.LinkedAbstractList;

/**
 * Linked Stack that implements the methods from the stack interface
 * @author Brian Wu, Noah Beneviste, Ben Gale
 * @param <E> list can contain any element type
 */
public class LinkedStack<E> {
    /** List of objects */
    private LinkedAbstractList<E> list;
    /** Size of list */
    private int size;

    /**
     * Constructs the linked stack list and sets capacity
     */
    @SuppressWarnings("unchecked")
    public LinkedStack() {
        LinkedAbstractList<Object> o = new LinkedAbstractList<Object>();
        list = (LinkedAbstractList<E>) o;
        this.size = 0;
    }

    /**
     * Pushes an element to the top of the stack
     * 
     * @param element
     *            the element we want to add to the stack
     */
    public void push(E element) {
        // Adds element to the end of ArrayList (top of our stack)
        list.add(0, element);
        this.size++;
    }

    /**
     * Pops off the element at the top of the stack
     * 
     * @return the element the user just popped off
     */
    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        E element = list.remove(0);
        this.size--;
        return element;
    }

    /**
     * Checks if the stack is empty
     * 
     * @return true if there is nothing in the stack, otherwise returns false
     */
    public boolean isEmpty() {
        return (this.size == 0);
    }

    /**
     * Returns the number of elements in the stack
     * 
     * @return the number of elements in the stack
     */
    public int size() {
        return this.size;
    }

}