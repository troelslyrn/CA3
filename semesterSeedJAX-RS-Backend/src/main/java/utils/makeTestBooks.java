/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entity.Book;
import facades.BookFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Peter
 */
public class makeTestBooks {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("pu_development").createEntityManager();
        try {
            System.out.println("Creating TEST Books");
            if (em.find(Book.class, 1) == null) {
                em.getTransaction().begin();
                Book book1 = new Book();
                book1.setTitle("book1");
                book1.setInfo("test info 1");
                Book book2 = new Book();
                book2.setTitle("book2");
                book2.setInfo("test info 2");
                book2.setMoreInfo("test more info 1");
                em.persist(book1);
                em.persist(book2);
                em.getTransaction().commit();
                System.out.println("Created TEST Books");
            }
        } catch (Exception ex) {
            Logger.getLogger(BookFacade.class.getName()).log(Level.SEVERE, null, ex);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        
    }

}
