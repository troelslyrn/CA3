/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entity.Book;
import facades.BookFacade;
import java.util.List;
import javax.persistence.Persistence;

/**
 *
 * @author Peter
 */
public class TestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        BookFacade fac = new BookFacade(Persistence.createEntityManagerFactory("pu_development"));
        
        List<Book> books = fac.getBooks();
        System.out.println(books);
    }
    
}
