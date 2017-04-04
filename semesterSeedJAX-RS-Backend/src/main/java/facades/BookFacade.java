/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import java.awt.print.Book;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import security.IBookFacade;

/**
 *
 * @author Troels
 */
public class BookFacade implements IBookFacade {

    EntityManagerFactory emf;

    public BookFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public Book createBook(Book book) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(book);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return book;
    }

    @Override
    public Book getBookById(int id) {
           EntityManager em = getEntityManager();
        Book book;
        try {
            book = em.find(Book.class, id);
        } finally {
            em.close();
        }
        return book;
    }

    @Override
    public List<Book> getBooks() {
        EntityManager em = getEntityManager();
        List<Book> books;
        try {
            books = em.createQuery("SELECT book FROM Book book").getResultList();
        } finally {
            em.close();
        }
        return books;
    }

    @Override
    public Book editBook(Book book) {
          EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(book);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return book;
    }

    @Override
    public Book deleteBook(Book book) {
         EntityManager em = getEntityManager();
        //Book book;
        try {
            em.getTransaction().begin();
            book = em.find(Book.class, book);
            em.remove(book);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return book;
    }

}
