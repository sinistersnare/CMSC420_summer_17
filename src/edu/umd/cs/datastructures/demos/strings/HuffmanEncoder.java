package edu.umd.cs.datastructures.demos.strings;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

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

        long getFreq(){
            return freq;
        }

        Character getC(){
            return c;
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
        List<Node> chars = getCharsAndFreqs(s);

        /* Huffman works best if you sort the nodes in ascending order of frequency.
         * This is because  heapify()  will run fastest when this occurs. Details:
         * https://en.wikipedia.org/wiki/Binary_heap (Constructing a heap, pay attention to the fact
         * that that section concerns max heaps) and https://en.wikipedia.org/wiki/Huffman_coding (first paragraph,
         *  about Huffman building the tree bottom-up is done in time linear to the input characters.
        */

        chars.sort(Comparator.comparing(Node::getFreq).thenComparing(Node::getC)); // TODO: Test this in-place sorting

        /*PriorityQueue<Node> pq = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                if(n1.getFreq() < n2.getFreq())
                    return -1;
                else if(n1.getFreq() > n2.getFreq())
                    return 1;
                else
                    return n1.getC().compareTo(n2.getC());

            }
        });*/
        PriorityQueue<Node> pq = new PriorityQueue<Node>((n1, n2)-> {
            if(n1.getFreq() < n2.getFreq())
                return -1;
            else if(n1.getFreq() > n2.getFreq())
                return 1;
            else
                return n1.getC().compareTo(n2.getC());
        });

        chars.forEach(insert); // Unless otherwise specified by the implementor, actions are performed in the order of iteration



    }

    private List<Node> getCharsAndFreqs(String s){
        return null;
        //return s.chars().mapToObj(i -> new Node((char)i));
    }

}
