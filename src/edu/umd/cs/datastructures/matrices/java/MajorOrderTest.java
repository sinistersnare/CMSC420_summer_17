package edu.umd.cs.datastructures.matrices.java;

import java.util.Random;
import static edu.umd.cs.datastructures.matrices.java.Dimension.*;

/**<p></p><tt>MajorOrderTest</tt> is a simple app that tests the efficiency of performing operations across rows and columns in Java.</p>
 * @author Jason
 */
public class MajorOrderTest {

    private static int N = 10000;
    private static int WAIT = 1; // In seconds


    private static Random r = new Random(); // Supply long seed if you seek re-producibility of experiments


    /**
     * <p><tt>main()</tt> is the routine that executes the experiment.</p>
     * @param args Arguments provided by the shell on the command line. This application ignores all command-line arguments.
     */
    public static void main(String[] args){

        double[][] M = randomMatrix(N);
        sumThrowDimension(M, ROWS);
        sumThrowDimension(M, COLUMNS);
        System.out.println("Good - bye!");
    }

    /* *********** Some helper functions to make main() cleaner: ***************** */

    private static void  sumThrowDimension(double[][] matrix, Dimension d){
        if(matrix == null || matrix.length == 0)
            throw new IllegalArgumentException("method MajorOrderTest.sumThroughDimension: invalid matrix given");
        long sum = 0, begin = System.currentTimeMillis();
        for(int i = 0; i < matrix.length; i++)
            for(int j = 0; j < matrix[i].length; j++) // Safe access
                sum += (d.equals(ROWS) ?  matrix[i][j] : matrix[j][i]);
        long end = System.currentTimeMillis();
        System.out.println("Summed along dimension " + d + " in " + (end - begin) + " ms.");
    }

    private static double[][] randomMatrix(int dim){
        double[][] matrix = allocateSquareMatrix(dim);

        // Implicitly obeying row-major order
        for(int i = 0; i < dim; i++)
            for(int j = 0; j < dim; j++)
                matrix[i][j] = r.nextDouble();
        return matrix;
    }

    private static double[][] allocateSquareMatrix(int dim){
        double[][] squareMatrix = new double[dim][];
        for(int i = 0; i < dim; i++)
            squareMatrix[i] = new double[dim];
        return squareMatrix;
    }
}
