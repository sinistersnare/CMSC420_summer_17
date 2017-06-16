package edu.umd.cs.datastructures.examcode.stack;

import java.util.List;

/**<p><tt>Stack</tt> is a simple abstraction over the LIFO Stack ADT. The internal data storage is an
 * undefined {@link List}. It is up to derived classes to specify the implementation of this {@link Stack}</p>.
 * @see StackOne
 * @see StackTwo
 * @author jason
 */
abstract class Stack<T> {

    protected List<T> list;

    /**
     * Pushes an element to the stack.
     * @param data The element to add to the stack.
     */
    public void push(T data){
        list.add(data);
    }

    /**
     * Queries the stack about the element at its top.
     * @return The element at the top of the stack or <tt>null</tt> if the stack is empty.
     */
    public T top(){
        if(isEmpty())
            return null;
        return list.get(list.size() - 1);
    }

    /**
     * Returns <b>and removes</b> the top element.
     * @return The element at the top of the stack or <tt>null</tt> if the element is not there.
     */
    public T pop(){
        if(isEmpty())
            return null;
        T last = list.get(list.size() - 1);
        list.remove(list.size() - 1);
        return last;
    }

    /**
     *  Checks if the Stack is empty.
     *  @return <tt>true</tt> if, and only if, the Stack is empty, <tt>false</tt> otherwise.
     */
    public boolean isEmpty(){
        return list.size() == 0;
    }
}
