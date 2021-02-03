package Library;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *The Kiosk class is used for processing commands from the console.
 *It allows the user to manage and interact with the library inventory.
 *@author Muhammad Faizan Saiyed, Michael Neustater
 */

public class Kiosk {

    final int ADDNUMARGS = 3;   //number of args accepted when adding a book
    final int MODNUMARGS = 2;   //number of args accepted when modifying a book
    final int MINNUMBER = 10001;
    final int MAXNUMBER = 99999;

    /**
     * Main method to run the Virtual Library, taking in inputs and expressing outputs.
     */
    public void run() {

        Library library = new Library();
        boolean kioskRunning = true;
        int nextNumber = MINNUMBER;

        System.out.println("Library Kiosk Running.");
        Scanner scan = new Scanner(System.in);

        while(kioskRunning == true) {

            String input = scan.nextLine();

            StringTokenizer command = new StringTokenizer(input,",");
            int totalArguments = command.countTokens();
            String operation = command.nextToken();
            boolean validInput;

            switch (operation) {
                case "A":
                    if(totalArguments !=ADDNUMARGS){
                        System.out.println("Invalid command!");
                        break;
                    }
                    String number = String.valueOf(nextNumber);
                    String name = command.nextToken();
                    String dateStr = command.nextToken();
                    Date date = new Date(dateStr);

                    if (date.isValid() == true) {
                        Book tempBook = new Book(number, name, date);
                        library.add(tempBook);
                        System.out.println(name + " added to the bag.");
                        nextNumber++;
                    } else {
                        System.out.println("Invalid Date!");
                    }
                    break;

                case "R":
                    String removingNumberStr = command.nextToken();
                    validInput = isValidInput(removingNumberStr,MODNUMARGS,totalArguments);

                    if(!validInput){
                        System.out.println("Invalid command!");
                        break;
                    }

                    int removingNumber = Integer.parseInt(removingNumberStr);
                    Book removeBook = library.findBook(removingNumber);

                    if(removeBook == null){
                        System.out.println("Unable to remove, the library does not have this book.");
                    } else {
                        library.remove(removeBook);
                        System.out.println("Book#" + " " + removingNumber + " removed");
                    }
                    break;

                case "O":
                    String checkingNumberStr = command.nextToken();
                    validInput = isValidInput(checkingNumberStr,MODNUMARGS,totalArguments);

                    if(!validInput){
                        System.out.println("Invalid command!");
                        break;
                    }

                    int checkingNumber = Integer.parseInt(checkingNumberStr);
                    Book checkingBook = library.findBook(checkingNumber);

                    if(checkingBook == null || checkingBook.getChecked() == true){
                        System.out.println("Book#" + checkingNumber + " is not available.");
                    } else {
                        System.out.println("You’ve checked out Book#" + checkingNumber + " Enjoy!");
                        library.checkOut(checkingBook);
                    }
                    break;

                case "I":
                    String returnNumberStr = command.nextToken();
                    validInput = isValidInput(returnNumberStr,MODNUMARGS,totalArguments);

                    if(!validInput){
                        System.out.println("Invalid command!");
                        break;
                    }

                    int returnNumber = Integer.parseInt(returnNumberStr);
                    Book returningBook = library.findBook(returnNumber);

                    if(returningBook == null){
                        System.out.println("Unable to return Book#" + returnNumber + ".");
                    }else{
                        library.returns(returningBook);
                        System.out.println("Book#" + returnNumber + " return has completed. Thanks!");
                    }
                    break;

                case "PA":
                    System.out.println("**List of books in the library.");
                    library.print();
                    System.out.println("**End of list");
                    break;
                case "PD":
                    System.out.println("**List of books by the dates published.");
                    library.printByDate();
                    System.out.println("**End of list");
                    break;
                case "PN":
                    System.out.println("**List of books by the book numbers.");
                    library.printByNumber();
                    System.out.println("**End of list");

                    break;
                case "Q":
                    kioskRunning = false;

                    break;
                default:
                    System.out.println("Invalid command!");
            }
        }

        System.out.println("Kiosk session ended.");


        Date fakeDate = new Date("12/10/2016"); //valid fake date
        Date fakeDate2 = new Date("13/10/2016"); //invalid fake date
        Date fakeDate3 = new Date("13/10/2016"); //invalid fake date
        Date fakeDate4 = new Date("1/32/2016"); //invalid fake date (day too large)
        Date fakeDate5 = new Date("2/29/2020"); //valid fake date (leap year) MORE LEAP YEARS CAN BE TESTED
        Date fakeDate6 = new Date("2/29/2021"); //invalid fake date (not leap year)
        Date fakeDate7 = new Date("1/10/1899"); //invalid fake date (too old)
        Date fakeDate8 = new Date("1/10/2022"); //invalid fake date (future date)

        Date fakeDate9 = new Date("2/13/2015"); //Valid

        Date currentDate = new Date();
        Library lib = new Library();
        Book fakeBook = new Book("10020", "Potato", fakeDate9);
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
        System.out.println("\nRetuned...");
        lib.remove(fakeBookThree);
        System.out.println("\nRemoved...");
        lib.print();
        System.out.println("Done..");


        // Adding a book "A,Programming in Java,11/20/2019"
        // Removing a book "R,10005"
        // Checking out a book "O,10005"
        // Returning a book "I,10005"
        // PA --> output the list of books to the console with the current sequence
        // PD --> output the list of books by the dates published in ascending order
        // PN --> output the list of books by the book numbers in ascending order



    }
    /**
     *Checks for a valid input for operations that modify the state of a book
     *@param number String representation of the book number
     *@param numArgs integer value of the number
     *       of comma spliced arguments entered by user
     *@param reqArgs integer value of the number of arguments
     *       expected by the operations
     *@return returns true if the input is valud, false otherwise
     */
    public boolean isValidInput(String number,int numArgs , int reqArgs){
        try {
            int validNumber = Integer.parseInt(number);
        } catch (NumberFormatException nfe) {
            return false;
        }
        int validNumber = Integer.parseInt(number);
        if((validNumber >= MINNUMBER && validNumber <= MAXNUMBER) && (numArgs == reqArgs)){
            return true;
        }else{
            return false;
        }
    }
}
