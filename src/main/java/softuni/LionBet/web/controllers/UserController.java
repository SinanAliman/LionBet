package softuni.LionBet.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import softuni.LionBet.data.models.entities.FootballMatch;
import softuni.LionBet.data.repositories.FootballMatchRepository;

import java.util.List;

@Controller
public class UserController {
    private final FootballMatchRepository matchRepository;

    @Autowired
    public UserController(FootballMatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @GetMapping("/matches")
    public ModelAndView getMatchesForm(@ModelAttribute ModelAndView modelAndView){
        List<FootballMatch> footballMatches = this.matchRepository.findAll();
        modelAndView.addObject("matches", footballMatches);
        modelAndView.setViewName("user/matches");
        return modelAndView;

    }
}
