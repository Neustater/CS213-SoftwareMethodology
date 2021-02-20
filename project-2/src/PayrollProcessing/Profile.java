package PayrollProcessing;

/**
 The Profile class used to hold the contents of the Profile in the Employee class.
 Allows access to the identification contents of the Employee.
 @author Muhammad Faizan Saiyed, Michael Neustater
 */
public class Profile {
    private String name; //employee’s name in the form “lastname,firstname”
    private String department; //department code: CS, ECE, IT
    private Date dateHired;


    /**
     * Method to create a Profile with the name, department, date hired, and salary.
     * @param name takes in the name of the Profile as a String.
     * @param department takes in the department of the Profile as a String.
     * @param dateHired takes in the date of the Profile as a Date Object.
     */
    public Profile(String name, String department, Date dateHired){
        this.name = name;
        this.department = department;
        this.dateHired = dateHired;
    }

    /**
     * Method to create a String in a specified format showing the name, department, and dateHired.
     * @return a String showing name, department, and date hired.
     */
    @Override
    public String toString() { //Doe,Jane::CS::7/1/2020
        return name + "::" + department + "::" + dateHired.toString();
    }

    /**
     * Method to check if two Profile Objects are the same.
     * @param obj taking an Object to see if it is a Profile class and checking if contents are equal.
     * @return a boolean checking if the name, department, and date object are the same.
     */
    @Override
    public boolean equals(Object obj) { //compare name, department and dateHired
        int dateComp;
        if(obj instanceof Profile){
            Profile prof = (Profile) obj;
            boolean equalName = prof.returnName().equals(this.name);
            boolean equalDepartment = prof.returnDepartment().equals(this.department);
            dateComp = prof.returnDateHired().compareTo(this.dateHired);
            boolean equalDate = (dateComp == 0);
            return (equalName && equalDepartment && equalDate);
        }
        return false;
    }

    /**
     * Method to get the name of the Profile.
     * @return a String name.
     */
    public String returnName(){
        return name;
    }

    /**
     * Method to get the department of the Profile.
     * @return a String department.
     */
    public String returnDepartment(){
        return department;
    }

    /**
     * Method to get the date of the Profile.
     * @return a Date Object dateHired.
     */
    public Date returnDateHired(){
        return dateHired;
    }
}
