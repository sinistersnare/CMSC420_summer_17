

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

public class BPQTests {

	BoundedPriorityQueue<Integer> b;

	@Before
	public void setUp() {
		b = new BoundedPriorityQueue<>(5);
	}

	@Test
	public void testEnqueue() {
		b.enqueue(3, 1);
		assertEquals(1, b.size());
		assertEquals(3, b.queue[0].elem.intValue());
		b.enqueue(4, 2);
		assertEquals(2, b.size());
		assertEquals(4, b.queue[1].elem.intValue());
		b.enqueue(5, 0);
		assertEquals(3, b.size());
		assertEquals(5, b.queue[0].elem.intValue());
		assertEquals(3, b.queue[1].elem.intValue());
		assertEquals(4, b.queue[2].elem.intValue());
		b.enqueue(6, 0);
		assertEquals(5, b.queue[0].elem.intValue());
		assertEquals(6, b.queue[1].elem.intValue());
		assertEquals(3, b.queue[2].elem.intValue());
		assertEquals(4, b.queue[3].elem.intValue());
		b.enqueue(7, 5);
		assertEquals(5, b.queue[0].elem.intValue());
		// inserting past bounded size will not add to array
		b.enqueue(10, 999);
		assertEquals(5, b.queue[0].elem.intValue());
		assertEquals(6, b.queue[1].elem.intValue());
		assertEquals(3, b.queue[2].elem.intValue());
		assertEquals(4, b.queue[3].elem.intValue());
		assertEquals(7, b.queue[4].elem.intValue());
		// unless it has a lower priority than at least one of the elements...
		b.enqueue(10, 3);
		assertEquals(5, b.queue[0].elem.intValue());
		assertEquals(6, b.queue[1].elem.intValue());
		assertEquals(3, b.queue[2].elem.intValue());
		assertEquals(4, b.queue[3].elem.intValue());
		// booted out 7 with an element with lower priority...
		assertEquals(10, b.queue[4].elem.intValue());
		// now inserting super low prio will push everything over, ejecting 10.
		b.enqueue(13, -1);
		assertEquals(13, b.queue[0].elem.intValue());
		assertEquals(5, b.queue[1].elem.intValue());
		assertEquals(6, b.queue[2].elem.intValue());
		assertEquals(3, b.queue[3].elem.intValue());
		assertEquals(4, b.queue[4].elem.intValue());
	}

	@Test
	public void testDequeue() {
		assertNull(b.dequeue());
		b.enqueue(1, 1);
		b.enqueue(2, 2);
		b.enqueue(3, 3);

		assertEquals(1, b.dequeue().intValue());
		b.enqueue(0, 0);
		assertEquals(0, b.dequeue().intValue());
		assertEquals(2, b.dequeue().intValue());
		assertEquals(3, b.dequeue().intValue());
		assertNull(b.dequeue());
	}

	@Test
	public void testFirst() {
		assertNull(b.first());
		b.enqueue(1, 1);
		b.enqueue(2, 2);
		b.enqueue(3, 3);
		assertEquals(1, b.first().intValue());
		assertEquals(1, b.dequeue().intValue());
		assertEquals(2, b.first().intValue());
		assertEquals(2, b.dequeue().intValue());
		assertEquals(3, b.first().intValue());
		assertEquals(3, b.dequeue().intValue());
		assertNull(b.first());
	}

	@Test
	public void testLast() {
		assertNull(b.last());
		b.enqueue(1, 1);
		b.enqueue(2, 2);
		b.enqueue(3, 3);
		assertEquals(3, b.last().intValue());
		assertEquals(1, b.dequeue().intValue());
		assertEquals(3, b.last().intValue());
		assertEquals(2, b.dequeue().intValue());
		assertEquals(3, b.last().intValue());
		assertEquals(3, b.dequeue().intValue());
		assertNull(b.last());
	}

	@Test
	public void testIsEmpty() {
		assertTrue(b.isEmpty());
		b.enqueue(1, 1);
		assertFalse(b.isEmpty());
		b.dequeue();
		assertTrue(b.isEmpty());
	}

	@Test
	public void testIteratorBasic() {
		b.enqueue(0, 0);
		b.enqueue(1, 1);
		b.enqueue(2, 2);
		b.enqueue(3, 3);
		Iterator<Integer> it = b.iterator();
		assertTrue(it.hasNext());
		assertEquals(0, it.next().intValue());
		assertTrue(it.hasNext());
		assertEquals(1, it.next().intValue());
		assertTrue(it.hasNext());
		assertEquals(2, it.next().intValue());
		assertTrue(it.hasNext());
		assertEquals(3, it.next().intValue());
		assertFalse(it.hasNext());
	}

	@Test
	public void testIteratorRemove() {
		b.enqueue(0, 0);
		b.enqueue(1, 1);
		b.enqueue(2, 2);
		b.enqueue(3, 3);
		Iterator<Integer> it = b.iterator();
		assertTrue(it.hasNext());
		assertEquals(0, it.next().intValue());
		it.remove();
		assertEquals(3, b.size());
	}

	@Test
	public void testIteratorThrowsConcModExceptionAfterEnqueue() {
		b.enqueue(0, 0);
		b.enqueue(1, 1);
		b.enqueue(2, 2);
		b.enqueue(3, 3);
		Iterator<Integer> it = b.iterator();
		assertTrue(it.hasNext());
		assertEquals(0, it.next().intValue());
		b.enqueue(1, 1);
		try {
			it.next();
			assertTrue("should throw concurrent modification excpetion here...", false);
		} catch (ConcurrentModificationException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testIteratorThrowsConcModExceptionAfterDequeue() {
		b.enqueue(0, 0);
		b.enqueue(1, 1);
		b.enqueue(2, 2);
		b.enqueue(3, 3);
		Iterator<Integer> it = b.iterator();
		assertTrue(it.hasNext());
		assertEquals(0, it.next().intValue());
		assertEquals(0, b.dequeue().intValue());
		try {
			it.next();
			assertTrue("should throw concurrent modification excpetion here...", false);
		} catch (ConcurrentModificationException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testIteratorRemoveAll() {
		b.enqueue(0, 0);
		b.enqueue(1, 1);
		b.enqueue(2, 2);
		b.enqueue(3, 3);
		assertEquals(4, b.size());
		Iterator<Integer> it = b.iterator();
		assertTrue(it.hasNext());
		assertEquals(0, it.next().intValue());
		it.remove();
		assertTrue(it.hasNext());
		assertEquals(1, it.next().intValue());
		it.remove();
		assertTrue(it.hasNext());
		assertEquals(2, it.next().intValue());
		it.remove();
		assertTrue(it.hasNext());
		assertEquals(3, it.next().intValue());
		it.remove();
		assertFalse(it.hasNext());
		assertEquals(0, b.size());
	}
	
	@Test
	public void testITeratorRemoveThrowsOnIllegalState() {
		b.enqueue(0, 0);
		Iterator<Integer> it = b.iterator();
		assertTrue(it.hasNext());
		try {
			// must call next before calling remove...
			it.remove();
			assertTrue(false);
		} catch (IllegalStateException e) {
			assertTrue(true);
		}
	}
}
