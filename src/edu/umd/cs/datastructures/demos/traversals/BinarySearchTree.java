package edu.umd.cs.datastructures.demos.traversals;

import java.util.List;
import java.util.Stack;

/** <p> <tt>BinarySearchTree</tt> is a simple container class of <a href ="https://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html"><tt>Comparable</tt></a>s. It is likely going to be used
 * in the second lecture, to illustrate some points about inorder traversal. </p>
 * @author Jason
 */
public class BinarySearchTree<T extends Comparable<T>> {
    class Node{
        T key;
        Node left, right;

        Node(T key){
            left = right = null;
            this.key = key;
        }

        // This will return the node's inorder successor in the tree.
        Node inSucc(){
            assert right != null; // Otherwise the caller is making the wrong application
            Node curr = right;
            while(curr.left != null)
                curr = curr.left;
            return curr;
        }
    };


    private Node root = null;
    private int count = 0;


    /**
     * A recursive implementation of inorder traversal.
     * @param visited A <tt>List</tt> that will store the nodes as they are being visited.
     * @see #inorderTraversalWithStack(List)
     */
    public void inorderTraversalRec(List<T> visited){
        inorderTraversalRec(root, visited);
    }

    private void inorderTraversalRec(Node n, List<T> visited){
        if(n==null)
            return;
        inorderTraversalRec(n.left, visited);
        visited.add(n.key);
        inorderTraversalRec(n.right, visited);
    }

    /**
     * A non-recursive implementation of inorder traversal, which uses a user-provided stack.
     * @param visited A <tt>List</tt> that will store the nodes as they are being visited.
     * @see #inorderTraversalRec(List)
     */
    public void inorderTraversalWithStack(List<T> visited){
        Stack<Node> s = new Stack<Node>();
        Node curr = root;
        while(!s.isEmpty() || curr != null){ // TODO: Remember to ask the students why the second check is important.
            // If you're null, you have to visit your parent. They might be one level above you, they
            // might be many levels above you!
            if(curr == null) {
                curr = s.pop(); // Important: this is why the stack needs to be a stack of nodes, not just Ts!
                visited.add(curr.key);
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
     * @param key The {@link java.lang.Comparable} key to add to the tree.
     */
    public  void insert(T key){
        if(root == null) {
            root = new Node(key);
            count++;
            return;
        }
        boolean left = false, right = false;
        Node curr = root, prev = null;
        while(curr != null){
            prev = curr;
            if(key.compareTo(curr.key) < 0) {
                curr = curr.left;
                left = true;
                right = false;
            } else {
                curr = curr.right;
                left = false;
                right = true;
            }
        }
        if(left)
            prev.left = new Node(key);
        else
            prev.right = new Node(key);
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
            if(curr.key.compareTo(key) == 0)
                return key;
            else if(curr.key.compareTo(key) > 0)
                curr = curr.left;
            else
                curr = curr.right;
        }
        return null;
    }

    /**
     * Deletes <tt>key</tt> from the tree if it's there, otherwise does nothing.
     * @param key The {@link java.lang.Comparable} key to delete from the tree.
     */
    public void delete(T key){
        root = delete(root, key); // call to private method, implemented below.
    }

    // TODO: Once you pass the unit tests for this, do an iterative delete().
    private Node delete(Node curr, T key){
        if(curr == null)
            return null;
        if(curr.key.compareTo(key) > 0)
            curr.left = delete(curr.left, key);
        else if(curr.key.compareTo(key) < 0)
            curr.right =  delete(curr.right, key);
        else { // All actual deletion cases will be implemented here.
            if((curr.right == null) && (curr.left == null)) {// pure leaf;
                curr = null;
                count--;
            }
            else if (curr.right == null){ // Has a left subtree - return that
                curr = curr.left;
                count--;
            }
            else { // Has a right subtree. Swap with inorder successor.
                curr.key = curr.inSucc().key;
                curr.right = delete(curr.right, curr.key);
            }
        }
        return curr;
    }



    /**
     * Queries the BST for emptiness.
     * @return true if, and only if, there are zero keys in the tree.
     */
    public boolean isEmpty(){
        return (count == 0);
    }
}
