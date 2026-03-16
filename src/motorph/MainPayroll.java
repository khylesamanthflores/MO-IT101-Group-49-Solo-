package motorph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MainPayroll {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String employeeFile = "employee_data.txt";
        String attendanceFile = "attendance_data.txt";

        System.out.print("Enter Employee Number: ");
        String inputEmpNo = sc.nextLine();

        String empName = "";
        double hourlyRate = 0;
        double totalHours = 0;

        boolean found = false;

        // =============================
        // READ EMPLOYEE FILE
        // =============================

        try (BufferedReader br = new BufferedReader(new FileReader(employeeFile))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length != 3)
                    continue;

                if (data[0].equals(inputEmpNo)) {

                    empName = data[1];
                    hourlyRate = Double.parseDouble(data[2]);
                    found = true;
                    break;
                }
            }

        } catch (Exception e) {
            System.out.println("Error reading employee file.");
            return;
        }

        if (!found) {
            System.out.println("Employee not found.");
            return;
        }

        // =============================
        // READ ATTENDANCE FILE
        // =============================

        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("H:mm");

        try (BufferedReader br = new BufferedReader(new FileReader(attendanceFile))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                if (!data[0].equals(inputEmpNo))
                    continue;

                LocalTime login = LocalTime.parse(data[2], timeFormat);
                LocalTime logout = LocalTime.parse(data[3], timeFormat);

                long minutesWorked = Duration.between(login, logout).toMinutes();

                if (minutesWorked > 60)
                    minutesWorked -= 60;

                double hours = minutesWorked / 60.0;

                totalHours += Math.min(hours, 8);
            }

        } catch (Exception e) {
            System.out.println("Error reading attendance file.");
            return;
        }

        // =============================
        // SALARY COMPUTATION
        // =============================

        double grossSalary = totalHours * hourlyRate;

        double sss = grossSalary * 0.045;
        double philhealth = grossSalary * 0.03;
        double pagibig = 100;

        double deductions = sss + philhealth + pagibig;

        double taxableIncome = grossSalary - deductions;

        double tax;

        if (taxableIncome <= 20832)
            tax = 0;
        else if (taxableIncome < 33333)
            tax = (taxableIncome - 20833) * 0.20;
        else
            tax = 2500 + (taxableIncome - 33333) * 0.25;

        double netPay = grossSalary - (deductions + tax);

        // =============================
        // DISPLAY PAYROLL
        // =============================

        System.out.println("\n==============================");
        System.out.println("      MOTORPH PAYROLL");
        System.out.println("==============================");

        System.out.println("Employee Name : " + empName);
        System.out.println("Hourly Rate   : " + hourlyRate);
        System.out.println("Hours Worked  : " + totalHours);

        System.out.printf("Gross Salary  : %.2f%n", grossSalary);
        System.out.printf("SSS           : %.2f%n", sss);
        System.out.printf("PhilHealth    : %.2f%n", philhealth);
        System.out.printf("Pag-IBIG      : %.2f%n", pagibig);
        System.out.printf("Income Tax    : %.2f%n", tax);
        System.out.printf("Net Pay       : %.2f%n", netPay);

        System.out.println("==============================");

        sc.close();
    }
}