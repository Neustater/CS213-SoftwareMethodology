package PayrollProcessing;

public class Profile {
    private String name; //employee’s name in the form “lastname,firstname”
    private String department; //department code: CS, ECE, IT
    private Date dateHired;


    public Profile(String name, String department, Date dateHired){
        this.name = name;
        this.department = department;
        this.dateHired = dateHired;
    }

    @Override
    public String toString() { //Doe,Jane::CS::7/1/2020
        return name + "::" + department + "::" + dateHired.toString();
    }

    @Override
    public boolean equals(Object obj) { //compare name, department and dateHired
        if(obj instanceof Profile){
            Profile prof = (Profile) obj;
            boolean equalName = prof.returnName().equals(this.name);
            boolean equalDepartment = prof.returnDepartment().equals(this.department);
            boolean equalDate = prof.returnDateHired().equals(this.dateHired);
            return (equalName && equalDepartment && equalDate);
        }
        return false;
    }

    public String returnName(){
        return name;
    }

    public String returnDepartment(){
        return department;
    }

    public Date returnDateHired(){
        return dateHired;
    }
}
