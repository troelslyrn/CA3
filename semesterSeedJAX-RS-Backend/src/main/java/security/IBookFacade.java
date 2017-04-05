/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import entity.Book;
import java.util.List;

/**
 *
 * @author Peter
 */
public interface IBookFacade {
    
    Book createBook(Book book);
    Book getBookById(int id);
    List<Book> getBooks();
    Book editBook(Book book);
    Book deleteBook(Book book);
}
