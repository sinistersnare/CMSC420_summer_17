package edu.umd.cs.datastructures.davis;

/**
 *
 * @author aakash
 *
 * @param <T>
 */
public class BinaryTree<T> {

	protected Node<T> root = null;

	public Node<T> getRoot() {
		return root;
	}

	public void visit(Node<T> data, Node<T> stack, Node<T> top) {
		System.out.println("node visiting currently::" + data.toString());
		if (stack != null) {
			Node<T> p = stack;
			System.out.println("Stack::");
			while (p != null) {
				System.out.print("\tdata:" + p.getData());
				if (p.getLeft() != null)
					System.out.print("\t left:" + p.getLeft().getData());
				else
					System.out.print("\t left:" + p.getLeft());
				if (p.getRight() != null)
					System.out.println("\t right:" + p.getRight().getData());
				else
					System.out.println("\t right:" + p.getRight());
				p = p.getLeft();
			}
		} else {
			System.out.println("stack::" + stack);
		}
		if (top != null) {
			System.out.println("top::" + top.getData());
		} else {
			System.out.println("top::" + top);
		}
	}

	/**
	 * Write and test a "modified" Robson Traversal program that uses the linked
	 * representation of the trees. This modified version differs from the
	 * Robson only in that the pointers to a node's predecessor should now be as
	 * in the Linked Inversion Traversal. That is, when a node's left(right)
	 * subtree is being traversed, its left(right) pointer will point to its
	 * predecessor. During the traversal, when a node is visited, output for
	 * each "stack' entry, its info value and the info value of its left and
	 * right successors. This means, first, output the number of the node Top
	 * points to, and then the number that that node's left and right pointers
	 * point to. Then, output the number of the node that Stack points to,and
	 * then the numbers that that node's left and right pointers point to. Do
	 * this for each node on the "stack". Also, output similar information for
	 * each node along the path from the predecessor of the node being visited
	 * to the root. That is, for each of these nodes, output its info value and
	 * the numbers that the nodes left left and right pointers point to.
	 */
	public void modifiedRobsonTreeTraversal() {
		Node<T> cur = root;
		boolean rootHasOnlyRightChild = false;
		Node<T> empty = root, prev = null, next = null, avail = null, stack = null, top = null;
		boolean done = false;
		if (root == null) {
			return;
		} else {
			rootHasOnlyRightChild = (root.getLeft() == null) ? (root.getRight() != null ? true : false) : false;
		}
		System.out.println("\n===============================");
		System.out.println("Modified Robson traversal::");
		System.out.println("===============================");
		do {
			while (cur != null) {
				visit(cur, stack, top);
				next = cur.getLeft();

				if (cur.getLeft() == null && cur.getRight() == null) {
					if (avail == null) {
						avail = cur;
					} else {
						cur.setRight(avail);
						avail = cur;
					}
				}
				cur.setLeft(prev);
				prev = cur;
				cur = next;
			}
			next = cur;
			cur = prev;
			prev = prev.getLeft();
			cur.setLeft(next);
			boolean up = true;
			boolean leftTraversingFinished = true;
			do {
				if (cur.getRight() != null && cur != avail && leftTraversingFinished) {
					next = cur.getRight();
					cur.setRight(prev);
					if (cur.getLeft() != null) {
						if (top != null) {
							Node<T> temp = avail;
							avail = avail.getRight();
							temp.setLeft(null);
							temp.setRight(null);
							temp.setRight(top);
							if (stack == null)
								stack = temp;
							else {
								temp.setLeft(stack);
								stack = temp;
							}

						}
						top = cur;

					}
					prev = cur;
					cur = next;
					up = false;
				}
				if (cur.equals(empty)) {
					done = true;
				}
				if (up == true && prev != null) {
					Node<T> nextPrev = null;
					boolean curIsPrevLeftChild = true;
					if (prev == empty) {
						nextPrev = null;
						if (rootHasOnlyRightChild) {
							curIsPrevLeftChild = false;
						}
					} else if (prev.getLeft() != null && top != prev) {
						nextPrev = prev.getLeft();
					} else {
						nextPrev = prev.getRight();
						curIsPrevLeftChild = false;
					}

					if (top == prev) {
						prev.setRight(cur);
						leftTraversingFinished = false;
						if (top != null) {

							if (stack != null) {
								top = stack.getRight();
								Node<T> currentStack = stack;
								stack = stack.getLeft();
								currentStack.setLeft(null);
								currentStack.setRight(null);
							} else
								top = null;

						}

					}

					else if (curIsPrevLeftChild) {
						prev.setLeft(cur);
						leftTraversingFinished = true;
					} else {
						prev.setRight(cur);
						prev.setLeft(null);
						leftTraversingFinished = false;

					}
					cur = prev;
					prev = nextPrev;
				}

			} while (cur != null && up && !done);

		} while (cur != null && !done);
		while (avail != null) {
			Node<T> temp = avail;
			avail = avail.getRight();
			temp.setLeft(null);
			temp.setRight(null);
		}
	}
}
