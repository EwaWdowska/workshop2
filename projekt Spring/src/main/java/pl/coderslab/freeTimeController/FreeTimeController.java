package pl.coderslab.freeTimeController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Controller
public class FreeTimeController {

    @RequestMapping(value = "/free-time", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String temporalStatus() {
        LocalDateTime now = LocalDateTime.now();
        DayOfWeek currentDay = now.getDayOfWeek();
        int currentHour = now.getHour();

        if (currentDay == DayOfWeek.SATURDAY || currentDay == DayOfWeek.SUNDAY) {
            return "Wolne!!";
        } else if (currentHour >= 9 && currentHour <= 17) {
            return "Pracuje, nie dzwoń.";
        } else {
            return "Po Pracy";
        }
    }
}
