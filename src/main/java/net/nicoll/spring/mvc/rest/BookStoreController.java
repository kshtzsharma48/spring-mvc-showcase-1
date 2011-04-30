package net.nicoll.spring.mvc.rest;

import net.nicoll.bookstore.core.BookStoreService;
import net.nicoll.bookstore.domain.Author;
import net.nicoll.bookstore.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

/**
 * A very dumb REST controller.
 * <p/>
 * All urls that this controller serves are in the '/api/v1/bookstore' space.
 *
 * @author Stephane Nicoll
 */
@Controller
@RequestMapping("/api/v1/bookstore")
public class BookStoreController {

    // TODO handle not found exception & stuff

    @Autowired
    BookStoreService bookService;

    @RequestMapping(value = "/books")
    public ModelAndView getAllBooks() {
        Collection<Book> books = bookService.getAllBooks();
        ModelAndView mav = new ModelAndView("books/show-all");
        mav.addObject("books", books);
        return mav;
    }

    @RequestMapping(value = "/books/{isbn}")
    public ModelAndView getBookByIsbn(@PathVariable String isbn) {
        Book book = bookService.getBookByIsbn(isbn);
        ModelAndView mav = new ModelAndView("books/show");
        mav.addObject("book", book);
        return mav;
    }

    @RequestMapping(value = "/authors")
    public ModelAndView getAllAuthors() {
        Collection<Author> authors = bookService.getAllAuthors();
        ModelAndView mav = new ModelAndView("authors/show-all");
        mav.addObject("authors", authors);
        return mav;
    }

    @RequestMapping(value = "/authors/{authorId}")
    public ModelAndView getAuthorById(@PathVariable long authorId) {
        Author author = bookService.getAuthorById(authorId);
        ModelAndView mav = new ModelAndView("authors/show");
        mav.addObject("author", author);
        return mav;
    }

    @RequestMapping(value = "/authors/{authorId}/books")
    public ModelAndView getBooksByAuthor(@PathVariable long authorId) {
        final Collection<Book> books = bookService.getBooksByAuthor(authorId);
        final Author author = bookService.getAuthorById(authorId);
        ModelAndView mav = new ModelAndView("authors/books");
        mav.addObject("author", author);
        mav.addObject("books", books);
        return mav;
    }


}
