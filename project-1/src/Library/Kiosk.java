package Library;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *The Kiosk class is used for processing commands from the console.
 *It allows the user to manage and interact with the library inventory.
 *@author Muhammad Faizan Saiyed, Michael Neustater
 */

public class Kiosk {

    final int ADDNUMARGS = 3;       //number of args accepted when adding a book
    final int MODNUMARGS = 2;       //number of args accepted when modifying a book
    final int SYSNUMARGS = 1;       //number of args accepted for system operation (ie: print or exit)
    final int MINNUMBER = 10001;    //minimum valid serial number
    final int MAXNUMBER = 99999;    //maximum valid serial number

    /**
     * Main method to run the Virtual Library, taking in inputs and expressing outputs.
     */
    public void run() {

        Library library = new Library();
        boolean kioskRunning = true;
        int nextNumber = MINNUMBER;         //serial number of the next created book

        System.out.println("Library Kiosk Running.");
        Scanner scan = new Scanner(System.in);

        while(kioskRunning) {
            boolean noArg;      //true if input is "" other wise false
            String input = scan.nextLine();
            int totalArguments;        //number of args entered by user
            String operation;          //determines operation input by user
            boolean validInput;

            if(input.equals("")){
                noArg = true;
                input = " ";        //sets input to " " to pass though tokenizer
            }
            else{
                noArg = false;
            }

            StringTokenizer command = new StringTokenizer(input,",");
            totalArguments = command.countTokens();
            operation = command.nextToken();

            if(noArg){
                operation = "";
            }

            switch (operation) {
                case "A":
                    if(totalArguments !=ADDNUMARGS){        //tests if number of args are valid
                        System.out.println("Invalid command!");
                        break;
                    }
                    String number = String.valueOf(nextNumber);
                    String name = command.nextToken();
                    String dateStr = command.nextToken();
                    Date date = new Date(dateStr);

                    if (date.isValid() == true) {       //function to check if date is valid
                        Book tempBook = new Book(number, name, date);
                        library.add(tempBook);
                        System.out.println(name + " added to the Library.");
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
                    if(totalArguments != SYSNUMARGS){
                        System.out.println("Invalid command!");
                        break;
                    }

                    if(library.isEmpty()){
                        System.out.println("Library catalog is empty!");
                    }
                    else{
                        System.out.println("**List of books in the library.");
                        library.print();
                        System.out.println("**End of list");
                    }
                    break;
                case "PD":
                    if(totalArguments != SYSNUMARGS){
                        System.out.println("Invalid command!");
                        break;
                    }

                    if(library.isEmpty()){
                        System.out.println("Library catalog is empty!");
                    }
                    else {
                        System.out.println("**List of books by the dates published.");
                        library.printByDate();
                        System.out.println("**End of list");
                    }
                    break;
                case "PN":
                    if(totalArguments != SYSNUMARGS){
                        System.out.println("Invalid command!");
                        break;
                    }

                    if(library.isEmpty()){
                        System.out.println("Library catalog is empty!");
                    }
                    else{
                        System.out.println("**List of books by the book numbers.");
                        library.printByNumber();
                        System.out.println("**End of list");
                    }
                    break;
                case "Q":
                    if(totalArguments != SYSNUMARGS){
                        System.out.println("Invalid command!");
                        break;
                    }

                    kioskRunning = false;

                    break;

                case "":
                    break;
                default:
                    System.out.println("Invalid command!");
            }
        }

        System.out.println("Kiosk session ended.");
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
        try {                               //attempts to parse int, returns false if input is not valid int
            int validNumber = Integer.parseInt(number);
        } catch (NumberFormatException nfe) {
            return false;
        }
        int validNumber = Integer.parseInt(number);
        if((validNumber >= MINNUMBER && validNumber <= MAXNUMBER) && (numArgs == reqArgs)){ //checks if serial is valid
            return true;
        }else{
            return false;
        }
    }
}
