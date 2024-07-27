package pl.coderslab.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.domain.Author;
import pl.coderslab.domain.Book;
import pl.coderslab.domain.Publisher;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Controller
public class ValidationController {

    private final static Logger logger = LoggerFactory.getLogger(ValidationController.class);
    private final Validator validator;

    public ValidationController(Validator validator) {
        this.validator = validator;
    }

    @GetMapping("/validate")
    public String validationTest(Model model) {
        Book book = new Book();
        Author author = new Author();
        Publisher publisher = new Publisher();
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        Set<ConstraintViolation<Author>> violationsAuthor = validator.validate(author);
        Set<ConstraintViolation<Publisher>> violationsPublisher = validator.validate(publisher);
        model.addAttribute("violations", violations);
        model.addAttribute("violationsAuthor", violationsAuthor);
        model.addAttribute("violationsPublisher", violationsPublisher);

        if (!violations.isEmpty()) {
            violations.forEach(v -> logger.debug("{} {}", v.getPropertyPath(), v.getMessage()));
        } else {
            // save object
        }

        if (!violationsAuthor.isEmpty()) {
            violationsAuthor.forEach(a -> logger.debug("{} {}", a.getPropertyPath(), a.getMessage()));
        } else {
            // save object
        }

        if (!violationsPublisher.isEmpty()) {
            violationsPublisher.forEach(p -> logger.debug("{} {}", p.getPropertyPath(), p.getMessage()));
        } else {
            // save object
        }
        return "validateResult";
    }

}