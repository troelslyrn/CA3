package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import facades.UserFacade;
import java.util.List;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import security.IUser;

@Path("demouser")
//@RolesAllowed("User")
public class User {
     @Context
    private UriInfo context;

    UserFacade fac = new UserFacade(Persistence.createEntityManagerFactory("pu_development", null));
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public User() {
      
    }
  
    
  @GET
  @Path("complete")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAllUser(){
     List<IUser> users = fac.getAllUsers();
     JsonArray jar = new JsonArray();
      for (IUser user : users) {
          JsonObject job = new JsonObject();
          job.addProperty("username", user.getUserName());
          JsonArray roles = new JsonArray();
          job.add("roles", roles);
          for (String role : user.getRolesAsStrings()) {
              roles.add(role);
          }
          jar.add(job);
      }
        return Response.status(Response.Status.OK).entity(gson.toJson(jar)).build();
  }
  
 
}