package Library;

/**
 The Library class creates a library object used for storing books.
 @author Muhammad Faizan Saiyed, Michael Neustater
 */
public class Library {
    private Book[] books; // array-based implementation of the bag data structure
    private int numBooks; // the number of books currently in the bag

    /**
     *Creates library object with no books and bag size of 4
     */
    public Library() { //default constructor to create an empty bag (Size 4)
        numBooks = 0;
        books = new Book[4];
    }

    /**
     *Finds the index of a book in the bag
     *@param book to be located
     *@return value of index, -1 indicates book is not found
     */
    private int find(Book book) {
        int curr_bagSize = books.length;
        int notFound = -1;

        for(int index = 0; index < curr_bagSize; index++){
            if(book == books[index]) {
                return index;
            }
        }
        return notFound;
    }
    /**
     *helper method to increase the capacity of library by 4
     */
    private void grow() {
        int curr_bagSize = books.length;
        int new_bagSize = curr_bagSize + 4;

        Book[] newBag = new Book[new_bagSize];

        for(int i = 0; i < curr_bagSize; i++){
            newBag[i] = books[i];
        }

        books = newBag;
    }
    /**
     *Method to add a new Book object to the library
     * @param book to be added
     */
    public void add(Book book) {
        int curr_bagSize = books.length;

        if(curr_bagSize <= numBooks){
            grow();
        }

        books[numBooks] = book;
        numBooks++;
    }
    /**
     *Method to remove a Book obejct from the library
     * @param book to be removed
     * @return true if book exists and has been removed, false otherwise
     */
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
                    bookIndex++;
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

    /**
     * Method to see if the book that is being checked out is available
     * for check out and exists in the library.
     * @param book, takes in a book that needs to be checked out of the library.
     * @return true if the book is available for check out, false otherwise.
     */
    public boolean checkOut(Book book) {
        return book.checkingOut();
    }

    /**
     * Method to see if the book that is being returned was checked
     * out before and exists in the library.
     * @param book, takes in a book that needs to be returned to the Library.
     * @return true if the book is checked Out for returning, false otherwise.
     */
    public boolean returns(Book book) {
        return book.returning();
    }

    /**
     * Method to print out all the books in the library.
     */
    public void print() { //print the list of books in the bag
        for(int i = 0; i < numBooks; i++){
            System.out.println(books[i].toString());
        }

    }

    /**
     * Method to call the date sorting method and printing out the
     * list of books, sorted by date.
     */
    public void printByDate() {
        sortDate();
        print();
    }

    /**
     * Method to call the number sorting method and printing out the
     * list of books, sorted by numbers.
     */
    public void printByNumber() { //prints numbers in ascending order
        sortNumber();
        print();
    }

    /**
     * Method to sort the library based on the date of the books in the
     * library.
     */
    private void sortDate(){
        Book tempBook;
        Date innerDate;
        int minIndex = 0;

        for(int outer = 0; outer < books.length; outer++){
            minIndex = outer;
            for(int inner = outer + 1; inner < books.length; inner++){
              innerDate = books[inner].getDate();
                if(books[minIndex].getDate().getYear() > innerDate.getYear()){
                    minIndex = inner;
                } else if (books[minIndex].getDate().getYear() == innerDate.getYear()){
                    if(books[minIndex].getDate().getMonth() > innerDate.getMonth()) {
                        minIndex = inner;
                    } else if (books[minIndex].getDate().getMonth() == innerDate.getMonth()){
                        if(books[minIndex].getDate().getDay() > innerDate.getDay()){
                            minIndex = inner;
                        }
                    }
                }
            }
            tempBook = books[minIndex];
            books[minIndex] = books[outer];
            books[outer] = tempBook;
        }
    }

    /**
     * Method to sort the library based on the serial number of the
     * books in the library.
     */
    private void sortNumber(){
        int curNum;
        int curNumIndex;
        int tempNum;
        Book tempBook;

        for(int i = 0; i < numBooks; i++) {
            curNum = books[i].getNumber();
            curNumIndex = i;
            for (int j = i - 1; j >= 0; j--) {
                tempNum = books[j].getNumber();
                if (curNum < tempNum) {
                    tempBook = books[curNumIndex];
                    books[curNumIndex] = books[j];
                    books[j] = tempBook;
                    curNumIndex--;
                }
                else{
                    continue;
                }
            }
        }
    }

    /**
     * Helper method to find a specified book in the library with the
     * use of a serial number.
     * @param number, takes an int to be used to find a book in the library.
     * @return the book that is found in the library, null if not found.
     */
    public Book findBook(int number){
        for(int i = 0; i < numBooks; i++){
            if(books[i].getNumber() == number){
                return books[i];
            }
        }

        return null;
    }

}
