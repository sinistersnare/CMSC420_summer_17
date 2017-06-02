package edu.umd.cs.datastructures.projectskeletons; // YOU WILL NEED TO ERASE THIS LINE BEFORE SUBMISSION

import java.util.Iterator; // You need this import because of the interface method inorderTraversal()'s return type.

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
		}

		public int getBalanceFactor() {
			int rightHeight = 0, leftHeight = 0;
			if (this.right != null) {
				rightHeight = this.right.height() + 1;
			}
			if (this.left != null) {
				leftHeight = this.left.height() + 1;
			}
			return rightHeight - leftHeight;
		}

		/**
		 * Returns true if the tree is unoptimally right heavy. If the
		 * balanceFactor is < 2, it is optimally right heavy.
		 */
		public boolean isRightHeavy() {
			return this.getBalanceFactor() >= 2;
		}

		/**
		 * Returns true if the tree is unoptimally left heavy. If the
		 * balanceFactor is > -2, it is optimally left heavy.
		 */
		public boolean isLeftHeavy() {
			return this.getBalanceFactor() <= -2;
		}

		public void rotateLeft() {
			Node temp = this.right;
			this.right = temp.left;
			temp.left = this;
		}

		public void rotateLR() {
			this.left.rotateLeft();
			this.rotateRight();
		}

		public void rotateRight() {
			Node temp = this.left;
			this.left = temp.right;
			temp.right = this;
		}

		public void rotateRL() {
			this.right.rotateRight();
			this.rotateLeft();
		}

		public int height() {
			int leftHeight = 0, rightHeight = 0;
			if (this.left != null) {
				leftHeight = this.left.height() + 1;
			}
			if (this.right != null) {
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
			// assert (cur.left.data.equals(this.data));
			// TODO Threads should guarantee this.
			// when threads implemented, uncomment that assertion
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
			assert (cur.right.data.equals(this.data));
			// assert (cur.right.data.equals(this.data));
			// TODO: Threads should guarantee this.
			// when threads implemented, uncomment that assertion
			return cur;
		}
	}

	Node root;

	/**
	 * Default constructor. Your code should allow for one, since the unit tests
	 * depend on the presence of a default constructor.
	 */
	public ThreadedAVLTree() {
	}

<<<<<<< Updated upstream
        /* Jason's Note: Depending on how you handle things. it might be completely ok
         * to have nothing in your constructor. That is, the only thing you'd need to do
         * here is erase the application of throw() above. Your code, your choice ! */
    }

=======
>>>>>>> Stashed changes
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
		this.rotateCheck(this.root);
	}

	public Node insert(T key, Node cur) {
		if (cur == null) {
			return new Node(key);
		}
		if (cur.compareTo(key) > 0) {
			cur.left = insert(key, cur.left);
		} else if (cur.compareTo(key) < 0) {
			cur.right = insert(key, cur.right);
		}
		this.rotateCheck(cur);
		return cur;
	}

	private void rotateCheck(Node cur) {
		// possible rotations if tree becomes unbalanced.
		if (cur.getBalanceFactor() > 1) { // if right heavy
			if (cur.right.getBalanceFactor() < -1) {
				// if right subtree is left heavy
				cur.rotateLR(); // LR/double-left rotation
			} else {
				cur.rotateLeft(); // Left rotation
			}
		} else if (cur.getBalanceFactor() < -1) { // if left heavy
			if (cur.left.getBalanceFactor() > 1) {
				// if left subtree is right heavy
				cur.rotateRL(); // RL/double-right rotation
			} else {
				cur.rotateRight(); // Right rotation
			}
		}
	}

	/**
<<<<<<< Updated upstream
	 * Delete the key from the data structure and return it to the caller. Note that it is assumed that there are no
     * duplicate keys in the tree. That is, if a key is deleted from the tree, it should no longer be found in it.
	 * @param key The key to delete from the structure.
	 * @return The key that was removed, or <tt>null</tt> if the key was not found.
=======
	 * Delete the key from the data structure and return it to the caller.
	 * 
	 * @param key
	 *            The key to delete from the structure.
	 * @return The key that was removed, or <tt>null</tt> if the key was not
	 *         found.
>>>>>>> Stashed changes
	 */
	public T delete(T key) {
		// TODO this is a standard BST delete.
		Node ret = this.deleteAux(key, this.root);
		if (ret == null) {
			return null;
		}
		this.root = ret;
		return key;
	}

	public Node deleteAux(T key, Node cur) {
		if (cur == null) {
			return null;
		}
		if (cur.compareTo(key) > 0) {
			if (cur.isLeftThread) {
				return null;
			}
			cur.left = deleteAux(key, cur.left);
		} else if (cur.compareTo(key) < 0) {
			if (cur.isRightThread) {
				return null;
			}
			cur.right = deleteAux(key, cur.right);
		} else {
			if (cur.isRightThread) {
				return cur.left;
			} else {
				Node succ = cur.inorderSucc();
				cur.data = succ.data;
				cur.right = deleteAux(succ.data, cur.right);
			}
		}
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
			while (this.cur.left != null) {
				this.cur = this.cur.left;
			}
		}

		@Override
		public boolean hasNext() {
			return cur == null;
		}

		@Override
		public T next() {
			if (!hasNext) {
				return null;
			}
			T val = cur.data;
			cur = cur.inorderSucc();
			return val;
		}

	}
}
