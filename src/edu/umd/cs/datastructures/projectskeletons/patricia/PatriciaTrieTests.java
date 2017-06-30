package edu.umd.cs.datastructures.projectskeletons.patricia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PatriciaTrieTests {

	BinaryPatriciaTrie bpt;

	@Before
	public void before() {
		bpt = new BinaryPatriciaTrie();
	}

	@Test
	public void testConstructor() {
		assertEquals(bpt.getSize(), 0);
	}

	@Test
	public void testInsert() {
		bpt.insert("0");
		bpt.insert("1");
		bpt.insert("01");
		bpt.insert("0110");
		bpt.insert("011");
		assertEquals(bpt.getSize(), 5);
		bpt = new BinaryPatriciaTrie();
		bpt.insert("0110");
		bpt.insert("01");
		assertEquals(bpt.getSize(), 2);
		bpt = new BinaryPatriciaTrie();
		bpt.insert("01");
		bpt.insert("0110");
		assertEquals(bpt.getSize(), 2);
		bpt = new BinaryPatriciaTrie();
		bpt.insert("01");
		bpt.insert("0100");
		bpt.insert("0111");
		assertEquals(bpt.getSize(), 3);
		bpt.insert("0110");
		assertEquals(bpt.getSize(), 4);
		bpt = new BinaryPatriciaTrie();
		bpt.insert("0110");
		bpt.insert("011000");
		bpt.insert("011011");
		assertEquals(bpt.getSize(), 3);
		bpt.insert("01");
		assertEquals(bpt.getSize(), 4);

		bpt.clear();
		bpt.insert("01");
		bpt.insert("0100");
		bpt.insert("0111");
		assertEquals(bpt.root.left.value, "01");
		assertTrue(bpt.root.left.isEnd);
		assertEquals(bpt.root.left.left.value, "00");
		assertTrue(bpt.root.left.left.isEnd);
		assertEquals(bpt.root.left.right.value, "11");
		assertTrue(bpt.root.left.right.isEnd);

		bpt.clear();
		bpt.insert("0110");
		bpt.insert("011000");
		bpt.insert("011011");
		bpt.insert("01");
		assertEquals(bpt.root.left.value, "01");
		assertTrue(bpt.root.left.isEnd);
		assertEquals(bpt.root.left.right.value, "10");
		assertTrue(bpt.root.left.right.isEnd);
		assertEquals(bpt.root.left.right.left.value, "00");
		assertTrue(bpt.root.left.right.left.isEnd);
		assertEquals(bpt.root.left.right.right.value, "11");
		assertTrue(bpt.root.left.right.right.isEnd);

		bpt.clear();
		assertTrue(bpt.insert("10"));
		assertFalse(bpt.insert("10"));

		// there is an inner node that is not marked as end
		// and when we add it, it is properly marked as in the trie.
		bpt.clear();
		assertTrue(bpt.insert("0110"));
		assertTrue(bpt.insert("0100"));
		assertTrue(bpt.insert("01"));
		// Do the same with the 1 side
		bpt.clear();
		assertTrue(bpt.insert("1001"));
		assertTrue(bpt.insert("1011"));
		assertTrue(bpt.insert("10"));

		bpt.clear();
		bpt.insert("0000");
		bpt.insert("00");
		assertEquals(bpt.getSize(), 2);
		bpt.clear();
		bpt.insert("1111");
		bpt.insert("11");
		assertEquals(bpt.getSize(), 2);

		bpt.clear();
		assertTrue(bpt.insert("0100"));
		assertTrue(bpt.insert("0110"));
		assertTrue(bpt.insert("01"));
	}

	@Test
	public void testSearch() {
		bpt.insert("01");
		bpt.insert("00");
		assertFalse(bpt.search("0"));
		assertTrue(bpt.search("01"));
		assertTrue(bpt.search("00"));
		bpt.clear();
		bpt.insert("10");
		bpt.insert("11");
		assertFalse(bpt.search("1"));
		assertTrue(bpt.search("10"));
		assertTrue(bpt.search("11"));
	}

	@Test
	public void testManyInsertsCreatesCorrectStructure() {
		bpt.insert("0110");
		bpt.insert("0101");
		bpt.insert("010100");
		bpt.insert("010111");
		bpt.insert("01100");
		bpt.insert("01101");
		assertEquals(bpt.getSize(), 6);
		bpt.insert("1010");
		bpt.insert("100100");
		bpt.insert("100111");
		bpt.insert("10100");
		bpt.insert("10101");
		assertEquals(bpt.getSize(), 11);
		// left half of tree
		assertEquals(bpt.root.left.value, "01");
		assertFalse(bpt.root.left.isEnd);
		assertEquals(bpt.root.left.left.value, "01");
		assertTrue(bpt.root.left.left.isEnd);
		assertEquals(bpt.root.left.left.left.value, "00");
		assertTrue(bpt.root.left.left.left.isEnd);
		assertEquals(bpt.root.left.left.right.value, "11");
		assertTrue(bpt.root.left.left.right.isEnd);
		assertEquals(bpt.root.left.right.value, "10");
		assertTrue(bpt.root.left.right.isEnd);
		assertEquals(bpt.root.left.right.left.value, "0");
		assertTrue(bpt.root.left.right.left.isEnd);
		assertEquals(bpt.root.left.right.right.value, "1");
		assertTrue(bpt.root.left.right.right.isEnd);
		// right half of tree
		assertEquals(bpt.root.right.value, "10");
		assertFalse(bpt.root.right.isEnd);
		assertEquals(bpt.root.right.left.value, "01");
		assertFalse(bpt.root.right.left.isEnd);
		assertEquals(bpt.root.right.right.value, "10");
		assertTrue(bpt.root.right.right.isEnd);
		assertEquals(bpt.root.right.left.left.value, "00");
		assertTrue(bpt.root.right.left.left.isEnd);
		assertEquals(bpt.root.right.left.right.value, "11");
		assertTrue(bpt.root.right.left.right.isEnd);
		assertEquals(bpt.root.right.right.left.value, "0");
		assertTrue(bpt.root.right.right.left.isEnd);
		assertEquals(bpt.root.right.right.right.value, "1");
		assertTrue(bpt.root.right.right.right.isEnd);

		// test leaves point to null...
		assertNull(bpt.root.left.left.left.left); // leftmost node
		assertNull(bpt.root.left.left.left.right);
		assertNull(bpt.root.left.left.right.left);
		assertNull(bpt.root.left.left.right.right);
		assertNull(bpt.root.left.right.left.left);
		assertNull(bpt.root.left.right.left.right);
		assertNull(bpt.root.left.right.right.left);
		assertNull(bpt.root.left.right.right.right);
		// right half
		assertNull(bpt.root.right.left.left.left);
		assertNull(bpt.root.right.left.left.right);
		assertNull(bpt.root.right.left.right.left);
		assertNull(bpt.root.right.left.right.right);
		assertNull(bpt.root.right.right.left.left);
		assertNull(bpt.root.right.right.left.right);
		assertNull(bpt.root.right.right.right.left);
		assertNull(bpt.root.right.right.right.right);// rightmost node
	}

	@Test(expected = RuntimeException.class)
	public void testSearchThrowsOnNonBinaryString() {
		bpt.search("abc");
	}

	// construct tree from testManyInsertsCreatesCorrectStructure
	@Test
	public void testDelete() {
		bpt.insert("0110");
		bpt.insert("0101");
		bpt.insert("010100");
		bpt.insert("010111");
		bpt.insert("01100");
		bpt.insert("01101");
		bpt.insert("1010");
		bpt.insert("100100");
		bpt.insert("100111");
		bpt.insert("10100");
		bpt.insert("10101");
		assertEquals(bpt.getSize(), 11);

		// delete rightmost node, should not change structure of tree
		assertTrue(bpt.delete("10101"));
		assertEquals(bpt.getSize(), 10);
		assertEquals(bpt.root.right.value, "10");
		assertEquals(bpt.root.right.right.value, "10");
		assertNull(bpt.root.right.right.right);
		assertEquals(bpt.root.right.right.left.value, "0");
		// delete the new rightmost (right.right) node,
		// compressing its left child into it.
		assertTrue(bpt.delete("1010"));
		assertEquals(bpt.getSize(), 9);
		assertEquals(bpt.root.right.value, "10");
		assertEquals(bpt.root.right.right.value, "100");
		assertNull(bpt.root.right.right.right);
		assertNull(bpt.root.right.right.left);

		// now to absorb a right child, delete from left-most node.
		assertTrue(bpt.delete("010100"));
		assertEquals(bpt.getSize(), 8);
		assertEquals(bpt.root.left.value, "01");
		assertEquals(bpt.root.left.left.value, "01");
		assertNull(bpt.root.left.left.left);
		assertEquals(bpt.root.left.left.right.value, "11");
		// now delete the new leftmost (left.left), to absorb
		assertTrue(bpt.delete("0101"));
		assertEquals(bpt.getSize(), 7);
		assertEquals(bpt.root.left.value, "01");
		assertEquals(bpt.root.left.left.value, "0111");
		assertNull(bpt.root.left.left.left);
		assertNull(bpt.root.left.left.right);

		assertFalse(bpt.delete("000000000000000"));

	}

	@Test
	public void testIsEmpty() {

	}

	@Test
	public void testGetSize() {

	}

	@Test
	public void testInorderTraversal() {
		List<String> elems = new ArrayList<>();
		assertEquals(bpt.getLongest(), "");
		bpt.insert("00000000000001");
		bpt.insert("11");
		Iterator<String> it = bpt.inorderTraversal();
		it.forEachRemaining(elems::add);
		assertEquals(elems.size(), 2);
		assertEquals(elems.get(0), "00000000000001");
		assertEquals(elems.get(1), "11");

		elems.clear();
		bpt.clear();
		bpt.insert("0110");
		bpt.insert("0101");
		bpt.insert("010100");
		bpt.insert("010111");
		bpt.insert("01100");
		bpt.insert("01101");
		bpt.insert("1010");
		bpt.insert("100100");
		bpt.insert("100111");
		bpt.insert("10100");
		bpt.insert("10101");
		it = bpt.inorderTraversal();
		it.forEachRemaining(elems::add);
		List<String> toCheck = new ArrayList<>();
		Collections.addAll(toCheck, "010100", "0101", "010111", "01100", "0110", "01101", "100100", "100111", "10100",
				"1010", "10101");
		assertEquals(elems.size(), 11);
		assertEquals(toCheck, elems);

	}

	@Test
	public void testGetLongest() {

	}

}
