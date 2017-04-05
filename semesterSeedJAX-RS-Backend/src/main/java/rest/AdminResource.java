package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import facades.UserFacade;
import entity.User;
import java.util.List;
import javax.persistence.Persistence;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;

@Path("demoadmin")
@RolesAllowed("Admin")
public class Admin {

    
    UserFacade fac = new UserFacade(Persistence.createEntityManagerFactory("pu", null));
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
  
  @GET
  @Path("complete")
  @Produces(MediaType.APPLICATION_JSON)
  public String getSomething(){
    String now = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(new Date());
    return "{\"message\" : \"Hello Admin from server (call accesible by only authenticated ADMINS)\",\n"+"\"serverTime\": \""+now +"\"}"; 
  }
 


   @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(String userJSON) {
        User u = fac.createUser(gson.fromJson(userJSON, User.class));
        return Response.status(Response.Status.OK).entity(gson.toJson(u)).build();
    }

    @GET
    @Path("complete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        List<User> users = fac.getUsers();
        return Response.status(Response.Status.OK).entity(gson.toJson(users)).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editUser(String userJSON) {
        User u = gson.fromJson(userJSON, User.class);
        u = fac.editUser(u);
        return Response.status(Response.Status.OK).entity(gson.toJson(u)).build();
    }
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(String userJSON) {
        User u = gson.fromJson(userJSON, User.class);
        u = fac.deleteUser(u);
        return Response.status(Response.Status.OK).entity(gson.toJson(u)).build();
    }

}
