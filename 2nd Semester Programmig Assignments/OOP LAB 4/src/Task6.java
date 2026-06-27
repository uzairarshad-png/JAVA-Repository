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
public class Task6 {
    
    public static int sumBetween(int a, int b) {
        // If numbers are in reverse order, swap them
        if (a > b) {
            return sumBetween(b, a);
        }

        // Base case
        if (a == b) {
            return a;
        }

        // Recursive case
        return a + sumBetween(a + 1, b);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter first positive integer: ");
        int num1 = scanner.nextInt();

        System.out.print("Enter second positive integer: ");
        int num2 = scanner.nextInt();

        int result = sumBetween(num1, num2);

        System.out.println("Sum between " + num1 + " and " + num2 + " is: " + result);

        scanner.close();
    }
}