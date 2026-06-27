/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Uzair Arshad
 */
import java.util.Scanner;
import java.util.Random;
public class Task5 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Get range from the player
        System.out.print("Enter the lower bound: ");
        int lowerBound = scanner.nextInt();

        System.out.print("Enter the upper bound: ");
        int upperBound = scanner.nextInt();

        // Get number of rounds
        System.out.print("Enter the number of rounds to play: ");
        int rounds = scanner.nextInt();

        for (int round = 1; round <= rounds; round++) {
            System.out.println("\n--- Round " + round + " ---");

            // Generate random number within range (inclusive)
            int randomNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;

            boolean guessedCorrectly = false;

            while (!guessedCorrectly) {
                System.out.print("Guess the number (or enter -1 to quit): ");
                int guess = scanner.nextInt();

                // Allow player to quit
                if (guess == -1) {
                    System.out.println("You chose to quit this round.");
                    break;
                }

                // Check correctness
                if (guess == randomNumber) {
                    System.out.println("Congratulations! You guessed the correct number.");
                    guessedCorrectly = true;
                } else {
                    int difference = Math.abs(guess - randomNumber);

                    if (difference <= 5) {
                        System.out.println("Well done! You're close!");
                    }

                    if (guess > randomNumber) {
                        System.out.println("Your guess is too high.");
                    } else {
                        System.out.println("Your guess is too low.");
                    }
                }
            }
        }

        System.out.println("\nGame over. Thanks for playing!");
        scanner.close();
    }
}