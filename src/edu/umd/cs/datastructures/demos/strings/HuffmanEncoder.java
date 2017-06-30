package edu.umd.cs.datastructures.demos.strings;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by jason on 6/24/17.
 */
public class HuffmanEncoder {

    private String input;

    private HuffmanTrieNode root;

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
        List<HuffmanTrieNode> nodes = getCharsAndFreqs(s);

        /* Huffman works best if you sort the nodes in ascending order of frequency.
         * This is because  heapify()  will run fastest when this occurs. Details:
         * https://en.wikipedia.org/wiki/Binary_heap (Constructing a heap, pay attention to the fact
         * that that section concerns max heaps) and https://en.wikipedia.org/wiki/Huffman_coding (first paragraph,
         *  about Huffman building the tree bottom-up is done in time linear to the input characters.
        */

        nodes.sort(Comparator.comparing(HuffmanTrieNode::getFreq).thenComparing(HuffmanTrieNode::getC)); // TODO: Test this in-place sorting


        PriorityQueue<HuffmanTrieNode> pq = new PriorityQueue<>((n1, n2)-> {
            if(n1.getFreq() < n2.getFreq())
                return -1;
            else if(n1.getFreq() > n2.getFreq())
                return 1;
            else
                return n1.getC().compareTo(n2.getC());
        });

        pq.addAll(nodes);

        while(pq.size() > 1){
            HuffmanTrieNode first = pq.remove(), second = pq.remove();
            pq.add(new HuffmanTrieNode(first.getFreq() + second.getFreq(), first, second));
        }

        root = pq.remove();

    }

    private List<HuffmanTrieNode> getCharsAndFreqs(String s){
        return s.chars().forEach(c -> new HuffmanTrieNode((char)c));
    }

}
