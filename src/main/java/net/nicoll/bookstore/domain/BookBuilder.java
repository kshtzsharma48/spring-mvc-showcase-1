package net.nicoll.bookstore.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Stephane Nicoll
 */
public class BookBuilder {

    private final String isbn;
    private String title;
    private int edition = 1;
    private int pages;
    private Date publicationDate;
    private final List<Author> authors = new ArrayList<Author>();
    private Publisher publisher;

    private BookBuilder(String isbn) {
        this.isbn = isbn;
    }

    public static BookBuilder forIsbn(String isbn) {
        return new BookBuilder(isbn);
    }

    public BookBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public BookBuilder withEdition(int edition) {
        this.edition = edition;
        return this;
    }

    public BookBuilder withNumberOfPages(int pages) {
        this.pages = pages;
        return this;
    }

    public BookBuilder withPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
        return this;
    }

    public BookBuilder withAuthor(String firstName, String lastName, String email) {
        final Author author = new Author(firstName, lastName, email);
        this.authors.add(author);
        return this;
    }

    public BookBuilder withAuthor(Author author) {
        this.authors.add(author);
        return this;
    }

    public BookBuilder withPublisher(String name) {
        this.publisher = new Publisher(name);
        return this;
    }

    public BookBuilder duplicate(BookBuilder other, String newIsbn) {
        final BookBuilder clone = new BookBuilder(newIsbn);
        clone.title = other.title;
        clone.edition = other.edition;
        clone.pages = other.pages;
        clone.publicationDate = other.publicationDate;
        clone.authors.addAll(other.authors);
        clone.publisher = other.publisher;
        return clone;
    }

    public Book build() {
        final Book book = new Book();
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setEdition(edition);
        book.setPages(pages);
        book.setPublicationDate(publicationDate);
        for (Author author : authors) {
            book.getAuthors().add(author);
        }
        book.setPublisher(publisher);
        return book;
    }
}
