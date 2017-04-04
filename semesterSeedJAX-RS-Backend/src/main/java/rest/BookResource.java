/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.BookFacade;
import java.awt.print.Book;
import java.util.List;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Troels
 */
@Path("book")
public class BookResource {

    @Context
    private UriInfo context;

    BookFacade fac = new BookFacade(Persistence.createEntityManagerFactory("pu", null));
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public BookResource() {
    }

    /**
     * Retrieves representation of an instance of rest.BookResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("complete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersons() {
        List<Book> books = fac.getBooks();
        return Response.status(Response.Status.OK).entity(gson.toJson(books)).build();
    }

    @GET
    @Path("complete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookById(@PathParam("id") int id) {
        Book book = fac.getBookById(id);
        return Response.status(Response.Status.OK).entity(gson.toJson(book)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBook(String bookJSON) {
        Book book = fac.createBook(gson.fromJson(bookJSON, Book.class));
        return Response.status(Response.Status.OK).entity(gson.toJson(book)).build();
    }

  @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBook(@PathParam("id") int id) {
        Book book = fac.deleteBook(fac.getBookById(id));
        return Response.status(Response.Status.OK).entity(gson.toJson(book)).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPerson(String personJSON) {
        Book book = gson.fromJson(personJSON, Book.class);
        book = fac.editBook(book);
        return Response.status(Response.Status.OK).entity(gson.toJson(book)).build();
    }
}
