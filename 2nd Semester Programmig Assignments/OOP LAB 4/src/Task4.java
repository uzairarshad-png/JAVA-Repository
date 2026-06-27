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

public class Task4 {

    public static long multiplyFrom1ToN(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * multiplyFrom1ToN(n - 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("\nEnter a positive integer (or 0 to exit): ");
            int number = scanner.nextInt();

            if (number == 0) {
                System.out.println("Goodbye!");
                break;
            }

            if (number < 0) {
                System.out.println("Please enter a non-negative number.");
                continue;
            }

            long result = multiplyFrom1ToN(number);  //using long

            System.out.printf("Product 1 x 2 x ... x %d = %,d%n", number, result);

            if (number > 20) {
                System.out.println("Note: Result may be inaccurate due to long overflow");
            }
        }

        scanner.close();
    }
}