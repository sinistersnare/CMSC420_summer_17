package edu.umd.cs.datastructures.projectskeletons;

import static org.junit.Assert.*;

import org.junit.Test;

public class AVLTests {

	@Test
	public void testConstructor() {
		ThreadedAVLTree<Integer> a = new ThreadedAVLTree<>();
		assertNotNull(a);
		assertNull(a.getRoot());
	}

	@Test
	public void testHeight() {
		ThreadedAVLTree<Integer> a = new ThreadedAVLTree<>();
		assertEquals(a.height(), -1);
		a.insert(12);
		assertEquals(a.height(), 0);
		a.insert(19);
		assertEquals(a.height(), 1);
		// TODO test height after a rotation.

		a = new ThreadedAVLTree<>();
		a.insert(10);
		a.insert(20);
		a.insert(5);
		a.insert(2);
		a.insert(25);
		assertEquals(a.height(), 2);
	}

	@Test
	public void testGetRoot() {
		ThreadedAVLTree<Integer> a = new ThreadedAVLTree<>();
		a.insert(12);
		assertEquals(a.getRoot(), new Integer(12));
		a.insert(13);
		assertEquals(a.getRoot(), new Integer(12));
		a.insert(11);
		assertEquals(a.getRoot(), new Integer(12));
		// TODO test getRoot after a rotation.
	}

	@Test
	public void testRightRotate() {
		ThreadedAVLTree<Character> a = new ThreadedAVLTree<>();
		a.insert('a');
		assertEquals(a.getRoot().charValue(), 'a');
		a.insert('b');
		assertEquals(a.getRoot().charValue(), 'a');
		a.insert('c');
		assertEquals(a.getRoot().charValue(), 'b');
		assertEquals(a.root.left.data.charValue(), 'a');
		assertEquals(a.root.right.data.charValue(), 'c');
	}
}
