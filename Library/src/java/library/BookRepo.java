package library;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A repository that takes care of storing and modifying all BookEntity data.
 * 
 * BookEntity objects are accessed through the application
 * only by this class.
 * BookEntity objects are stored inside a Concurrent HashmMap.
 * 
 * 
 * @author saeri + frede + carl
 * @version 1.0
 * @since 05-29-2019
 * 
 */

public class BookRepo {
    static BookRepo bookRepo = null;
    private int nextBookId = 0;
    ConcurrentHashMap<Integer,BookEntity> books;
    
    private BookRepo() {
        this.books = new ConcurrentHashMap();
    }
    //Method to create a book and store it into a ConcurrentHashMap
    
    public BookEntity createBook(String title, String description, 
            String isbn, String author, String publisher){
        BookEntity book = new BookEntity(nextBookId++, title, description, 
            isbn, author, publisher);
        this.books.put(book.getId(), book);
        return book;
    }
    //Method to retrieve a book by id
    public BookEntity getBook(Integer id) {
        BookEntity book = this.books.get(id);
        return book;
    }
    /**
     *
     * @return
     * 
     * Method to return all books inside ConcurrentHashMap
     * as an ArrayList of BookEntity objects
     */
    public ArrayList<BookEntity> getAllBooks() {
        ArrayList<BookEntity> allBooks = new ArrayList<>();
        
        for (BookEntity book: books.values()) {
            allBooks.add(book);
        }
        return allBooks;
    }
    //Method to update a single book.
    //All properties must be inputed
    public BookEntity updateBook(Integer id, String title, String description, 
            String isbn, String author, String publisher) {
        BookEntity book = getBook(id);
        
        if (book != null) {
            book.title = title;
            book.description = description;
            book.isbn = isbn;
            book.author = author;
            book.publisher = publisher;
        
            books.put(id, book);
        }
        return book;
    }
    //Method to delete a book using its id
    public boolean deleteBook(Integer id) {
        BookEntity book = getBook(id);
        
        if (book == null) {
            return false;
        }
        this.books.remove(id);
        return true;
    }
    
   //Static method to create a singleton of BookRepo for data persistency
    public static BookRepo getInstance() {
        if (bookRepo == null) {
            bookRepo = new BookRepo();
        }
        return bookRepo;
    }
}



   