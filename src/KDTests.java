import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import utils.KDPoint;

public class KDTests {

	KDTree t;

	@Before
	public void setUp() {
		t = new KDTree();
	}

	@Test
	public void testConstruction() {
		assertEquals(2, t.dimensions);
		assertTrue(t.isEmpty());

		t = new KDTree(123);
		assertEquals(123, t.dimensions);
		assertTrue(t.isEmpty());

		assertEquals(-1, t.height());

	}

	@Test
	public void testInsert() {
		t.insert(new KDPoint(1, 1));
		assertEquals(0, t.height());
		t.insert(new KDPoint(2, 2));
		assertFalse(t.isEmpty());
		assertEquals(1, t.height());
		assertEquals(new KDPoint(1, 1), t.getRoot());
		assertNotNull(t.root.right);
		assertEquals(new KDPoint(2, 2), t.root.right.point);
		t.insert(new KDPoint(0, 0));
		assertNotNull(t.root.left);
		assertEquals(new KDPoint(0, 0), t.root.left.point);
		t.insert(new KDPoint(0, 1));
		assertNotNull(t.root.left.right);
		assertEquals(new KDPoint(0, 1), t.root.left.right.point);
		assertEquals(2, t.height());
		// inserting same element will not change tree
		t.insert(new KDPoint(0, 1));
		assertNull(t.root.left.right.right);

		// inserting element with same value at curDim will push to Right.
		t.insert(new KDPoint(-1, 1));
		assertNotNull(t.root.left.right.right);
	}
}
