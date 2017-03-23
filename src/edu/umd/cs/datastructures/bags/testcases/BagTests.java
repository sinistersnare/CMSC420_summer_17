package edu.umd.cs.datastructures.bags.testcases;

import edu.umd.cs.datastructures.bags.Bag;
import edu.umd.cs.datastructures.bags.DynamicallyShuffledBag;
import edu.umd.cs.datastructures.bags.RandomAccessBag;
import edu.umd.cs.datastructures.bags.StaticallyPerturbedBag;

import java.util.stream.IntStream;
import java.util.Random;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by jason on 3/22/17.
 */
public class BagTests {

    private Bag<Integer> staticBag, randomAccessBag, shuffledBag;
    private int[] thousand;
    private int[] tenthousand;
    private static int NUM_ITERS=100000;
    private Random r;

    @org.junit.Test
    public void setUp() throws Exception {
        staticBag = new StaticallyPerturbedBag<Integer>();
        randomAccessBag = new RandomAccessBag<Integer>();
        shuffledBag = new DynamicallyShuffledBag<Integer>();

        thousand = IntStream.range(0, 999).toArray();
        tenthousand = IntStream.range(0, 9999).toArray();

        r = new Random();
        r.setSeed(47); // Comment out for actual pseudorandomness
    }

    @org.junit.Test
    public void tearDown() throws Exception {
        staticBag = randomAccessBag = shuffledBag = null;
        thousand = tenthousand = null;
        r = null;
        System.gc();
    }

    @org.junit.Test
    public void add() throws Exception {
        try {

            testAdditions(thousand, staticBag);
            testAdditions(thousand, shuffledBag);
            testAdditions(thousand, randomAccessBag);

            // Clear and reset the bags pretty quick
            tearDown();
            setUp();

            testAdditions(tenthousand, staticBag);
            testAdditions(tenthousand, shuffledBag);
            testAdditions(tenthousand, randomAccessBag);
        }
        catch(Exception e){
            fail("Failed  BagTests::add() with message: " + e.getMessage());
        }
    }


    private void testAdditions(int[] ints, Bag b){
        for (Integer i : ints)
            try {
                b.add(i);
            } catch (Exception e) {
                System.err.println("testAdditions subroutine caught an exception for integer " + i + ".");
                throw (e);
            }
    }

    @org.junit.Test
    public void isEmpty() throws Exception {
        assertTrue("Statically Perturbed Bag should be empty.", staticBag.isEmpty());
        assertTrue("Dynamically Shuffled Bag should be empty.", shuffledBag.isEmpty());
        assertTrue("Random Access Bag should be empty.", randomAccessBag.isEmpty());

        assertTrue("Statically Perturbed Bag should should have a size of 0.", staticBag.size() == 0);
        assertTrue("Dynamically Shuffled Bag should should have a size of 0.", shuffledBag.size() == 0);
        assertTrue("Random Access Bag should should have a size of 0.", randomAccessBag.size() == 0);
    }

    @org.junit.Test
    public void shake() throws Exception {
        // Shake() unit tests are tricky... some things should clearly not happen regardless of the shaking.
        // Let's start with those.

        for(int i = 0; i < NUM_ITERS; i++){
            for(int j = 0; j < 3000; j++)

        }

        // Also, shaking should not make objects disappear, or new ones appear!
    }

    @org.junit.Test
    public void iterator() throws Exception {

        // TODO Maybe I should be checking behavior of hasNext() and next()?
    }

}