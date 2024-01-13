package pl.edu.pw.mini.zpoif.zespol9.People;


import pl.edu.pw.mini.zpoif.zespol9.Book.Book;
import pl.edu.pw.mini.zpoif.zespol9.Book.Status;
import pl.edu.pw.mini.zpoif.zespol9.System.CheckOutDesk;
import pl.edu.pw.mini.zpoif.zespol9.System.SystemAccess;

import java.time.LocalDate;


public class Librarian extends Person implements CheckOutDesk {

    private final SystemAccess systemAccess;

    public Librarian(String name, String surname, SystemAccess systemAccess) {
        super(name, surname);
        this.signInData.setLogin("l" + this.signInData.getLogin());
        this.systemAccess = systemAccess;
    }

    @Override
    public void addBook(Book book) {
        systemAccess.getCatalogue().getCatalogue().add(book);
    }

    @Override
    public void deleteBook(Book book) {
        systemAccess.getCatalogue().getCatalogue().remove(book);
    }

    @Override
    public void checkOutBook(String login, Book book) {
        Reader reader = systemAccess.getReader(login);
        book.status = Status.CheckedOut;
        reader.getCheckedOutBooks().put(book, LocalDate.now());
    }

    @Override
    public void acceptBookReturn(String login, Book book) {

    }

    @Override
    public SignInData addUser(String name, String surname) {
        return null;
    }

    @Override
    public void imposeFine(Reader reader) {

    }
}
