package pl.edu.pw.mini.zpoif.zespol9.System;

import pl.edu.pw.mini.zpoif.zespol9.Book.Book;
import pl.edu.pw.mini.zpoif.zespol9.People.Reader;
import pl.edu.pw.mini.zpoif.zespol9.People.SignInData;

public interface CheckOutDesk {

    void addBook(Book book);

    void deleteBook(Book book);

    void checkOutBook(String login, Book book);

    void acceptBookReturn(String login, Book book);

    SignInData addUser(String name, String surname);

    void imposeFine(Reader reader, double fine);
    void CheckOutBookFromReservedBooks(String login, Book book);
}
