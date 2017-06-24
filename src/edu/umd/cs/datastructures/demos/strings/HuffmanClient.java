package edu.umd.cs.datastructures.demos.strings;
import java.util.Scanner;

/**
 * Created by jason on 6/24/17.
 */
public class HuffmanClient {

    public static void main(String[] args){
        /*if(args.length < 2)
            throw new IllegalArgumentException("Need at least one argument");
        else if(args.length > 2)
            System.err.println("Note: ignoring all arguments beyond first.")*/
        Scanner sc = new Scanner(System.in);
        System.out.println("Provide a non-empty string to encode.");
        String in = sc.next();
        while(in.isEmpty()){
            System.out.println("Please provide a non-empty string");
            in = sc.next();
        }

    }
}
