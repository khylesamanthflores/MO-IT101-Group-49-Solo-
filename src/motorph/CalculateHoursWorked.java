package motorph;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */
public class CalculateHoursWorked {

    public static void main(String[] args) {
        // --- Step 2: Plan Your Variables and Logic ---
        
        // Information needed for the calculation
        String employeeName = "Manuel Garcia III";
        double timeIn = 8.0;    // 8:00 AM
        double timeOut = 17.0;  // 5:00 PM (using 24-hour format)
        double breakTime = 1.0; // 1 hour break
        
        // Design the formula: (End Time - Start Time) - Break
        double totalHoursWorked = (timeOut - timeIn) - breakTime;

        // --- Step 3: Display and Verify ---
        
        // Display the results with clear labels
        System.out.println("Employee Name: " + employeeName);
        System.out.println("Time In: " + timeIn + " AM");
        System.out.println("Time Out: " + (timeOut - 12) + " PM");
        System.out.println("Total Hours Worked: " + totalHoursWorked + " hours");

        // Verification Logic
        // We expect (17 - 8) - 1 = 8.0
        double expectedResult = 8.0;
        
        if (totalHoursWorked == expectedResult) {
            System.out.println("\nStatus: Test passed: Computation is correct");
        } else {
            System.out.println("\nStatus: Test failed: Check your formula or data types.");
        }
    }
}