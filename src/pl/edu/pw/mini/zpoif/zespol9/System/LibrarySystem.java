package pl.edu.pw.mini.zpoif.zespol9.System;

import pl.edu.pw.mini.zpoif.zespol9.Book.Book;
import pl.edu.pw.mini.zpoif.zespol9.Catalogue.Catalogue;
import pl.edu.pw.mini.zpoif.zespol9.People.Librarian;
import pl.edu.pw.mini.zpoif.zespol9.People.Reader;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LibrarySystem implements SystemAccess, CatalogueAccess {

    private Catalogue catalogue = new Catalogue();
    private List<Reader> readerList = new ArrayList<>();
    private Librarian librarian = new Librarian("Bilbo", "Baggins", this);

    public LibrarySystem() {
        createReaders();
    }

    private void createReaders() {
        Reader reader1 = new Reader("Harry", "Potter", this);
        readerList.add(reader1);

        Reader reader2 = new Reader("Herkules", "Poirot", this);
        readerList.add(reader2);

        Reader reader3 = new Reader("Anna", "Shirley", this);
        readerList.add(reader3);

        Reader reader4 = new Reader("Romeo", "Monteki", this);
        readerList.add(reader4);

        Reader reader5 = new Reader("Jo", "March", this);
        readerList.add(reader5);
    }

    public Librarian getLibrarian() {
        return librarian;
    }

    public List<Reader> getReaderList() {
        return readerList;
    }

    @Override
    public Catalogue getCatalogue() {
        return catalogue;
    }

    @Override
    public Reader getReader(String login) {
        List<Reader> listReadersWithLogin = new LinkedList<>();
        readerList.forEach(r -> {
            if (r.getSignInData().getLogin() == login) {
                listReadersWithLogin.add(r);
            }
        });
        return listReadersWithLogin.get(0);
    }

    @Override
    public void addReader(Reader reader) {
        readerList.add(reader);
    }
}
