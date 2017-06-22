package edu.umd.cs.datastructures.examcode.stack;

import java.util.LinkedList;

/**
 * <p>A {@link Stack} internally implemented with a {@link LinkedList}. If a Double-Linked List implementation
 * can be assumed for {@link LinkedList}, all public operations of the Stack are implemented in constant time.</p>
 * @author jason
 * @see Stack
 * @see StackOne
 */
public class StackTwo<T> extends Stack<T> {

    /**
     * Default constructor initializes the inner data store.
     */
    public StackTwo(){
        list = new LinkedList<>();
    }
}
