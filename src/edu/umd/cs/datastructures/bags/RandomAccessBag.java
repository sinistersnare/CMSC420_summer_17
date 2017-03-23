package edu.umd.cs.datastructures.bags;

import java.util.Iterator;

/**
 * Created by jason on 3/22/17.
 */
public class RandomAccessBag<Item> implements Bag<Item>{
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
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Item> iterator() {
        return null;
    }
}
