package payrollGUI;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 The PayrollProcessing class runs the process to hold and pay all the employees in the company.
 Provides actions to interact with the company.
 @author Muhammad Faizan Saiyed, Michael Neustater
 */
public class PayrollProcessing {

    static final int MINPAY = 0;
    static final int MINHOURS = 0;
    static final int MAXHOURS = 100;
    static final String[] VALIDDEPTS = {"CS","ECE","IT"};
    static final int[] VALIDCODES = {1,2,3};

    /**
     * Method to run a payroll simulation in a company.
     */
    public void run() {
        System.out.println("Payroll Processing Starts.");

        Company company = new Company();
        boolean payrollRunning = true;

        Scanner scan = new Scanner(System.in);

        while (payrollRunning) {
            String input = scan.nextLine();
            String operation;          //determines operation input by user

            StringTokenizer command = new StringTokenizer(input, " ");
            try{
                operation = command.nextToken();
            }catch(NoSuchElementException e){
                continue;
            }

            String name;
            String department;
            String dateStr;
            String rateStr;
            String salaryStr;
            String codeStr;
            Date date;
            double rate;
            double salary;
            int code;

            switch (operation) {
                case "AP":
                    try{
                        name = command.nextToken();
                        department = command.nextToken();
                        dateStr = command.nextToken();
                        rateStr = command.nextToken();
                        date = new Date(dateStr);
                        rate = Double.parseDouble(rateStr);
                        if (command.hasMoreTokens())
                            throw new NoSuchElementException("Too many arguments!");
                    }
                    catch(NumberFormatException e) {
                        System.out.println("Payrate or Date is not a valid number");
                        break;
                    } catch(NoSuchElementException e){
                        System.out.println("Invalid Command!");
                        break;
                    }

                    if (!date.isValid()){
                        System.out.println(dateStr + " is not a valid date!");
                    } else if(!isValidDept(department)) {
                        System.out.println("'" + department + "' is not a valid department code.");
                    } else if(rate < MINPAY){
                        System.out.println("Pay rate cannot be negative.");
                    } else{
                        Parttime tempParttime = new Parttime(name, department, date, rate);
                        if(company.add(tempParttime)) {
                            System.out.println("Employee Added.");
                        }else{
                            System.out.println("Employee is already in the list.");
                        }
                    }

                    break;
                case "AF":
                    try{
                        name = command.nextToken();
                        department = command.nextToken();
                        dateStr = command.nextToken();
                        salaryStr = command.nextToken();
                        date = new Date(dateStr);
                        salary = Double.parseDouble(salaryStr);
                        if (command.hasMoreTokens())
                            throw new NoSuchElementException("Too many arguments!");
                    } catch(NumberFormatException e) {
                        System.out.println("Salary or Date is not a valid number");
                        break;
                    }catch(NoSuchElementException e){
                        System.out.println("Invalid Command!");
                        break;
                    }

                    if (!date.isValid()){       //function to check if date is valid
                        System.out.println(dateStr + " is not a valid date!");
                    } else if(!isValidDept(department)) {
                        System.out.println("'" + department + "' is not a valid department code.");
                    } else if(salary < MINPAY){
                        System.out.println("Salary cannot be negative.");
                    } else {       //function to check if date is valid
                        Fulltime tempFulltime = new Fulltime(name, department, date, salary);
                        if(company.add(tempFulltime)) {
                            System.out.println("Employee Added.");
                        }else{
                            System.out.println("Employee is already in the list.");
                        }
                    }

                    break;
                case "AM":
                    try {
                        name = command.nextToken();
                        department = command.nextToken();
                        dateStr = command.nextToken();
                        salaryStr = command.nextToken();
                        codeStr = command.nextToken();
                        date = new Date(dateStr);
                        salary = Double.parseDouble(salaryStr);
                        code = Integer.parseInt(codeStr);
                        if (command.hasMoreTokens())
                            throw new NoSuchElementException("Too many arguments!");
                    } catch(NumberFormatException e) {
                        System.out.println("Manager Code, Date or Salary is not a valid number");
                        break;
                    } catch(NoSuchElementException e){
                        System.out.println("Invalid Command!");
                        break;
                    }

                    if (!date.isValid()) {       //function to check if date is valid
                        System.out.println(dateStr + " is not a valid date!");
                    } else if(!isValidDept(department)) {
                        System.out.println("'" + department + "' is not a valid department code.");
                    } else if(!isValidCode(code)){
                        System.out.println("Invalid management code.");
                    }else if(salary < MINPAY){
                        System.out.println("Salary cannot be negative.");
                    }
                    else {       //function to check if date is valid
                        Management tempManager = new Management(name, department, date, salary, code);
                        if(company.add(tempManager)) {
                            System.out.println("Employee Added.");
                        }else{
                            System.out.println("Employee is already in the list.");
                        }
                    }

                    break;
                case "R": //Remove employee
                    try {
                        name = command.nextToken();
                        department = command.nextToken();
                        dateStr = command.nextToken();
                        date = new Date(dateStr);
                        if (command.hasMoreTokens())
                            throw new NoSuchElementException("Too many arguments!");
                    }catch(NoSuchElementException e){
                        System.out.println("Invalid Command!");
                        break;
                    }


                    Employee tempRemoval = new Employee(name, department, date);

                    boolean removed = company.remove(tempRemoval);


                    if(removed){
                        System.out.println("Employee removed.");
                    } else if (company.isEmpty()) {
                    System.out.println("Employee database is empty!");
                    } else {
                        System.out.println("Employee doesn't exist.");
                    }
                    break;
                case "C": //Calculate Payments
                    try {
                        if (command.hasMoreTokens())
                            throw new NoSuchElementException("Too many arguments!");
                    } catch(NoSuchElementException e){
                        System.out.println("Invalid Command!");
                        break;
                    }

                    if (company.isEmpty()) {
                        System.out.println("Employee database is empty!");
                        break;
                    }

                    company.processPayments();
                    System.out.println("Calculation of employee payments is done.");
                    break;
                case "S":

                    int hours;
                    try {
                        name = command.nextToken();
                        department = command.nextToken();
                        dateStr = command.nextToken();
                        date = new Date(dateStr);
                        String hoursStr = command.nextToken();
                        hours = Integer.parseInt(hoursStr);
                        if(command.hasMoreTokens()){
                            throw new NoSuchElementException("Too many inputs!");
                        }
                    }
                    catch(NumberFormatException e) {
                        System.out.println("Entered hours not a valid number");
                        break;
                    }
                    catch(NoSuchElementException e){
                        if (company.isEmpty()) {
                            System.out.println("Employee database is empty!");
                        }else {
                            System.out.println("Invalid Command!");
                        }
                        break;
                    }

                    if (company.isEmpty()) {
                        System.out.println("Employee database is empty!");
                        break;
                    }

                    if(hours > MAXHOURS){
                       System.out.println("Invalid Hours: Over " + MAXHOURS+ "!");
                       break;
                    }
                    if (hours < MINHOURS) {
                        System.out.println("Invalid Hours: Negative Value!");
                        break;
                    }

                    Employee tempSetter = company.findEmployee(name,department,date);

                    if (tempSetter == null) {
                        System.out.println("Hours could not be set: Employee does not exist");
                    } else if(!company.setHours(tempSetter)) {
                        System.out.println("Hours could not be set: Employee is not parttime");
                    }else {
                            Parttime parttimeSetter = (Parttime)tempSetter;
                            parttimeSetter.setHours(hours);
                            System.out.println("Working hours set.");
                        }
                    break;
                case "PA":
                    try {
                        if (command.hasMoreTokens())
                            throw new NoSuchElementException("Too many arguments!");
                    } catch(NoSuchElementException e){
                        System.out.println("Invalid Command!");
                        break;
                    }

                    if (company.isEmpty()) {
                        System.out.println("Employee database is empty!");
                    } else {
                        System.out.println("--Printing earning statements for all employees--");
                        company.print();
                    }
                    break;
                case "PH":
                    try {
                        if (command.hasMoreTokens())
                            throw new NoSuchElementException("Too many arguments!");
                    } catch(NoSuchElementException e){
                        System.out.println("Invalid Command!");
                        break;
                    }

                    if (company.isEmpty()) {
                        System.out.println("Employee database is empty!");
                    } else {
                        System.out.println("--Printing earning statements by date hired--");
                        company.printByDate();
                    }
                    break;
                case "PD":
                    try {
                        if (command.hasMoreTokens())
                            throw new NoSuchElementException("Too many arguments!");
                    } catch(NoSuchElementException e){
                        System.out.println("Invalid Command!");
                        break;
                    }

                    if (company.isEmpty()) {
                        System.out.println("Employee database is empty!");
                    } else {
                        System.out.println("--Printing earning statements by department--");
                        company.printByDepartment();
                    }
                    break;
                case "Q":
                    try {
                        if (command.hasMoreTokens())
                            throw new NoSuchElementException("Too many arguments!");
                    } catch(NoSuchElementException e){
                        System.out.println("Invalid Command!");
                        break;
                    }

                    payrollRunning = false;
                    break;

                case "":
                    break;
                default:
                    System.out.println("Command '" + operation + "' is not supported!");
            }
        }

        System.out.println("Payroll Processing completed.");
    }
    /**
     * Method to check if an entered department is valid
     * @param dept string for department name
     * @return true value if department is valid otherwise false
     */
    public static boolean isValidDept(String dept){
        int numDepts = VALIDDEPTS.length;
        boolean validDept = false;
        for(int i = 0; i < numDepts; i++){
            if(VALIDDEPTS[i].equals(dept)){
                validDept = true;
            }
        }
        return validDept;
    }
    /**
     * Method to check if an entered manager code is valid
     * @param code int value for manager code
     * @return true value if manager code is valid otherwise false
     */
    public static boolean isValidCode(int code){
        int numCodes = VALIDDEPTS.length;
        boolean validCode = false;
        for(int i = 0; i < numCodes; i++){
            if(VALIDCODES[i] == code){
                validCode = true;
            }
        }
        return validCode;
    }

}
