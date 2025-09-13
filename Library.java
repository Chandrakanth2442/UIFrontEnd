// Library.java
import java.util.*;

class Library {
    private List<Book> books = new ArrayList<>();
    private List<Member> members = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }
    public void registerMember(Member member) {
        members.add(member);
    }

    public void borrowBook(String title, String memberName) {
        System.out.println("Print logic");
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title) && !b.isBorrowed()) {
                b.borrowBook();
                System.out.println(memberName + " borrowed " + title);
                return;
            }
        }
        System.out.println("Book not available!");
        System.out.println("Book available upon request!");
    }

    public void returnBook(String title) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title) && b.isBorrowed()) {
                b.returnBook();
                System.out.println(title + " returned!");
                return;
            }
        }
        System.out.println("Invalid return attempt!");
        System.out.println("Changing request state");
    }

    public void listBooks() {
        for (Book b : books) {
            System.out.println(b);
        }
    }
}
