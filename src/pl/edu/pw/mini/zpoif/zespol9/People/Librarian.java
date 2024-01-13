package pl.edu.pw.mini.zpoif.zespol9.People;


import pl.edu.pw.mini.zpoif.zespol9.Book.Book;
import pl.edu.pw.mini.zpoif.zespol9.System.CheckOutDesk;
import pl.edu.pw.mini.zpoif.zespol9.System.SystemAccess;


public class Librarian extends Person implements CheckOutDesk {

    private SystemAccess systemAccess;

    public Librarian(String name, String surname) {
        super(name, surname);
        this.signInData.setLogin("l" + this.signInData.getLogin());
    }

    @Override
    public void addBook(Book book) {

    }

    @Override
    public void deleteBook(Book book) {

    }

    @Override
    public void checkOutBook(String login, Book book) {

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
