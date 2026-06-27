/**
 *
 * @author Uzair Arshad
 */
import java.io.*;
import java.util.*;

public class EmployeeManagement {

    static final String FILE_NAME = "EmployeeData.txt";
    static final String UPDATED_FILE_NAME = "UpdatedEmployeeInfo.txt";

    public static void main(String[] args) {
        List<String> employees = new ArrayList<>();
        employees.add("Uzair,Development,12345,Senior Developer,150000,Active");
        employees.add("Abeer,HR,23456,HR Manager,100000,Active");
        employees.add("Altahan,SQA,34567,Executive QA Engineer,110000,ON Maternity LEAVE");
        employees.add("Saim,Development,45678,Backend Developer,118000,Active");

        saveToFile(FILE_NAME, employees);

        System.out.println("=== Original Employee Data ===");   // Fetch and Display Original Data
        List<String> loadedData = readFromFile(FILE_NAME);
        displayData(loadedData);

        if (loadedData.size() >= 3) {   // Update 2nd and 3rd Employee (Index 1 and 2)
            loadedData.set(1, "Abeer,HR,23456,Senior Manager,110000,Active");   // Updated Salary/Designation
            loadedData.set(2, "Altahan,SQA,34567,Executive QA Engineer,110000,Resigned");   // Updated Status
        }

        
        saveToFile(UPDATED_FILE_NAME, loadedData);  //Save to New File and Display Updated Data
        System.out.println("\n--- Updated Employee Data (from " + UPDATED_FILE_NAME + ") ---");
        displayData(readFromFile(UPDATED_FILE_NAME));
    }

    private static void saveToFile(String fileName, List<String> data) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (String line : data) {
                writer.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private static List<String> readFromFile(String fileName) {
        List<String> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return data;
    }

    private static void displayData(List<String> data) {
        System.out.printf("%-10s | %-10s | %-10s | %-15s | %-10s | %-10s%n", 
                          "Name", "Dept", "Contact", "Designation", "Salary", "Status");
        System.out.println("--------------------------------------------------------------------------------");
        for (String record : data) {
            String[] parts = record.split(",");
            System.out.printf("%-10s | %-10s | %-10s | %-15s | %-10s | %-10s%n", 
                              parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
        }
    }
}