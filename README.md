# MotorPH

This Java application is a basic procedural payroll system designed to automate salary computation for MotorPH employees. The system reads employee data from external text/CSV files and calculates payroll based on the employee's hourly rate, hours worked, and mandatory government deductions.

The program demonstrates key programming concepts such as file reading, loops, conditional statements, and modular methods while simulating a simplified payroll process.

System Logic
1. Employee Data Loading

The system reads employee records from an external file (employee_data.txt).
Each line represents one employee and contains:

EmployeeNumber,EmployeeName,HourlyRate

The program parses each line using Java file reading (BufferedReader) and extracts the necessary data for payroll computation.

2. Attendance and Hours Worked

Employee attendance records contain login and logout times.
The program calculates hours worked using the following rules:

• Login and logout times determine the total working minutes
• A 1-hour lunch break deduction is applied when the shift exceeds one hour
• Working hours are capped at 8 hours per day

The system converts minutes worked into total hours for salary computation.

3. Semi-Monthly Payroll Computation

The system calculates payroll using a semi-monthly structure.

First Cutoff (1–15)
Gross Pay = Hours Worked × Hourly Rate
Net Pay = Gross Pay

No deductions are applied during the first cutoff.

Second Cutoff (16–End of Month)

The system combines the gross salaries from both cutoffs to determine the employee’s correct government deduction brackets.

4. Government Contributions

Mandatory deductions include:

• SSS Contribution
• PhilHealth Contribution
• Pag-IBIG Contribution

These deductions are calculated based on the employee's total monthly salary before computing income tax.

5. Withholding Tax Calculation

After subtracting government contributions, the remaining taxable income is evaluated using the Withholding Tax Matrix.

Tax brackets determine the final income tax deduction applied to the second cutoff payroll.

6. Payroll Summary Output

The system displays a detailed payroll summary including:

Employee Name
Hourly Rate
Total Hours Worked
Gross Salary
SSS Contribution
PhilHealth Contribution
Pag-IBIG Contribution
Income Tax
Net Pay
System Flow
Program Start
      ↓
Load Employee Data File
      ↓
Input Employee Number
      ↓
Retrieve Employee Information
      ↓
Read Attendance Records
      ↓
Compute Hours Worked
      ↓
Compute Semi-Monthly Gross Salary
      ↓
Apply Government Deductions
      ↓
Compute Withholding Tax
      ↓
Display Payroll Summary
How to Run the Program (For Testers)

This program is built using Java in NetBeans.

Step 1 – Download the Project

Clone or download the repository as a ZIP file.

Step 2 – Open in NetBeans

In NetBeans:

File → Open Project

Select the extracted MotorPH project folder.

Step 3 – Ensure Required Files Exist

The project should contain:

employee_data.txt
attendance_data.txt

These files must be located in the project root or resources folder.

Step 4 – Run the Program

Right-click:

MainPayroll.java

Then select:

Run File
Technologies Used

• Java
• NetBeans IDE
• File Handling (BufferedReader, FileReader)
• Java Time API (LocalTime, Duration)

Programming Concepts Demonstrated

✔ File Input Handling
✔ Procedural Programming
✔ Loops and Conditional Statements
✔ Modular Method Design
✔ Payroll Computation Logic
