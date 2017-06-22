package edu.umd.cs.datastructures.examcode.matrix;
import java.util.ArrayList;
import java.util.LinkedList;

/**<p> <tt>SquareMatrixTwo</tt> is an implementation of a 2D matrix of <tt>double</tt>s based on
 * a {@link LinkedList} of {@link ArrayList}s. Assuming that {@link LinkedList} is implemented as a double-
 * linked list with a head and tail pointer, {@link #addRow()} runs in constant time. {@link #addColumn()} runs in
 * in amortized linear time. Both {@link #get(int, int)} and {@link #set(double, int, int)} run in linear
 * time. All linear operations have favorable constants (at most 1/2.) </p>
 * @see SquareMatrix
 * @see SquareMatrixOne
 * @author jason
 */
public class SquareMatrixTwo extends SquareMatrix{

    /**
     * Simple constructor.
     * @param n The dimension of the square matrix.
     */
    public SquareMatrixTwo(int n){
        super(n);
        matrix = new LinkedList<ArrayList<Double>>();
        initialize();
    }
}
