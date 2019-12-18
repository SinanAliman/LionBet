package softuni.LionBet.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RankingController {

    @GetMapping("/ranking")
    public ModelAndView getRanking(){
        return new ModelAndView();
    }
}
