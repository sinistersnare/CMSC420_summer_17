package edu.umd.cs.datastructures.examcode.matrix;

import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.List;

/** <p>Abstraction for a square matrix of {@link Double}s. Implementation is completed by
 * derived classes.</p>
 * @see SquareMatrixOne
 * @see SquareMatrixTwo
 * @author jason
 */
public abstract class SquareMatrix {

    protected int dimension;
    protected List<ArrayList<Double>> matrix;

    private static int DEFAULT_DIMENSION = 10;

    /**
     * The default constructor initializes the matrix with a default dimension
     * tweakable by the implementation.
     * @see #SquareMatrix(int)
     */
    public SquareMatrix(){
        this(DEFAULT_DIMENSION);
    }

    /** <p>Parameterized <tt>protected</tt>constructor. To initialize our inner data
     * store, we have more work to do which should be offloaded to actual implementors
     * of this abstract class.</p>
     * @param n The dimension to initialize the square matrix with.
     * @see #SquareMatrix()
     */
    protected SquareMatrix(int n) throws IllegalArgumentException {
        if(n < 0)
            throw new IllegalArgumentException("Invalid dimension provided.");
        dimension = n;
    }

    /**
     * <p>xInitializes the data store with zeroes. Derived classes access this method to initialize their
     * specific stores.</p>
     */
    protected void initialize(){
        for(int i = 0; i < dimension; i++) {
            matrix.add(new ArrayList<Double>(dimension));
            for(int j = 0; j < dimension; j++){
                matrix.get(i).add(0.0);
            }
        }
    }

    /**
     * Simple getter for the matrix's dimension.
     * @deprecated Will not return the correct dimension if calls to {@link #addRow()} {@link #addColumn()}
     * are made.
     * @return The matrix's dimension.
     */
    public int getDimension(){
        return dimension;
    }



    /**
     * Adds a bottom row of zeroes in the matrix.
     */
    public void addRow() {
        matrix.add(new ArrayList<Double>(dimension));
    }

    /**
     * Adds a right column of zeroes in the matrix.
     */
    public void addColumn() {
        for(int i = 0; i < matrix.size(); i++)
            matrix.get(i).add(0.0);
    }

    /** Simple getter for the element at position (i, j) in the matrix.
     * @param i The <b>0-based</b> row index of the element.
     * @param j The <b>0-based column</b> index of the element.
     * @return The element at position (i, j).
     * @throws NoSuchElementException if either <tt>i</tt> or <tt>j</tt> are not valid indices for the matrix.
     */
    public double get(int i, int j) throws NoSuchElementException {
        if( i < 0 || i > dimension - 1 || j < 0 || j > dimension -1 )
            throw new NoSuchElementException("Provided indices (" + i + ", " + j +"), which are inconsistent with matrix dimensions.");
        return matrix.get(i).get(j);
    }

    /** Simple setter for the element at position (i, j) in the matrix.
     * @param num The <tt>double</tt>  to insert.
     * @param i The <b>0-based</b> row index of the element.
     * @param j The <b>0-based column</b> index of the element.
     * @throws NoSuchElementException if either <tt>i</tt> or <tt>j</tt> are not valid indices for the matrix.
     */
    public void set(double num, int i, int j) throws NoSuchElementException {
        if( i < 0 || i > dimension -1 || j < 0 || j > dimension - 1)
            throw new NoSuchElementException("Provided indices (" + i + ", " + j +"), which are inconsistent with matrix dimensions.");
        matrix.get(i).set(j, num);
    }
}
