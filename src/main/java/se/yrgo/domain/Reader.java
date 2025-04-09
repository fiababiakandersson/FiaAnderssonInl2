package se.yrgo.domain;

import java.util.*;

import jakarta.persistence.*;

@Entity
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 20)
    private String name;
    @Column(length = 20)
    private String email;

    @ManyToMany
    private List<Book> books = new ArrayList<Book>();

    public Reader() {
    }

    public Reader(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void addBookToReader(Book book) {
        this.books.add(book);
        // tutor.getSubjects().add(this);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return "Reader [name=" + name + ", email=" + email + ", books=" + books + "]";
    }
}
