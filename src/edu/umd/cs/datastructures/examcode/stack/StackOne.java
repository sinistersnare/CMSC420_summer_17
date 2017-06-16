package edu.umd.cs.datastructures.examcode.stack;

import java.util.ArrayList;

/**
 * <p>A {@link Stack} internally implemented with a {@link ArrayList}. {@link #push(Object)} is implemented
 * in <em>amortized constant time</em>. {@link #top()} and {@link #pop()} are implemented in constant time.</p>
 * @author jason
 * @see Stack
 * @see StackOne
*/
public class StackOne<T> extends Stack<T> {

    /**
     * Default constructor initializes the inner data store.
     */
    public StackOne(){
        list = new ArrayList<T>();
    }
}
