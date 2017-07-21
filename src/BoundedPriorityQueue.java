import java.lang.reflect.Array;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.StringJoiner;

/**
 * <p>
 * <tt>BoundedPriorityQueue</tt> is a Priority Queue whose number of elements is
 * bounded. Insertions are such that if the queue's provided capacity is
 * surpassed, its length is not expanded, but rather the maximum priority
 * element is ejected (which could be the element just attempted to be
 * enqueued). Note that <tt>BoundedPriorityQueue</tt>s are {@link Iterable}s.
 * </p>
 * 
 * @author ------ DAVIS SILVERMAN! --------
 *
 * @param <T>
 *            An {@link Object} that will be held by <tt>this</tt>. Note that
 *            <tt>T</tt> is not declared as {@link Comparable}: its priority is
 *            supplied on the fly by the enqueueing method.
 */
public class BoundedPriorityQueue<T> implements Iterable<T> {

	class Pair {
		T elem;
		double priority;

		public Pair(T elem, double priority) {
			this.elem = elem;
			this.priority = priority;
		}

		@Override
		public String toString() {
			return String.format("Pair<elem: %s, priority: %s>", elem.toString(), priority);
		}
	}

	Pair[] queue;
	private int size;
	int modCount;

	/**
	 * Constructor that specifies the size of our queue.
	 * 
	 * @param size
	 *            The static size of the <tt>BoundedPriorityQueue</tt>. Has to
	 *            be a positive integer.
	 * @throws RuntimeException
	 *             if <tt>size</tt> is not positive.
	 */
	@SuppressWarnings("unchecked")
	public BoundedPriorityQueue(int size) {
		this.queue = (Pair[]) Array.newInstance(Pair.class, size);
	}

	/**
	 * Enqueueing elements for<tt> BoundedPriorityQueue</tt>s works a little bit
	 * differently from general case PriorityQueue objects. If the queue is not
	 * at capacity, the <tt>element</tt> is inserted at its appropriate location
	 * in the sequence. On the other hand, if the object is at capacity, the
	 * element is inserted in its appropriate spot in the sequence (if such a
	 * spot exists, based on its <tt>priority</tt>) and the maximum priority
	 * element is ejected from the structure.
	 * 
	 * @param element
	 *            The element to insert in the queue.
	 * @param priority
	 *            The priority of the element to insert in the queue.
	 * @see #dequeue()
	 */
	public void enqueue(T element, double priority) {
		Pair cur = new Pair(element, priority);
		boolean first = true;

		for (int i = 0; i < this.queue.length && cur != null; i++) {
			if (first) {
				if (this.queue[i] == null || cur.priority < this.queue[i].priority) {
					Pair old = this.queue[i];
					this.queue[i] = cur;
					cur = old;
					// no longer first element,
					// so to preserve FIFO, use <= from now on.
					first = false;
				}
			} else {
				// use <= when not first element to preserve FIFO order.
				if (this.queue[i] == null || cur.priority <= this.queue[i].priority) {
					Pair old = this.queue[i];
					this.queue[i] = cur;
					cur = old;
				}
			}
		}
		this.size = Math.min(this.size + 1, this.queue.length);
		this.modCount++;
	}

	/**
	 * Remove and return the minimum priority (top) element from the queue.
	 * 
	 * @return The minimum priority element from the queue.
	 * @see #first()
	 * @see #enqueue(Object, double)
	 */
	public T dequeue() {
		if (this.size == 0) {
			return null;
		}
		Pair ret = this.queue[0];

		for (int i = 0; i < this.size - 1; i++) {
			this.queue[i] = this.queue[i + 1];
		}
		// overwrite last element to null.
		this.queue[this.size - 1] = null;
		this.size--;
		this.modCount++;
		return ret.elem;
	}

	/**
	 * Return, <b>without removing it from the queue</b>, the minimum priority
	 * (top) element of the queue.
	 * 
	 * @return The minimum priority element of the queue.
	 * @see #last()
	 * @see #dequeue()
	 */
	public T first() {
		if (this.size == 0) {
			return null;
		}
		return this.queue[0].elem;
	}

	/**
	 * Returns the last element in the queue. Useful for cases where we want to
	 * compare the priorities of a given quantity with the maximum priority of
	 * our stored quantities. In a linked minheap-based implementation of any
	 * PriorityQueue, this operation would scan <em>O(n)</em> nodes and
	 * <em>O(nlogn)</em> links. In an array-based minheap, it takes constant
	 * time.
	 * 
	 * @return The maximum priority element in our queue, or <tt>null</tt> if
	 *         the queue is empty.
	 */
	public T last() {
		if (this.size == 0) {
			return null;
		}
		return this.queue[this.size - 1].elem;
	}

	/**
	 * Return the queue's size, as measured by the amount of elements stored in
	 * it.
	 * 
	 * @return The number of elements in the queue.
	 */
	public int size() {
		return this.size;
	}

	/**
	 * Query the queue for emptiness.
	 * 
	 * @return <tt>true</tt> if and only if there are <b>no</b> elements in the
	 *         queue, <tt>false</tt> otherwise.
	 */
	public boolean isEmpty() {
		return this.size == 0;
	}

	/**
	 * Returns a fail-fast {@link Iterator} over the queue's elements. The
	 * Iterator exposes the elements in linear order according to the ordering
	 * imposed by {@linkplain utils.KNNComparator}.
	 * 
	 * @return An {@link Iterator} over the queue's elements.
	 */
	@Override
	public Iterator<T> iterator() {
		return new BPQIterator(this);
	}

	class BPQIterator implements Iterator<T> {
		int expectedModCount;
		BoundedPriorityQueue<T> inst;
		int cur;

		BPQIterator(BoundedPriorityQueue<T> inst) {
			this.inst = inst;
			this.expectedModCount = inst.size;
			this.cur = -1;
		}

		@Override
		public boolean hasNext() {
			return cur + 1 < inst.size;
		}

		@Override
		public T next() {
			if (this.expectedModCount != this.inst.modCount) {
				throw new ConcurrentModificationException();
			}
			this.cur++;
			return this.inst.queue[this.cur].elem;
		}

		@Override
		public void remove() {
			this.inst.queue[this.cur] = null;

			for (int i = this.cur; i < this.inst.size - 1; i++) {
				this.inst.queue[i] = this.inst.queue[i + 1];
			}
			// overwrite last element to null.
			this.inst.queue[this.inst.size - 1] = null;
			this.inst.size--;
			this.cur--;
		}
	}

	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(",", "[", "]");
		for (Pair p : this.queue) {
			if (p != null) {
				sj.add(p.toString());
			}
		}
		return sj.toString();
	}
}
