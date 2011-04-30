package net.nicoll.bookstore.core;

import net.nicoll.bookstore.domain.Author;
import net.nicoll.bookstore.domain.Book;

import java.util.Collection;

/**
 * @author Stephane Nicoll
 */
public interface BookStoreService {

    public Book getBookByIsbn(String isbn);

    public Collection<Book> getBooksByAuthor(long authorId);

    public Collection<Book> getAllBooks();

    public Collection<Author> getAllAuthors();

    public Author getAuthorById(long authorId);

    public void addBook(Book book);
}
