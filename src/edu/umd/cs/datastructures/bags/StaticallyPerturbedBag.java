package edu.umd.cs.datastructures.bags;

import java.util.List;
import java.util.Iterator;

/**
 *
 * A StaticallyPerturbedBag is a bag where shaking perturbs the elements in a pre-defined way. For this example, the way
 * will be (index + offset) MOD length.
 * @implNote The class uses a static array for storing of <b>Item</b>s.
 * @author jason
 * @version 1.0
 */
public class StaticallyPerturbedBag<Item> implements Bag{


    private int current;
    private Item[] storage; // Will have to make a parallel list
    private static int DEFAULT_CAPACITY = 10;
    private static int OFFSET=3;

    public StaticallyPerturbedBag(){
        storage = (Item[])new Object[DEFAULT_CAPACITY]; //TODO: See if this will actually work
        current =0;
    }

    public StaticallyPerturbedBag(int initCapacity){
        storage = (Item[])new Object[initCapacity];
        current = 0;
    }

    // Let's not spend time on equals(), extended hashcode(), copy constructors...

    public void add(Object i) {

    }


    public boolean isEmpty() {
        return false;
    }


    public void shake() {

    }

    /**
     * Returns the number of elements in the bag.
     *
     * @since 1.0
     */
    @Override
    public int size() {
        return storage.size();
    }


    public Iterator iterator() {
        return new Iterator() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Object next() {
                return null;
            }
        }
    }
}
