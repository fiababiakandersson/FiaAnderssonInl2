package se.yrgo.domain;

import java.util.*;

import jakarta.persistence.*;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 20)
    private String name;
    @Column(length = 20)
    private String nationality;

    @OneToMany
    private List<Book> books = new ArrayList<Book>();

    public Author() {
    }

    public Author(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public void createBookAndAddToBooks(String title, String genre, int year) {
        Book book = new Book(title, genre, year);
        books.add(book);
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public List<Book> getbooks() {
        return books;
    }

    @Override
    public String toString() {
        return "Author [name=" + name + ", nationality=" + nationality + "]";
    }
}
