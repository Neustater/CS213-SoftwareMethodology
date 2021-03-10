package payrollGUI;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 The Employee class used to hold the profile of an employee and the payment.
 Provides access to changing the payment of an employee.
 @author Muhammad Faizan Saiyed, Michael Neustater
 */
public class Employee {
    private Profile profile;
    private double payment = 0;

    /**
     * Method to create the Employee object with the name, department, and date hired.
     * @param name takes in the name of the employee, as a String.
     * @param department takes in the department in which the employee will be in, as a String.
     * @param dateHired takes in the date the employee was hired, as a Date Object.
     */
    public Employee(String name, String department, Date dateHired){
        profile = new Profile(name,department,dateHired);
        payment = 0;
    }

    /**
     * Method to put the Employee object into a specified string format.
     * @return a string containing the profile and the payment.
     */
    @Override
    public String toString(){ //Doe,Jane::CS::7/1/2020::Payment $0.00::STATUS
        String personInfo = profile.toString();
        DecimalFormat formattedPayment =
                new DecimalFormat("0.00", DecimalFormatSymbols.getInstance());
        formattedPayment.setGroupingUsed(true);
        formattedPayment.setGroupingSize(3);
        return  personInfo + "::Payment $" + formattedPayment.format(payment);
    }

    /**
     * Method to put the Employee object into a specified string format.
     * @param obj takes in an Object to see if it is an Employee then compares with this employee.
     * @return a boolean returning true if the employees are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Employee){
            Profile isEmployeeProf = ((Employee) obj).returnProfile();
            return(this.profile.equals(isEmployeeProf));
        }else{
            return false;
        }
    }

    /**
     * Method made to hold in the superclass for calculating payments in other classes.
     */
    public void calculatePayment() {}

    /**
     * Method made to set the payment of the employee.
     * @param payment value to be set
     */
    public void setPayment(double payment) {
        this.payment = payment;
    }

    /**
     * Helper method to return the employee's profile object.
     * @return the profile of the current employee
     */
    public Profile returnProfile() {
        return this.profile;
    }

}
