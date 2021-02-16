package PayrollProcessing;

public class Parttime extends Employee{
    private double hourlyRate;
    private int hoursWorked = 0;
    public static final double OVERTIMERATE = 1.5;
    public static final double PAYPERIODLENGTH = 2;
    public static final int MAXNORMALHOURS = 40;

    public Parttime(String name, String department, String dateHiredStr
            ,double hourlyRate){
        super(name,department,dateHiredStr);
        this.hourlyRate = hourlyRate;
    }

    @Override
    public void calculatePayment() {    //TODO handle more than 100 hrs
        double normalHrs;
        double overtimeHrs = hoursWorked - MAXNORMALHOURS;
        double overtimeRate = hourlyRate * OVERTIMERATE;
        double payment;

        if(overtimeHrs < 0){
            normalHrs = hoursWorked;
            overtimeHrs = 0;
        }else{
            normalHrs = hoursWorked - overtimeHrs;
        }

        overtimeHrs *= PAYPERIODLENGTH;
        normalHrs *= PAYPERIODLENGTH;
        payment = (normalHrs * hourlyRate) + (overtimeHrs * overtimeRate);

        setPayment(payment);
    }

    @Override
    public String toString(){//::PART TIME::Hourly Rate $xx.xx::Hours worked this period: x
        return super.toString() + "::PART TIME::Hourly Rate $" + hourlyRate +
                "::Hours worked this period: " + hoursWorked;
    }

    @Override
    public boolean equals(Object obj){ //@TODO equals
        boolean isParttime = obj instanceof Parttime;
        return (isParttime);
    }

    public void setHoursWorked(int hoursWorked){
        this.hoursWorked = hoursWorked;
    }
}
