package org.howard.edu.lsp.midterm.crccards;

/**
 * Represents a book in the Library Lending System, holding its ISBN, title, author,
 * and current availability status.
 * 
 * @author Andres Huart
 */
public class Book {
    private String isbn;
    private String title;
    private String author;
    private boolean available;

    /**
     * Constructs a new Book with the given ISBN, title, and author.
     * New books start as available.
     * 
     * @param isbn   the unique ISBN of the book
     * @param title  the title of the book
     * @param author the author of the book
     */
    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    /**
     * Returns the ISBN of the book.
     * 
     * @return the ISBN string
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Returns the title of the book.
     * 
     * @return the title string
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the author of the book.
     * 
     * @return the author string
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Checks whether the book is currently available for borrowing.
     * 
     * @return true if available, false otherwise
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Borrows the book by making it unavailable.
     * 
     * @throws IllegalStateException if the book is already borrowed
     */
    public void borrowBook() {
        if (!available) {
            throw new IllegalStateException("Book is already borrowed.");
        }
        this.available = false;
    }

    /**
     * Returns the book by making it available.
     * 
     * @throws IllegalStateException if the book is already available
     */
    public void returnBook() {
        if (available) {
            throw new IllegalStateException("Book is already available.");
        }
        this.available = true;
    }

    /**
     * Returns a string representation of the book in the format:
     * ISBN Title by Author [AVAILABLE] (or [BORROWED]).
     * 
     * @return formatted string representation
     */
    @Override
    public String toString() {
        String status = available ? "[AVAILABLE]" : "[BORROWED]";
        return isbn + " " + title + " by " + author + " " + status;
    }
}