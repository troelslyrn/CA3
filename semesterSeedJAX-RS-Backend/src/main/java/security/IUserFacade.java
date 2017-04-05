/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import entity.User;
import java.util.List;

/**
 *
 * @author lam
 */
public interface IUserFacade {
    User createUser (User u);
    List<User> getUsers();
    User editUser(User u);
    User deleteUser(User u);
    
    /*
    Return the Roles if users could be authenticated, otherwise null
     */
    List<String> authenticateUser(String userName, String password);

    IUser getUserByUserId(String id);
    List<IUser> getAllUsers();
    
}
