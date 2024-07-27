package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.clas.Student;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping
public class StudentController {

    @ModelAttribute("countries")
    public List<String> countries() {
        return Arrays.asList("Poland", "Germany", "France", "Russia", "Denmark");
    }

    @ModelAttribute("hobies")
    public List<String> hobies() {
        return Arrays.asList("watching movie", "swimming", "walk");
    }

    @ModelAttribute("countries")
    public List<String> programmingSkills() {
        return Arrays.asList("Java", "PHP", "C#");
    }


    @GetMapping("/student-bind")
    public String formBind(Model model) {
        model.addAttribute("student",new Student());
        return "/student-bind";
    }

    @PostMapping("/student-bind")
    @ResponseBody
    public String saveFormBind(Student student) {
    return student.toString();
    }
}
