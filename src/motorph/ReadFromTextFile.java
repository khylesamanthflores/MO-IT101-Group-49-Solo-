package motorph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */
public class ReadFromTextFile {
   
    // Method to compute SSS deduction (example)
    public static double computeSSS(double salary) {
        return salary * 0.045; // sample logic
    }

    // Method to compute PhilHealth deduction
    public static double computePhilHealth(double salary) {
        return salary * 0.03;
    }

    // Method to compute Pag-IBIG deduction
    public static double computePagIbig(double salary) {
        return 100;
    }

    // Method to compute Income Tax
    public static double computeIncomeTax(double taxableIncome) {
        if (taxableIncome <= 20832) {
            return 0;
        } else if (taxableIncome < 33333) {
            return (taxableIncome - 20833) * 0.20;
        } else {
            return 2500 + (taxableIncome - 33333) * 0.25;
        }
    }

    public static void main(String[] args) {

        String filePath = "employee_data.txt";

        try {

            File file = new File(filePath);

            // Check if file exists
            if (!file.exists()) {
                System.out.println("Error: File not found.");
                return;
            }

            // FileReader opens the file
            FileReader fr = new FileReader(file);

            // BufferedReader reads the file line by line
            BufferedReader br = new BufferedReader(fr);

            String line;

            // Read each line from the file
            while ((line = br.readLine()) != null) {

                try {

                    // Split the line using comma
                    String[] data = line.split(",");

                    // Validate if record contains name and salary
                    if (data.length != 3 ) {
                        System.out.println("Invalid record format: " + line);
                        continue;
                    }

                    String name = data[0].trim();
                    double grossSalary = Double.parseDouble(data[1].trim());

                    // Validate salary
                    if (grossSalary <= 0) {
                        System.out.println("Invalid salary for " + name);
                        continue;
                    }

                    // Compute deductions
                    double sss = computeSSS(grossSalary);
                    double philhealth = computePhilHealth(grossSalary);
                    double pagibig = computePagIbig(grossSalary);

                    double totalDeductions = sss + philhealth + pagibig;

                    double taxableIncome = grossSalary - totalDeductions;

                    double incomeTax = computeIncomeTax(taxableIncome);

                    double netPay = grossSalary - (totalDeductions + incomeTax);

                    // Display payroll summary
                    System.out.println("=================================");
                    
                    System.out.println("Employee Name: " + name);
                    System.out.printf("Gross Salary : %.2f%n", grossSalary);
                    System.out.printf("SSS          : %.2f%n", sss);
                    System.out.printf("PhilHealth   : %.2f%n", philhealth);
                    System.out.printf("Pag-IBIG     : %.2f%n", pagibig);
                    System.out.printf("Income Tax   : %.2f%n", incomeTax);
                    System.out.printf("Net Pay      : %.2f%n", netPay);

                } catch (NumberFormatException e) {
                    // Handles invalid salary values
                    System.out.println("Invalid salary format in record: " + line);
                }

            }

            // Close the reader
            br.close();

        } catch (IOException e) {
            // Handles file reading errors
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}