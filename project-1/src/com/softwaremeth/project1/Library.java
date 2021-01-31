package com.softwaremeth.project1;

public class Library {
    private Book[] books; // array-based implementation of the bag data structure
    private int numBooks; // the number of books currently in the bag

    public Library() { //default constructor to create an empty bag
        numBooks = 0;
        books = new Book[0];
    }

    private int find(Book book) { // helper method to find a book in the bag
        int curr_bagSize = books.length;
        int notFound = -1;

        for(int i = 0; i < curr_bagSize; i++){
            if(book == books[i]) {
                return i;
            }
        }
        return notFound;
    }

    private void grow() { // helper method to grow the capacity by 4
        int curr_bagSize = books.length;
        int new_bagSize = curr_bagSize + 4;

        Book[] newBag = new Book[new_bagSize];

        for(int i = 0; i < curr_bagSize; i++){
            newBag[i] = books[i];
        }

        books = newBag;
    }

    public void add(Book book) { //@todo add to full bag + isValid?
        int curr_bagSize = books.length;
        int i = 0;

        while(i < curr_bagSize && books[i] != null){
            i++;
        }
        if(i > curr_bagSize ){
            //WHAT WHEN BAG IS FULL???
        }
        else{
            books[i] = book;
            numBooks++;
        }
    }

    public boolean remove(Book book) {
        int curr_bagSize = books.length;
        boolean bookExists;
        int bookIndex;

        bookIndex = find(book);
        if(bookIndex >= 0){
            bookExists = true;
            numBooks--;
            while(bookIndex < curr_bagSize && books[bookIndex] != null){
                if(bookIndex != (curr_bagSize - 1)) {
                    books[bookIndex] = books[bookIndex + 1];
                }
                else{
                    books[bookIndex] = null;
                }
            }
        }
        else{
            bookExists = false;
        }
        return bookExists;
    }

    public boolean checkOut(Book book) {
        return book.checkingOut();
    }

    public boolean returns(Book book) {
        return book.returning();
    }

    public void print() { //print the list of books in the bag
        for(int i = 0; i < numBooks; i++){
            System.out.println(books[i].toString());
        }
    }

    public void printByDate() { //@todo print the list of books by datePublished (ascending)
        int curDate;
        int minDate;

        for(int i = 0; i < numBooks; i++){

        }
    }

    public void printByNumber() { //@todo print the list of books by number (ascending)

    }
}
