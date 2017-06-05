package edu.umd.cs.datastructures.projectskeletons;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>
 * <tt>ThreadedAVLTree</tt> implements threaded Adelson-Velsky-Landis (AVL)
 * trees (TAVL trees). These trees:
 * </p>
 * <ol>
 * <li>Allow for efficient lookup, insertion and deletion in <em>O(logn)</em>
 * time, by virtue of being AVL trees.</li>
 * <li>Perform a full inorder traversal in <em>O(n)</em> time, by virtue of
 * being threaded trees.</li>
 * </ol>
 * <p>
 * Hence, two powerful ideas that we have talked about in lecture will now be
 * combined in one data structure.
 * </p>
 * 
 * @author Davis Silverman -- 113494963
 * @param <T>
 *            The {@link java.lang.Comparable} type held by the data structure.
 */
public class ThreadedAVLTree<T extends Comparable<T>> {

	class Node implements Comparable<T> {
		T data;
		Node left;
		boolean isLeftThread;
		Node right;
		boolean isRightThread;

		public Node(T data) {
			this.data = data;
			this.isLeftThread = true;
			this.isRightThread = true;
		}

		public int getBalanceFactor() {
			int leftHeight = 0, rightHeight = 0;
			if (!this.isLeftThread) {
				leftHeight = this.left.height() + 1;
			}
			if (!this.isRightThread) {
				rightHeight = this.right.height() + 1;
			}
			return rightHeight - leftHeight;
		}

		public int height() {
			int leftHeight = 0, rightHeight = 0;
			if (!this.isLeftThread) {
				leftHeight = this.left.height() + 1;
			}
			if (!this.isRightThread) {
				rightHeight = this.right.height() + 1;
			}
			return Math.max(leftHeight, rightHeight);
		}

		// Just a nice abstraction
		// compares this node with a T, which compares with this's inner data.
		@Override
		public int compareTo(T o) {
			return this.data.compareTo(o);
		}

		public Node inorderSucc() {
			if (this.isRightThread) {
				return this.right;
			}
			Node cur = this.right;
			while (!cur.isLeftThread) {
				cur = cur.left;
			}
			return cur;
		}

		public Node inorderPred() {
			if (this.isLeftThread) {
				return this.left;
			}
			Node cur = this.left;
			while (!cur.isRightThread) {
				cur = cur.right;
			}
			return cur;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("Node<Data={");
			sb.append(this.data.toString());
			sb.append("}, Left={");
			if (this.isLeftThread) {
				sb.append("Thread->");
				if (this.left == null) {
					sb.append("null");
				} else {
					sb.append(this.left.data.toString());
				}
			} else {
				sb.append("Direct->");
				sb.append(this.left.toString());
			}
			sb.append("}, Right={");
			if (this.isRightThread) {
				sb.append("Thread->");
				if (this.right == null) {
					sb.append("null");
				} else {
					sb.append(this.right.data.toString());
				}
			} else {
				sb.append("Direct->");
				sb.append(this.right.toString());
			}
			sb.append("}>");
			return sb.toString();
		}
	}

	Node root;

	/**
	 * Default constructor. Your code should allow for one, since the unit tests
	 * depend on the presence of a default constructor.
	 */
	public ThreadedAVLTree() {
	}

	private Node rotateLeft(Node oldRoot) {
		Node newRoot = oldRoot.right;
		if (newRoot.isLeftThread) {
			oldRoot.isRightThread = true;
		} else {
			oldRoot.right = newRoot.left;
		}
		newRoot.left = oldRoot;
		newRoot.isLeftThread = false;
		if (!newRoot.isRightThread) {
			Node newRootSucc = newRoot.inorderSucc();
			newRootSucc.isLeftThread = true;
			newRootSucc.left = newRoot;
		}
		if (!newRoot.isLeftThread) {
			Node newRootPred = newRoot.inorderPred();
			newRootPred.isRightThread = true;
			newRootPred.right = newRoot;
		} else {
			throw new IllegalStateException("rotateLeft -- " + this.root.toString());
		}
		return newRoot;
	}

	private Node rotateRight(Node oldRoot) {
		Node newRoot = oldRoot.left;
		if (newRoot.isRightThread) {
			oldRoot.isLeftThread = true;
		} else {
			oldRoot.left = newRoot.right;
		}
		newRoot.right = oldRoot;
		newRoot.isRightThread = false;
		if (!newRoot.isRightThread) {
			Node newRootSucc = newRoot.inorderSucc();
			newRootSucc.isLeftThread = true;
			newRootSucc.left = newRoot;
		} else {
			throw new IllegalStateException("rotateRight -- " + this.root.toString());
		}
		if (!newRoot.isLeftThread) {
			Node newRootPred = newRoot.inorderPred();
			newRootPred.isRightThread = true;
			newRootPred.right = newRoot;
		}
		return newRoot;
	}

	private Node rebalance(Node cur) {
		int curBalance = cur.getBalanceFactor();
		// possible rotations if tree becomes unbalanced.
		if (curBalance > 1) { // if right heavy
			if (cur.right.getBalanceFactor() <= -1) {
				// if right subtree is left heavy
				cur.right = rotateRight(cur.right); // LR/double-left rotation
				cur = rotateLeft(cur);
			} else {
				cur = rotateLeft(cur); // Left rotation
			}
		} else if (curBalance < -1) { // if left heavy
			if (cur.left.getBalanceFactor() >= 1) {
				// if left subtree is right heavy
				cur.left = rotateLeft(cur.left); // RL/double-right rotation
				cur = rotateRight(cur);
			} else {
				cur = rotateRight(cur); // Right rotation
			}
		}
		return cur;
	}

	/**
	 * Insert <tt>key</tt> in the tree.
	 * 
	 * @param key
	 *            The key to insert in the tree.
	 */
	public void insert(T key) {
		if (key == null) {
			// TODO wat do
			throw new IllegalArgumentException("No null keys!");
		}
		this.root = insert(key, this.root);
		this.root = this.rebalance(this.root);
	}

	private Node insert(T key, Node cur) {
		if (cur == null) {
			// pretty sure this only happens when root is null.
			if (this.root != null) {
				throw new IllegalStateException("ROOT NOT NULL! -- " + this.root.toString());
			}
			return new Node(key);
		}
		if (cur.compareTo(key) > 0) {
			if (cur.isLeftThread) {
				Node oldLeftThread = cur.left;
				cur.left = new Node(key);
				cur.isLeftThread = false;
				cur.left.right = cur; // thread successor
				cur.left.left = oldLeftThread;
			} else {
				cur.left = insert(key, cur.left);
			}
		} else if (cur.compareTo(key) < 0) {
			if (cur.isRightThread) {
				Node oldRightThread = cur.right;
				cur.right = new Node(key);
				cur.isRightThread = false;
				cur.right.right = oldRightThread; // inherit successor.
				cur.right.left = cur; // thread predecessor
			} else {
				cur.right = insert(key, cur.right);
			}
		}
		cur = this.rebalance(cur);
		return cur;
	}

	public T delete(T key) {
		// TODO this is a standard BST delete.
		if (this.lookup(key) == null) {
			return null;
		}
		Node ret = this.deleteAux(key, this.root);
		this.root = ret;
		return key;
	}

	private Node deleteAux(T key, Node cur) {
		if (cur.compareTo(key) > 0) {
			Node oldLeft = cur.left;
			cur.left = deleteAux(key, cur.left);
			if (cur.left == null) {
				cur.isLeftThread = true;
				cur.left = oldLeft.left;
			}
		} else if (cur.compareTo(key) < 0) {
			Node oldRight = cur.right;
			cur.right = deleteAux(key, cur.right);
			if (cur.right == null) {
				cur.isRightThread = true;
				cur.right = oldRight.right;
			}
		} else { // at the node.
			if (cur.isRightThread) {
				if (cur.isLeftThread) {
					// leaf -- replace with nothing.
					return null;
				} else {
					// only left exists --
					// can replace cur node with inorder predecessor
					Node newCur = cur.inorderPred();
					cur.left = deleteAux(newCur.data, cur.left);
					if (cur.left == null) {
						cur.isLeftThread = true;
						cur.left = newCur.left;
					} else {
						throw new IllegalStateException(
								"i1 -- Should have Balanced by here -- " + this.root.toString());
					}
					cur.data = newCur.data;
				}
			} else {
				// right exists
				// can replace cur node with inorder successor.
				Node newCur = cur.inorderSucc();
				cur.right = deleteAux(newCur.data, cur.right);
				if (cur.right == null) {
					cur.isRightThread = true;
					cur.right = newCur.right;
				}
				cur.data = newCur.data;
			}
		}
		cur = rebalance(cur);
		return cur;
	}

	/**
	 * Search for <tt>key</tt> in the tree. Return a reference to it if it's in
	 * there, or <tt>null</tt> otherwise.
	 * 
	 * @param key
	 *            The key to look for in the tree.
	 * @return <tt>key</tt> if <tt>key</tt> is in the tree, or <tt>null</tt>
	 *         otherwise.
	 */
	public T lookup(T key) {
		Node cur = this.root;
		while (cur != null) {
			if (cur.compareTo(key) > 0) {
				if (cur.isLeftThread) {
					return null;
				}
				cur = cur.left;
			} else if (cur.compareTo(key) < 0) {
				if (cur.isRightThread) {
					return null;
				}
				cur = cur.right;
			} else {
				return key;
			}
		}
		// Only hits this when root == null.
		return null;
	}

	/**
	 * Return the height of the tree. The height of the tree is defined as the
	 * length of the longest path between the root and the leaf level. By
	 * definition of path length, a stub tree has a height of 0, and we define
	 * an empty tree to have a height of -1.
	 * 
	 * @return The height of the tree.
	 */
	public int height() {
		if (root == null) {
			return -1;
		}
		return this.root.height();
	}

	/**
	 * Query the tree for emptiness. A tree is empty iff it has zero keys
	 * stored.
	 * 
	 * @return <tt>true</tt> if the tree is empty, <tt>false</tt> otherwise.
	 */
	public boolean isEmpty() {
		return this.root == null;
	}

	/**
	 * Return the key at the tree's root node.
	 * 
	 * @return The key at the tree's root node.
	 */
	public T getRoot() {
		if (this.root == null) {
			return null;
		}
		return this.root.data;
	}

	/**
	 * Generate an inorder traversal over the tree's stored keys. This should be
	 * done by using the tree's threads, to be able to find every inorder
	 * successor in amortized constant time. TO GET CREDIT IN RELATED UNIT
	 * TESTS, YOU <b>MUST</b> USE THE TREE'S THREADS TO GENERATE THE TRAVERSAL!
	 * IN FACT, YOU SHOULD NOT HAVE ANY KIND OF RECURSION IN THE IMPLEMENTATION
	 * OF THIS METHOD, NOR SHOULD YOU USE A STACK OF ANY KIND! WE <b>WILL</b>
	 * CHECK YOUR SOURCE CODE TO MAKE SURE YOU ADHERE TO THESE SPECIFICATIONS
	 * FOR THE RELEVANT TEST CREDIT!
	 * 
	 * @return An {@link java.util.Iterator} over <tt>T</tt>s, which exposes the
	 *         elements in ascending order.
	 */
	public Iterator<T> inorderTraversal() {
		return new ThreadedTraversal(this.root);
	}

	class ThreadedTraversal implements Iterator<T> {
		Node cur;
		boolean hasNext;

		public ThreadedTraversal(Node root) {
			if (root == null) {
				return;
			}
			this.cur = root;
			while (!this.cur.isLeftThread) {
				this.cur = this.cur.left;
			}
		}

		@Override
		public boolean hasNext() {
			return this.hasNext = this.cur != null;
		}

		@Override
		public T next() {
			if (!this.hasNext) {
				throw new NoSuchElementException("No more elements!");
			}
			T val = this.cur.data;
			this.cur = this.cur.inorderSucc();
			return val;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("TAVLTree<root=");
		sb.append(this.root.toString());
		sb.append(">");
		return sb.toString();
	}
}
