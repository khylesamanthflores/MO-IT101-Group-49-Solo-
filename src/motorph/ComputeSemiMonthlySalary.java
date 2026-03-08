package motorph;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */
public class ComputeSemiMonthlySalary {
    
    public static void main(String[]args) {
        //1. Declare Employee Information
        String employeeName = "Manuel Garcia III";
        double hourlyRate = 535.71;  // Pesos per hour
        double hoursWorked = 78.5;  // Total hours for the 2-week cutoff
        double semiMonthlySalary = 0.0;
        
        // --- 2. Validation & Computation ---
        // Using a conditional statement to handle invalid data
        if (hoursWorked <= 0 || hourlyRate <= 0) {
            System.out.println("Error: Invalid input. Hours and rate must be greater than zero.");
        } else {
            // Perform arithmetic calculation
            semiMonthlySalary = hoursWorked * hourlyRate;
            //Net Pay logic
            double deductionRate = 0.10;
            double deductions = semiMonthlySalary * deductionRate;
            double netSalary = semiMonthlySalary - deductions;
 
            // --- 3. Display Result ---
            System.out.println("------------------------------------------");
            System.out.println("PAYROLL SUMMARY - SEMI-MONTHLY");
            System.out.println("------------------------------------------");
            System.out.println("Employee Name      : " + employeeName);
            System.out.println("Hourly Rate        : PHP " + hourlyRate);
            System.out.println("Total Hours Worked : " + hoursWorked); 
            System.out.printf("GROSS PAY      : PHP %.2f%n", semiMonthlySalary);
            System.out.printf("DEDUCTIONS     : PHP %.2f%n", deductions);
            System.out.printf("NET PAY        : PHP %.2f%n", netSalary);
            System.out.println("------------------------------------------");

            // --- 4. Test and Verify (Step 4) --
            double roundedSalary = Math.round(semiMonthlySalary * 100.0) / 100.0;
            double expected = 42053.24;
            if (roundedSalary == expected){
            
                System.out.println("Computation verified successfully!");
          }
        }
    }
}
    

        

        



