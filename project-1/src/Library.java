public class Library {
    private Book[] books; // array-based implementation of the bag data structure
    private int numBooks; // the number of books currently in the bag

    public Library() { //default constructor to create an empty bag (Size 4)
        numBooks = 0;
        books = new Book[4];
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

    public void add(Book book) {
        int curr_bagSize = books.length;
        int i = 0;
        Date date = book.getDate();
        String name = book.getName();

        if(date.isValid() == true) {
            while (i < curr_bagSize && books[i] != null) {
                i++;
            }
            if (i > curr_bagSize) {
                grow();
                i++;
            }
            books[i] = book;
            numBooks++;
            System.out.println(name + " added to the bag.");
        }
        else {
            System.out.println("Invalid Date!");
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
        System.out.println("**List of books in the library.");
        for(int i = 0; i < numBooks; i++){
            System.out.println(books[i].toString());
        }

        System.out.println("**End of list");
    }

    public void printByDate() { //@todo print the list of books by datePublished (ascending)
        Date tempDate; // ok
        Date minDate;   //sure get year day and month exist in the date class btw
        Book[] changing = new Book[books.length];
        Book tempBook = changing[0];

        System.out.println("**List of books by the dates published.");

        for(int i=0; i < books.length; i++){
            changing[i] = books[i];
        }

        minDate = changing[0].getDate();
        int inside = 0;
        int outside = 0;
        for(outside = 0; outside < books.length; outside++) {
            for (inside = 0; inside < books.length; inside++) {
                if (changing[inside] == null) {
                    if(inside + 1 == books.length) {
                        break;
                    }
                    continue;
                }
                tempDate = changing[inside].getDate();
                if (minDate.getYear() > tempDate.getYear()) { //Compare years
                    minDate = changing[inside].getDate();
                    tempBook = changing[inside];
                    changing[inside] = changing[outside];
                    changing[outside] = tempBook;
                    break;
                } else if (minDate.getYear() == tempDate.getYear()) { //if Equal Years compare month
                    if (minDate.getMonth() > tempDate.getMonth()) { // Compare months
                        minDate = changing[inside].getDate();
                        tempBook = changing[inside];
                        changing[inside] = changing[outside];
                        changing[outside] = tempBook;
                        break;
                    } else if (minDate.getMonth() == tempDate.getMonth()) { //if Equal Months compare day
                        if (minDate.getDay() > tempDate.getDay()) { //Compare day
                            minDate = changing[inside].getDate();
                            tempBook = changing[inside];
                            changing[inside] = changing[outside];
                            changing[outside] = tempBook;
                            break;
                        }
                    }
                }
            }
        }

        for(int i = 0; i < books.length; i++){
            if(changing[i] == null) continue;
            System.out.println(changing[i].toString());
        }

        System.out.println("**End of list");
    }

    public void printByNumber() { //prints numbers in ascending order
        int curNum = Integer.MAX_VALUE;
        int tempNum;
        int lastNum = -1;
        int[] numbersArray = new int[numBooks];

        System.out.println("**List of books by the book numbers.");

        for(int i = 0; i < numBooks; i++){      //makes array of all books numbers
            numbersArray[i] = books[i].getNumber();
        }

        for(int i = 0; i < numBooks; i++) {         //compares numbers of all books to the last smallest
            int curNumPos = 0;
            for (int j = 0; j < numBooks; j++) {    //and finds the current smallest
                tempNum = numbersArray[j];
                if (tempNum > lastNum && tempNum < curNum && tempNum != -1) {
                    curNum = numbersArray[j];
                    curNumPos = j;
                }
            }
            lastNum = numbersArray[curNumPos];              //prints and sets new smallest and sets it to out of bounds
            numbersArray[i] = -1;
            System.out.println(books[curNumPos].toString());
        }
        System.out.println("**End of list");
    }
}
