package pl.coderslab.random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
public class RandomController {
    private final Random rand;

    public RandomController() {
        this.rand = new Random();
    }

    @RequestMapping(value = "/show-random", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String showRandom() {
        return String.format("Wylosowano liczbę: %d", rand.nextInt(101));
    }
    @RequestMapping(value="random/{min}/{max}",  produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String random(@PathVariable int max, @PathVariable int min){
        return String.format("uzytkowni podał  wartośc %d i %d. Wylosowano: %d", min, max, rand.nextInt(max-min)+min);
    }
}
