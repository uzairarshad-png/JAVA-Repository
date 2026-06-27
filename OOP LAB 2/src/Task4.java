/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Arshad
 */
import java.util.Scanner;
public class Task4 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Input RGB values
        System.out.print("Enter Red value (0–255): ");
        int R = scanner.nextInt();

        System.out.print("Enter Green value (0–255): ");
        int G = scanner.nextInt();

        System.out.print("Enter Blue value (0–255): ");
        int B = scanner.nextInt();

        double C, M, Y, K;

        // Edge case: black color
        if (R == 0 && G == 0 && B == 0) {
            C = 0.0;
            M = 0.0;
            Y = 0.0;
            K = 1.0;
        } else {
            double rPrime = R / 255.0;
            double gPrime = G / 255.0;
            double bPrime = B / 255.0;

            double w = Math.max(rPrime, Math.max(gPrime, bPrime));

            C = (w - rPrime) / w;
            M = (w - gPrime) / w;
            Y = (w - bPrime) / w;
            K = 1 - w;
        }

        // Output CMYK values
        System.out.printf("CMYK values:\n");
        System.out.printf("Cyan: %.2f\n", C);
        System.out.printf("Magenta: %.2f\n", M);
        System.out.printf("Yellow: %.2f\n", Y);
        System.out.printf("Black: %.2f\n", K);

        scanner.close();
    }
}   