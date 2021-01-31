package com.softwaremeth.project1;

/**
 Book class (Ill change the descriptions later)
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
    /* method returns true if the serial numbers for the 2 book objects are the same.
     if the object is a book, creates a book copy of the object and compares it with
     the book using the method. */
    public boolean equals(Object obj){
        if(obj instanceof Book){
            Book book = (Book) obj;
            boolean booksAreEqual = book.number.equals(this.number);
            return booksAreEqual;
        } else {
            return false;
        }
    }

    @Override
    /* dateString --> turns datePublished into a string
     availability --> checks if the book is checkedOut of the library or not
     Returns this Format: "Book#10007::Design Patterns::5/30/1996::is available." */
    public String toString() {
        String dateString = datePublished.toString();
        String availability = "is available.";
        if(this.checkedOut){
            availability = "is not available.";
        }
        return "Book#" + number + "::" + name + "::" + dateString + "::" + availability;
    }

    // Method for checking out a book
    public boolean checkingOut(){
        if(this.checkedOut){
            return false;
        } else {
            this.checkedOut = true;
            return true;
        }
    }

    // Method for returning a book
    public boolean returning(){
        if(this.checkedOut){
            this.checkedOut = false;
            return true;
        } else {
            return false;
        }
    }

    // Method for returning the Date
    public Date getDate(){
        return datePublished;
    }

    // Method for returning the Name
    public String getName(){
        return name;
    }

    //Method for returning the Serial Number
    public int getNumber(){
        return Integer.parseInt(number);
    }
}
