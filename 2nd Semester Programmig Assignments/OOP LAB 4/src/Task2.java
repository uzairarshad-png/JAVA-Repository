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
public class Task2 {

    public static int computePrimeSum(int input) {
        if (input < 2) {
            System.out.println("No primes less than " + input);
            return 0;
        }

        int sum = 0;
        System.out.print("Prime numbers <= " + input + ": ");

        for (int num = 2; num <= input; num++) {
            if (isPrime(num)) {
                System.out.print(num + " ");
                sum += num;
            }
        }
        System.out.println(); 
        return sum;
    }

    //For Checking if the Number is prime or not
    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;

        int i = 5;
        while (i * i <= n) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
            i += 6;
        }
        return true;
    }
    
    public static int computeOddSum(int input) {
        if (input <= 1) {
            return 0;
        }

        int lastOdd = (input % 2 == 0) ? (input - 1) : (input - 2);

        if (lastOdd < 1) {
            return 0;
        }
        //recursive logic for computing and Saving The Value in Last odd Variable
        return lastOdd + computeOddSum(lastOdd);
    }
    

    public static int computeEvenSum(int input) {
        if (input <= 2) {
            return 0;
        }

        int lastEven = (input % 2 == 0) ? (input - 2) : (input - 1);

        if (lastEven < 2) {
            return 0;
        }

        int count = lastEven / 2;

        return count * (count + 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter positive numbers (enter negative number to stop):");

        while (true) {
            System.out.print("\nEnter a number: ");
            int num = sc.nextInt();

            if (num < 0) {
                System.out.println("Negative number entered. Exiting...");
                break;
            }

            System.out.println("========================================");
            int primeSum = computePrimeSum(num);
            System.out.println("Sum of primes <= " + num + " = " + primeSum);

            int oddSum = computeOddSum(num);
            System.out.println("Sum of odd numbers < " + num + " = " + oddSum);

            int evenSum = computeEvenSum(num);
            System.out.println("Sum of even numbers < " + num + " = " + evenSum);
            System.out.println("========================================");
        }

        sc.close();
    }
}