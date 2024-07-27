package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.BookDao;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.domain.Book;
import pl.coderslab.domain.Publisher;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/bookForm")
public class BookFormController {

    private final BookDao dao;
    private final PublisherDao publisherDao;

    public BookFormController(BookDao dao, PublisherDao publisherDao) {
        this.dao = dao;
        this.publisherDao = publisherDao;
    }

    @ModelAttribute("publishers")
    public List<Publisher> publishers() {
        return publisherDao.findAll();
    }

    @GetMapping("/form")
    public String displayForm(Model model) {
        model.addAttribute("book", new Book());
        return "book/form";
    }

    @PostMapping("/form")
    public String processForm(@Valid Book book, BindingResult result) {
        if(result.hasErrors()){
            return "bookForm/form";
        }
        dao.saveBook(book);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String listAll(Model model) {
        model.addAttribute("books", dao.findAll());
        return "book/list";
    }
}
