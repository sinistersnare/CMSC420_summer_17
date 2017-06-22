package edu.umd.cs.datastructures.projectskeletons.tavl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

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
	public void testLeftRotate() {
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

	@Test
	public void testRightRotate() {
		ThreadedAVLTree<Character> a = new ThreadedAVLTree<>();
		a.insert('c');
		assertEquals(a.getRoot().charValue(), 'c');
		a.insert('b');
		assertEquals(a.getRoot().charValue(), 'c');
		a.insert('a');
		assertEquals(a.getRoot().charValue(), 'b');
		assertEquals(a.root.left.data.charValue(), 'a');
		assertEquals(a.root.right.data.charValue(), 'c');
	}

	@Test
	public void testDoubleLeftLR() {
		ThreadedAVLTree<Character> a = new ThreadedAVLTree<>();
		a.insert('a');
		assertEquals(a.getRoot().charValue(), 'a');
		a.insert('c');
		assertEquals(a.getRoot().charValue(), 'a');
		a.insert('b');
		assertEquals(a.getRoot().charValue(), 'b');
		assertEquals(a.root.left.data.charValue(), 'a');
		assertEquals(a.root.right.data.charValue(), 'c');
	}

	@Test
	public void testDoubleRightRL() {
		ThreadedAVLTree<Character> a = new ThreadedAVLTree<>();
		a.insert('c');
		assertEquals(a.getRoot().charValue(), 'c');
		a.insert('a');
		assertEquals(a.getRoot().charValue(), 'c');
		assertEquals(a.height(), 1);
		a.insert('b');
		assertEquals(a.getRoot().charValue(), 'b');
		assertEquals(a.root.left.data.charValue(), 'a');
		assertEquals(a.root.right.data.charValue(), 'c');
	}

	@Test
	public void testNonRotatingDelete() {
		ThreadedAVLTree<Character> a = new ThreadedAVLTree<>();
		a.insert('a');
		assertFalse(a.isEmpty());
		a.delete('a');
		assertTrue(a.isEmpty());
		a.insert('b');
		a.insert('a');
		a.insert('c');
		assertEquals(a.delete('a').charValue(), 'a');
		assertEquals(a.delete('a'), null);
	}

	@Test
	public void testInorderTraversal() {
		ThreadedAVLTree<Integer> a = new ThreadedAVLTree<>();
		a.insert(50);
		a.insert(25);
		a.insert(30);
		a.insert(10);
		a.insert(20);
		a.insert(40);
		List<Integer> xs = new ArrayList<>();
		Iterator<Integer> it = a.inorderTraversal();
		while (it.hasNext()) {
			int i = it.next();
			xs.add(i);
		}
		assertEquals(xs.size(), 6);
		List<Integer> copy = new ArrayList<>(xs);
		Collections.sort(copy);
		assertEquals(xs, copy);
	}

	@Test(expected = NoSuchElementException.class)
	public void testEmptyInorderTraversal() {
		ThreadedAVLTree<Integer> a = new ThreadedAVLTree<>();
		Iterator<Integer> it = a.inorderTraversal();
		it.next();
	}

	@Test
	public void testNonRotatingCorrectThreading() {
		ThreadedAVLTree<Integer> a = new ThreadedAVLTree<>();
		a.insert(4);
		a.insert(1);
		a.insert(7);
		a.insert(0);
		a.insert(2);
		a.insert(5);
		a.insert(11);
		a.insert(9);
		a.insert(12);

		a.delete(12);
		assertTrue(a.root.right.right.isRightThread);
		a.delete(9);
		assertEquals(a.root.right.right.left.data.intValue(), 7);
		a.delete(2);
		assertEquals(a.root.left.right.data.intValue(), 4);
	}

	@Test
	public void testRotatingCorrectThreading() {
		ThreadedAVLTree<Integer> a = new ThreadedAVLTree<>();
		// Right rotate after deleting 5.
		a.insert(4);
		a.insert(3);
		a.insert(5);
		a.insert(2);
		a.delete(5);
		assertEquals(a.getRoot().intValue(), 3);

		a = new ThreadedAVLTree<>();
		// Left rotate after deleting 2
		a.insert(4);
		a.insert(2);
		a.insert(5);
		a.insert(6);
		a.delete(2);
		assertEquals(a.getRoot().intValue(), 5);
	}

	@Test
	public void testToString() {
		ThreadedAVLTree<Integer> a = new ThreadedAVLTree<>();
		a.insert(50);
		a.insert(25);
		a.insert(75);
		a.insert(10);
		assertEquals(a.toString(),
				"TAVLTree<root=Node<Data={50}, Left={Direct->Node<Data={25}, Left={Direct->Node<Data={10}, Left={Thread->null}, Right={Thread->25}>}, Right={Thread->50}>}, Right={Direct->Node<Data={75}, Left={Thread->50}, Right={Thread->null}>}>>");
		assertEquals(a.root.left.left.toString(), "Node<Data={10}, Left={Thread->null}, Right={Thread->25}>");
		assertEquals(a.root.left.right.toString(),
				"Node<Data={50}, Left={Direct->Node<Data={25}, Left={Direct->Node<Data={10}, Left={Thread->null}, Right={Thread->25}>}, Right={Thread->50}>}, Right={Direct->Node<Data={75}, Left={Thread->50}, Right={Thread->null}>}>");
		assertEquals(a.root.right.toString(), "Node<Data={75}, Left={Thread->50}, Right={Thread->null}>");
	}

	@Test
	public void testInorderPred() {
		ThreadedAVLTree<Integer> a = new ThreadedAVLTree<>();
		a.insert(50);
		a.insert(25);
		a.insert(75);
		a.insert(10);
		a.insert(40);
		a.insert(60);
		a.insert(80);
		a.insert(30);
		a.insert(78);
		assertEquals(a.height(), 3);
		assertEquals(a.root.left.right.left.inorderPred().data.intValue(), 25);
		assertEquals(a.root.left.inorderPred().data.intValue(), 10);
		assertEquals(a.root.right.right.inorderPred().data.intValue(), 78);
		assertEquals(a.root.inorderPred().data.intValue(), 40);
		assertNull(a.root.left.left.inorderPred());
	}

	@Test
	public void testDelete() {
		ThreadedAVLTree<Integer> a = new ThreadedAVLTree<>();
		a.insert(50);
		a.insert(25);
		a.insert(75);
		a.insert(10);
		a.insert(40);
		a.insert(60);
		a.insert(80);
		a.insert(30);
		a.insert(78);
		assertEquals(a.delete(80).intValue(), 80);
		assertEquals(a.root.right.right.data.intValue(), 78);
		assertEquals(a.delete(75).intValue(), 75);
		assertEquals(a.delete(50).intValue(), 50);
		assertEquals(a.root.data.intValue(), 40);
		assertEquals(a.root.right.data.intValue(), 60);
		assertEquals(a.root.right.right.data.intValue(), 78);

		a = new ThreadedAVLTree<>();
		a.insert(50);
		a.insert(25);
		a.insert(75);
		a.insert(10);
		a.insert(40);
		a.insert(60);
		a.insert(80);
		a.insert(30);
		a.insert(78);
		assertEquals(a.delete(30).intValue(), 30);
		assertEquals(a.delete(40).intValue(), 40);
		assertEquals(a.delete(10).intValue(), 10);
		assertEquals(a.root.data.intValue(), 75);
	}

	@Test
	public void testLookup() {
		ThreadedAVLTree<Integer> a = new ThreadedAVLTree<>();
		assertNull(a.lookup(12));
		a.insert(50);
		a.insert(25);
		a.insert(75);
		a.insert(10);
		a.insert(40);
		a.insert(60);
		a.insert(80);
		a.insert(30);
		a.insert(78);
		assertNull(a.lookup(82));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNoNullKeys() throws IllegalArgumentException {
		ThreadedAVLTree<Integer> a = new ThreadedAVLTree<>();
		a.insert(null);
	}

	@Test
	public void testInsertSameKeyDoesNoOp() {
		ThreadedAVLTree<Integer> a = new ThreadedAVLTree<>();
		assertEquals(a.height(), -1);
		a.insert(12);
		assertEquals(a.height(), 0);
		a.insert(12);
		assertEquals(a.height(), 0);
		a.insert(12);
		assertEquals(a.height(), 0);
	}

	@Test
	public void testManyInorders() {
		ThreadedAVLTree<Integer> a = new ThreadedAVLTree<>();
		int howMany = 0;
		for (int i = 9_999; i >= 0; i--) {
			int n = i;
			if (a.lookup(n) == null) {
				a.insert(n);
				howMany++;
			}
		}
		List<Integer> xs = new ArrayList<>();
		Iterator<Integer> it = a.inorderTraversal();
		while (it.hasNext()) {
			int i = it.next();
			xs.add(i);
		}
		assertEquals(xs.size(), howMany);
		List<Integer> copy = new ArrayList<>(xs);
		Collections.sort(copy);
		assertEquals(xs, copy);
		assertEquals(xs.get(0).intValue(), 0);
		for (int i = 0; i < 10_000; i++) {
			assertEquals(xs.remove(0).intValue(), i);
		}
	}

	@Test
	public void testEvenInorders() {
		ThreadedAVLTree<Integer> a = new ThreadedAVLTree<>();
		// inserting 5001 elements.
		for (int n = 10_000; n >= 0; n -= 2) {
			a.insert(n);
		}
		List<Integer> xs = new ArrayList<>();
		Iterator<Integer> it = a.inorderTraversal();
		while (it.hasNext()) {
			int i = it.next();
			xs.add(i);
		}
		assertEquals(xs.size(), 5001);
		List<Integer> copy = new ArrayList<>(xs);
		Collections.sort(copy);
		assertEquals(xs, copy);
		assertEquals(xs.get(0).intValue(), 0);
		for (int i = 0; i < 5001; i++) {
			assertEquals(xs.remove(0).intValue(), i*2);
		}
	}
}
