/**
 Kiosk class (Ill change the descriptions later)
 @author Muhammad Faizan Saiyed, Michael Neustater
 */

public class Kiosk {
/*
@TODO SCANNER Start and stop messages, testbed main, handle bad commands test equals
 */
    public void run() {

        Date fakeDate = new Date("1/10/2010"); //valid fake date
        Date fakeDate2 = new Date("0/10/2010"); //invalid fake date (month too small)
        Date fakeDate3 = new Date("13/10/2010"); //invalid fake date (month too large)
        Date fakeDate4 = new Date("1/32/2010"); //invalid fake date (day too large)
        Date fakeDate5 = new Date("2/29/2020"); //valid fake date (leap year) MORE LEAP YEARS CAN BE TESTED
        Date fakeDate6 = new Date("2/29/2021"); //invalid fake date (not leap year)
        Date fakeDate7 = new Date("1/10/1899"); //invalid fake date (too old)
        Date fakeDate8 = new Date("1/10/2022"); //invalid fake date (future date)

        Date currentDate = new Date();
        Library lib = new Library();
        Book fakeBook = new Book("10020", "Potato", fakeDate5);
        Book fakeBookTwo = new Book("10001", "Tomato", fakeDate2);
        Book fakeBookThree = new Book("10002", "Gym", fakeDate);
        Book fakeBookFour = new Book("10004", "Computers", fakeDate4);
        lib.add(fakeBook);
        lib.add(fakeBookTwo);
        lib.add(fakeBookThree);
        lib.add(fakeBookFour);
        System.out.println(fakeBook.toString()); // prints the format for a book
        System.out.println(fakeBook.equals(fakeBook)); // should be true
        System.out.println(fakeBook.equals(fakeBookTwo)); // should be false
        System.out.println(currentDate); // should be current date
        System.out.println(fakeDate);
        System.out.println(fakeDate + " " + fakeDate.isValid() + " valid fake date");
        System.out.println(fakeDate2 + " " + fakeDate2.isValid() + " invalid fake date (month too small)");
        System.out.println(fakeDate3 + " " + fakeDate3.isValid() + " invalid fake date (month too large)");
        System.out.println(fakeDate4 + " " + fakeDate4.isValid() + " invalid fake date (day too large)");
        System.out.println(fakeDate5 + " " + fakeDate5.isValid() + " valid fake date (leap year)");
        System.out.println(fakeDate6 + " " + fakeDate6.isValid() + " invalid fake date (not leap year)");
        System.out.println(fakeDate7 + " " + fakeDate7.isValid() + " invalid fake date (too old)");
        System.out.println(fakeDate8 + " " + fakeDate8.isValid() + " invalid fake date (future date)");

        lib.print();

        lib.printByDate();

        lib.printByNumber();

        System.out.println("\nCheckOut Test:");
        lib.checkOut(fakeBook);
        lib.checkOut(fakeBook);
        lib.print();

        System.out.println("\nReturn and Remove Test:");
        lib.returns(fakeBook);
        lib.remove(fakeBookThree);
        lib.print();
        System.out.println("");


        // Adding a book "A,Programming in Java,11/20/2019"
        // Removing a book "R,10005"
        // Checking out a book "O,10005"
        // Returning a book "I,10005"
        // PA --> output the list of books to the console with the current sequence
        // PD --> output the list of books by the dates published in ascending order
        // PN --> output the list of books by the book numbers in ascending order

    }

}
