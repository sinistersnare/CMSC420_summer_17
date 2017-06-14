package edu.umd.cs.datastructures.projectskeletons;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Created by jason on 6/13/17.
 */
public class SquareMatrixTwo extends SquareMatrix{

    private LinkedList<ArrayList<Double>> matrix;


    /**
     * @param n
     */
    public SquareMatrixTwo(int n){
        super(n);
        matrix = new LinkedList<>();
    }

    /**
     * @param i
     * @param j
     * @throws
     */
    @Override
    public double get(int i, int j) throws NoSuchElementException {
        if( i < 1 || i > dimension || j < 1 || j > dimension )
            throw new NoSuchElementException("Provided dimensions (" + i + ", " + j +"), which are inconsistent with matrix dimensions.");
        if(i -1 >= matrix.size())
            return 0.0;
        return matrix.get(i).get(j);
    }

    /**
     * @param dbl
     * @param i
     * @param j
     * @throws
     */
    @Override
    public void set(double dbl, int i, int j) throws NoSuchElementException {
        if( i < 1 || i > dimension || j < 1 || j > dimension )
            throw new NoSuchElementException("Provided dimensions (" + i + ", " + j +"), which are inconsistent with matrix dimensions.");
    }

    /**
     * Adds a bottom row of zeroes in the matrix.
     */
    @Override
    public void addRow() {
        matrix.add(new ArrayList<Double>(dimension));
    }

    /**
     * Adds a right column of zeroes in the matrix.
     */
    @Override
    public void addColumn() {
        for(int i = 0; i < matrix.size(); i++)
            matrix.get(i).add(0.0);
    }
}
