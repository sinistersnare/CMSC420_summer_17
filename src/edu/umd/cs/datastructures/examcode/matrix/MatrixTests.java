package edu.umd.cs.datastructures.examcode.matrix;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by jason on 6/13/17.
 */
public class MatrixTests {

    private SquareMatrix sm;
    private static final int DIM = 50;
    private Random r;
    private static final long SEED=47;
    private static int NUM_ITERS = 2000;

    @Before
    public void setUp(){
        r = new Random(SEED);
    }

    @Test
    public void testDimension(){
        sm = new SquareMatrixOne(DIM);
        assertEquals("Dimension mismatch", 50, sm.getDimension());
        sm = new SquareMatrixTwo(DIM);
        assertEquals("Dimension mismatch", 50, sm.getDimension());
        assertTrue("Dimension 0 should not be accepted.", ensureBadDimension(0));
        assertTrue(ensureBadDimension(-1));
    }

    private boolean ensureBadDimension(int badDim){

        try {
            sm = new SquareMatrixOne(badDim);
        } catch(IllegalArgumentException ignored){
            // Do nothing
        } catch(Throwable ignored){
            return false;
        }

        try {
            sm = new SquareMatrixTwo(badDim);
        } catch(IllegalArgumentException ignored){
            // Do nothing
        } catch(Throwable ignored){
            return false;
        }
        return true;
    }

    @Test
    public void testEnsureZeroesEverywhere(){
        sm = new SquareMatrixOne(DIM);
        for(int i = 0 ; i < DIM; i++)
            for(int j = 0; j < DIM; j++)
                assertEquals("Mismatch at position (" + i + ", " + j + ").", 0.0, sm.get(i, j), 0);
        sm = new SquareMatrixTwo(DIM);
        for(int i = 0 ; i < DIM; i++)
            for(int j = 0; j < DIM; j++)
                assertEquals("Mismatch at position (" + i + ", " + j + ").", 0.0, sm.get(i, j), 0);
    }

    @Test
    public void testSetAndGet(){
        sm = new SquareMatrixOne(DIM);
        for(int i = 0; i < NUM_ITERS; i++) {
            int randI = r.nextInt(DIM);
            int randJ = r.nextInt(DIM);
            double val = r.nextDouble();
            sm.set(val, randI, randJ);
            assertEquals("Mismatch of inputted and retrieved value for i=" + randI + " and j=" + randJ, val, sm.get(randI, randJ), 0);
        }

        sm = new SquareMatrixTwo(DIM);
        for(int i = 0; i < NUM_ITERS; i++) {
            sm = new SquareMatrixOne(DIM);
            int randI = r.nextInt(DIM);
            int randJ = r.nextInt(DIM);
            double val = r.nextDouble();
            sm.set(val, randI, randJ);
            assertEquals("Mismatch of inputted and retrieved value for i=" + randI + " and j=" + randJ, val, sm.get(randI, randJ), 0);
        }
    }
}
