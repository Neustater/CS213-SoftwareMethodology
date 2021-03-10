package PayrollProcessing;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 The Parttime class extends the Employee class.
 Allows access to change the hours worked and hourly rate of the Employee.
 @author Muhammad Faizan Saiyed, Michael Neustater
 */
public class Parttime extends Employee{
    private double hourlyRate;
    private int hoursWorked = 0;
    public static final double OVERTIMEMULTI = 1.5; //Overtime multiplier
    public static final double PAYPERIODLENGTH = 2;
    public static final int MAXNORMALHOURS = 40;

    /**
     * Method to create a Parttime object with the name, department, date hired, and salary.
     * @param name takes in the name of the Parttime Employee as a String.
     * @param department takes in the department of the Parttime Employee as a String.
     * @param dateHired takes in the date of the Parttime Employee as a Date Object.
     * @param hourlyRate takes in the salary of the Parttime Employee as a double.
     */
    public Parttime(String name, String department, Date dateHired
            ,double hourlyRate){
        super(name,department,dateHired);
        this.hourlyRate = hourlyRate;
    }

    /**
     * Method to calculate the payment of the Parttime Employee.
     */
    @Override
    public void calculatePayment() {
        double weeklyHrsWorked = hoursWorked / PAYPERIODLENGTH;
        double normalHrs;
        double overtimeHrs = weeklyHrsWorked - MAXNORMALHOURS;
        double overtimeRate = hourlyRate * OVERTIMEMULTI;
        double payment;

        if(overtimeHrs < 0){
            normalHrs = weeklyHrsWorked;
            overtimeHrs = 0;
        }else{
            normalHrs = weeklyHrsWorked - overtimeHrs;
        }

        overtimeHrs *= PAYPERIODLENGTH;
        normalHrs *= PAYPERIODLENGTH;
        payment = (normalHrs * hourlyRate) + (overtimeHrs * overtimeRate);

        setPayment(payment);
    }

    /**
     * Method to return a string in a specified format showing the contents of the Parttime Class.
     * @return a String in a specified format.
     */
    @Override
    public String toString(){//::PART TIME::Hourly Rate $xx.xx::Hours worked this period: x
        DecimalFormat formattedRate =
                new DecimalFormat("0.00", DecimalFormatSymbols.getInstance());
        formattedRate.setGroupingUsed(true);
        formattedRate.setGroupingSize(3);
        return super.toString() + "::PART TIME::Hourly Rate $" + formattedRate.format(hourlyRate) +
                "::Hours worked this period: " + hoursWorked;
    }

    /**
     * Method to see if an Object being compared is a Parttime Object.
     * @return true if both are Parttime, false otherwise.
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Parttime){
            return super.equals(obj);
        }else{
            return false;
        }
    }

    /**
     * Method to set the hoursWorked variable.
     * @param hoursWorked takes in the int that sets the hoursWorked variable in the Parttime Object.
     */
    public void setHours(int hoursWorked){
        this.hoursWorked = hoursWorked;
    }
}
