package edu.umd.cs.datastructures.projectskeletons.kdtree; // <------------ ERASE THIS LINE BEFORE YOU SUBMIT!!!!
import edu.umd.cs.datastructures.projectskeletons.kdtree.utils.*;

import java.util.Collection;
/**
 * <tt>KDTree</tt> imlements <em>K</em>-D Trees. <em>K</em> is a positive integer.
 *  By default, <em>k=2</em>. <tt>KDTree</tt> supports standard insertion, deletion and
 *  search routines, and also allows for range searching and nearest neighbor queries.
 *
 * @author ---- YOUR NAME HERE! ---------
 */
public class KDTree {


	private static RuntimeException exc = new RuntimeException("Unimplemented KD-Tree method!");


	/*
	 * Put your private fields and methods here!
	 */


	/* To pass our tests, you will need to complete the class' public interface, declared below.
	 * YOU WILL NEED TO COMMENT OUT THE EXCEPTION THROWINGS, OTHERWISE YOU WILL FAIL ALL TESTS THAT
	 * INCLUDE CALLS TO THE RELEVANT METHODS! */

	/**
	 * Default constructor constructs <tt>this</tt> with <em>k=2</em>.
	 */
	public KDTree(){
		throw exc;
	}

	/**
	 * This constructor requires that the user provide the value for <em>k</em>.
	 * @param k The dimensionality of <tt>this</tt>.
	 * @throws RuntimeException if <tt>k&lt;=0</tt>.
	 */
	public KDTree(int k){
		throw exc;
	}

	/**
	 * Inserts <tt>p</tt> into the <tt>KDTree</tt>.
	 * @param p The {@link KDPoint} to insert into the tree.
	 */
	public void insert(KDPoint p){
		throw exc;
	}

	/**
	 * Deletes <tt>p</tt> from the <tt>KDTree</tt>. If <tt>p</tt> is not in the
	 * tree, this method performs no changes to the tree.
	 * @param p The {@link KDPoint} to delete from the tree.
	 */
	public void delete(KDPoint p){
		throw exc;
	}

	/**
	 * Searches the tree for <tt>p</tt> and reports if it found it.
	 * @param p The {@link KDPoint} to look for in the tree.
	 * @return <tt>true</tt> iff <tt>p</tt> is in the tree.
	 */
	public boolean search(KDPoint p){
		throw exc;
	}

	/**
	 * Returns the root node of the <tt>KDTree</tt>.
	 * @return The {@link KDPoint} located at the root of the tree, or <tt>null</tt>
	 * if the tree is empty.
	 */
	public KDPoint getRoot(){
		throw exc;
	}

	/**
	 * Performs a range query on the KD-Tree. Returns all the {@link KDPoint}s whose
	 * {@link KDPoint#distance(KDPoint) distance} from  <tt>p</tt> is <b>at most</b> <tt>range</tt>. This means that
	 * range queries on the KD-Tree are <b>inclusive</b> of the range proper! The query point itself should <b>NOT</b>
	 * be reported. The <tt>KDPoint</tt>s are <b>NOT</b> required to be sorted by distance from <tt>p</tt>.
	 * @param p The query {@link KDPoint}.
	 * @param range The maximum {@link KDPoint#distance(KDPoint, KDPoint)} from <tt>p</tt>
	 * that we allow a {@link KDPoint} to have if it should be part of the solution.
	 * @return A {@link Collection} over all {@link KDPoint}s which satisfy our query. The
	 * {@linkplain Collection} will be empty if there are no points which satisfy the query.
	 */
	public Collection<KDPoint> range(KDPoint p, double range){
		throw exc;
	}

	/** Performs a nearest neighbor query on the <tt>KDTree</tt>. Returns the {@link KDPoint}
	 * which is closest to <tt>p</tt>, as dictated by {@link KDPoint#distance(KDPoint) distance(KDPoint p)}.
	 * This point will be <b>DISTINCT</b> from <tt>p</tt> (otherwise this would make this operation trivial to implement).
	 * @param p The query {@link KDPoint}.
	 * @return The solution to the nearest neighbor query. This method will return <tt>null</tt> if
	 * there are no points other than <tt>p</tt> in the tree.
	 */
	public KDPoint nearestNeighbor(KDPoint p){
		throw exc;
	}

	/**
	 * Performs a m-nearest neighbors query on the <tt>KDTree</tt>. Returns the <em>m</em>
	 * {@link KDPoint}s which are nearest to <tt>p</tt>, as dictated by {@link KDPoint#distance(KDPoint) distance(KDPoint p)}.
	 * The {@link KDPoint}s are sorted in ascending order of distance.
	 * @param m A positive integer denoting the amount of neighbors to return.
	 * @param p The query point.
	 * @return A {@link BoundedPriorityQueue} containing the m-nearest neighbors of <tt>p</tt>.
	 * This queue will be empty if the tree contains only <tt>p</tt>.
	 * @throws RuntimeException If <tt>m&lt;=0</tt>.
	 */
	public BoundedPriorityQueue<KDPoint> mNearestNeighbors(int m, KDPoint p){
		throw exc;
	}

	/**
	 * Returns the height of the tree. By convention, the height of an empty tree is -1.
	 * @return The height of <tt>this</tt>.
	 */
	public int height(){
		throw exc;
	}

	/**
	 * Reports whether the <tt>KDTree</tt> is empty, that is, it contains zero {@link KDPoint}s.
	 * @return <tt>true</tt> iff there are no {@link KDPoint}s stored in <tt>this</tt>.
	 */
	public boolean isEmpty(){
		throw exc;
	}

}
