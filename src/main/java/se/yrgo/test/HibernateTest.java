package se.yrgo.test;

import java.time.*;
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

    public static void setUpData(EntityManager em) {
        Author author1 = new Author("Pedro Pascal", "Chile");
        Author author2 = new Author("Toshikazu Kawaguchi", "Japan");
        Author author3 = new Author("Matt LeBlanc", "America");

        // author1.createBookAndAddToBooks("title1", "genre1", Year.of(2001));
        // author1.createBookAndAddToBooks("title2", "genre2", Year.of(2002));
        // author2.createBookAndAddToBooks("title3", "genre3", Year.of(2003));
        // author3.createBookAndAddToBooks("title4", "genre4", Year.of(2004));

        Reader reader1 = new Reader("name1", "email1@email.com");
        Reader reader2 = new Reader("name2", "email2@email.com");
        Reader reader3 = new Reader("name3", "email3@email.com");

        em.persist(reader1);
        em.persist(reader2);
        em.persist(reader3);

        Book book1 = new Book("title1", "genre1", Year.of(2001));
        Book book2 = new Book("title2", "genre2", Year.of(2002));
        Book book3 = new Book("title3", "genre3", Year.of(2003));
        Book book4 = new Book("title4", "genre4", Year.of(2004));
        Book book5 = new Book("title5", "genre5", Year.of(2005));

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

        em.persist(book1);
        em.persist(book2);
        em.persist(book3);
        em.persist(book4);
        em.persist(book5);

        author1.addBook(book1);
        author2.addBook(book2);
        author3.addBook(book3);
        author3.addBook(book4);
        author3.addBook(book5);

        em.persist(author1);
        em.persist(author2);
        em.persist(author3);

        reader1.addBookToReader(book1);
        reader2.addBookToReader(book1);
        reader2.addBookToReader(book2);
        reader3.addBookToReader(book3);
        reader3.addBookToReader(book4);

        // List<Author> authors = em.createQuery("FROM Author",
        // Author.class).getResultList();
        // for (Author author : authors) {
        // System.out.println("All authors:");
        // System.out.println(author);
        // }

        // // get all books from a specifik author
        // Query q1 = em.createQuery(
        // "select b from Author as a join a.books as b where lower(a.name) = 'pedro
        // pascal'");
        // List<Book> booksByPedroPascal = q1.getResultList();
        // for (Book book : booksByPedroPascal) {
        // System.out.println("Books by Pedro Pascal: " + book);
        // }

        // // get all reader that has read a specifik book
        // Book findBook = em.find(Book.class, 4);
        // Query q2 = em.createQuery("from Reader as r where :title member of r.books");

        // q2.setParameter("title", findBook);

        // List<Reader> readersThatReadTitle1 = q2.getResultList();
        // for (Reader reader : readersThatReadTitle1) {
        // System.out.println("Readers that read title1: " + reader);
        // }

        // get authors that at least one of their books has been read atleast once

        // Query q3 = em.createQuery(
        // "select distinct a from Author as a join a.books as b where b.reader is not
        // empty");

        Query q3 = em.createQuery(
                "select distinct a from Author as a join a.books as b join b.readers as r ", Author.class);

        // Query q3 = em.createQuery(
        // "select distinct a from Author as a join a.books as b where b.reader is not
        // empty");

        List<Author> getAuthorWithReadBooks = q3.getResultList();

        for (Author author : getAuthorWithReadBooks) {
            System.out.println(author);
        }

    }

}