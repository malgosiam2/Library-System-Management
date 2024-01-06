package pl.edu.pw.mini.zpoif.zespol9.People;

import pl.edu.pw.mini.zpoif.zespol9.Book.Book;
import pl.edu.pw.mini.zpoif.zespol9.System.CatalogueAccess;

import java.time.LocalDate;
import java.util.*;

public class Reader extends Person {

    private List<Book> reservedBooks = new ArrayList<>();
    private double fine;
    private Map<Book, LocalDate> checkedOutBooks = new LinkedHashMap<>();
    private List<Book> toReadBooks = new ArrayList<>();
    private static CatalogueAccess catalogueAccess;

    private static List<Reader> readerList = new ArrayList<>();

    public Reader(CatalogueAccess catalogueAccess){
        this.signInData.setLogin("r" + this.signInData.getLogin());
        this.catalogueAccess = catalogueAccess;
        readerList.add(this);
    }


    public static List<Reader> getReaderList() {
        return readerList;
    }

    public void reserveBook(Book book){
        book.available = false;
        reservedBooks.add(book);
    }

    public List<Book> getReservedBooks() {
        return reservedBooks;
    }

    public double getFine() {
        return fine;
    }

    public Map<Book, LocalDate> getCheckedOutBooks() {
        return checkedOutBooks;
    }

    public List<Book> getToReadBooks() {
        return toReadBooks;
    }

    public void postponeReturnDate(Book book){
        LocalDate returnDate = checkedOutBooks.get(book);
        LocalDate newReturnDate = returnDate.plusDays(30);
        checkedOutBooks.put(book, newReturnDate);
    }
}