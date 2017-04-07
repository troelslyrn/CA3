package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import entity.User;
import facades.UserFacade;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import security.IUser;
import security.PasswordStorage;

@Path("demouser")
@RolesAllowed("Admin")
public class UserResource {

    @Context
    private UriInfo context;

    UserFacade fac = new UserFacade(Persistence.createEntityManagerFactory("pu_development", null));
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public UserResource() {

    }

    @GET
    @Path("complete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUser() {
        List<IUser> users = fac.getAllUsers();
        JsonArray jar = new JsonArray();
        for (IUser user : users) {
            JsonObject job = new JsonObject();
            job.addProperty("username", user.getUserName());
            JsonArray roles = new JsonArray();

            job.add("roles", roles);
            List<String> r = user.getRolesAsStrings();
            if (r != null) {
                for (String role : user.getRolesAsStrings()) {
                    roles.add(role);
                }
            }
            jar.add(job);
        }
        return Response.status(Response.Status.OK).entity(gson.toJson(jar)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(String userJSON) {
        User user = gson.fromJson(userJSON, User.class);
        try {
            String password = PasswordStorage.createHash(user.getPassword());
            user.setPassword(password);
        } catch (PasswordStorage.CannotPerformOperationException ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        User u = fac.createUser(user);
        return Response.status(Response.Status.OK).entity(gson.toJson(u)).build();
        //return Response.status(Response.Status.OK).entity(userJSON).build();
    }
     @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") String id) {
        User user = fac.getUserByUserId(id);
        
        fac.deleteUser(user);
        return Response.status(Response.Status.OK).entity(gson.toJson(user)).build();
    }
}
