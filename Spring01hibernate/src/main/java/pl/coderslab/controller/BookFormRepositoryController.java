package pl.coderslab.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.domain.Book;
import pl.coderslab.repository.BookRepository;

import java.util.List;

@Controller
@RequestMapping("/bookForm/v2")
public class BookFormRepositoryController {

    private final static Logger logger = LoggerFactory.getLogger(BookFormRepositoryController.class);
    private final BookRepository bookRepository;

    public BookFormRepositoryController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    @ResponseBody
    public void testRepo(){

       List<Book> books =  bookRepository.findByTitle("Nazwa ksiązki");//nazwa ksiązki
        books.forEach(b -> logger.info("{}", b));
    }
}
