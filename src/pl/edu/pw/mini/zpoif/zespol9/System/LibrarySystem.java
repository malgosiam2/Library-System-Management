package pl.edu.pw.mini.zpoif.zespol9.System;

import pl.edu.pw.mini.zpoif.zespol9.Catalogue.Catalogue;
import pl.edu.pw.mini.zpoif.zespol9.People.Librarian;
import pl.edu.pw.mini.zpoif.zespol9.People.Reader;

import java.util.ArrayList;
import java.util.List;

public class LibrarySystem implements SystemAccess, CatalogueAccess {

    private Catalogue catalogue = new Catalogue();
    private List<Reader> readerList = new ArrayList<>();
    private Librarian librarian = new Librarian("Bilbo", "Baggins");

    public LibrarySystem(){
        createLibrarian();
        createReaders();
    }

    private void createLibrarian() {
        System.out.println(librarian.getSignInData().getLogin());
        System.out.println(librarian.getSignInData().getPassword());
    }

    private void createReaders() {
        Reader reader1 = new Reader("Harry", "Potter", this);
        readerList.add(reader1);
        System.out.println(reader1.getSignInData().getLogin());
        System.out.println(reader1.getSignInData().getPassword());
        Reader reader2 = new Reader("Herkules", "Poirot", this);
        readerList.add(reader2);
        System.out.println(reader2.getSignInData().getLogin());
        System.out.println(reader2.getSignInData().getPassword());
        Reader reader3 = new Reader("Anna", "Shirley", this);
        readerList.add(reader3);
        System.out.println(reader3.getSignInData().getLogin());
        System.out.println(reader3.getSignInData().getPassword());
        Reader reader4 = new Reader("Romeo", "Monteki", this);
        readerList.add(reader4);
        System.out.println(reader4.getSignInData().getLogin());
        System.out.println(reader4.getSignInData().getPassword());
        Reader reader5 = new Reader("Jo", "March", this);
        readerList.add(reader5);
        System.out.println(reader5.getSignInData().getLogin());
        System.out.println(reader5.getSignInData().getPassword());

    }

    public List<Reader> getReaderList() {
        return readerList;
    }

    @Override
    public Catalogue getCatalogue() {
        return catalogue;
    }

    @Override
    public void setCatalogue(Catalogue catalogue) {

    }

    @Override
    public Reader getReader(String login) {
        return null;
    }
}
