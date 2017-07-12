package edu.umd.cs.datastructures.projectskeletons.kdtree; // <------------ ERASE THIS LINE BEFORE YOU SUBMIT!!!!

import java.util.Iterator;


/**
 * <p><tt>BoundedPriorityQueue</tt> is a Priority Queue whose number of elements
 * is bounded. Insertions are such that if the queue's provided capacity is surpassed,
 * its length is not expanded, but rather the maximum priority element is ejected
 * (which could be the element just attempted to be enqueued). Note that <tt>BoundedPriorityQueue</tt>s
 * are {@link Iterable}s.</p>
 * 
 * @author ------ YOUR NAME HERE! --------
 *
 * @param <T> An {@link Object} that will be held by <tt>this</tt>. Note that <tt>T</tt> is not declared as
 *           {@link Comparable}: its priority is supplied on the fly by the enqueueing method.
 */
public class BoundedPriorityQueue<T> implements  Iterable<T>{

	private static RuntimeException exc = new RuntimeException("Unimplemented BPQ method!");


	/*
	 * Put your private fields and methods here!
	 */


	/**
	 * Constructor that specifies the size of our queue.
	 * @param size The static size of the <tt>BoundedPriorityQueue</tt>. Has to be a positive integer.
	 * @throws RuntimeException if <tt>size</tt> is not positive.
	 */
	public BoundedPriorityQueue(int size){
		throw exc;
	}

	/**
	 * Enqueueing elements for<tt> BoundedPriorityQueue</tt>s works a little bit differently from general case
	 * PriorityQueue objects. If the queue is not at capacity, the <tt>element</tt> is inserted at its
	 * appropriate location in the sequence. On the other hand, if the object is at capacity, the element is
	 * inserted in its appropriate spot in the sequence (if such a spot exists, based on its <tt>priority</tt>) and
	 * the maximum priority element is ejected from the structure.
	 * 
	 * @param element The element to insert in the queue.
	 * @param priority The priority of the element to insert in the queue.
	 * @see #dequeue()
	 */
 	public void enqueue(T element, double priority) {
		throw exc;
	}

	/**
	 * Remove and return the minimum priority (top) element from the queue.
	 * @return The minimum priority element from the queue.
	 * @see #first()
	 * @see #enqueue(Object, double)
	 */
 	public T dequeue() {
		throw exc;
	}

	/**
	 * Return, <b>without removing it from the queue</b>, the minimum priority (top) element of the queue.
	 * @return The minimum priority element of the queue.
	 * @see #last()
	 * @see #dequeue()
	 */
 	public T first() {
		throw exc;
	}
	
	/**
	 * Returns the last element in the queue. Useful for cases where we want to 
	 * compare the priorities of a given quantity with the maximum priority of 
	 * our stored quantities. In a linked minheap-based implementation of any PriorityQueue,
	 * this operation would scan <em>O(n)</em> nodes and <em>O(nlogn)</em> links. In an array-based minheap,
	 * it takes constant time.
	 * @return The maximum priority element in our queue, or <tt>null</tt> if the queue is empty.
	 */
	public T last() {
		throw exc;
	}

	/**
	 * Return the queue's size, as measured by the amount of elements stored in it.
	 * @return The number of elements in the queue.
	 */
 	public int size() {
		throw exc;
	}

	/**
	 * Query the queue for emptiness.
	 * @return <tt>true</tt> if and only if there are <b>no</b> elements in the queue, <tt>false</tt> otherwise.
	 */
 	public boolean isEmpty() {
		throw exc;
	}




	/**
	 * Returns a fail-fast {@link Iterator} over the queue's elements. The Iterator exposes the elements in linear order
	 * according to the ordering imposed by {@linkplain edu.umd.cs.datastructures.projectskeletons.kdtree.utils.KNNComparator}.
	 * @return An {@link Iterator} over the queue's elements.
	 */
	@Override
 	public Iterator<T> iterator() {
		throw exc;
	}
	

}
