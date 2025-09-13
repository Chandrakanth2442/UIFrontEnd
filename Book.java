import java.util.Date;
import java.util.HashMap;

// Book.java
class Book {
    private String title;
    private String author;
    private boolean isBorrowed;
    private boolean isReturned;
    private Date date;
    HashMap<String, String> map;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
        this.isReturned=false;
        date=new Date();
        Object obj=new Object();
        this.title = title;
        this.author = author;

    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isBorrowed() { return isBorrowed; }

    public void borrowBook() { isBorrowed = true; }
    public void returnBook() { isBorrowed = false; }

    @Override
    public String toString() {
        return title + " by " + author + (isBorrowed ? " (Borrowed)" : " (Available)");
    }
}
