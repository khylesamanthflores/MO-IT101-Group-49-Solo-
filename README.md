1. File Preparation
Before clicking "Run," ensure the following three files are in your Project Root Folder (the same folder that contains your src folder, not inside src).

NewClass.java: The source code.

employee_data.txt: Contains Employee #, Name, Birthday, and Hourly Rate.

attendance_data.txt: Contains ID, Date, Time-In, and Time-Out (Format: YYYY-MM-DD,HH:mm,HH:mm).

2. Login Phase (Rule 2)
The program starts by asking for credentials.

Usernames: employee or payroll_staff.

Password: 12345.

Note: If you type the wrong password, the program will display "Incorrect username and/or password." and immediately terminate (it will not loop).

3. Navigation (The Menu Structure)
Depending on your login, the program branches:

A. If logged in as employee:

Select 1. Enter your employee number.

Input your ID (e.g., 10001).

The program displays your Employee #, Name, and Birthday, then ends.

B. If logged in as payroll_staff:

Select 1. Process Payroll.

Choose your scope:

1. One employee: Enter a specific ID to see their June-December report.

2. All employees: Scans the entire file and prints reports for everyone.

3. Exit: Returns or ends the session.

4. Payroll Calculation Logic (Rule 4)
When processing payroll, the program follows these strict internal rules:

Work Window: Only calculates time between 8:00 AM and 5:00 PM.

Grace Period: If an employee logs in at or before 8:10 AM, the program treats it as 8:00 AM.

Lunch Break: Automatically subtracts 1 hour from the daily total.

Cutoffs: * 1st to 15th: Gross Salary only (No deductions).

16th to End of Month: Includes SSS (4.5%), PhilHealth (3%), Pag-IBIG (100.00), and Withholding Tax.
