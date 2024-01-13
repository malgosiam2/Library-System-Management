package pl.edu.pw.mini.zpoif.zespol9.People;


import pl.edu.pw.mini.zpoif.zespol9.System.SystemAccess;


public class Librarian extends Person {

    private SystemAccess systemAccess;

    public Librarian(String name, String surname) {
        super(name, surname);
        this.signInData.setLogin("l" + this.signInData.getLogin());
    }

}
