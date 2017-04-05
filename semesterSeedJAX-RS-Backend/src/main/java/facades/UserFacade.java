package facades;

import entity.Book;
import security.IUserFacade;
import entity.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import security.IUser;
import security.PasswordStorage;

public class UserFacade implements IUserFacade {

    EntityManagerFactory emf;

    public UserFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public IUser getUserByUserId(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    /*
  Return the Roles if users could be authenticated, otherwise null
     */
    @Override
    public List<String> authenticateUser(String userName, String password) {
        IUser user = getUserByUserId(userName);

        try {
            if (user != null && PasswordStorage.verifyPassword(password, user.getPassword())) {
                return user.getRolesAsStrings();
            }
        } catch (PasswordStorage.CannotPerformOperationException | PasswordStorage.InvalidHashException ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<IUser> getAllUsers() {
        EntityManager em = getEntityManager();
        List<IUser> users;
        try {
            users = em.createQuery("SELECT u FROM entity.User u").getResultList();
        } finally {
            em.close();
        }
        return users;
    }

    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_development");
         UserFacade uf = new UserFacade(emf);
        List<IUser> users = uf.getAllUsers();
       
        users.stream().forEach((user)->{System.out.println(user.getUserName());});
    }
}
