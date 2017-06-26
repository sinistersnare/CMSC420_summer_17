package edu.umd.cs.datastructures.demos.strings;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Created by jason on 6/24/17.
 */
public class HuffmanEncoder {

    private String input;

    private class Node {
        Character c; // Only non-null for leaf nodes. 
        long freq;
        Node left, right;

        Node(Character c){
            this.c = c;
        }
    }


    public HuffmanEncoder(){
        input = "";
    }

    public HuffmanEncoder(String s){
        input = s;
    }

    public void encode() {
        encode(input);
    }

    public void encode(String s){
        Collection<Node> chars = getCharsAndFreqs(s);

        /* Huffman works best if you sort the nodes in ascending order of frequency.
         * This is because  heapify()  will run fastest when this occurs. Details:
         * https://en.wikipedia.org/wiki/Binary_heap (Constructing a heap, pay attention to the fact
         * that that section concerns max heaps) and https://en.wikipedia.org/wiki/Huffman_coding (first paragraph,
         *  about Huffman building the tree bottom-up is done in time linear to the input characters.
        */

        Collections.sort(chars, (p1, p2) -> p1.freq.compareTo(p2.freq));



    }

    private Collection<Node> getCharsAndFreqs(String s){
        return s.chars().mapToObj(i -> new Node((char)i));
    }

}
