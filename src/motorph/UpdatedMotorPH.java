package motorph;

import java.io.*;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class UpdatedMotorPH {

    // --- Deductions (No rounding as per Rule 1) ---
    public static double computeSSS(double s) { return s * 0.045; }
    public static double computePH(double s) { return s * 0.03; }
    public static double computePI(double s) { return 100.0; }
    public static double computeTax(double t) {
        if (t <= 20832) return 0;
        if (t < 33333) return (t - 20833.33) * 0.20;
        return 2500 + (t - 33333.33) * 0.25;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter username: ");
        String user = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();

        // Rule 2: If incorrect, display message and terminate
        if (!pass.equals("12345") || (!user.equals("employee") && !user.equals("payroll_staff"))) {
            System.out.println("Incorrect username and/or password.");
            return; // Terminate program
        }

        if (user.equals("employee")) {
            System.out.println("\n1. Enter your employee number\n2. Exit the program");
            if (sc.nextInt() == 1) {
                System.out.print("Enter Employee #: ");
                process(sc.next(), true);
            }
        } else {
            System.out.println("\n1. Process Payroll\n2. Exit the program");
            if (sc.nextInt() == 1) {
                System.out.println("\n1. One employee\n2. All employees\n3. Exit the program");
                int sub = sc.nextInt();
                if (sub == 1) {
                    System.out.print("Enter Employee #: ");
                    process(sc.next(), false);
                } else if (sub == 2) {
                    process("all", false);
                }
            }
        }
    }

    // Rule 4: Special Calculation Logic
    public static double calculateTotalHours(String id, String start, String end) {
        double total = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("attendance_data.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] r = line.split(",");
                LocalDate date = LocalDate.parse(r[1].trim());
                if (r[0].equals(id) && !date.isBefore(LocalDate.parse(start)) && !date.isAfter(LocalDate.parse(end))) {
                    
                    // Logic for 8:00 AM - 5:00 PM (17:00)
                    String[] inTime = r[2].trim().split(":");
                    String[] outTime = r[3].trim().split(":");
                    
                    int inH = Integer.parseInt(inTime[0]);
                    int inM = Integer.parseInt(inTime[1]);
                    int outH = Integer.parseInt(outTime[0]);
                    int outM = Integer.parseInt(outTime[1]);

                    // Adjust "In" time: If before 8:10, count as 8:00 (Rule 4c)
                    if (inH == 8 && inM <= 10) { inH = 8; inM = 0; }
                    // Adjust "Out" time: Cap at 17:00 (Rule 4a)
                    if (outH >= 17) { outH = 17; outM = 0; }

                    double hours = (outH + outM/60.0) - (inH + inM/60.0);
                    total += (hours - 1.0); // Less 1 hour lunch
                }
            }
        } catch (Exception e) {}
        return total;
    }

    public static void process(String target, boolean isEmployeeView) {
        boolean found = false;
        try (BufferedReader br = new BufferedReader(new FileReader("employee_data.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                if (!target.equals("all") && !target.equals(d[0].trim())) continue;
                found = true;

                System.out.println("\nEmployee #: " + d[0]);
                System.out.println("Employee Name: " + d[1]);
                System.out.println("Birthday: " + d[2]);

                if (!isEmployeeView) { // Payroll Details
                    double rate = Double.parseDouble(d[3]);
                    for (int m = 6; m <= 12; m++) {
                        String mStr = String.format("2024-%02d", m);
                        String mName = Month.of(m).name();

                        double h1 = calculateTotalHours(d[0], mStr+"-01", mStr+"-15");
                        if (h1 > 0) {
                            System.out.println("Cutoff Date: " + mName + " 1 to 15");
                            System.out.println("Total Hours Worked: " + h1);
                            System.out.println("Gross Salary: " + (h1 * rate));
                            System.out.println("Net Salary: " + (h1 * rate));
                        }

                        int last = LocalDate.of(2024, m, 1).lengthOfMonth();
                        double h2 = calculateTotalHours(d[0], mStr+"-16", mStr+"-"+last);
                        if (h2 > 0) {
                            double g2 = h2 * rate;
                            double sss = computeSSS(g2), ph = computePH(g2), pi = computePI(g2);
                            double tax = computeTax(g2 - (sss+ph+pi));
                            System.out.println("Cutoff Date: " + mName + " 16 to " + last + " (Deductions Included)");
                            System.out.println("Total Hours Worked: " + h2);
                            System.out.println("Gross Salary: " + g2);
                            System.out.println("SSS: " + sss + " | PhilHealth: " + ph + " | Pag-IBIG: " + pi + " | Tax: " + tax);
                            System.out.println("Total Deductions: " + (sss+ph+pi+tax));
                            System.out.println("Net Salary: " + (g2 - (sss+ph+pi+tax)));
                        }
                    }
                }
            }
            if (!found) System.out.println("Employee number does not exist.");
        } catch (Exception e) {}
    }
}
