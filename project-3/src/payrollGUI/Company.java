package payrollGUI;

import javafx.scene.control.TextArea;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;


/**
 The Company class used to hold all the employees and number of employees.
 Allows access to run a company with employees.
 @author Muhammad Faizan Saiyed, Michael Neustater
 */
public class Company {
    private Employee[] emplist;
    private int numEmployee;
    private static final int NOT_FOUND = -1;

    /**
     * Method to create a company object with the default array of employees of size 4.
     */
    public Company(){ //Default size 4
        numEmployee = 0;
        emplist = new Employee[4];
    }

    /**
     * Method to find an employee within the employee array in the Company Class.
     * @param employee takes in the employee that needs to be found in the company.
     * @return the index where the employee was found in the employee array.
     */
    private int find(Employee employee) {
        for(int index = 0; index < numEmployee; index++){
            if(employee.equals(emplist[index])){

                return index;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Method to grow the employee array if the array is full.
     */
    private void grow() {
        int curr_catalogSize = emplist.length;
        int new_catalogSize = curr_catalogSize + 4;

        Employee[] newCatalog = new Employee[new_catalogSize];  //array with new catalog capacity

        for(int i = 0; i < curr_catalogSize; i++){  //fills new array
            newCatalog[i] = emplist[i];
        }

        emplist = newCatalog;
    }

    /**
     * Method to add an employee to the company.
     * @param employee takes in the employee that is going to be added to the company.
     * @return a boolean value of true if added, false if the employee already exists or is not an employee.
     */
    public boolean add(Employee employee) { //check the profile before adding
        //Checking profile

        if(employee == null){
            return false;
        }

        for(int index = 0; index < emplist.length; index++){
            if(emplist[index] == null){
                continue;
            }
            if(employee.equals(emplist[index])){
                return false;
            }
        }

        //Check for full list
        if(numEmployee == emplist.length){
            grow();
        }

        //add the employee
        for(int index = 0; index < emplist.length; index++) {
            if (emplist[index] == null) {
                emplist[index] = employee;
                numEmployee++;
                return true;
            }
        }
        return false;
    }

    /**
     * Method to remove an employee from the company.
     * @param employee takes in the employee object that needs to be removed.
     * @return a boolean value of true if the employee was removed, false if employee not found.
     */
    public boolean remove(Employee employee) { //maintain the original sequence
        //Find the employee
        int index = 0;
        index = find(employee);
        if(index == NOT_FOUND){
            return false;
        }

        //Check if it was found or not
        if(index == emplist.length) return false;

        //Remove employee
        emplist[index] = null;

        //Fix list
        int previous = index;
        int current = index + 1;
        while(current < emplist.length){
            if(emplist[current] != null){
                emplist[previous] = emplist[current];
                emplist[current] = null;
                previous++;
                current++;
            } else {
                break;
            }
        }
        return true;
    }

    /**
     * Method to see if the employee is a part time worker in order to set hours for them.
     * @param employee takes in an employee object to check for part time.
     * @return boolean value of true if the employee is a part time, false otherwise.
     */
    public boolean setHours(Employee employee) { //set working hours for a part time
        //Find the employee
        int index = 0;
        index = find(employee);

        //Check if it was found or not
        if(index == emplist.length || index == -1) return false;

        //Change the working hours (Change later)
        Employee currEmp = emplist[index];
        if(currEmp instanceof Parttime) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to process the payments for all employees in the company.
     */
    public void processPayments() { //process payments for all employees
        for(int index = 0; index < emplist.length; index++){
            if(emplist[index] == null){
                continue;
            }
            if(emplist[index] instanceof Parttime){
                emplist[index].calculatePayment();
            } else if(emplist[index] instanceof Fulltime){
                if(emplist[index] instanceof Management) {
                    emplist[index].calculatePayment();
                } else {
                    emplist[index].calculatePayment();
                }
            }
        }
    }

    /**
     * Method to print out the current array of employees in the Company.
     */
    public void print() { //print earning statements for all employee

        for(int index = 0; index < emplist.length; index++) { //
            if (emplist[index] == null) {
                continue;
            }
            if (emplist[index] instanceof Parttime) {
                System.out.println(emplist[index].toString());
            } else if (emplist[index] instanceof Fulltime) {
                if (emplist[index] instanceof Management) {
                    System.out.println(emplist[index].toString());
                } else System.out.println(emplist[index].toString());
            }
        }

    }


    public void printToController(TextArea consoleOutput) { //print earning statements for all employee
        for(int index = 0; index < emplist.length; index++) { //
            if (emplist[index] == null) {
                continue;
            }
            if (emplist[index] instanceof Parttime) {
                consoleOutput.appendText(emplist[index].toString() + "\n");
            } else if (emplist[index] instanceof Fulltime) {
                if (emplist[index] instanceof Management) {
                    consoleOutput.appendText(emplist[index].toString() + "\n");
                } else consoleOutput.appendText(emplist[index].toString() + "\n");
            }
        }
    }

    public void outputToFile(File targetFile, TextArea consoleOutput) { //print earning statements for all employee
        try {
            FileWriter fileWriter = new FileWriter(targetFile, false);
            for(int index = 0; index < emplist.length; index++) { //
                if (emplist[index] == null) {
                    continue;
                }
                if (emplist[index] instanceof Parttime) {
                    fileWriter.write(emplist[index].toString() + "\n");
                } else if (emplist[index] instanceof Fulltime) {
                    if (emplist[index] instanceof Management) {
                        fileWriter.write(emplist[index].toString() + "\n");
                    } else  fileWriter.write(emplist[index].toString() + "\n");
                }
            }
            fileWriter.close();
            consoleOutput.appendText("Database Exported!");
        } catch (IOException e) {
            consoleOutput.appendText("An error occurred.");
        }
    }

    public void sortByDepartment() { //print earning statements by department (CS --> ECE --> IT)

        //Sort it by department
        String currentDepartment;
        String movingDepartment;
        Employee tempEmployee;
        for(int index = 0; index < emplist.length; index++) {
            if (emplist[index] == null) continue;
            currentDepartment = emplist[index].returnProfile().returnDepartment();
            for (int movingIndex = index; movingIndex < emplist.length; movingIndex++) {
                if (emplist[movingIndex] == null) continue;
                movingDepartment = emplist[movingIndex].returnProfile().returnDepartment();
                if (movingDepartment.equals("CS")) {
                    if (currentDepartment.equals("ECE") || currentDepartment.equals("IT")) {
                        tempEmployee = emplist[index];
                        emplist[index] = emplist[movingIndex];
                        emplist[movingIndex] = tempEmployee;
                    }
                } else if (movingDepartment.equals("ECE")) {
                    if (currentDepartment.equals("IT")) {
                        tempEmployee = emplist[index];
                        emplist[index] = emplist[movingIndex];
                        emplist[movingIndex] = tempEmployee;
                    }
                }
            }
        }
    }
    public void sortByDate() {
        Employee tempBook;
        Date innerDate;
        int minIndex = 0;

        for (int outer = 0; outer < emplist.length; outer++) {
            minIndex = outer;
            for (int inner = outer + 1; inner < emplist.length; inner++) {
                if (emplist[inner] == null) {
                    continue;
                }
                innerDate = emplist[inner].returnProfile().returnDateHired();
                if (emplist[minIndex].returnProfile().returnDateHired().getYear() > innerDate.getYear()) {
                    minIndex = inner;
                } else if (emplist[minIndex].returnProfile().returnDateHired().getYear() == innerDate.getYear()) {
                    if (emplist[minIndex].returnProfile().returnDateHired().getMonth() > innerDate.getMonth()) {
                        minIndex = inner;
                    } else if (emplist[minIndex].returnProfile().returnDateHired().getMonth() == innerDate.getMonth()) {
                        if (emplist[minIndex].returnProfile().returnDateHired().getDay() > innerDate.getDay()) {
                            minIndex = inner;
                        }
                    }
                }
            }
            tempBook = emplist[minIndex];
            emplist[minIndex] = emplist[outer];
            emplist[outer] = tempBook;
        }
    }

    /**
     * Method to print out the current array of employees in the company sorted by their department.
     */
    public void printByDepartment() { //print earning statements by department (CS --> ECE --> IT)
        sortByDepartment();
        print();
    }

    /**
     * Method to print out the current array of employees in the company sorted by their date hired.
     */
    public void printByDate() { //print earning statements by date hired
        sortByDate();
        print();
    }

    /**
     * Method to find an employee based on their name, department, and date hired.
     * @param name takes in the name of the employee object.
     * @param department takes in the department of the employee object.
     * @param dateHired takes in the date hired of the employee object.
     * @return the employee object that is found within the company.
     */
    public Employee findEmployee(String name, String department, Date dateHired){
        Employee finder = new Employee(name, department, dateHired);
        Employee found;
        int index = find(finder);
        if(index == NOT_FOUND)
        {
            found = null;
        }else{
            found = emplist[index];
        }
        return found;
    }

    /**
     * Method to check if the company is empty, having no employees.
     * @return boolean value of true if there are no employees, false otherwise.
     */
    public boolean isEmpty(){
        boolean companyEmpty;

        if(numEmployee == 0 || emplist[0] == null){
            companyEmpty = true;
        }
        else{
            companyEmpty = false;
        }
        return companyEmpty;
    }
}
