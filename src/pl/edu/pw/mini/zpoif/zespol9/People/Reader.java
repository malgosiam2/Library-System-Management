package pl.edu.pw.mini.zpoif.zespol9.People;

import pl.edu.pw.mini.zpoif.zespol9.Book.Book;
import pl.edu.pw.mini.zpoif.zespol9.System.CatalogueAccess;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Reader extends Person {

    private List<Book> reservedBooks;
    private double fine;
    private Map<Book, LocalDate> checkedOutBooks;
    private List<Book> toReadBooks;
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
}