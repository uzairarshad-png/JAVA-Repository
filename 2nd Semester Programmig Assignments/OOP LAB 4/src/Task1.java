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
public class Task1 {
    
    int P_number;
    String P_Name;
    int P_age;
    String P_email;
    String P_contact;
    String P_Complain;
    double P_bill;           
    public void takePatientData() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("---------------------Patient's Info---------------------");
        
        System.out.print("Enter Patient's Number please: ");
        P_number = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Enter Patient's name please: ");
        P_Name = sc.nextLine();
        
        System.out.print("Enter Patient's age: ");
        P_age = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Enter patient's Email please: ");
        P_email = sc.nextLine();
        
        System.out.print("Enter Patient's Contact please: ");
        P_contact = sc.nextLine();
        
        System.out.print("Enter Patient's Complain: ");
        P_Complain = sc.nextLine();
        
        System.out.print("Enter Bill please: ");
        P_bill = sc.nextDouble();
        
        sc.close();
    }
    
    public void PrintReceipt() {
        System.out.println("\n\t\t------Patient's Receipt------");
        System.out.println("Patient's Number          --------------------- " + P_number);
        System.out.println("Patient's Name            --------------------- " + P_Name);
        System.out.println("Patient's Age             --------------------- " + P_age);
        System.out.println("Patient's Email           --------------------- " + P_email);
        System.out.println("Patient's Contact         --------------------- " + P_contact);
        System.out.println("Patient's Complain        --------------------- " + P_Complain);
        System.out.println("Patient's Bill            --------------------- " + P_bill);
    }
    
    public static void main(String[] args) {
        // Create object of the class
        Task1 patient = new Task1();
        
        // Take input
        patient.takePatientData();
        
        // Print receipt
        patient.PrintReceipt();
    }
}