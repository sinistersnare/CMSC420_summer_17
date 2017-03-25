package edu.umd.cs.datastructures.bags;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * A StaticallyPerturbedBag is a bag where shaking perturbs the elements in a pre-defined way. For this example, the way
 * will be (index + offset) MOD length.
 * @implNote The class uses a static array for storing of <b>Item</b>s.
 * @author jason
 * @version 1.0
 */
public class StaticallyPerturbedBag<Item> implements Bag{


    private int current;
    private Item[] storage;
    private static int DEFAULT_CAPACITY = 10;
    private static int OFFSET=3; // Modular

    public StaticallyPerturbedBag(){
        /* TODO: See if this will actually work or will through a CLassCastException or whatnot. Would happen for sure for, say, an Iterable or
        something else that extends or implements a class since Object obiously won't.
         */
        storage = (Item[])new Object[DEFAULT_CAPACITY];
        current =-1;
    }

    public StaticallyPerturbedBag(int initCapacity){
        storage = (Item[])new Object[initCapacity];
        current = -1;
    }

    // Let's not spend time on equals(), extended hashcode(), copy constructors...

    public void add(Object o) {
        if(current == capacity())
            expand();
        storage[++current] = (Item)o;
    }

    private void expand() {
        int currCap = capacity();
        Item[] newArr = (Item[])new Object[2*currCap];
        for(int i = 0; i < currCap; i ++)
            newArr[i] = storage[i];
        storage = newArr; // current already points where we want it to
        // System.gc(); // TODO: see if the implementation is burdened too much (does a Thread cover this?)
    }

    private int capacity(){
        return storage.length;
    }


    public boolean isEmpty() {
        return current == -1;
    }


    /* And here's where the meat of it all is. This particular bag will take any element and move it from position i
        to position (i+OFFSET)%Length. This shouldn't cause many cash misses for the new array, except for those times
         where we need to wrap around the last cell in the new array. Eh, can't do much better than this.
     */
    public void shake() {
        int sz = size();
        Item[] newStorage = (Item[])(new Object[sz]);
        for(int i = 0; i < sz; i++)
            newStorage[(i+OFFSET)%sz] = storage[i];
        storage = newStorage;
        // System.gc();
    }

    /**
     * Returns the number of elements in the bag.
     *
     * @since 1.0
     */
    @Override
    public int size() {
        return current+1;
    }


    public Iterator iterator() {
        return new Iterator() {

            private int index = -1;
            private int initSize = size();
            @Override
            public boolean hasNext() {
                return index < current;
            }

            @Override
            public Object next() {
                if(size() != initSize)
                    throw new ConcurrentModificationException("StaticallyPerturbedBag was mutated between calls to iterator().next().");
                return storage[++index];
            }
        };
    }
}
