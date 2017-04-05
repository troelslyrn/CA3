/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import facades.UserFacade;
import java.util.List;
import javax.persistence.Persistence;
import security.IUser;

/**
 *
 * @author Peter
 */
public class TestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        UserFacade fac = new UserFacade(Persistence.createEntityManagerFactory("pu_development"));
        
        List<IUser> users = fac.getAllUsers();
        System.out.println(users);
    }
    
}
