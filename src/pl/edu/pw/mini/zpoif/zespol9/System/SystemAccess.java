package pl.edu.pw.mini.zpoif.zespol9.System;

import pl.edu.pw.mini.zpoif.zespol9.Catalogue.Catalogue;
import pl.edu.pw.mini.zpoif.zespol9.People.Reader;

public interface SystemAccess {
    Catalogue getCatalogue();
    void setCatalogue(Catalogue catalogue);
    Reader getReader(String login);
}
