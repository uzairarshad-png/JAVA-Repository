/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Uzair Arshad
 */
import java.util.Scanner;
public class Task2 {
    public static void main(String[] args) {

        double temp, vel;

        Scanner scanner = new Scanner(System.in);

        System.out.println("\t\tWelcome to Karachi Weather Application");
        do{
        System.out.print("Enter The Tempeture in Farhenhiet(3-50)");
        temp = scanner.nextDouble();
        if(temp>50){
        System.out.println("Invalid Input!!! Try Again...");
        }
        }while(temp>50);
        
        do{
        System.out.print("Enter The Velocity in Miles per Hour(3-120)");
        vel = scanner.nextDouble();
        if(vel>120||vel<3){
        System.out.println("Invalid Input!!! Try Again...");
        }
        }while(vel>120||vel<3);

        double chill = 35.74 + (0.6215 * temp)+ ((0.4275 * temp)-35.75) * Math.pow(vel, 0.16);
        System.out.println("\nThe Wind Chill According to the given input is:" + chill);
        
        scanner.close();
    }
}
