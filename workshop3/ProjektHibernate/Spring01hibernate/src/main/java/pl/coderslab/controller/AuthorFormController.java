package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.dao.AuthorDao;
import pl.coderslab.domain.Author;

import javax.validation.Valid;

@Controller
@RequestMapping("/authorForm")
public class AuthorFormController {

    private final AuthorDao authorDao;

    public AuthorFormController(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("author", new Author());
        return "author/form";
    }

    @PostMapping("/form")
    public String saveForm(@Valid Author author, BindingResult result) {
        if (result.hasErrors()) {
            return "author/form";
        }
        if (author.getId() != null) {
            authorDao.update(author);
        } else {
            authorDao.saveAuthor(author);
        }
        return "redirect:list";
    }

    @GetMapping("/list")
    public String listAuthors(Model model) {
        model.addAttribute("authors", authorDao.findAll());
        return "author/list";
    }

    @GetMapping("/edit/{id}")
    public String editAuthor(@PathVariable long id, Model model) {
        Author author = authorDao.findById(id);
        model.addAttribute("author", author);
        return "/author/form";
    }

    @GetMapping("/confirmDel/{id}")
    public String confirmDel(@PathVariable long id, Model model) {
        Author author = authorDao.findById(id);
        model.addAttribute("author", author);
        return "/author/confirmDel";
    }


    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable long id) {
        authorDao.delete(id);
        return "redirect:/authorForm/list";
    }
}
