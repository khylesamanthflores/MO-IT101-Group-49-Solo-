package motorph;

/*
 * MotorPH Payroll System - Deduction Computation
 * Computes government deductions and net pay
 */

public class ComputeDeductions {

    public static void main(String[] args) {

        // --- 1. Employee Information ---
        String employeeName = "Manuel Garcia III";
        double hourlyRate = 535.71;
        double hoursWorked = 78.5;

        // --- 2. Validation ---
        if (hourlyRate <= 0 || hoursWorked <= 0) {
            System.out.println("Error: Hours worked and hourly rate must be positive values.");
            return;
        }

        // --- 3. Compute Gross Salary ---
        double grossSalary = hourlyRate * hoursWorked;

        // --- 4. Compute Net Pay ---
        double netPay = computeNetPay(grossSalary);

        // --- 5. Compute deductions again for display ---
        double sss = computeSSS(grossSalary);
        double philHealth = computePhilHealth(grossSalary);
        double pagIbig = computePagIbig(grossSalary);

        double taxableIncome = grossSalary - (sss + philHealth + pagIbig);
        double tax = computeIncomeTax(taxableIncome);

        double totalDeductions = sss + philHealth + pagIbig + tax;

        // --- 6. Display Payroll Summary ---
        System.out.println("------------------------------------------");
        System.out.println("MOTORPH PAYROLL SUMMARY");
        System.out.println("------------------------------------------");

        System.out.println("Employee Name      : " + employeeName);
        System.out.printf("Gross Salary       : PHP %.2f%n", grossSalary);

        System.out.println("\nDEDUCTIONS");
        System.out.printf("SSS                 : PHP %.2f%n", sss);
        System.out.printf("PhilHealth          : PHP %.2f%n", philHealth);
        System.out.printf("Pag-IBIG            : PHP %.2f%n", pagIbig);
        System.out.printf("Withholding Tax     : PHP %.2f%n", tax);

        System.out.println("------------------------------------------");
        System.out.printf("Total Deductions    : PHP %.2f%n", totalDeductions);
        System.out.printf("Net Pay             : PHP %.2f%n", netPay);
        System.out.println("------------------------------------------");
    }

    /*
     * Computes SSS deduction based on salary bracket
     */
    public static double computeSSS(double salary) {

        if (salary >= 24750)
            return 1125.00;
        else if (salary >= 24250)
            return 1102.50;
        else if (salary >= 23750)
            return 1080.00;
        else if (salary >= 23250)
            return 1057.50;
        else if (salary >= 22750)
            return 1035.00;
        else if (salary >= 22250)
            return 1012.50;
        else if (salary >= 21750)
            return 990.00;
        else if (salary >= 21250)
            return 967.50;
        else if (salary >= 20750)
            return 945.00;
        else
            return 900.00;
    }

    /*
     * Computes PhilHealth deduction
     * 3% premium shared 50% employee / 50% employer
     */
    public static double computePhilHealth(double salary) {

        double premium = salary * 0.03;

        if (premium < 300)
            premium = 300;

        if (premium > 1800)
            premium = 1800;

        return premium / 2;
    }

    /*
     * Computes Pag-IBIG contribution
     */
    public static double computePagIbig(double salary) {

        if (salary <= 1500)
            return salary * 0.01;
        else
            return salary * 0.02;
    }

    /*
     * Computes Withholding Tax using BIR tax table
     */
    public static double computeIncomeTax(double taxableIncome) {

        if (taxableIncome <= 20832)
            return 0;

        else if (taxableIncome < 33333)
            return (taxableIncome - 20833) * 0.20;

        else if (taxableIncome < 66667)
            return 2500 + (taxableIncome - 33333) * 0.25;

        else if (taxableIncome < 166667)
            return 10833 + (taxableIncome - 66667) * 0.30;

        else if (taxableIncome < 666667)
            return 40833.33 + (taxableIncome - 166667) * 0.32;

        else
            return 200833.33 + (taxableIncome - 666667) * 0.35;
    }

    /*
     * Computes Net Pay by calling all deduction methods
     */
    public static double computeNetPay(double grossSalary) {

        double sss = computeSSS(grossSalary);
        double philHealth = computePhilHealth(grossSalary);
        double pagIbig = computePagIbig(grossSalary);

        double taxableIncome = grossSalary - (sss + philHealth + pagIbig);

        double tax = computeIncomeTax(taxableIncome);

        return grossSalary - (sss + philHealth + pagIbig + tax);
    }
}