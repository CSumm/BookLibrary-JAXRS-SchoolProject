package library;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.PathParam;
import javax.ws.rs.POST;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
/**
 * RESTful Web Service 
 * BooksResource.java is a container class
 * that holds the BookResouce.java resource file.
 * 
 * Here we can access a list of all books,
 * and we are able to add a book into the repository.
 *
 * @author saeri + frede + carl
 * @version 1.0
 * @since 2019-05-29
 */
@Path("/books")
public class BooksResource {

    public BooksResource() {
    }
    /**
     * In this method, we retrieve all books and return them as String
     * If the database is empty, we return a String message instead
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getBooks() {
        if (BookRepo.getInstance().getAllBooks().isEmpty()) {
            return "Book Database is Empty";
        }
        return BookRepo.getInstance().getAllBooks().toString();
    }

    /**
     * POST method for creating an instance of BookResource
     *
     * 
     * @param title
     * @param description
     * @param isbn
     * @param servletResponse
     * @param author
     * @param publisher
     */
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String addBook(@FormParam("title")String title, 
            @FormParam("description")String description, 
            @FormParam("isbn")String isbn, 
            @FormParam ("author")String author, 
            @FormParam("publisher")String publisher, 
            @Context HttpServletResponse servletResponse){
        return BookRepo.getInstance().createBook(title, description, isbn, author, publisher).toString();
    }

    /**
     * Sub-resource locator method for {id}
     * @param id
     * @return 
     * 
     * Here we use an id endpoint to access the root resource file,
     * BookResource.java
     */
    @Path("{id}")
    public BookResource getBookResource(@PathParam("id") String id) {
        return BookResource.getInstance(id);
    }
}