// Main.java
public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        System.out.println("New line");
        // Add books
        library.addBook(new Book("The Hobbit", "J.R.R. Tolkien"));
        library.addBook(new Book("1984", "George Orwell"));

        // Register members
        library.registerMember(new Member("Alice"));
        library.registerMember(new Member("Bob"));

        // Borrow and return
        library.listBooks();
        library.borrowBook("The Hobbit", "Alice");
        library.listBooks();
        library.returnBook("The Hobbit");
        library.listBooks();
    }
}
