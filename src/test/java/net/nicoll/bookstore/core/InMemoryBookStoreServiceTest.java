package net.nicoll.bookstore.core;

import net.nicoll.bookstore.domain.Author;
import net.nicoll.bookstore.domain.Book;
import net.nicoll.bookstore.domain.BookBuilder;
import org.junit.Test;

import java.util.Collection;
import java.util.UUID;

import static com.sun.tools.internal.ws.wsdl.parser.Util.fail;
import static junit.framework.Assert.assertEquals;

/**
 * @author Stephane Nicoll
 */
public class InMemoryBookStoreServiceTest {

    @Test
    public void addBook() {
        final BookStoreService localService = new InMemoryBookStoreService();
        final String isbn = createRandomTestIsbn();
        final Book book = BookBuilder.forIsbn(isbn)
                .withTitle("Maghain Repos").withAuthor("John", "Doe", "john@doe.net")
                .withNumberOfPages(50).withPublisher("Red Cross").build();
        localService.addBook(book);
        assertEquals(book, localService.getBookByIsbn(isbn));
        assertEquals("John", localService.getAuthorById(0).getFirstName());
        assertBooksByAuthor(localService.getBooksByAuthor(0), book);
    }

    @Test
    public void addTwoBookSameAuthor() {
        final BookStoreService localService = new InMemoryBookStoreService();
        final String isbnOne = createRandomTestIsbn();
        final String isbnTwo = createRandomTestIsbn();

        final Author author = new Author("Robert", "Dupont", null);
        final Book firstBook = BookBuilder.forIsbn(isbnOne)
                .withTitle("Maghain Repos").withAuthor(author)
                .withNumberOfPages(50).withPublisher("Red Cross").build();
        final Book secondBook = BookBuilder.forIsbn(isbnTwo)
                .withTitle("Les maisons de repos").withAuthor(author)
                .withNumberOfPages(43).withPublisher("Yellow Cross").build();

        localService.addBook(firstBook);
        localService.addBook(secondBook);
        assertEquals(firstBook, localService.getBookByIsbn(isbnOne));
        assertEquals(secondBook, localService.getBookByIsbn(isbnTwo));

        assertEquals(author, localService.getAuthorById(0));
        assertBooksByAuthor(localService.getBooksByAuthor(0), firstBook, secondBook);
    }

    @Test
    public void createFakeData() {
        final InMemoryBookStoreService service = new InMemoryBookStoreService();
        service.createFakeData();
    }


    protected void assertBooksByAuthor(Collection<Book> actual, Book... expectedBooks) {
        for (Book expectedBook : expectedBooks) {
            if (!actual.contains(expectedBook)) {
                fail("Expected book [" + expectedBook + "] was not found in [" + actual + "]");
            }
        }
        assertEquals("Wrong number of returned book", expectedBooks.length, actual.size());
    }


    protected String createRandomTestIsbn() {
        return UUID.randomUUID().toString();
    }
}
