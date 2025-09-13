// File: Main.java
import model.Book;
import service.Library;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        library.loadFromFile("data/books.csv");

        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nLibrary Inventory Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Search by ISBN");
            System.out.println("4. Search by Title");
            System.out.println("5. List All Books");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter ISBN: ");
                    String isbn = sc.nextLine();
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    library.addBook(new Book(isbn, title, author));
                    break;
                case 2:
                    System.out.print("Enter ISBN to remove: ");
                    library.removeBook(sc.nextLine());
                    break;
                case 3:
                    System.out.print("Enter ISBN to search: ");
                    Book b = library.searchByISBN(sc.nextLine());
                    System.out.println(b != null ? b : "Book not found.");
                    break;
                case 4:
                    System.out.print("Enter title keyword: ");
                    List<Book> results = library.searchByTitle(sc.nextLine());
                    if (results.isEmpty()) System.out.println("No books found.");
                    else results.forEach(System.out::println);
                    break;
                case 5:
                    library.listAllBooks();
                    break;
                case 6:
                    library.saveToFile("data/books.csv");
                    System.out.println("Exiting. Data saved.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 6);
        sc.close();
    }
}
