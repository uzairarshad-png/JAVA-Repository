/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Uzair Arshad
 */
import java.util.Scanner;
public class Task1 {

    public static void main(String[] args) {

        int carton = 10; //We assumed the Carton to be having 10 boxes
        double reg_price = 1.14;
        double disc_price = 0.57;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter total number of boxes produced: ");
        int totalboxes = scanner.nextInt();

        int cartons_sold = totalboxes / carton;
        int boxes_sold = cartons_sold * carton;
        int leftoverBoxes = totalboxes % carton;

        double revenueRegular = boxes_sold * reg_price;
        double revenueDiscount = leftoverBoxes * disc_price;
        double totalRevenue = revenueRegular + revenueDiscount;

        System.out.println("\t\tTCS Boxes Sales Report");
        System.out.println("Number of cartons sold: " + cartons_sold);
        System.out.println("Number of full boxes sold at regular price: " + boxes_sold);
        System.out.println("Number of leftover boxes sold at discount: " + leftoverBoxes);
        System.out.printf("Total revenue generated: $%.2f\n", totalRevenue);

        scanner.close();
    }
}
