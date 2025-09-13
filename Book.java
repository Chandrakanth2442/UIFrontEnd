// Book.java
class Book {
    private String title;
    private String author;
    private boolean isBorrowed;
    private boolean isReturned;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
        this.isReturned=false;
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
