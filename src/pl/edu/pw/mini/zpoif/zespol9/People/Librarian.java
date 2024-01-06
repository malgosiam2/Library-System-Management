package pl.edu.pw.mini.zpoif.zespol9.People;

import pl.edu.pw.mini.zpoif.zespol9.System.SystemAccess;

import java.util.ArrayList;
import java.util.List;

public class Librarian extends Person {

    private SystemAccess systemAccess;

    private static List<Librarian> librarianList = new ArrayList<>();

    public Librarian() {
        this.signInData.setLogin("l" + this.signInData.getLogin());
        librarianList.add(this);
    }

    public static List<Librarian> getLibrarianList() {
        return librarianList;
    }
}
