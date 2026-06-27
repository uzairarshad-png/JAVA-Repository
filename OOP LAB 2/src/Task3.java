/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Uzair Arshad
 */
import java.util.Scanner;
public class Task3 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Prompt user for monthly income
        System.out.print("Enter your monthly income: ");
        double income = scanner.nextDouble();

        // Prompt user for monthly expenses
        System.out.print("Enter your total monthly expenses: ");
        double expenses = scanner.nextDouble();

        // Check loan eligibility conditions
        if (income >= 3 * expenses && income > 1000) {
            System.out.println("You are eligible for a loan.");
        } else {
            System.out.println("You are not eligible for a loan.");
        }

        scanner.close();
    }
}
