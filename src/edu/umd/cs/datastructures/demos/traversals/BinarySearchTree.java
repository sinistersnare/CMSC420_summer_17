package edu.umd.cs.datastructures.demos.traversals;

import java.util.List;
import java.util.Stack;

/** <p> <tt>BinarySearchTree</tt> is a simple container class of <a href ="https://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html"><tt>Comparable</tt></a>s. It is likely going to be used
 * in the second lecture, to illustrate some points about inorder traversal. </p>
 * @author Jason
 */
public class BinarySearchTree<T extends Comparable<T>> {
    class Node{
        T value;
        Node left, right;

        Node(T val){
            left = right = null;
            value = val;
        }

    };


    private Node root = null;
    private int count = 0;


    /**
     * A recursive implementation of inorder traversal.
     * @param visited A <tt>List</tt> that will store the nodes as they are being visited.
     */
    public void inorderTraversalRec(List<T> visited){
        inorderTraversalRec(root, visited);
    }

    private void inorderTraversalRec(Node n, List<T> visited){
        if(n==null)
            return;
        inorderTraversalRec(n.left, visited);
        visited.add(n.value);
        inorderTraversalRec(n.right, visited);
    }

    /**
     * A non-recursive implementation of inorder traversal, which uses a user-provided stack.
     * @param visited A <tt>List</tt> that will store the nodes as they are being visited.
     */
    public void inorderTraversalWithStack(List<T> visited){
        Stack<Node> s = new Stack<Node>();
        Node curr = root;
        while(!s.isEmpty() || curr != null){ // TODO: Remember to ask the students why the second check is important.
            // If you're null, you have to visit your parent. They might be one level above you, they
            // might be many levels above you!
            if(curr == null) {
                curr = s.pop(); // Important: this is why the stack needs to be a stack of nodes, not just Ts!
                visited.add(curr.value);
                curr = curr.right;
            // Otherwise, go as left as you can. This is inorder traversal, after all!
            } else {
                s.push(curr);
                curr = curr.left;
            }
        }
    }

    /**
     * Non-recursive insertion routine! Insertion doesn't even need a stack!
     * @param element  The {@link java.lang.Comparable} element to add to the tree.
     */
    public  void insert(T element){
        if(root == null) {
            root = new Node(element);
            count++;
            return;
        }
        boolean left = false, right = false;
        Node curr = root, prev = null;
        while(curr != null){
            prev = curr;
            if(element.compareTo(curr.value) < 0) {
                curr = curr.left;
                left = true;
                right = false;
            } else {
                curr = curr.right;
                left = false;
                right = true;
            }
        }
        if (left == right)  // TODO: Remember to point out to the students what an awesome IDE does for you.
            throw new AssertionError("Failed an invariant of iterative insertion.");
        if(left)
            prev.left = new Node(element);
        else
            prev.right = new Node(element);
        count++;
    }

    /**
     * Returns the number of keys stored in the tree.
     * @return The number of nodes in the tree.
     */
    public int getCount(){
        return count;
    }

    /**
     * Search for <tt>key</tt> in the binary search tree. Non-recursive.
     * @param key The key to search for in the tree.
     * @return <tt>key</tt>, if it is found, <tt>null</tt> otherwise.
     */
    public T search(T key){
        Node curr = root;
        while(curr != null){
            if(curr.value.compareTo(key) == 0)
                return key;
            else if(curr.value.compareTo(key) > 0)
                curr = curr.left;
            else
                curr = curr.right;
        }
        return null;
    }

    /**
     * 
     */
    public void delete(T key){

    }
}
