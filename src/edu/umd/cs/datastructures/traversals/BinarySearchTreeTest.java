package edu.umd.cs.datastructures.traversals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

/** A jUnit4 (not Arquillian) testing framework for {@link BinarySearchTree}s. All <tt>public</tt> methods
 * will be tested.
 * @author Jason
 * @see BSTClient
 * @see BinarySearchTree
 */
public class BinarySearchTreeTest {

    private BinarySearchTree<Integer> tree;
    private Random r;
    private static int NUM_INTS = 1000;
    private IntStream ints;

    @Before
    public void setUp() throws Exception {
        tree = new BinarySearchTree<Integer>();
        ints = IntStream.range(0, NUM_INTS);
        r = new Random();
    }

    @After
    public void tearDown() throws Exception {
        tree = null;
        ints = null;
        r = null; // TODO: How feasible is it for a weakly compiled language to be able to do tree = ints = r = null? Compiler should intuitively not complain. Recall: Python is weakly typed.
        System.gc(); // HAHA THIS WILL ACTUALLY DO SOMETHING
    }

    @Test
    public void inorderTraversalRec() throws Exception {
        List<Integer> intList = ints.boxed().collect(Collectors.toList());
        List<Integer> copy = new LinkedList<Integer>(intList);
        Collections.shuffle(copy);
        copy.forEach(tree::insert);

        LinkedList<Integer> visited = new LinkedList<Integer>();
        tree.inorderTraversalRec(visited);

        ListIterator<Integer> intIt = intList.listIterator(), visitedIt = visited.listIterator();

        while(intIt.hasNext() && visitedIt.hasNext())
            assertTrue(intIt.next().equals(visitedIt.next()));
        assertTrue(!intIt.hasNext() && !visitedIt.hasNext());

    }

    @Test
    public void inorderTraversalWithStack() throws Exception {

        List<Integer> intList = ints.boxed().collect(Collectors.toList());
        List<Integer> copy = new LinkedList<Integer>(intList);
        Collections.shuffle(copy);
        copy.forEach(tree::insert);

        LinkedList<Integer> visited = new LinkedList<Integer>();
        tree.inorderTraversalWithStack(visited);

        ListIterator<Integer> intIt = intList.listIterator(), visitedIt = visited.listIterator();

        while(intIt.hasNext() && visitedIt.hasNext())
            assertTrue(intIt.next().equals(visitedIt.next()));
        assertTrue(!intIt.hasNext() && !visitedIt.hasNext());
    }

    @Test
    public void insert() throws Exception {

        // Some easy stuff first
        tree.insert(100);
        assertEquals(1, tree.getCount());
        IntStream.range(0, 100).filter(i -> i%2 ==1).forEach(tree::insert); // 50 odds in {0, 1, 2, ..., 99}
        assertEquals(51, tree.getCount());

        // Now pummel it
        try {
            r.ints(0, 5000).limit(NUM_INTS).forEach(tree::insert);
        } catch(Exception e){
            fail("Insertion tests received an exception " + e.getClass().getSimpleName() + " with message: " + e.getMessage() + ".");
        }
    }

    @Test
    public void getCount() throws Exception {
        assertTrue(tree.getCount() == 0);
        assertFalse(tree.getCount() != 0);
    }

}