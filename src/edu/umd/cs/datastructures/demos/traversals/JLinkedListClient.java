package edu.umd.cs.datastructures.demos.traversals;

import java.util.stream.IntStream;

import static java.lang.System.*;

/**<p><tt>JLinkedListClient</tt> is a timing client for {@link JLinkedList}. It also monitors the throwing of
 * potential stack overflow / Out of memory exceptions.</p>

 * @author jason
 * @since 1.2
 * @see JLinkedList
 */

public class JLinkedListClient {


    private static int ELEMENTS = 100000; // Number of nodes to put in the list.

    /** Driver for the class. Times the three different traversals and reports findings.
     * @param args An array of command-line parameters the user can give.
     */
    public static void main(String[] args){
        JLinkedList l = new JLinkedList();
        IntStream.range(0, ELEMENTS).forEach(i -> l.pushBack());
        System.out.println("--------------------------------- " +
                "\n Timing iterative counting.... \n " +
                "--------------------------- \n");
        long start = currentTimeMillis();
        System.out.println("List getCount retrieved was: " + l.countIter() + ".");
        System.out.println("Iterative counting took " + (currentTimeMillis() - start) + " ms.\n");

        System.out.println("------------------------------------------" +
                "\n Timing custom stack counting.... \n " +
                "------------------------------------------ \n");
        try {
            start = currentTimeMillis();
            System.out.println("List getCount retrieved was: " + l.countStack() + ".");
            System.out.println("Custom stack-based counting took " + (currentTimeMillis() - start)  + " ms. \n");
        } catch(Throwable t){
            System.err.println("While generating the stack-based getCount, we received a " +
                    t.getClass().getSimpleName() + " with message: " + t.getMessage() + ".");
        }

        System.out.println("------------------------------------------ " +
                "\n Timing recursive counting.... \n " +
                "------------------------------------------\n");
        try {
            start = currentTimeMillis();
            System.out.println("List getCount retrieved was: " + l.countRec() + ".");
            System.out.println("System stack-based traversal took " + (currentTimeMillis() - start) + " ms.\n");
        }catch(Throwable t){
            System.err.println("While generating the recursive getCount, we received a " +
                    t.getClass().getSimpleName() + " with message: " + t.getMessage() + ".");
        }



    }

}
