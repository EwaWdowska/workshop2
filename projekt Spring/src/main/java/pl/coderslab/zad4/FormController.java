package pl.coderslab.zad4;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/first")
public class FormController {

    @GetMapping("/form")
    public String hello() {
        return "/form.jsp";
    }

    @PostMapping(value = "/form", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String formPost(@RequestParam String paramName, @RequestParam String paramDate) {
        LocalDate parsedDate = LocalDate.parse(paramDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return String.format("paramName = %s, paramDate = %s", paramName, parsedDate);
    }
}
