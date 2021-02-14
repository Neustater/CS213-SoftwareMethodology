package PayrollProcessing;

public class Fulltime extends Employee{
    private double annualSalary;
    public static final int NUMPAYPERIODS = 26;

    public Fulltime(String name, String department, String dateHiredStr
            , double annualSalary){
        super(name,department,dateHiredStr);
        this.annualSalary = annualSalary;
    }

    @Override
    public void calculatePayment() {
        double payment  = annualSalary/NUMPAYPERIODS;
        setPayment(payment);
    }

    @Override
    public String toString(){//"::FULL TIME::Annual Salary $xx,xxx.xx
        return super.toString() + "::FULL TIME::Annual Salary $" + annualSalary;
    }

    @Override
    public boolean equals(Object obj){ //@TODO equals
        boolean isFulltime = obj instanceof Fulltime;
        return (isFulltime);
    }

    public void setAnnualSalary(double annualSalary){
        this.annualSalary = annualSalary;
    }

    public double getAnnualSalary(){
        return annualSalary;
    }
}
