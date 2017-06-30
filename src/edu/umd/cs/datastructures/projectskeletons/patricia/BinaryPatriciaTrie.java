package edu.umd.cs.datastructures.projectskeletons.patricia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

//@formatter:off
/**
 * @author Davis Ross Silverman 
 * 
 * <p>
 * <tt>BinaryPatriciaTrie</tt> is a Patricia Trie over the binary
 * alphabet &#123;0, 1&#125;. By restricting themselves to this small
 * but terrificly useful alphabet, Binary Patricia Tries combine all
 * the positive aspects of Patricia Tries while shedding the storage
 * cost typically associated with Tries that deal with huge alphabets.
 * </p>
 */
//@formatter:on
public class BinaryPatriciaTrie {

	// TODO: maybe in some cases root is populated after a delete?
	// that would be bad...

	class Node {
		String value;
		boolean isEnd;
		Node left, right;
	}

	Node root;
	int size;

	/**
	 * Simple constructor that will initialize the internals of <tt>this</tt>.
	 */
	public BinaryPatriciaTrie() {
		this.root = new Node();
		this.size = 0;
	}

	/**
	 * Searches the trie for a given key.
	 * 
	 * @param key
	 *            The input {@link String} key.
	 * @return <tt>true</tt> if and only if <tt>key</tt> is in the trie,
	 *         <tt>false</tt> otherwise.
	 */
	public boolean search(String key) {
		if (!key.matches("^(0|1)*$")) {
			throw new RuntimeException("Key to insert must be a binary string!");
		}
		char lead = key.charAt(0);
		if (lead == '0') {
			return search(key, this.root.left);
		} else {
			return search(key, this.root.right);
		}
	}

	/*
	 * Probably could be chopped down a lot if i wrote down all the cases...
	 */
	public boolean search(String rest, Node cur) {
		if (cur == null) {
			return false;
		}
		if (rest.length() == cur.value.length()) {
			return rest.equals(cur.value) && cur.isEnd;
		} else if (rest.length() < cur.value.length()) {
			// if there is more in cur than the rest, then
			// the key is not in the trie.
			return false;
		} else {
			// Now we can assert that commonAmount never equals rest.length;
			// As rest.length will never be < cur.value.length;
			// so now we must advance to the next node.
			int commonAmount = this.commonPrefixAmount(rest, cur.value);
			String newRest = rest.substring(commonAmount);
			char lead = newRest.charAt(0);
			Node next;
			if (lead == '0') {
				next = cur.left;
			} else {
				next = cur.right;
			}
			return search(rest.substring(commonAmount), next);
		}
	}

	private int commonPrefixAmount(String one, String two) {
		int min = Math.min(one.length(), two.length());
		for (int i = 0; i < min; i++) {
			if (one.charAt(i) != two.charAt(i)) {
				return i;
			}
		}
		return min;
	}

	/**
	 * Inserts <tt>key</tt> into the trie.
	 * 
	 * @param key
	 *            The input {@link String} key.
	 * @return <tt>true</tt> if and only if the key was not already in the trie,
	 *         <tt>false</tt> otherwise.
	 */
	public boolean insert(String key) {
		if (search(key)) {
			return false;
		}
		this.root = insert(key, "", this.root);
		this.size++;
		return true;
	}

	/*
	 * The worst thing ever. Sorry...
	 */
	public Node insert(String rest, String builtUp, Node cur) {
		char lead = rest.charAt(0);
		if (lead == '0') {
			if (cur.left == null) {
				cur.left = new Node();
				cur.left.value = rest;
				cur.left.isEnd = true;
				return cur;
			} else {
				int commonAmount = this.commonPrefixAmount(rest, cur.left.value);
				if (commonAmount == cur.left.value.length() && commonAmount == rest.length()) {
					// the node we will visit is the rest of the string.
					cur.left.isEnd = true;
				} else if (commonAmount == cur.left.value.length()) {
					// simple, just swallow the node, and continue.
					// test by inserting 01 then 0110
					builtUp += cur.left.value;
					String nRest = rest.substring(commonAmount);
					cur.left = insert(nRest, builtUp + cur.left.value, cur.left);
				} else if (commonAmount == rest.length()) {
					// split the child, the rest is a complete prefix of left
					// test by inserting 0110 then 01
					String right = cur.left.value.substring(commonAmount);
					boolean wasEnd = cur.left.isEnd;
					cur.left.value = rest;
					cur.left.isEnd = true;
					Node newChild = new Node();
					newChild.isEnd = wasEnd;
					newChild.value = right;
					newChild.left = cur.left.left;
					newChild.right = cur.left.right;
					lead = right.charAt(0);
					if (lead == '0') {
						cur.left.left = newChild;
						cur.left.right = null;
					} else {
						cur.left.right = newChild;
						cur.left.left = null;
					}
				} else {
					// common is less than both, so its in the middle somewhere.
					String newCurVal = cur.left.value.substring(0, commonAmount);
					String curOldHalf = cur.left.value.substring(commonAmount);
					String restOldHalf = rest.substring(commonAmount);
					boolean wasEnd = cur.left.isEnd;
					cur.left.value = newCurVal;
					cur.left.isEnd = false;

					lead = restOldHalf.charAt(0);
					if (lead == '0') {
						Node newLeft = new Node();
						Node newRight = new Node();
						newLeft.value = restOldHalf;
						newLeft.isEnd = true;
						newRight.value = curOldHalf;
						newRight.left = cur.left.left;
						newRight.right = cur.left.right;
						newRight.isEnd = wasEnd;
						cur.left.left = newLeft;
						cur.left.right = newRight;
					} else {
						Node newLeft = new Node();
						Node newRight = new Node();
						newRight.value = restOldHalf;
						newRight.isEnd = true;
						newLeft.value = curOldHalf;
						newLeft.left = cur.left.left;
						newLeft.right = cur.left.right;
						newLeft.isEnd = wasEnd;
						cur.left.left = newLeft;
						cur.left.right = newRight;
					}
				}
				return cur;
			}
			// else calculate commonPrefix
		} else {
			if (cur.right == null) {
				cur.right = new Node();
				cur.right.value = rest;
				cur.right.isEnd = true;
				return cur;
			} else {
				int commonAmount = this.commonPrefixAmount(rest, cur.right.value);
				if (commonAmount == cur.right.value.length() && commonAmount == rest.length()) {
					// the node we will visit is the rest of the string.
					cur.right.isEnd = true;
				} else if (commonAmount == cur.right.value.length()) {
					// simple, just swallow the node, and continue.
					// test by inserting 01 then 0110
					builtUp += cur.right.value;
					String nRest = rest.substring(commonAmount);
					cur.right = insert(nRest, builtUp + cur.right.value, cur.right);
				} else if (commonAmount == rest.length()) {
					// split the child, the rest is a complete prefix of left
					// test by inserting 0110 then 01
					String rightHalf = cur.right.value.substring(commonAmount);
					cur.right.value = rest;
					cur.right.isEnd = true;
					Node newChild = new Node();
					newChild.value = rightHalf;
					newChild.left = cur.right.left;
					newChild.right = cur.right.right;
					lead = rightHalf.charAt(0);
					if (lead == '0') {
						cur.right.right = newChild;
						cur.right.left = null;
					} else {
						cur.right.left = newChild;
						cur.right.right = null;
					}
				} else {
					// common is less than both, so its in the middle somewhere.
					String newCurVal = cur.right.value.substring(0, commonAmount);
					String curOldHalf = cur.right.value.substring(commonAmount);
					String restOldHalf = rest.substring(commonAmount);
					boolean wasEnd = cur.right.isEnd;
					cur.right.value = newCurVal;
					cur.right.isEnd = false;

					lead = restOldHalf.charAt(0);
					if (lead == '0') {
						Node newLeft = new Node();
						Node newRight = new Node();
						newLeft.value = restOldHalf;
						newLeft.isEnd = true;
						newRight.value = curOldHalf;
						newRight.left = cur.right.left;
						newRight.right = cur.right.right;
						newRight.isEnd = wasEnd;
						cur.right.left = newLeft;
						cur.right.right = newRight;
					} else {
						Node newLeft = new Node();
						Node newRight = new Node();
						newRight.value = restOldHalf;
						newRight.isEnd = true;
						newLeft.value = curOldHalf;
						newLeft.left = cur.right.left;
						newLeft.right = cur.right.right;
						newLeft.isEnd = wasEnd;
						cur.right.left = newLeft;
						cur.right.right = newRight;
					}
				}
				return cur;
			}
		}
	}

	private Node compressNode(Node cur) {
		// reasons to compress:
		// if !cur.isEnd and cur.left==null&&cur.right==null, return null
		// if !cur.isEnd and only one child, concat that child to cur.
		if (cur.isEnd) {
			return cur;
		}
		if (cur.left == null && cur.right == null) {
			return null;
		} else if (cur.right == null && cur.left != null) {
			// absorb the left child
			cur.value += cur.left.value;
			cur.isEnd = cur.left.isEnd;
			cur.right = cur.left.right;
			cur.left = cur.left.left;
		} else if (cur.left == null && cur.right != null) {
			// absorb the right child
			cur.value += cur.right.value;
			cur.isEnd = cur.right.isEnd;
			cur.left = cur.right.left;
			cur.right = cur.right.right;
		}
		return cur;
	}

	/**
	 * Deletes <tt>key</tt> from the trie.
	 * 
	 * @param key
	 *            The {@link String} key to be deleted.
	 * @return <tt>true</tt> if and only if <tt>key</tt> was contained by the
	 *         trie before we attempted deletion, <tt>false</tt> otherwise.
	 */
	public boolean delete(String key) {
		if (!this.search(key)) {
			return false;
		}
		this.root = delete(key, this.root);
		this.size--;
		return true;
	}

	public Node delete(String rest, Node cur) {
		char lead = rest.charAt(0);

		if (lead == '0') {
			if (cur.left == null) {
				// string not in trie, dead code.
				// Checked from search in main function call.
				return cur;
			} else {
				Node next = cur.left;
				int commonAmount = this.commonPrefixAmount(next.value, rest);
				if (next.value.length() == rest.length()) {
					if (next.value.equals(rest)) {
						next.isEnd = false;
						cur.left = this.compressNode(next);
						return cur;
					} else {
						// the string is not in the trie.
						// dead code.
						throw new IllegalStateException("Dead code #1!");
					}
				} else if (next.value.length() == commonAmount) {
					// the next node is a prefix of the rest. so chomp it up!
					String newRest = rest.substring(commonAmount);
					cur.left = delete(newRest, cur.left);
					cur.left = this.compressNode(cur.left);
					return cur;
				} else if (rest.length() == commonAmount) {
					// the rest is a prefix of the next node.
					// that means that the node is not in the trie, therefore...
					// DEAD CODE!
					throw new IllegalStateException("Dead code #2!");
				} else {
					// there is a commonality, but key is not in the tree.
					// DEAD CODE!
					throw new IllegalStateException("Dead code #3!");
				}
			}
		} else {
			if (cur.right == null) {
				// string not in trie, dead code.
				// Checked from search in main function call.
				throw new IllegalStateException("Dead code #4!");
			} else {
				Node next = cur.right;
				int commonAmount = this.commonPrefixAmount(next.value, rest);
				if (next.value.length() == rest.length()) {
					if (next.value.equals(rest)) {
						next.isEnd = false;
						cur.right = this.compressNode(cur.right);
						return cur;
					} else {
						// the string is not in the trie.
						// dead code.
						throw new IllegalStateException("Dead code #5!");
					}
				} else if (next.value.length() == commonAmount) {
					// the next node is a prefix of the rest. so chomp it up!
					String newRest = rest.substring(commonAmount);
					cur.right = delete(newRest, next);
					cur.right = this.compressNode(cur.right);
					return cur;
				} else if (rest.length() == commonAmount) {
					// the rest is a prefix of the next node.
					// that means that the node is not in the trie, therefore...
					// DEAD CODE!
					throw new IllegalStateException("Dead code #6!");
				} else {
					// there is a commonality, but key is not in the tree.
					// DEAD CODE!
					throw new IllegalStateException("Dead code #7!");
				}
			}
		}
	}

	/**
	 * Queries the trie for emptiness.
	 * 
	 * @return <tt>true</tt> if, and only if, {@link #getSize()} == 0,
	 *         <tt>false</tt> otherwise.
	 */
	public boolean isEmpty() {
		return this.size == 0;
	}

	/**
	 * Counts the number of keys in the tree.
	 * 
	 * @return The number of keys in the tree.
	 */
	public int getSize() {
		return this.size;

	}

	/**
	 * Performs an inorder traversal of the <tt>Binary Patricia Trie</tt>.
	 * Remember from lecture that <b>inorder traversal in tries is NOT sorted
	 * traversal, unless all the stored keys have the same length.</b> This is
	 * of course not required by your implementation, so you should make sure
	 * that in your tests you are not expecting this method to return keys in
	 * lexicographic order. We put this method in the interface because it helps
	 * <b>us</b> test your submission thoroughly and it helps <b>you</b> debug
	 * your code!
	 * 
	 * @return An {@link Iterator} over the {@link String} keys stored in the
	 *         trie, exposing the elements in an &#34;inorder&#34; ordering.
	 *         <b>We neither require nor test that the iterator is fail-safe or
	 *         fail-fast</b>; that is, you do <b>not</b> need to test for thrown
	 *         {@link java.util.ConcurrentModificationException}s and we do
	 *         <b>not</b> test your code for concurrent modifications.
	 */
	public Iterator<String> inorderTraversal() {
		if (this.size == 0) {
			return Collections.emptyIterator();
		}
		List<String> xs = new ArrayList<>(this.size);
		inorder(this.root, "", xs);
		return xs.iterator();
	}

	private void inorder(Node cur, String builtUp, List<String> acc) {
		if (cur == null) {
			return;
		}
		String toAdd = cur.value == null ? "" : cur.value;
		String newBuilt = builtUp + toAdd;
		inorder(cur.left, newBuilt, acc);
		if (cur.isEnd) {
			acc.add(newBuilt);
		}
		inorder(cur.right, newBuilt, acc);
	}

	/**
	 * Finds the longest {@link String} stored in the Binary Patricia Trie.
	 * 
	 * @return The longest {@link String} stored in <tt>this</tt>. The following
	 *         rules apply:
	 *         <ol>
	 *         <li>If the trie is empty, the <b>empty string</b> &quot;&quot;
	 *         should be returned. <b>Careful: the empty string &quot;&quot; is
	 *         <b>not</b> the same string as &quot; &quot;&#59; the latter is a
	 *         string consisting of a space character!</b></li>
	 *         <li>If there exist two strings &sigma; and &sigma;&#39; with
	 *         length(&sigma;) = length(&sigma;&#39;), then the string with the
	 *         highest <b>lexicographical order</b> is considered the
	 *         longest.</li>
	 *         <li>In all other cases, the length of the strings is the only
	 *         parameter compared.</li>
	 *         </ol>
	 */
	public String getLongest() {
		if (this.size == 0) {
			return "";
		}
		List<String> strings = new ArrayList<>(this.size);
		inorder(this.root, "", strings);
		String max = maxOf(strings);
		return max;
	}

	private String maxOf(List<String> strings) {
		String max = strings.get(0);
		for (int i = 1; i < strings.size(); i++) {
			String contender = strings.get(i);
			if (contender.length() > max.length()) {
				max = contender;
			} else if (contender.length() == max.length()) {
				if (contender.compareTo(max) > 0) {
					max = contender;
				}
			}
		}
		return max;
	}

	public void clear() {
		this.root = new Node();
		this.size = 0;
	}

}
