package edu.umd.cs.datastructures.bags.testcases;

import edu.umd.cs.datastructures.bags.Bag;
import edu.umd.cs.datastructures.bags.DynamicallyShuffledBag;
import edu.umd.cs.datastructures.bags.RandomAccessBag;
import edu.umd.cs.datastructures.bags.StaticallyPerturbedBag;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Random;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by jason on 3/22/17.
 */
public class BagTests {

    private Bag<Integer> staticBag, randomAccessBag, shuffledBag;
    private Integer[] thousand;
    private Integer[] tenthousand;
    private static int NUM_ITERS=100000;
    private Random r;

    @org.junit.Test
    public void setUp() throws Exception {
        staticBag = new StaticallyPerturbedBag<Integer>();
        randomAccessBag = new RandomAccessBag<Integer>();
        shuffledBag = new DynamicallyShuffledBag<Integer>();

        // Fucking Java 7 won't give me an easy way to generate a list of Integers
        //thousand = IntStream.range(0, 999).toArray();
        //tenthousand = IntStream.range(0, 9999).toArray();
        // Do it the goddamn stupid way but note this: http://stackoverflow.com/questions/16020741/shortest-way-of-filling-an-array-with-1-2-n
        thousand = new Integer[1000];
        tenthousand = new Integer[10000];
        for(int i = 0; i < 10000; i++){
            if(i < 1000)
                thousand[i] = i + 1;
            tenthousand[i] = i+1;
        }

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


    private void testAdditions(Integer[] ints, Bag b){
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
            for(int j = 0; j < 3000; j++){
                staticBag.add(j);
                try {
                    staticBag.shake();
                } catch(Exception e){
                    fail("While adding integer " + j + " to staticBag, we received an " + e.getClass() + " with message " + e.getMessage());
                }

                staticBag.add(j);
                try {
                    staticBag.shake();
                } catch(Exception e){
                    fail("While adding integer " + j + " to staticBag, we received an " + e.getClass() + " with message " + e.getMessage());
                }

                randomAccessBag.add(j);
                try {
                    randomAccessBag.shake();
                } catch(Exception e){
                    fail("While adding integer " + j + " to staticBag, we received an " + e.getClass() + " with message " + e.getMessage());
                }
            }

        }

        // Also, shaking should not make objects disappear, or new ones appear!

        tearDown();
        setUp();

        // The following will depend on iterator() working properly, but we can have this information
        // from the unit test that follows anyway.
        for(int i = 0; i < 100; i++){
            staticBag.add(i);
            staticBag.shake();
            if(!found(staticBag, i))
                fail("After adding integer " + i + " in staticBag and shaking it, we could no longer find " + i + " in staticBag.");

            shuffledBag.add(i);
            shuffledBag.shake();
            if(!found(shuffledBag, i))
                fail("After adding integer " + i + " in shuffledBag and shaking it, we could no longer find " + i + " in shuffledBag.");


            randomAccessBag.add(i);
            randomAccessBag.shake();
            if(!found(randomAccessBag, i))
                fail("After adding integer " + i + " in randomAccessBag and shaking it, we could no longer find " + i + " in randomAccessBag.");
        }
    }

    /* Note that I have to assign the looping reference of the for-each loop to an Object,
     because Bags are Iterables, but Bags can take any Object. I don't want to be doing downcasts if I can.
     Let the equals() chips fall where they may.
      */
    private boolean found(Bag b, Object o){
        for(Object ob: b)
            if(ob.equals(o))
                return true;
        return false;
    }

    @org.junit.Test
    public void iterator() throws Exception {

        // Add a bunch of elements to all the bags

        for(int i = 0; i > -1000; i--){
            staticBag.add(i);
            shuffledBag.add(i);
            randomAccessBag.add(i);
        }


        if(!iteratorTraversesOk(staticBag))
            fail("itStatic is not looping correctly.");

        if(!iteratorTraversesOk(shuffledBag))
            fail("itShuffled is not looping correctly.");

        if(!iteratorTraversesOk(randomAccessBag))
            fail("itRandomAccess is not looping correctly.");

        // Make an additional check to make sure that the Iterators produced are fail-fast!

        if(!testIteratorFailFast(staticBag))
            fail("itStatic is not fail-fast!");

        if(!testIteratorFailFast(shuffledBag))
            fail("itShuffled is not fail-fast!");

        if(!testIteratorFailFast(randomAccessBag))
            fail("itRandom is not fail-fast!");


    }

    private boolean iteratorTraversesOk(Bag<Integer> b){
        Iterator<Integer> it = b.iterator();
        if(!it.hasNext())
            return false;
        while(it.hasNext()) {
            try {
                it.next();
            } catch (Exception e) {
                System.err.println("iteratorOk method: received an " + e.getClass() + " with message " +
                        e.getMessage() + " while accessing next() on Iterator."); //TODO: Look up how IntelliJ gives you information on stderr.
                return false;
            }
        }

        return true;
    }

    private boolean testIteratorFailFast(Bag<Integer> b){
        Iterator<Integer> it = b.iterator();
        b.add(2);
        try {
            it.next();
        } catch(ConcurrentModificationException ce){
            return true;
        } catch(Exception e){
            System.err.println("Caught an exception of type " + e.getClass() + " with message " + e.getMessage() +
                    ". Was expecting a " + new ConcurrentModificationException().getClass());
            return false;
        }
        return false;

    }

}