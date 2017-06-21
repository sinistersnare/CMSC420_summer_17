package edu.umd.cs.datastructures.examcode.matrix;
import java.util.ArrayList;

/**<p> <tt>SquareMatrixOne</tt> is an implementation of a 2D matrix of <tt>double</tt>s based on
 * an {@link ArrayList} of {@link ArrayList}s. {@link #addRow()}, therefore, runs in <em>amortized</em>
 * constant time. {@link #addColumn()} runs in amortized linear time. {@link #get(int, int)}  and
 * {@link #set(double, int, int)} runs in constant time.</p>
 * @see SquareMatrix
 * @see SquareMatrixTwo
 * @author jason
 */
public class SquareMatrixOne  extends SquareMatrix{

    /**
     * Simple constructor.
     * @param n The dimension to build the square matrix with.
     */
    public SquareMatrixOne(int n){
        super(n);
        matrix = new ArrayList<ArrayList<Double>>(n);   // Preallocating ArrayList for efficiency
        initialize();
    }
}
