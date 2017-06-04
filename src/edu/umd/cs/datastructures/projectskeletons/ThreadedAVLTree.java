package edu.umd.cs.datastructures.projectskeletons;     // YOU WILL NEED TO ERASE THIS LINE BEFORE SUBMISSION
import java.util.Iterator;                              // You need this import because of the interface method inorderTraversal()'s return type.

/**<p> <tt>ThreadedAVLTree</tt> implements threaded Adelson-Velsky-Landis (AVL) trees (TAVL trees).
 * These trees:</p>
 * <ol>
 *      <li> Allow for efficient lookup, insertion and deletion in <em>O(logn)</em> time, by virtue
 *       of being AVL trees.</li>
 *       <li>Perform a full inorder traversal in <em>O(n)</em> time, by virtue of being threaded trees.</li>
 * </ol>
 * <p>Hence, two powerful ideas that we have talked about in lecture will now be combined in one data structure.</p>
 * 
 * @author ------- PUT YOUR NAME HERE! ------
 * @param <T> The {@link java.lang.Comparable} type held by the data structure.
 */
public class ThreadedAVLTree<T extends Comparable<T>> {

	private static final String UNIMPL_MSG = "UNIMPLEMENTED PUBLIC METHOD!";
    /* Place your private, package-private and protected members
     * and methods here:
     */



	/*
	* Public (interface) methods to be implemented follow.
	*/


    /**
     * Default constructor. Your code should allow for one, since the unit tests
     * depend on the presence of a default constructor.
     */
	public ThreadedAVLTree(){
	    /*
		 *  Fill-in the code here!
		 */
	    throw new RuntimeException(UNIMPL_MSG);

        /* Jason's Note: Depending on how you handle things. it might be completely ok
         * to have nothing in your constructor. That is, the only thing you'd need to do
         * here is erase the application of throw() above. Your code, your choice ! */
    }

	/**
	 * Insert <tt>key</tt> in the tree.
	 * @param key The key to insert in the tree.
	 */
	public void insert(T key){
		/*
		 *  Fill-in the code here!
		 */
        throw new RuntimeException(UNIMPL_MSG);
	}

	/**
	 * Delete the key from the data structure and return it to the caller. Note that it is assumed that there are no
     * duplicate keys in the tree. That is, if a key is deleted from the tree, it should no longer be found in it.
	 * @param key The key to deleteRec from the structure.
	 * @return The key that was removed, or <tt>null</tt> if the key was not found.
	 */
	public T delete(T key){
		/*
		 *  Fill-in the code here!
		 */
		throw new RuntimeException(UNIMPL_MSG);
	}

	/**
	 * Search for <tt>key</tt> in the tree. Return a reference to it if it's in there,
	 * or <tt>null</tt> otherwise.
     * @param key The key to look for in the tree.
	 * @return <tt>key</tt> if <tt>key</tt> is in the tree, or <tt>null</tt> otherwise.
	 */
	public T lookup(T key){
	    /*
		 *  Fill-in the code here!
		 */
        throw new RuntimeException(UNIMPL_MSG);
	}


	/**
	 * Return the height of the tree. The height of the tree is defined as the length of the
	 * longest path between the root and the leaf level. By definition of path length, a 
	 * stub tree has a height of 0, and we define an empty tree to have a height of -1.
	 * @return The height of the tree.
	 */
	public int height(){
		/*
		 *  Fill-in the code here!
		 */
        throw new RuntimeException(UNIMPL_MSG);
	}

	/**
	 * Query the tree for emptiness. A tree is empty iff it has zero keys stored.
	 * @return <tt>true</tt> if the tree is empty, <tt>false</tt> otherwise.
	 */
	public boolean isEmpty(){
		/*
		 *  Fill-in the code here!
		 */
        throw new RuntimeException(UNIMPL_MSG);
	}

	/**
	 * Return the key at the tree's root node.
	 * @return The key at the tree's root node.
	 */
	public T getRoot(){
	    /*
		 *  Fill-in the code here!
		 */
        throw new RuntimeException(UNIMPL_MSG);
	}

	/**
	 * Generate an inorder traversal over the tree's stored keys. This should be done
	 * by using the tree's threads, to be able to find every inorder successor in amortized constant
	 * time. TO GET CREDIT IN RELATED UNIT TESTS, YOU <b>MUST</b> USE THE TREE'S THREADS TO GENERATE THE TRAVERSAL! IN FACT,
     * YOU SHOULD NOT HAVE ANY KIND OF RECURSION IN THE IMPLEMENTATION OF THIS METHOD, NOR SHOULD YOU USE A STACK OF ANY
     * KIND! WE <b>WILL</b> CHECK YOUR SOURCE CODE TO MAKE SURE YOU ADHERE TO THESE SPECIFICATIONS FOR THE RELEVANT
     * TEST CREDIT!
	 * @return An {@link java.util.Iterator} over <tt>T</tt>s, which exposes the elements in
	 * ascending order.
	 */
	public Iterator<T> inorderTraversal(){
		/*
		 *  Fill-in the code here!
		 */
        throw new RuntimeException(UNIMPL_MSG);
	}
}
