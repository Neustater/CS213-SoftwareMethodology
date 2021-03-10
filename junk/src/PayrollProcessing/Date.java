package PayrollProcessing;
import java.util.StringTokenizer;
import java.util.Calendar;

/**
 The Date class is used to hold a date with the variables: year, month, and day
 Provides easier ways to return a date for use.
 @author Muhammad Faizan Saiyed, Michael Neustater
 */

public class Date implements Comparable<Date>{
    private int year;
    private int month;
    private int day;

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public static final int MINYEAR = 1900;

    /**
     * Used to create a Date Object with variables taken in by a string parameter in a specific format.
     * @param date takes a String in a mm/dd/yyyy format.
     */
    public Date(String date) {
        String monthSt;             //tokenized string of month
        String daySt;               //tokenized string of day
        String yearSt;              //tokenized string of year
        final String INVALIDPARAM = "-1";  //sets value to -1 if part of date is missing

        StringTokenizer dateToken = new StringTokenizer(date,"/");

        monthSt = dateToken.hasMoreTokens() ? dateToken.nextToken() : INVALIDPARAM;
        daySt = dateToken.hasMoreTokens() ? dateToken.nextToken() : INVALIDPARAM;
        yearSt = dateToken.hasMoreTokens() ? dateToken.nextToken() : INVALIDPARAM;

        if(dateToken.hasMoreTokens()){      //if more fields exists (ie: mm/dd/yyyy/xx) date values set invalid
            month = Integer.parseInt(INVALIDPARAM);
            day = Integer.parseInt(INVALIDPARAM);
            year = Integer.parseInt(INVALIDPARAM);
        }
        else {
            month = Integer.parseInt(monthSt);
            day = Integer.parseInt(daySt);
            year = Integer.parseInt(yearSt);
        }
    }
    /**
     * Creates a Date object with today's date using the Calendar Class.
     */
    public Date() {     //creat an object with today's date (See Calendar Class)
        Calendar currentDate = Calendar.getInstance();
        year = currentDate.get(Calendar.YEAR);
        month = currentDate.get(Calendar.MONTH) + 1;
        day = currentDate.get(Calendar.DATE);
    }

    /**
     * A helper method to get the year
     * @return an int of the year
     */
    public int getYear(){
        return year;
    }

    /**
     * A helper method to get the month
     * @return an int of the month
     */
    public int getMonth(){
        return month;
    }

    /**
     * A helper method to get the day
     * @return an int of the day
     */
    public int getDay(){
        return day;
    }

    /**
     * Method to see if the date hired is a valid date or not.
     * @return a boolean presenting true if it is a valid date, false otherwise.
     */
    public boolean isValid() {
        boolean isValidDate = false;
        Date cur = new Date();          //date object of current date value
        int curYear = cur.getYear();
        int curMonth = cur.getMonth();
        int curDay = cur.getDay();
        int firstDay = 1;               //value for first day of the month
        int lastDay = 0;                //value of last day of the month

        if(year < MINYEAR || year > curYear){
            return isValidDate;
        }
        else if(year == curYear){
                if(month > curMonth){
                    return isValidDate;
                }
                else if(day > curDay){
                    return isValidDate;
                }
        }
        lastDay = daysInMonth(year,month);

        if(day >= firstDay && day <= lastDay)   //false if days are outside range specified for month
            isValidDate = true;
        return isValidDate;
    }

    /**
     * A helper method to find out how many days are in a specified month, including leap years
     * @param year needs to be checked if it is a leap year.
     * @param month needs to see which month is being referred to, to return the correct days.
     * @return an int of the amount of days in a specific month.
     */
    public int daysInMonth(int year, int month){
        boolean isLeapYear;
        int lastDay;

        if(year % QUADRENNIAL == 0){        //determines if year is a leap year
            if(year % CENTENNIAL == 0){
                if(year % QUATERCENTENNIAL == 0) {
                    isLeapYear = true;
                }
                else{
                    isLeapYear = false;
                }
            }
            else{
                isLeapYear = true;
            }
        }
        else{
            isLeapYear = false;
        }

        if(month == Month.JANUARY || month == Month.MARCH ||
                month == Month.MAY || month == Month.JULY ||  //Checks if month is a 31 day month
                month == Month.AUGUST || month == Month.OCTOBER
                || month == Month.DECEMBER)
        {
            lastDay = 31;
        }else if (month == Month.APRIL || month == Month.JUNE ||
                    month == Month.SEPTEMBER || month == Month.NOVEMBER)   //Checks if month is a 30 day month and sets lastDay
        {
            lastDay = 30;
        }
        else if (month == Month.FEBRUARY)  //Checks if month is a 29 day month and sets lastDay
        {
            if (isLeapYear) {
                lastDay = 29;
            } else {
                lastDay = 28;
            }
        }
        else{
            lastDay = -1;
        }
        return lastDay;
    }

    /**
     * Method to represent the date in a String mm/dd/yyyy format.
     * @return a string in the specified format.
     */
    public String toString(){   //returns date in mm/dd/yyyy format
        return (month + "/" + day + "/" + year);
    }


    /**
     * A method used to compare two instances of the date class
     * @param date object being compared to instance
     * @return returns 1 if the instance is greater than the given, -1 if less than and 0 if equal
     */
    @Override
    public int compareTo(Date date) {
        int compYear = date.getYear();
        int compMonth = date.getMonth();
        int compDay = date.getDay();

        if(year > compYear){
            return 1;
        }else if(year < compYear){
            return -1;
        }else if(month > compMonth){
            return 1;
        }else if(month < compMonth){
            return -1;
        }else if(day > compDay){
            return 1;
        }else if(day < compDay){
            return -1;
        }
        return 0;
    }

    public static void main(String[] args){
        //testing isValid() method
        Boolean result;

        System.out.println("Testing isValid() method...\n");
        //test case #1, insert valid date
        System.out.println("Running test case #1...");
        Date testDate = new Date("3/6/1955"); //valid test date
        System.out.println("Insert date inside valid date range: " + testDate);
        result = testDate.isValid();
        if(result){
            System.out.println("Create date inside valid date range. PASSED.");
        }
        else{
            System.out.println("Create date inside valid date range. FAILED.");
        }

        System.out.println("Running test case #2...");
        Date testDate2 = new Date("13/10/2016"); //invalid test date (month too large)
        System.out.println("Insert date with month outside valid date range: " + testDate2);
        result = testDate2.isValid();
        if(!result){
            System.out.println("Create date with month outside valid date range. PASSED.");
        }
        else{
            System.out.println("Create date with month outside valid date range. FAILED.");
        }

        System.out.println("Running test case #3...");
        Date testDate3 = new Date("1/32/2016"); //invalid test date (day too large)
        System.out.println("Insert date with day outside valid date range: " + testDate3);
        result = testDate3.isValid();
        if(!result){
            System.out.println("Create date with month outside valid date range. PASSED.");
        }
        else{
            System.out.println("Create date with month outside valid date range. FAILED.");
        }

        System.out.println("Running test case #4...");
        Date testDate4 = new Date("0/32/2016"); //invalid test date (month too small)
        System.out.println("Insert date with day outside valid date range (month too small): " + testDate3);
        result = testDate4.isValid();
        if(!result){
            System.out.println("Create date with month outside valid date range (month too small). PASSED.");
        }
        else{
            System.out.println("Create date with month outside valid date range (month too small). FAILED.");
        }

        System.out.println("Running test case #5...");
        Date testDate5 = new Date("1/0/2016"); //invalid test date (day too small)
        System.out.println("Insert date with day outside valid date range (day too small): " + testDate3);
        result = testDate5.isValid();
        if(!result){
            System.out.println("Create date with month outside valid date range (day too small). PASSED.");
        }
        else{
            System.out.println("Create date with month outside valid date range (day too small). FAILED.");
        }

        System.out.println("Running test case #6...");
        Date testDate6 = new Date("2/29/2020"); //valid test date (leap year)
        System.out.println("Insert leap date on leap year: " + testDate6);
        result = testDate6.isValid();
        if(result){
            System.out.println("Create date with leap date on leap year. PASSED.");
        }
        else{
            System.out.println("Create date with leap date on leap year. FAILED.");
        }

        System.out.println("Running test case #7...");
        Date testDate7 = new Date("2/29/2021"); //invalid test date (not leap year)
        System.out.println("Insert leap date on non-leap year: " + testDate7);
        result = testDate7.isValid();
        if(!result){
            System.out.println("Create date with leap date on non-leap year. PASSED.");
        }
        else{
            System.out.println("Create date with leap date on non-leap year. FAILED.");
        }

        System.out.println("Running test case #8...");
        Date testDate8 = new Date("1/10/1899"); //invalid test date (too old)
        System.out.println("Insert date before minimum year: " + testDate8);
        result = testDate8.isValid();
        if(!result){
            System.out.println("Create date before minimum year. PASSED.");
        }
        else{
            System.out.println("Create date before minimum year. FAILED.");
        }

        System.out.println("Running test case #9...");
        Date testDate9 = new Date("1/10/2022"); //invalid test date (future date)
        System.out.println("Insert date in the future: " + testDate9);
        result = testDate9.isValid();
        if(!result){
            System.out.println("Create date in the future. PASSED.");
        }
        else{
            System.out.println("Create date in the future. FAILED.");
        }

        System.out.println("Running test case #10...");
        Date currentDate = new Date();
        System.out.println("Insert current date: " + currentDate);
        result = currentDate.isValid();
        if(result){
            System.out.println("Create current date. PASSED.");
        }
        else{
            System.out.println("Create current date. FAILED.");
        }

    }
}


