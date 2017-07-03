package edu.umd.cs.datastructures.demos.hashing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * Created by Jason on 7/2/2017.
 */
public class LinearProbingTests {

    private LinearProbingHashTable ht;
    private String[] desserts = new String[]{
            "waffles", "pancakes", "grapes", "yoghurt", "ice cream", "limoncello", "cookies", "lemon cake",
            "quiche lorraine", "strawberries", "red velvet cake", "tiramissu", "milfeuille"
    };

    private static final int MAX_INSERTS = 200;

    /* Setup and teardown methods */
    @Before
    public void setUp(){
        ht = new LinearProbingHashTable();
    }

    @After
    public void tearDown(){
        ht = null;
        System.gc();
    }

    /* Testing the API */
    @Test
    public void testEmptyAndCount(){
        assertTrue("Hash Table should be empty.", ht.isEmpty());
        assertEquals("Count of keys in hash table should be 0.", 0, ht.getCount());
        ht.insert("aba");
        assertFalse("Hash Table should no longer be empty.", ht.isEmpty());
        assertEquals("Count of keys in hash table should be 1.", 1, ht.getCount());
    }

    @Test
    public void testInsertAndSearch(){
        for(String s : desserts)
            ht.insert(s);
        assertEquals("Count of hash table and number of input strings should be identical.", desserts.length, ht.getCount());
        assertFalse("Hash Table should not be considered empty.", ht.isEmpty());

        for(String s: desserts)
            assertNotEquals("String " + s + " was not found in the table.", null, ht.search(s));
    }

    @Test
    public void stressTestInsert(){
        IntStream.range(0, 200).forEach(x->ht.insert(String.valueOf(x)));
        try {
            IntStream.range(200, 400).forEach(x->ht.insert(String.valueOf(x)));
        } catch(MaxEnlargementsReachedException ignored){
            // Good, we expected this.
        } catch(Throwable t){
            fail("Caught a " + t.getClass().getSimpleName() + " with message " + t.getMessage() + ".");
        }
    }

    // No deletion tests yet...
}
