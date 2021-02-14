package PayrollProcessing;

public class Management extends Fulltime{

    int role;
    double bonus;

    public Management(String name, String department, String dateHiredStr
            , double annualSalary, int role){
        super(name, department, dateHiredStr, annualSalary);

        this.role = role;

        switch(this.role){
            case 1: bonus = 5000;
                break;
            case 2: bonus = 9500;
                break;
            case 3: bonus = 12000;
                break;
            default:
                bonus = 0;
        }
    }

    @Override
    public void calculatePayment() {
        double annualSalary = getAnnualSalary();
        double payment = (annualSalary + bonus)/NUMPAYPERIODS;
        setPayment(payment);
    }

    @Override
    public String toString(){//::Manager Compensation $xxx.xx
        return super.toString() + "::Manager Compensation $" + bonus;
    }

    @Override
    public boolean equals(Object obj){ //@TODO equals
        boolean isManagement = obj instanceof Management;
        return (isManagement);
    }
}
