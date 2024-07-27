package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.dao.PersonDao;
import pl.coderslab.dao.PersonDetailsDao;
import pl.coderslab.domain.Person;
import pl.coderslab.domain.PersonDetails;

import java.util.Optional;

@Controller
@RequestMapping("/persons")
public class PersonController {

    private final PersonDao dao;
    private final PersonDetailsDao personDetailsDao;

    public PersonController(PersonDao dao, PersonDetailsDao personDetailsDao) {
        this.dao = dao;
        this.personDetailsDao = personDetailsDao;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String getPerson(@PathVariable long id) {
        return Optional.ofNullable(dao.findById(id))
                .map(Person::toString)
                .orElse("Person not found");
    }

    @GetMapping("/{id}/delete")
    @ResponseBody
    public String deletePerson(@PathVariable long id) {
        dao.deleteById(id);
        return "deleted";
    }

    @GetMapping("/{id}/updateEmail/{email}")
    @ResponseBody
    public String updatePerson(@PathVariable long id, @PathVariable String email) {
        Person person = dao.findById(id);
        person.setEmail(email);
        dao.update(person);
        return person.toString();
    }

    @GetMapping
    @ResponseBody
    public String savePerson() {
        PersonDetails personDetails = new PersonDetails("John", "Doe", "25/4", "Street", "City");
        personDetailsDao.savePersonDetails(personDetails);
        Person person = new Person("testLogin", "secretPass", "test@test.com", personDetails);
        dao.savePerson(person);
        return "Person id: " + person.getId();
    }

    //przesyłanie formularzy bez bindoania
    @GetMapping("/form")
    public String form() {
        return "person/form";
    }

    @PostMapping("/form")
    @ResponseBody
    public String saveForm(@RequestParam String login, @RequestParam String password, @RequestParam String email) {
        Person person = new Person(login, password, email);
        dao.savePerson(person);
        return person.toString();
    }


    ///wysyłanie formularzy z bindowaniem ( automatycznie )

    @GetMapping("/form-bind")
    public String formBind(Model model) {
        model.addAttribute("person", new Person());
        return "person/form-bind";
    }

    @PostMapping("/form-bind")
    @ResponseBody
    public String saveFormBind(Person person) {
        dao.savePerson(person);
        return person.toString();
    }
}
