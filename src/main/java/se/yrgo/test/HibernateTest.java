package se.yrgo.test;

import java.util.*;

import jakarta.persistence.*;
import se.yrgo.domain.*;

public class HibernateTest {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("databaseConfig");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        setUpData(em);

        tx.commit();
        em.close();
    }

    // get all books from a specifik author
    private static List<Book> question2(EntityManager em) {
        Query q1 = em.createQuery(
                "select b from Author as a join a.books as b where lower(a.name) = 'pedro pascal'");
        return q1.getResultList();
    }

    // get all reader that has read a specifik book
    private static List<Reader> question3(EntityManager em) {

        Book findBook = em.find(Book.class, 4);
        Query q2 = em.createQuery("from Reader as r where :title member of r.books");

        q2.setParameter("title", findBook);

        return q2.getResultList();
    }

    // get authors that at least one of their books has been read atleast once
    private static List<Author> question4(EntityManager em) {
        Query q3 = em.createQuery(
                "select distinct a from Author as a join a.books as b join b.readers as r ",
                Author.class);

        return q3.getResultList();
    }

    private static List<Object[]> question5(EntityManager em) {
        Query q4 = em.createQuery(
                "select a.name, count(b) from Author as a join a.books as b group by a.name");

        return q4.getResultList();
    }

    private static List<Book> question6(EntityManager em) {
        String genre = "genre1";

        Query q5 = em.createNamedQuery("findGenre", Book.class).setParameter("genre", genre);

        return q5.getResultList();
    }

    public static void setUpData(EntityManager em) {
        Author author1 = new Author("Pedro Pascal", "Chile");
        Author author2 = new Author("Toshikazu Kawaguchi", "Japan");
        Author author3 = new Author("Matt LeBlanc", "America");

        Reader reader1 = new Reader("name1", "email1@email.com");
        Reader reader2 = new Reader("name2", "email2@email.com");
        Reader reader3 = new Reader("name3", "email3@email.com");

        Book book1 = new Book("title1", "genre1", 2001);
        Book book2 = new Book("title2", "genre2", 2002);
        Book book3 = new Book("title3", "genre3", 2003);
        Book book4 = new Book("title4", "genre4", 2004);
        Book book5 = new Book("title5", "genre5", 2005);

        book1.addReaderToBook(reader1);
        book1.addReaderToBook(reader2);
        book2.addReaderToBook(reader2);
        book3.addReaderToBook(reader3);
        book4.addReaderToBook(reader3);

        reader1.addBookToReader(book1);
        reader2.addBookToReader(book1);
        reader2.addBookToReader(book2);
        reader3.addBookToReader(book3);
        reader3.addBookToReader(book4);

        author1.addBook(book1);
        author2.addBook(book2);

        em.persist(book1);
        em.persist(book2);
        em.persist(book3);
        em.persist(book4);
        em.persist(book5);

        em.persist(reader1);
        em.persist(reader2);
        em.persist(reader3);

        em.persist(author1);
        em.persist(author2);
        em.persist(author3);

        // List<Author> authors = em.createQuery("from Author",
        // Author.class).getResultList();
        // for (Author author : authors) {
        // System.out.println("All authors:");
        // System.out.println(author);
        // }

        // change method below for different query results!
        // for (Object o : question6(em)) {
        // System.out.println(o);
        // }

        // loop for question 5!
        for (Object[] o : question5(em)) {
            System.out.print(o[0] + " ");
            System.out.println(o[1]);
        }
    }
}