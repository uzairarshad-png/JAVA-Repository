/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Uzair Arshad
 */
import java.util.Scanner;
public class Task3 {


    public static int[][] readMatrix(Scanner sc, String matrixName) {
        int[][] mat = new int[3][3];
        System.out.println("\nEnter values for " + matrixName + " (3x3):");
        for (int i = 0; i < 3; i++) 
        {
            for (int j = 0; j < 3; j++) 
            {
                System.out.print("Element [" + i + "][" + j + "]: ");
                mat[i][j] = sc.nextInt();
            }
        }
        return mat;
    }

    public static void printMatrix(int[][] mat, String label) {
        System.out.println(label);
        for (int i = 0; i < 3; i++) 
        {
            for (int j = 0; j < 3; j++) 
            {
                System.out.printf("%d\t", mat[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int[][] scalarMultiply(int[][] mat, int scalar) {
        int[][] result = new int[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++) 
            {
                result[i][j] = mat[i][j] * scalar;
            }
        }
        return result;
    }

    public static int[][] addMatrices(int[][] A, int[][] B) {
        int[][] result = new int[3][3];
        for (int i = 0; i < 3; i++) 
        {
            for (int j = 0; j < 3; j++)
            {
                result[i][j] = A[i][j] + B[i][j];
            }
        }
        return result;
    }

    public static int[][] subtractMatrices(int[][] A, int[][] B) {
        int[][] result = new int[3][3];
        for (int i = 0; i < 3; i++) 
        {
            for (int j = 0; j < 3; j++) 
            {
                result[i][j] = A[i][j] - B[i][j];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[][] Mat_1 = readMatrix(sc, "Mat_1");

        int[][] Mat_2 = readMatrix(sc, "Mat_2");

        printMatrix(Mat_1, "Matrix Mat_1:");
        printMatrix(Mat_2, "Matrix Mat_2:");

        int[][] Mat1_times_3 = scalarMultiply(Mat_1, 3);
        int[][] Mat2_times_2 = scalarMultiply(Mat_2, 2);

        int[][] expr1 = addMatrices(Mat1_times_3, Mat2_times_2);

        System.out.println("1) (Mat_1 * 3) + (Mat_2 * 2)");
        printMatrix(Mat1_times_3, "Mat_1 × 3:");
        printMatrix(Mat2_times_2, "Mat_2 × 2:");
        printMatrix(expr1, "Result → (Mat_1×3) + (Mat_2×2):");

        int[][] Mat2_minus_3 = subtractMatrices(Mat_2, scalarMultiply(new int[3][3], 3)); 
        int[][] expr2 = scalarMultiply(Mat2_minus_3, 2);

        System.out.println("2) (Mat_2 - 3) * 2");
        printMatrix(Mat2_minus_3, "Mat_2 - 3 (each element):");
        printMatrix(expr2, "Result → (Mat_2-3) × 2:");

        int[][] Mat2_times_5 = scalarMultiply(Mat_2, 5);
        int[][] Mat1_minus_1 = subtractMatrices(Mat_1, scalarMultiply(new int[3][3], 1)); // subtract 1 from each
        int[][] expr3 = subtractMatrices(Mat2_times_5, Mat1_minus_1);

        System.out.println("(3) (Mat_2 * 5) - (Mat_1 - 1)");
        printMatrix(Mat2_times_5, "Mat_2 × 5:");
        printMatrix(Mat1_minus_1, "Mat_1 - 1 (each element):");
        printMatrix(expr3, "Result = (Mat_2×5) - (Mat_1-1):");


        sc.close();
    }
}