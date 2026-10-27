package org.howard.edu.lsp.midterm.crccards;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages a collection of books, enforcing borrowing rules, inventory limits,
 * and search criteria.
 * 
 * @author Andres Huart
 */
public class Library {
    private Map<String, Book> books;
    private int borrowedBookCount;
    private static final int MAX_BORROWED_LIMIT = 3;

    /**
     * Constructs an empty Library.
     */
    public Library() {
        this.books = new HashMap<>();
        this.borrowedBookCount = 0;
    }

    /**
     * Adds a book to the library. Duplicate ISBNs are prohibited.
     * 
     * @param book the book to add
     * @throws IllegalArgumentException if a book with the same ISBN already exists
     */
    public void addBook(Book book) {
        if (books.containsKey(book.getIsbn())) {
            throw new IllegalArgumentException("Book with ISBN " + book.getIsbn() + " already exists.");
        }
        books.put(book.getIsbn(), book);
    }

    /**
     * Finds and returns a book by its ISBN.
     * 
     * @param isbn the ISBN to search for
     * @return the matching Book instance, or null if absent
     */
    public Book findBook(String isbn) {
        return books.get(isbn);
    }

    /**
     * Borrows a book specified by its ISBN, enforcing the library borrowing limit.
     * 
     * @param isbn the ISBN of the book to borrow
     * @throws IllegalArgumentException if the ISBN is not in the library
     * @throws IllegalStateException    if the book is already borrowed or the borrowing limit is reached
     */
    public void borrowBook(String isbn) {
        Book book = books.get(isbn);
        if (book == null) {
            throw new IllegalArgumentException("Book with ISBN " + isbn + " not found in library.");
        }
        if (!book.isAvailable()) {
            throw new IllegalStateException("Book is already borrowed.");
        }
        if (borrowedBookCount >= MAX_BORROWED_LIMIT) {
            throw new IllegalStateException("Library borrowing limit of " + MAX_BORROWED_LIMIT + " reached.");
        }
        
        book.borrowBook();
        borrowedBookCount++;
    }

    /**
     * Returns a borrowed book specified by its ISBN.
     * 
     * @param isbn the ISBN of the book to return
     * @throws IllegalArgumentException if the ISBN is not in the library
     * @throws IllegalStateException    if the book is already available
     */
    public void returnBook(String isbn) {
        Book book = books.get(isbn);
        if (book == null) {
            throw new IllegalArgumentException("Book with ISBN " + isbn + " not found in library.");
        }
        if (book.isAvailable()) {
            throw new IllegalStateException("Book is already available.");
        }
        
        book.returnBook();
        borrowedBookCount--;
    }

    /**
     * Retrieves all currently available books in the library.
     * 
     * @return a list of available books
     */
    public List<Book> getAvailableBooks() {
        List<Book> availableList = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.isAvailable()) {
                availableList.add(book);
            }
        }
        return availableList;
    }

    /**
     * Retrieves all books matching the specified author (case-sensitive).
     * 
     * @param author the author name to search for
     * @return a list of matching books (including borrowed ones)
     */
    public List<Book> getBooksByAuthor(String author) {
        List<Book> authorList = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getAuthor().equals(author)) {
                authorList.add(book);
            }
        }
        return authorList;
    }

    /**
     * Retrieves all books sorted by ISBN in ascending lexicographical order.
     * 
     * @return a sorted list of all books
     */
    public List<Book> getBooksSortedByIsbn() {
        List<Book> sortedList = new ArrayList<>(books.values());
        sortedList.sort(Comparator.comparing(Book::getIsbn));
        return sortedList;
    }

    /**
     * Returns the current number of borrowed books in the library.
     * 
     * @return the borrowed book count
     */
    public int getBorrowedBookCount() {
        return borrowedBookCount;
    }
}