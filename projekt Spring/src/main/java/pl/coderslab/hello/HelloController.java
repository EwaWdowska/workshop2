package pl.coderslab.hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.ls.LSOutput;

import java.time.LocalTime;

@Controller
public class HelloController {

    @RequestMapping(value = "/hello", produces = "text/plain")
    @ResponseBody
    public String helloWorld() {
        return "Hello World";
    }

    @RequestMapping("/helloView1")
    public String view1 (){
        return "home";
    }


    @RequestMapping("/helloView")
    public String helloView(Model model) {
//        int currentHour = LocalTime.now().getHour();
        int currentHour = 1;
        if (currentHour >= 8 && currentHour <= 20) {
            model.addAttribute("fontColor", "black");
            model.addAttribute("backgroundColor", "white");
        } else {
            model.addAttribute("fontColor", "white");
            model.addAttribute("backgroundColor", "black");
        }
        return "home";
    }

    @RequestMapping(value = "/name/{firstName}/{lastName}", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getName(@PathVariable String firstName, @PathVariable String lastName) {
        return String.format("Witaj: " + firstName + " " + lastName);
    }
}
