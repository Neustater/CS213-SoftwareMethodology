package PayrollProcessing;
import java.lang.Exception;

import java.util.Scanner;
import java.util.StringTokenizer;
//TODO EXCEPTIONS

/**
 The PayrollProcessing class runs the process to hold and pay all the employees in the company.
 Provides actions to interact with the company.
 @author Muhammad Faizan Saiyed, Michael Neustater
 */
public class PayrollProcessing {

    static final int RMVNUMARGS = 4;       //standard number of args for removal
    static final int STDNUMARGS = 5;       //standard number of args for operations
    static final int MNGNUMARGS = 6;       //number of args for adding a mana
    static final int SYSNUMARGS = 1;       //number of args accepted for system operation (ie: print or exit)
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
            boolean noArg;      //true if input is "" other wise false
            String input = scan.nextLine();
            int totalArguments;        //number of args entered by user
            String operation;          //determines operation input by user

            if (input.equals("")) {
                noArg = true;
                input = " ";        //sets input to " " to pass though tokenizer
            } else {
                noArg = false;
            }

            StringTokenizer command = new StringTokenizer(input, " ");
            totalArguments = command.countTokens();
            if(!input.equals(" ")) {
                operation = command.nextToken();
            }else if (noArg) {
                operation = "";
            }else{
                operation = " ";
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
                    if (totalArguments != STDNUMARGS) {        //TODO Exception
                        System.out.println("Invalid command!");
                        break;
                    }

                    name = command.nextToken();
                    department = command.nextToken();
                    dateStr = command.nextToken();
                    rateStr = command.nextToken();
                    rate = Double.parseDouble(rateStr);
                    date = new Date(dateStr);

                    if (!date.isValid()){       //TODO Exception
                        System.out.println("Invalid Date!");
                    } else if(!isValidDept(department)) {   //TODO Exception
                        System.out.println("'" + department + "' is not a valid department code.");
                    } else if(rate < MINPAY){   //TODO Exception
                        System.out.println("Pay rate cannot be negative.");
                    } else{
                        Parttime tempParttime = new Parttime(name, department, date, rate);
                        company.add(tempParttime);
                        System.out.println("Employee Added.");
                    }

                    break;
                case "AF":
                    if (totalArguments != STDNUMARGS) {
                        System.out.println("Invalid command!"); //TODO Exception
                        break;
                    }
                    name = command.nextToken();
                    department = command.nextToken();
                    dateStr = command.nextToken();
                    salaryStr = command.nextToken();
                    date = new Date(dateStr);
                    salary = Double.parseDouble(salaryStr);

                    if (!date.isValid()){       //function to check if date is valid
                        System.out.println("Invalid Date!");    //TODO Exception
                    } else if(!isValidDept(department)) {
                        System.out.println("'" + department + "' is not a valid department code.");
                    }else if(salary < MINPAY){
                        System.out.println("Salary cannot be negative.");   //TODO Exception
                    } else {       //function to check if date is valid
                        Fulltime tempFulltime = new Fulltime(name, department, date, salary);
                        company.add(tempFulltime);
                        System.out.println("Employee Added.");
                    }

                    break;
                case "AM":
                    if (totalArguments != MNGNUMARGS) {
                        System.out.println("Invalid command!"); //TODO Exception
                        break;
                    }
                    name = command.nextToken();
                    department = command.nextToken();
                    dateStr = command.nextToken();
                    salaryStr = command.nextToken();
                    codeStr = command.nextToken();
                    date = new Date(dateStr);
                    salary = Double.parseDouble(salaryStr);
                    code = Integer.parseInt(codeStr);

                    if (!date.isValid()) {       //function to check if date is valid
                        System.out.println("Invalid Date!");    //TODO Exception
                    } else if(!isValidDept(department)) {
                        System.out.println("'" + department + "' is not a valid department code."); //TODO Exception
                    } else if(!isValidCode(code)){
                        System.out.println("Invalid management code."); //TODO Exception
                    }else if(salary < MINPAY){
                        System.out.println("Salary cannot be negative.");   //TODO Exception
                    }
                    else {       //function to check if date is valid
                        Management tempManager = new Management(name, department, date, salary, code);
                        company.add(tempManager);
                        System.out.println("Employee Added.");
                    }
                    break;
                case "R": //Remove employee
                    if (totalArguments !=  RMVNUMARGS) {        //tests if number of args are valid
                        System.out.println("Invalid command!"); //TODO Exception
                        break;
                    }
                    name = command.nextToken();
                    department = command.nextToken();
                    dateStr = command.nextToken();
                    date = new Date(dateStr);

                    Employee tempRemoval = new Employee(name, department, date);

                    boolean removed = company.remove(tempRemoval);

                    if(removed){
                        System.out.println("Employee removed.");
                    } else {
                        System.out.println("Employee doesn't exist.");  //TODO Exception
                    }
                    break;
                case "C": //Calculate Payments
                    if (totalArguments != SYSNUMARGS) {
                        System.out.println("Invalid command!"); //TODO Exception
                        break;
                    }

                    company.processPayments();
                    System.out.println("Calculation of employee payments is done.");
                    break;
                case "S":   //TODO Exception for double instead of int
                    if (totalArguments != STDNUMARGS) {        //tests if number of args are valid
                        System.out.println("Invalid command!"); //TODO Exception
                        break;
                    }
                    name = command.nextToken();
                    department = command.nextToken();
                    dateStr = command.nextToken();
                    date = new Date(dateStr);
                    String hoursStr = command.nextToken();
                    int hours = Integer.parseInt(hoursStr);

                    if(hours > MAXHOURS){
                       System.out.println("Invalid Hours: Over 100!");  //TODO Exception
                       break;
                    }
                    if (hours < MINHOURS) {
                        System.out.println("Invalid Hours: Negative Value!");   //TODO Exception
                        break;
                    }

                    Employee tempSetter = company.findEmployee(name,department,date);

                    if (tempSetter == null) {
                        System.out.println("Hours could not be set: Employee does not exist");  //TODO Exception
                    } else if(!company.setHours(tempSetter)) {
                        System.out.println("Hours could not be set: Employee is not parttime"); //TODO Exception
                    }else {
                            Parttime parttimeSetter = (Parttime)tempSetter;
                            parttimeSetter.setHours(hours);
                            System.out.println("Working hours set.");

                        }
                    break;
                case "PA":
                    if (totalArguments != SYSNUMARGS) {
                    System.out.println("Invalid command!"); //TODO Exception
                    break;
                    }

                    if (company.isEmpty()) {
                        System.out.println("Employee database is empty!");  //TODO Exception
                    } else {
                        System.out.println("--Printing earning statements for all employees--");
                        company.print();
                    }
                    break;
                case "PH":
                    if (totalArguments != SYSNUMARGS) {
                        System.out.println("Invalid command!"); //TODO Exception
                        break;
                    }

                    if (company.isEmpty()) {
                        System.out.println("Employee database is empty!");  //TODO Exception
                    } else {
                        System.out.println("--Printing earning statements for all employees--");
                        company.printByDate();
                    }
                    break;
                case "PD":
                    if (totalArguments != SYSNUMARGS) {
                        System.out.println("Invalid command!"); //TODO Exception
                        break;
                    }

                    if (company.isEmpty()) {
                        System.out.println("Employee database is empty!");  //TODO Exception
                    } else {
                        System.out.println("--Printing earning statements for all employees--");
                        company.printByDepartment();
                    }
                    break;
                case "Q":
                    if (totalArguments != SYSNUMARGS) {
                        System.out.println("Invalid command!"); //TODO Exception
                        break;
                    }

                    payrollRunning = false;

                    break;

                case "":
                    break;
                default:
                    System.out.println("Command '" + operation + "' is not supported!");    //TODO Exception
            }
        }

        System.out.println("Payroll Processing completed.");
    }

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
