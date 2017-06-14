package edu.umd.cs.datastructures.projectskeletons;

import java.util.NoSuchElementException;

/** <p>Abstraction for a square matrix of {@link Double}s.</p>
 * Created by jason on 6/13/17.
 */
public abstract class SquareMatrix {

    protected int dimension;
    private static int DEFAULT_DIMENSION = 10;

    /**
     * Initialize the matrix with a default dimension tweakable by the implementation.
     */
    public SquareMatrix(){
        this(DEFAULT_DIMENSION);
    }
    /**
     * @param n The dimension to initialize the square matrix with.
     */
    public SquareMatrix(int n) throws IllegalArgumentException {
        if(n < 1)
            throw new IllegalArgumentException("Need at least a 1 x 1 matrix.");
        dimension = n;
    }


    /**
     * Getter for the matrix's dimension.
     * @return The matrix's dimension.
     */
    public int getDimension(){
        return dimension;
    }

    /**
     * @param i
     * @param j
     * @throws
     */
    public abstract double get(int i, int j) throws NoSuchElementException;

    /**
     *
     * @param dbl
     * @param i
     * @param j
     * @throws
     */
    public abstract void set(double dbl, int i, int j) throws NoSuchElementException;

    /**
     * Adds a bottom row of zeroes in the matrix.
     */
    public abstract void addRow();

    /**
     * Adds a right column of zeroes in the matrix.
     */
    public abstract void addColumn();
}
