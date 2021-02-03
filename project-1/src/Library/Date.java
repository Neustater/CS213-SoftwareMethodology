package Library;
import java.util.StringTokenizer;
import java.util.Calendar;

/**
 The Date class is used to hold a date with the variables: year, month, and day.
 This class provides an easier way to interact with the validity of dates of Books in the Library.
 Also provides easier ways to return a date for use.
 @author Muhammad Faizan Saiyed, Michael Neustater
 */

//@TODO constant class

public class Date {
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
    public Date(String date) {  //taking mm/dd/yyyy and create a Library.Date object
        String monthSt;
        String daySt;
        String yearSt;

        StringTokenizer dateToken = new StringTokenizer(date,"/");

        monthSt = dateToken.nextToken();
        daySt = dateToken.nextToken();
        yearSt = dateToken.nextToken();

        month = Integer.parseInt(monthSt);
        day = Integer.parseInt(daySt);
        year = Integer.parseInt(yearSt);
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
     * A helper method to get the year of the Book's datePublished
     * @return an int of the year
     */
    public int getYear(){
        return year;
    }

    /**
     * A helper method to get the month of the Book's datePublished
     * @return an int of the month
     */
    public int getMonth(){
        return month;
    }

    /**
     * A helper method to get the day of the Book's datePublished
     * @return an int of the day
     */
    public int getDay(){
        return day;
    }

    /**
     * Method to see if a date is a valid date to be used for the Library.
     * A date with the year less than 1900 or a date beyond today’s date is invalid.
     * @return a boolean presenting true if it is a valid date, false otherwise.
     */
    public boolean isValid() {
        boolean isLeapYear;
        boolean isValidDate = false;
        Date cur = new Date();
        int curYear = cur.getYear();
        int curMonth = cur.getMonth();
        int curDay = cur.getDay();
        int firstDay = 1;
        int lastDay = 0;

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

        if(day >= firstDay && day <= lastDay)   //false if day or month is invalid, invalid months have day = 0
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
        if(year % QUADRENNIAL == 0){
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
}
