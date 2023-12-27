package pl.edu.pw.mini.zpoif.zespol9.Book;

import java.util.Random;

public class Book {

    private Random random = new Random();

    public String title;
    public String author;
    public String description;
    public BookFormat bookFormat;
    public double bookRating;
    public int bookRatingCount;
    public int bookReviewCount;
    public int numberOfFollowers;
    public Genre genre;
    public BookCondition bookCondition;

    public Book(String title, String author, String description, double bookRating, int bookRatingCount, int bookReviewCount, int numberOfFollowers, Genre genre) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.bookRating = bookRating;
        this.bookRatingCount = bookRatingCount;
        this.bookReviewCount = bookReviewCount;
        this.numberOfFollowers = numberOfFollowers;
        this.genre = genre;

        setBookFormat();
        setBookCondition();

    }

    private void setBookFormat(){
        double pstwo = random.nextDouble();
        if (pstwo < 0.4){
            bookFormat = BookFormat.Softback;
        } else if (pstwo < 0.7) {
            bookFormat = BookFormat.Hardcover;
        } else if (pstwo < 0.9) {
            bookFormat = BookFormat.Ebook;
        } else {
            bookFormat = BookFormat.Clothbound;
        }
    }

    private void setBookCondition(){
        double pstwo = random.nextDouble();
        if(bookFormat == BookFormat.Ebook){
            bookCondition = BookCondition.NotApplicable;
        } else if (pstwo < 0.1){
            bookCondition = BookCondition.AsNew;
        } else if (pstwo < 0.7) {
            bookCondition = BookCondition.Good;
        } else if (pstwo < 0.95){
            bookCondition = BookCondition.Poor;
        } else {
            bookCondition = BookCondition.Damaged;
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", bookFormat=" + bookFormat +
                ", bookRating=" + bookRating +
                ", bookRatingCount=" + bookRatingCount +
                ", bookReviewCount=" + bookReviewCount +
                ", genre=" + genre +
                ", bookCondition=" + bookCondition +
                '}';
    }
}
