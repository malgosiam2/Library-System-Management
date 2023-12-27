package pl.edu.pw.mini.zpoif.zespol9.Testy;

import pl.edu.pw.mini.zpoif.zespol9.Book.Book;
import pl.edu.pw.mini.zpoif.zespol9.Catalogue.BooksParser;

import java.util.List;

public class Main {

    //Main na potrzeby testów

    public static void main(String[] args) {

        // DZIALA!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        List<Book> catalogue = BooksParser.parseBooks();

        for(Book book : catalogue){
            System.out.println(book.toString());
        }

    }
}

