package library;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * RESTful Web Service
 * This is the root resource class
 * used to retrieve a single book,
 * update a single book,
 * or delete a single book.
 * 
 *
 * 
 * @author saeri + frede + carl
 * @version 1.0
 * @since 05-29-2019
 * 
 */
public class BookResource {

    private String id;

    /**
     * Creates a new instance of BookResource
     */
    private BookResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the BookResource
     * @param id
     * @return 
     */
    public static BookResource getInstance(String id) {
        return new BookResource(id);
    }

    /**
     * @param id
     * @return 
     * 
     * Retrieve a single book using id value
     */
      @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getBookById(String bookId) {
        bookId = id;
        BookEntity book = BookRepo.getInstance().getBook(Integer.valueOf(bookId));
        
        if (book == null) {
            return "Sorry, no book exists with that ID";
        }else{
            return book.toString(); 
        }
    }

    /**
     * PUT method for updating a book
     * @param id
     * @param title
     * @param description
     * @param isbn
     * @param author
     * @param publisher
     * @param servletResponse
   
     */
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateBook(@FormParam("id") String idString,
            @FormParam("title")String title, 
            @FormParam("description")String description, 
            @FormParam("isbn")String isbn, 
            @FormParam ("author")String author, 
            @FormParam("publisher")String publisher, 
            @Context HttpServletResponse servletResponse) {
        //This is a weird way to handle it but...that was simple.
        if(this.getBookById(idString).matches("Sorry, no book exists with that ID"))
        {
            return Response.
                    status(Response.Status.NOT_FOUND).entity("Sorry, no book exists with that ID").build();
        }
        else
        {
            Integer bookId = Integer.valueOf(idString);
            BookEntity updatedBook = BookRepo.getInstance().updateBook(bookId, title, 
                    description, isbn, author, publisher);
            return Response
                    .status(Response.Status.OK).entity(updatedBook.toString()).build();
        }
    }

    /**
     * DELETE method for deleting a book
     * @return 
     */
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public String delete() {
        boolean didDelete = BookRepo.getInstance().deleteBook(Integer.valueOf(id));
        
        if (didDelete) {
            return "**Success! Book has been deleted**";
        }else{
            return "**Error: Book not found**";
        }    
    }
}
