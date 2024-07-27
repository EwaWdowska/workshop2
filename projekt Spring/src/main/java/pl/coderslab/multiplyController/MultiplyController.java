package pl.coderslab.multiplyController;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class MultiplyController {

    @RequestMapping("/multiply") //przy@PathVariable  podaje parametr size   @RequestMapping("/multiply" {size})
    public String multiply(@RequestParam(defaultValue = "10", required = false) int size, Model model) {
        model.addAttribute("size", size);
        return "multiply";
    }

    @RequestMapping("/paramMultiply/{rows}/{cols}")
    public String paramMultiply(@PathVariable int rows, @PathVariable int cols, Model model) {
        model.addAttribute("rows", rows);
        model.addAttribute("cols", cols);
        return "paramMultiply";
    }
}
