package pl.coderslab.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.AuthorDao;
import pl.coderslab.dao.BookDao;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.domain.Author;
import pl.coderslab.domain.Book;
import pl.coderslab.domain.Publisher;


@Controller
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    private final BookDao bookDao;
    private final PublisherDao publisherDao;
    private final AuthorDao authorDao;

    public BookController(BookDao bookDao, PublisherDao publisherDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
    }
    @RequestMapping("/book/add")
    @ResponseBody
    public String bookAdd() {
        Book book = new Book("think", 3, "story");
        book.setTitle("Thinking in Java");
        book.setRating(4);
        bookDao.saveBook(book);
        return "Id dodanej książki to:"
                + book.getId();
    }


    @RequestMapping("/book/get/{id}")
    @ResponseBody
    public String getBook(@PathVariable long id) {
        Book book = bookDao.findById(id);
        return book.toString();
    }

    @RequestMapping("/book/update/{id}/{title}")
    @ResponseBody
    public String updateBook(@PathVariable long id, @PathVariable String title ) {
        Book book = bookDao.findById(id);
        book.setTitle(title);
        bookDao.update(book);
        return book.toString();
    }

    @RequestMapping("/book/delete/{id}")
    @ResponseBody
    public String deleteBook(@PathVariable long id) {
        bookDao.delete(id);
        return "deleted";
    }


    @GetMapping("/saveBookWithPublisher")
    @ResponseBody
    public String saveBookWithPublisher() {
        Publisher publisher = new Publisher("Book publisher");
        publisherDao.savePublisher(publisher);
        Book book = new Book("Book with publisher", 5, "Book from controller");
        book.setPublisher(publisher);
        bookDao.saveBook(book);
        return book.toString();
    }

    @GetMapping("/saveWithAuthors")
    @ResponseBody
    public String saveBookWithAuthors() {
        Author author1 = authorDao.findById(1L);
        Author author2 = authorDao.findById(2L);
        Book book = new Book("Book with authors", 6, "Book from controller");
        book.addAuthor(author1);
        book.addAuthor(author2);
        bookDao.saveBook(book);
        return book.toString();

    }


    @GetMapping("/all")
    @ResponseBody
    public String findAll() {
        bookDao.findAll().forEach(b -> logger.info(b.toString()));
        return "all";
    }

}
