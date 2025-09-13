// File: service/Library.java
package service;

import model.Book;
import java.io.*;
import java.util.*;

public class Library {
    private Map<String, Book> inventory = new HashMap<>();

    public void addBook(Book book) {
        inventory.put(book.getIsbn(), book);
        System.out.println("Book added successfully!");
    }

    public void removeBook(String isbn) {
        if (inventory.remove(isbn) != null) {
            System.out.println("Book removed successfully!");
        } else {
            System.out.println("Book not found.");
        }
    }

    public Book searchByISBN(String isbn) {
        return inventory.get(isbn);
    }

    public List<Book> searchByTitle(String keyword) {
        List<Book> results = new ArrayList<>();
        for (Book b : inventory.values()) {
            if (b.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(b);
            }
        }
        return results;
    }

    public void listAllBooks() {
        if (inventory.isEmpty()) {
            System.out.println("No books in inventory.");
            return;
        }
        for (Book b : inventory.values()) {
            System.out.println(b);
        }
    }

    public void saveToFile(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Book b : inventory.values()) {
                bw.write(b.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    Book b = new Book(parts[0], parts[1], parts[2]);
                    b.setAvailable(parts[3].equalsIgnoreCase("Available"));
                    inventory.put(b.getIsbn(), b);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }
}
