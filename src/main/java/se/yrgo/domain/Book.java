package se.yrgo.domain;

import java.time.*;
import java.util.*;

import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 20)
    private String title;
    @Column(length = 20)
    private String genre;
    private Year publicationYear;

    @ManyToMany(mappedBy = "books")
    private List<Reader> readers = new ArrayList<Reader>();

    public Book() {
    }

    public Book(String title, String genre, Year publicationYear) {
        this.title = title;
        this.genre = genre;
        this.publicationYear = publicationYear;
    }

    public void addReaderToBook(Reader reader) {
        this.readers.add(reader);
        // tutor.getSubjects().add(this);
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public Year getPublicationYear() {
        return publicationYear;
    }

    public List<Reader> getReaders() {
        return readers;
    }

    @Override
    public String toString() {
        return "Book [title=" + title + ", genre=" + genre + ", publicationYear=" + publicationYear + ", readers="
                + readers + "]";
    }
}
