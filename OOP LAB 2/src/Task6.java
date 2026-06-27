/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Uzair Arshad
 */
import java.util.Scanner;
public class Task6 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

           //Part (a): Pulley Speed Formula
           //RPM2 = (diameter1 / diameter2) * RPM1
           
        System.out.println("Pulley Speed Calculation");

        System.out.print("Enter diameter of pulley 1: ");
        double diameter1 = scanner.nextDouble();

        System.out.print("Enter diameter of pulley 2: ");
        double diameter2 = scanner.nextDouble();

        System.out.print("Enter RPM of pulley 1: ");
        double rpm1 = scanner.nextDouble();

        double rpm2 = (diameter1 / diameter2) * rpm1;

        System.out.println("Speed of pulley 2 (RPM): " + rpm2);

           //Part (b): Weight Lifted by Pulley System
           //Weight lifted = force exerted × number of up ropes
        System.out.println("\nWeight Lifting Calculation");

        System.out.print("Enter force exerted (in Newtons): ");
        double force = scanner.nextDouble();

        System.out.print("Enter number of supporting ropes: ");
        int numberOfRopes = scanner.nextInt();

        double weightLifted = force * numberOfRopes;

        System.out.println("Maximum weight that can be lifted: " + weightLifted + " Newtons");

        scanner.close();
    }
}


