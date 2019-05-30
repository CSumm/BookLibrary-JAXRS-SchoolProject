package library;

import java.io.Serializable;

/**
 * This is the model class for BookEntity objects.
 * All appropriate getter and setters methods are implemented here;
 * BookEntity objects are identified with a primary key of Integer id.
 * 
 * @author saeri + frede + carl
 * @version 1.0
 * @since 05-29-2019
 * 
 */
public class BookEntity implements Serializable{
    Integer id;
    String title;
    String description;
    String isbn;
    String author;
    String publisher;
   
    
    public BookEntity (Integer id, String title, String description, String isbn, 
            String author, String publisher) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isbn = isbn;
        this.author = author;
        this.publisher = publisher;
    }
    public Integer getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    
    public String getIsbn() {
        return isbn;
    }
    public String getAuthor() {
        return author;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
     public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Override this method to return all BookEntity properties
     * concatenated in a single String
     * 
     * @return
     */
    @Override
    public String toString() {
        return "\n\nId: " + id.toString() + "\nTitle: " + title + "\nDescription: "
                + description + "\nISBN: " + isbn + "\nAuthor: " + author + 
                "\nPublisher: " + publisher;
    }
}

