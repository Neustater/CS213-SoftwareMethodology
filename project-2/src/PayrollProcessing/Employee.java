package PayrollProcessing;

public class Employee {
    private Profile profile;
    private double payment = 0;


    public Employee(String name, String department, String dateHiredStr){
        Date dateHired = new Date(dateHiredStr);
        profile = new Profile(name,department,dateHired);
        calculatePayment();
        payment = 0;
    }

    @Override
    public String toString(){ //Doe,Jane::CS::7/1/2020::Payment $0.00::STATUS
        String personInfo = profile.toString();

        return  personInfo + "::Payment $" + payment;
    }

    @Override
    public boolean equals(Object obj){
        boolean isEmployee = obj instanceof Employee;
        return (isEmployee);
    }

    public void calculatePayment() {}

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public Profile returnProfile() {
        return this.profile;
    }

}
