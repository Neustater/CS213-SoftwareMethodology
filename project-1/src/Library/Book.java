package Library;

/**
 The Book class is where four variables of a book is stored for being
 used in the Library.
 The private variables: number, name, checkedOut, and datePublished are
 used for the virtual Library.
 @author Muhammad Faizan Saiyed, Michael Neustater
 */

public class Book {
    private String number; //a 5-digit serial number unique to the book
    private String name;
    private boolean checkedOut;
    private Date datePublished;

    //Initializes the book with variables
    public Book(String inputNumber, String inputName, Date inputDate){
        this.number = inputNumber;
        this.name = inputName;
        this.datePublished = inputDate;
        this.checkedOut = false;
    }

    @Override
    /**
     * Checks if the serial numbers for 2 book objects are the same. If
     * the object is a book it creates a book copy of the object and
     * compares it with the book using the method.
     * @param object which is checked if it is a book or not.
     * @return true if the books have the same serial number, false otherwise.
     */
    public boolean equals(Object obj){ //@todo test equals
        if(obj instanceof Book){
            Book book = (Book) obj;
            boolean booksAreEqual = book.number.equals(this.number);
            return booksAreEqual;
        } else {
            return false;
        }
    }

    @Override
    /**
     * Provides a specified format to print the contents of a book:
     * serial number, name, date, checked-out.
     * @return a string in the format "Book#10007::Design Patterns::5/30/1996::is available."
     */
    public String toString() {
        String dateString = datePublished.toString();
        String availability = "is available.";
        if(this.checkedOut){
            availability = "is not available.";
        }
        return "Book#" + number + "::" + name + "::" + dateString + "::" + availability;
    }

    /**
     * A helper method to change the checkedOut boolean value to true
     * @return true if the book is available for check out, false if it is already checked out.
     */
    public boolean checkingOut(){
        if(this.checkedOut){
            return false;
        } else {
            this.checkedOut = true;
            return true;
        }
    }

    /**
     * A helper method to change the checkOut boolean value to false
     * @return true if the book was checked out and is now returned,
     *         false if it was never checked out.
     */
    public boolean returning(){
        if(this.checkedOut){
            this.checkedOut = false;
            return true;
        } else {
            return false;
        }
    }

    /**
     * A helper method to get the date of the book
     * @return a Date object containing the datePublished of the book.
     */
    public Date getDate(){
        return datePublished;
    }

    /**
     * A helper method to get the name of the book
     * @return a String containing the name of the book.
     */
    public String getName(){
        return name;
    }

    /**
     * A helper method to get the serial number of the book
     * @return an int of the serial number of book.
     */
    public int getNumber(){
        return Integer.parseInt(number);
    }

    /**
     * A helper method to see if a book is checked out or not.
     * @return a boolean, true if it is checked out, false otherwise.
     */
    public boolean getChecked(){
        return checkedOut;
    }
}
