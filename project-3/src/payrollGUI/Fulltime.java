package payrollGUI;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 The Fulltime class extends the Employee class and holds the salary and pay periods of full time employees.
 Allows access to change a full time employees salary.
 @author Muhammad Faizan Saiyed, Michael Neustater
 */
public class Fulltime extends Employee{
    private double annualSalary;
    private static final int NUM_PAY_PERIODS = 26;

    /**
     * Method to create a Fulltime object with the name, department, date hired, and salary.
     * @param name takes in the name of the Fulltime Employee as a String.
     * @param department takes in the department of the Fulltime Employee as a String.
     * @param dateHired takes in the date of the Fulltime Employee as a Date Object.
     * @param annualSalary takes in the salary of the Fulltime Employee as a double.
     */
    public Fulltime(String name, String department, Date dateHired
            , double annualSalary){
        super(name,department,dateHired);
        this.annualSalary = annualSalary;
    }

    /**
     * Method to calculate the payment of the Fulltime Employee.
     * Calculated by taking the salary and dividing it by the pay periods.
     */
    @Override
    public void calculatePayment() {
        double payment  = annualSalary/NUM_PAY_PERIODS;
        setPayment(payment);
    }

    /**
     * Method to put the Fulltime Object into a specified string format.
     * @return a String in a specified format, showing the salary.
     */
    @Override
    public String toString(){//"::FULL TIME::Annual Salary $xx,xxx.xx
        DecimalFormat formattedSalary =
                new DecimalFormat("0.00", DecimalFormatSymbols.getInstance());
        formattedSalary.setGroupingUsed(true);
        formattedSalary.setGroupingSize(3);

        return super.toString() + "::FULL TIME::Annual Salary $" +
                formattedSalary.format(annualSalary);
    }

    /**
     * Method to check if a Fulltime Object is equal to another Fulltime Object.
     * Checking to see if two employees are the same.
     * @param obj taking in an Employee, and checking if it is a Fulltime Employee.
     * @return true if the employees are both Fulltime and equal in the Employee class, false otherwise.
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Fulltime){
            return super.equals(obj);
        }else{
            return false;
        }
    }

    /**
     * Method to get the annual salary of the Fulltime Object.
     * @return a double value of the salary.
     */
    public double getAnnualSalary(){
        return annualSalary;
    }
}
