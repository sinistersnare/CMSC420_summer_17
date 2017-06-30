package edu.umd.cs.datastructures.demos.strings;

/**
 * Created by jason on 6/29/17.
 */
public class HuffmanTrieNode {

    /* ***********
     * * Fields *
     * ***********
    */

    private Character c; // Only non-null for leaf nodes.
    private Long freq;
    private HuffmanTrieNode left, right;


    /* ****************
     * * Constructors *
     * ****************
    */

    public HuffmanTrieNode(Character c, Long freq, HuffmanTrieNode left, HuffmanTrieNode right){
        this.c = new Character(c);
        this.freq = new Long(freq);
        this.left = left;
        this.right = right;
    }

    public HuffmanTrieNode(Long freq, HuffmanTrieNode left, HuffmanTrieNode right){
        this('\0', freq, left, right);
    }

    public HuffmanTrieNode(Character c, Long freq){
        this(c, freq, null, null);
    }

    public HuffmanTrieNode(HuffmanTrieNode left, HuffmanTrieNode right){
        this(left.getFreq() + right.getFreq(), left, right);
    }

    public HuffmanTrieNode(Character c){

    }
    public HuffmanTrieNode(Long freq){
        this('\0', freq);
    }

    public HuffmanTrieNode(HuffmanTrieNode n){
        this(n.getC(), n.getFreq());
    }

    /* ***********
     * * Setters *
     * ***********
    */

    public void setLeft(HuffmanTrieNode left){
        this.left = left;
    }

    public void setRight(HuffmanTrieNode right){
        this.right = right;
    }


    public void setC(Character c){
        this.c = new Character(c);
    }

    public void setFreq(Long freq){
        this.freq = new Long(freq);
    }

    /* ***********
     * * Getters *
     * ***********
    */
    public  Long getFreq(){
        return new Long(freq);
    }

    public Character getC(){
        return new Character(c);
    }

    public HuffmanTrieNode getLeft(){
        return left;
    }

    public HuffmanTrieNode getRight(){
        return right;
    }

    // Stringify
    public String toString(){
        return "[ HuffmanTrieNode with: (" + c +  ", " + freq + ")]";
    }
}
