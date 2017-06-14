package edu.umd.cs.datastructures.projectskeletons;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Created by jason on 6/13/17.
 */
public class SquareMatrixOne  extends  SquareMatrix{

    private ArrayList<ArrayList<Double>> matrix;


    public SquareMatrixOne(int n){
        super(n);
        matrix = new ArrayList<ArrayList<Double>>(n);
        for(int i = 0; i < n; i++) {
            matrix.add(new ArrayList<Double>(n));
            for(int j = 0; j < n; j++)
                matrix.get(i).add(0.0);
        }
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

    /**
     * @param i
     * @param j
     */
    @Override
    public double get(int i, int j) throws NoSuchElementException {
        if( i < 1 || i > dimension || j < 1 || j > dimension )
            throw new NoSuchElementException("Provided dimensions (" + i + ", " + j +"), which are inconsistent with matrix dimensions.");
        return matrix.get(i-1).get(j-1);
    }

    /**
     * @param
     * @param i
     * @param j
     */
    @Override
    public void set(double num, int i, int j) throws NoSuchElementException {
        if( i < 1 || i > dimension || j < 1 || j > dimension )
            throw new NoSuchElementException("Provided dimensions (" + i + ", " + j +"), which are inconsistent with matrix dimensions.");
        matrix.get(i-1).set(j-1, num);
    }
}
