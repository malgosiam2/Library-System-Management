package pl.edu.pw.mini.zpoif.zespol9.System;

import pl.edu.pw.mini.zpoif.zespol9.Catalogue.Catalogue;
import pl.edu.pw.mini.zpoif.zespol9.People.Reader;

public class LibrarySystem implements SystemAccess, CatalogueAccess{
    @Override
    public Catalogue getCatalogue() {
        return null;
    }

    @Override
    public void setCatalogue(Catalogue catalogue) {

    }

    @Override
    public Reader getReader(String login) {
        return null;
    }
}
