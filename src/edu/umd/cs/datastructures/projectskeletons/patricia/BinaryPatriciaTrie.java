package edu.umd.cs.datastructures.projectskeletons.patricia; // <------------ ERASE THIS!!!!!!
import java.util.Iterator;


/**
 * <p><tt>BinaryPatriciaTrie</tt> is a Patricia Trie over the binary alphabet &#123;0, 1&#125;. By restricting themselves to
 * this small but terrifically useful alphabet, Binary Patricia Tries combine all the positive aspects of Patricia Tries
 * while shedding the storage cost typically associated with Tries that deal with huge alphabets. </p>
 * @author  ------ YOUR NAME HERE! ----------
 */
public class BinaryPatriciaTrie {

	// You can erase the following line after you erase the exception throwings in the public methods...
	private static final RuntimeException UNIMPL_METHOD_EXC = new RuntimeException("Unimplemented method!");


	/*
	 * Put your private / protected fields and methods here!
	 * /





	/* To pass our tests, you will need to complete the class' public interface, declared below.
	 * YOU WILL NEED TO COMMENT OUT THE EXCEPTION THROWINGS, OTHERWISE YOU WILL FAIL ALL TESTS THAT
	  * INCLUDE CALLS TO THE RELEVANT METHODS! */

	/**
	 * Simple constructor that will initialize the internals of <tt>this</tt>.
	 */
	public BinaryPatriciaTrie() {
		throw UNIMPL_METHOD_EXC; // ERASE THIS BEFORE YOU TEST YOUR CODE!
	}


	/** Searches the trie for a given key.
	 * @param key The input {@link String} key.
	 * @return <tt>true</tt> if and only if <tt>key</tt> is in the trie, <tt>false</tt> otherwise.
	 */
	public boolean search(String key) {
		throw UNIMPL_METHOD_EXC; // ERASE THIS BEFORE YOU TEST YOUR CODE!
	}


	/** Inserts <tt>key</tt> into the trie.
	 * @param key The input {@link String} key.
	 * @return <tt>true</tt> if and only if the key was not already in the trie, <tt>false</tt> otherwise.
	 */
	public boolean insert(String key) {
		throw UNIMPL_METHOD_EXC; // ERASE THIS BEFORE YOU TEST YOUR CODE!
	}


	/** Deletes <tt>key</tt> from the trie.
	 * @param key The {@link String} key to be deleted.
	 * @return <tt>true</tt> if and only if <tt>key</tt> was contained by the trie before we attempted deletion,
	 * <tt>false</tt> otherwise.
	 */
	public boolean delete(String key) {
		throw UNIMPL_METHOD_EXC; // ERASE THIS BEFORE YOU TEST YOUR CODE!
	}

	/**
	 * Queries the trie for emptiness.
	 * @return <tt>true</tt> if, and only if, {@link #getSize()} == 0, <tt>false</tt> otherwise.
	 */
	public boolean isEmpty() {
		throw UNIMPL_METHOD_EXC; // ERASE THIS BEFORE YOU TEST YOUR CODE!
	}

	/**
	 * Counts the number of keys in the tree.
	 *@return The number of keys in the tree.
	 */
	public int getSize() {
		throw UNIMPL_METHOD_EXC; // ERASE THIS BEFORE YOU TEST YOUR CODE!
	}

	/** Performs an inorder traversal of the <tt>Binary Patricia Trie</tt>. Remember from lecture that <b>inorder traversal in tries is
	 * NOT sorted traversal, unless all the stored keys have the same length.</b> This is of course not required by your implementation,
	 * so you should make sure that in your tests you are not expecting this method to return keys in lexicographic order. We put this
	 * method in the interface because it helps <b>us</b> test your submission thoroughky and it helps <b>you</b> debug your code!
	 * @return An {@link Iterator} over the {@link String} keys stored in the trie, exposing the elements in an &#34;inorder&#34;
	 * ordering. <b>We neither require nor test that the iterator is fail-safe or fail-fast</b>; that is, you do <b>not</b> need to test for thrown
	 * {@link java.util.ConcurrentModificationException}s and we do <b>not</b> test your code for concurrent modifications.
	 */
	public Iterator<String> inorderTraversal() {
		throw UNIMPL_METHOD_EXC; // ERASE THIS BEFORE YOU TEST YOUR CODE!
	}

	/** Finds the longest {@link String} stored in the Binary Patricia Trie. &quot;<i>Longest</i>&quot; is rigorously defined below.
	 * @return The longest {@link String} stored in <tt>this</tt>. The following rules apply:
	 * <ol><li>If the trie is empty, the <b>empty string</b> &quot;&quot; should be returned. <b>Careful: the empty string &quot;&quot; is <em>not</em> the same string as &quot; &quot;&#59;	the latter is a string consisting of a space character!</b></li>
	 * <li>If there exist two strings &sigma; and &sigma;&#39;	with length(&sigma;) = length(&sigma;&#39;), then the string with the highest <b>lexicographical order</b> is considered the longest.</li>
	 * <li>In all other cases, the length of the strings is the only parameter compared.</li>
	 * </ol>
	 */
	public String getLongest () {
		throw UNIMPL_METHOD_EXC; // ERASE THIS BEFORE YOU TEST YOUR CODE!
	}

}
