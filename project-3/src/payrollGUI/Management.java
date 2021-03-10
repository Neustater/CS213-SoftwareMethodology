package payrollGUI;

import java.text.DecimalFormat;

/**
 The Management class extends the Fulltime class, holding the salary values through the role assigned.
 Access to calculate payments for the Fulltime class and assigns the role for the Employee.
 @author Muhammad Faizan Saiyed, Michael Neustater
 */
public class Management extends Fulltime{

    private int role;
    private double bonus;
    private double compensation;
    private static final String MANAGER = "Manager";
    private static final String DEPT_HEAD = "Department Head";
    private static final String DIRECTOR = "Director";
    private String status;
    private static final int NUM_PAY_PERIODS = 26;

    /**
     * Method to create a Management object with the name, department, date hired, salary, and role.
     * @param name takes in the name of the Fulltime Employee as a String.
     * @param department takes in the department of the Fulltime Employee as a String.
     * @param dateHired takes in the date of the Fulltime Employee as a Date Object.
     * @param annualSalary takes in the salary of the Fulltime Employee as a double.
     * @param role takes in the int value referring to the 3 roles, each one having a different salary.
     */
    public Management(String name, String department, Date dateHired
            , double annualSalary, int role){
        super(name, department, dateHired, annualSalary);

        this.role = role;

        switch(this.role){
            case 1: bonus = 5000;
                status =MANAGER;
                break;
            case 2: bonus = 9500;
                status =DEPT_HEAD;
                break;
            case 3: bonus = 12000;
                status =DIRECTOR;
                break;
            default:
                bonus = 0;
        }

        compensation = bonus/NUM_PAY_PERIODS;
    }

    /**
     * Method to calculate the payment of the Fulltime Employee through the role in Management.
     */
    @Override
    public void calculatePayment() {
        double annualSalary = getAnnualSalary();
        double payment = ((annualSalary)/NUM_PAY_PERIODS) + compensation;
        setPayment(payment);
    }

    /**
     * Method to return a String in a specified format showing the salary based on the role.
     * @return a string in a specified format with the contents of the Management class.
     */
    @Override
    public String toString(){//::Manager Compensation $xxx.xx
        DecimalFormat formattedCompensation =
                new DecimalFormat("0.00");
        formattedCompensation.setGroupingUsed(true);
        formattedCompensation.setGroupingSize(3);
        if(status == null){
            return super.toString();
        }
        return super.toString() + "::" + status + " Compensation $" +
                formattedCompensation.format(compensation);
    }

    /**
     * Method to check if two Employees are both Management.
     * @param obj takes in an Object checking if it is Management.
     * @return a boolean value of true if it is Management and equal in the Fulltime class, false otherwise.
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Management){
                return super.equals(obj);
        }else{
            return false;
        }

    }
}
