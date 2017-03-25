package edu.umd.cs.datastructures.bags;

import java.util.Iterator;
import java.util.Random;

/**
 * A DynamicallyShuffledBag is a {@link Bag} which, very much like a {@link RandomAccessBag}, shakes its contents
 * completely (pseudo-) randomly. However, it does so by storing the elements in their new order, instead of
 * indexing into the old container with a permuted index set.
 * Created by jason on 3/22/17.
 */
public class DynamicallyShuffledBag<Item> implements Bag<Item>{

    private Random r;
    private int DEFAULT_CAPACITY = 100;

    /**
     * This constructor is used to
     */
    public DynamicallyShuffledBag() {

    }

    public DynamicallyShuffledBag
    /**
     * Adds an <b>Item</b> to the bag.
     *
     * @param i The <b>Item</b> to add to the Bag.
     * @since 1.0
     */
    @Override
    public void add(Item i) {

    }

    /**
     * Returns true if there are no elements in the bag.
     *
     * @return True if and only if the Bag is empty, False otherwise.
     * @since 1.0
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * "Shakes" the bag, randomly perturbing the order of its elements.
     *
     * @since 1.0
     */
    @Override
    public void shake() {

    }

    /**
     * Returns the number of elements in the bag.
     *
     * @since 1.0
     */
    @Override
    public int size() {
        return 0;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Item> iterator() {
        return null;
    }
}
