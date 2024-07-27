package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.domain.Publisher;

import java.util.Optional;

@Controller
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherDao dao;

    public PublisherController(PublisherDao dao) {
        this.dao = dao;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String getPublisher(@PathVariable long id) {
        return Optional.ofNullable(dao.findById(id))
                .map(Publisher::toString)
                .orElse("Publisher not found");
    }

    @GetMapping("/{id}/delete")
    @ResponseBody
    public String deletePublisher(@PathVariable long id) {
        dao.deleteById(id);
        return "deleted";
    }

    @GetMapping("/{id}/updateName/{name}")
    @ResponseBody
    public String updatePublisher(@PathVariable long id, @PathVariable String name) {
        Publisher publisher = dao.findById(id);
        publisher.setName(name);
        dao.update(publisher);
        return publisher.toString();
    }

    @GetMapping
    @ResponseBody
    public String savePublisher() {
        Publisher publisher = new Publisher("Publisher from controller");
        dao.savePublisher(publisher);
        return "Publisher id: " + publisher.getId();
    }
}