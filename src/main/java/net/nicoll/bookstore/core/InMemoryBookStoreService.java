package net.nicoll.bookstore.core;

import net.nicoll.bookstore.domain.Author;
import net.nicoll.bookstore.domain.Book;
import net.nicoll.bookstore.domain.BookBuilder;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @author Stephane Nicoll
 */
public class InMemoryBookStoreService implements BookStoreService {

    private final Logger logger = Logger.getLogger(InMemoryBookStoreService.class);

    // Now you clearly can see I was too lazy to setup Hibernate with H2
    private final Map<Long, Author> authorsById = new HashMap<Long, Author>();
    private final Map<Long, Book> booksById = new HashMap<Long, Book>();
    private final Map<String, Book> booksByIsbn = new HashMap<String, Book>();

    private long lastAuthorId = -1;
    private long lastBookId = -1;

    private final Object lock = new Object();


    @Override
    public Book getBookByIsbn(String isbn) {
        return booksByIsbn.get(isbn);
    }

    @Override
    public Collection<Book> getBooksByAuthor(long authorId) {
        // Full in memory map scan. How wonderful
        final List<Book> result = new ArrayList<Book>();
        for (Book book : booksById.values()) {
            for (Author author : book.getAuthors()) {
                if (author.getObjectId().equals(authorId)) {
                    result.add(book);
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public Collection<Book> getAllBooks() {
        return booksById.values();
    }

    @Override
    public Collection<Author> getAllAuthors() {
        return authorsById.values();
    }

    @Override
    public Author getAuthorById(long authorId) {
        return authorsById.get(authorId);
    }

    @Override
    public void addBook(Book book) {
        for (Author author : book.getAuthors()) {
            // Does not exist
            if (author.getObjectId() == null) {
                createAuthor(author);
            } else if (!author.equals(getAuthorById(author.getObjectId()))) {
                throw new IllegalStateException("Attempt to create an author with same id [" + author + "]");
            }
        }
        if (book.getObjectId() != null) {
            logger.warn("Ignoring user specified id for [" + book + "]");
        }
        createBook(book);
    }

    protected void createBook(Book book) {
        synchronized (lock) {
            final long id = ++lastBookId;
            book.setObjectId(id);
            booksById.put(id, book);
            if (book.getIsbn() != null) {
                booksByIsbn.put(book.getIsbn(), book);
            }
        }
    }

    protected void createAuthor(Author author) {
        synchronized (lock) {
            final long id = ++lastAuthorId;
            author.setObjectId(id);
            authorsById.put(id, author);
        }
    }


    @PostConstruct
    public void createFakeData() {
        final Author gordonEmery = new Author("Gordon", "Emery", "gordon.emery@gmail.com");
        final BookBuilder builder = BookBuilder
                .forIsbn("0907758509")
                .withAuthor(gordonEmery)
                .withNumberOfPages(80)
                .withTitle("Family Walks:NTH Wales Borderlands")
                .withPublisher("Scarthin Books");
        addBook(builder.build());
        addBook(builder.duplicate(builder, "0907758517")
                .withTitle("Walks In The Weald Of Kent And Sussex").build());
        addBook(builder.duplicate(builder, "0907758525")
                .withTitle("Family Walks In The North Yorkshire Dales").build());
        addBook(builder.duplicate(builder, "0907758541")
                .withTitle("Family Walks:Cardiff & Valleys").build());
        addBook(builder.duplicate(builder, "0907758568")
                .withTitle("Family Walks On The Isle Of Wight").build());
        addBook(builder.duplicate(builder, "0907758606")
                .withTitle("Family Walks In The New Forest").build());
    }
}
