import java.util.Calendar;
import java.util.StringTokenizer;

/**
 Date class (Ill change the descriptions later)
 @author Muhammad Faizan Saiyed, Michael Neustater
 */

public class Date {
    private int year;
    private int month;
    private int day;

    public Date(String date) {  //taking mm/dd/yyyy and create a Date object
        String monthSt;
        String daySt;
        String yearSt;

        StringTokenizer st = new StringTokenizer(date,"/");

        monthSt = st.nextToken();
        daySt = st.nextToken();
        yearSt = st.nextToken();

        month = Integer.parseInt(monthSt);
        day = Integer.parseInt(daySt);
        year = Integer.parseInt(yearSt);
    }

    public Date() {     //creat an object with today's date (See Calendar Class)
        Calendar currentDate = Calendar.getInstance();
        this.year = currentDate.get(Calendar.YEAR);
        this.month = currentDate.get(Calendar.MONTH) + 1;
        this.day = currentDate.get(Calendar.DATE);
    }

    public int getYear(){
        return year;
    }

    public int getMonth(){
        return month;
    }

    public int getDay(){
        return day;
    }

    public boolean isValid() {
        boolean isLeapYear;
        boolean isValidDate = false;
        Date cur = new Date();
        int minYear = 1900;
        int curYear = cur.getYear();
        int curMonth = cur.getMonth();
        int curDay = cur.getDay();
        int firstDay = 1;
        int lastDay = 0;

        if(year < minYear || year > curYear){
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

        if(year % 4 == 0){
            if(year % 100 == 0){
                if(year % 400 == 0) {
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

        if(month == 1 || month == 3 || month == 5 || month == 7 ||  //Checks if month is a 31 day month
                month == 8 || month == 10 || month == 12)
        {
            lastDay = 31;
        }

        if(month == 4 || month == 5 || month == 9 || month == 11)   //Checks if month is a 30 day month and sets lastDay
        {
            lastDay = 30;
        }

        if(month == 2)  //Checks if month is a 29 day month and sets lastDay
        {
            if(isLeapYear){
                lastDay = 29;
            }
            else{
                lastDay = 28;
            }
        }
        if(day >= firstDay && day <= lastDay)   //false if day or month is invalid, invalid months have day = 0
            isValidDate = true;
        return isValidDate;
    }


    public String toString(){   //returns date in mm/dd/yyyy format
        return (month + "/" + day + "/" + year);
    }
}
