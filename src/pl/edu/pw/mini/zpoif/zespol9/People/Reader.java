package pl.edu.pw.mini.zpoif.zespol9.People;

import pl.edu.pw.mini.zpoif.zespol9.Book.Book;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Reader extends Person {

    private List<Book> reservedBooks;
    private double fine;
    private Map<Book, LocalDate> checkedOutBooks;
    private List<Book> toReadBooks;

    public Reader(){
        this.signInData.setLogin("r" + this.signInData.getLogin());
    }

}
