package edu.umd.cs.datastructures.bags.testcases;

import edu.umd.cs.datastructures.bags.Bag;
import edu.umd.cs.datastructures.bags.DynamicallyShuffledBag;
import edu.umd.cs.datastructures.bags.RandomAccessBag;
import edu.umd.cs.datastructures.bags.StaticallyPerturbedBag;

import java.util.stream.IntStream;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by jason on 3/22/17.
 */
public class BagTests {

    private Bag<Integer> staticBag, randomAccessBag, shuffledBag;
    private IntStream thousand = IntStream.range(0, 1000);
    private IntStream tenthousand = IntStream.range(0, 10000);

    @org.junit.Test
    public void setUp() throws Exception {
        staticBag = new StaticallyPerturbedBag<Integer>();
        randomAccessBag = new RandomAccessBag<Integer>();
        shuffledBag = new DynamicallyShuffledBag<Integer>();
    }

    @org.junit.Test
    public void tearDown() throws Exception {
        staticBag = randomAccessBag = shuffledBag = null;
        System.gc();
    }

    @org.junit.Test
    public void add() throws Exception {
        try {

            testAdditions(thousand, staticBag);
            testAdditions(thousand, shuffledBag);
            testAdditions(thousand, randomAccessBag);

            // Clear and reset the bags up pretty quick
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


    private void testAdditions(IntStream stream, Bag b){
        stream.forEach(value -> { // Look at this sweet Scala-like expression....
            try {
                b.add(value); // TODO: Make the underlying collection thread-safe!
            }catch (Exception e){
                System.err.println("Adding element " + value + " in bag " + b.getClass() + " failed.");
                throw(e);
            }
        });
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
    }

    @org.junit.Test
    public void iterator() throws Exception {
    }

}