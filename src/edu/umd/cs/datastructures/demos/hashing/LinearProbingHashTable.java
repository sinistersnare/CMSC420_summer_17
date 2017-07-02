package edu.umd.cs.datastructures.demos.hashing;

/**
 * Created by Jason on 7/2/2017.
 */
public class LinearProbingHashTable {

    private static final int[] PRIMES = {
            3 ,   5,	7,	11,	13,	17,	19,	23,	29,
            31,	37,	41,	43,	47,	53,	59,	61,	67,	71,
            73,	79,	83,	89,	97,	101,103,107,109,113,
            127,131,137,139, 149, 151, 157,	163, 167, 173,
            179	,181,191,193,197,	199,	211,	223,	227,	229,
            233,	239,	241,	251,	257,	263,	269,	271,	277,	281,
            283,	293,	307,	311,	313,	317,	331,	337,	347,	349,
            353,	359,	367,	373,	379,	383,	389,	397,	401,	409,
            419,	421,	431,	433,	439,	443,	449,	457,	461,	463};

    private int currrPrimeInd ;
    private String[] buffer;
    private int count;

    public LinearProbingHashTable(){
        currrPrimeInd =4; // Start with 13
        buffer = new String[PRIMES[currrPrimeInd]];
        count = 0;
    }

    public void insert(String key){
        int probe = myHash(key);
        while(buffer[probe] != null)
            probe++;
        buffer[probe] = key;
        if((++count) > (buffer.length / 2))
            enlarge(); // This won't be costly or anything
    }

    // The approach of the slides
    private int myHash(String s){
        return (s.hashCode() & 0x7fffffff) % buffer.length;
    }

    private void enlarge(){
        String[] old = buffer;
        if(++currrPrimeInd > PRIMES.length)
            throw new RuntimeException("Sorry boss, no more primes in my list.");
        buffer = new String[PRIMES[currrPrimeInd]];
        for(String s : old)
            insert(s); // Reinsert everything to the new buffer
    }

    public String search(String key){
        int probe = myHash(key);
        while(buffer[probe] != null)
            if(key.equals(buffer[probe]))
                return buffer[probe];
        return null;
    }

    public void delete(String key){


        /* Implement this! */



    }


}
