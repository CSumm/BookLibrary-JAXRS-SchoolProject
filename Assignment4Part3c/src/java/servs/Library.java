package servs;

import javax.servlet.ServletContext;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * <h1>Book service</h1>
 * <p>
 * This code is used to access the library.</p>
 *
 * @author Olivier
 * @version 1
 * @since 2019-05-26
 *
 */
@Path("/")
public class Library {

    @Context
    private ServletContext sctx;

    /**
     * <p>
     * The getHello method is used to test the server in the most simple way
     * possible. A string is send to the user when the server is running.
     * </p>
     *
     * @return
     */
    @GET
    @Produces({MediaType.TEXT_PLAIN})
    @Path("/hello")
    public String getHello() {

        return "The server is alive! \n";
    }

    @GET
    @Produces({MediaType.TEXT_XML})
    @Path("/xml")
    public String getXml() {

        return "<book>\n"
                + "\n"
                + "\n"
                + "<author>\n"
                + "\n"
                + "<fn>First Name</fn>\n"
                + "\n"
                + "<ln>Last Name</ln>\n"
                + "\n"
                + "</author>\n"
                + "\n"
                + "<publisher>Original Publisher</publisher>\n"
                + "\n"
                + "<title>Original Title</title>\n"
                + "\n"
                + "</book>";
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/json")
    public String getJson() {

        return "{\"title\":\"My Title/1 1\",\"description\":\"My Description 1\",\"isbn\":\"My Isbn 1\",\"author\":\"My author 1\",\"publisher\":\"My publisher 1\"}";
    }

    @GET
    @Produces({MediaType.TEXT_PLAIN})
    @Path("/plain")
    public String getPlain() {

        return "This is a book info";
    }

    @GET
    @Produces({MediaType.TEXT_PLAIN})
    @Path("/search/{title}/{description}/{publisher}")
    public String getBook(@PathParam("title") String title,
            @PathParam("description") String description,
            @PathParam("publisher") String publisher) {

        return "This the book info searched:\n" +
                "Title: " + title + "\n" +
                "Description: " + description + "\n" +
                "Publisher: " + publisher;
    }
}
