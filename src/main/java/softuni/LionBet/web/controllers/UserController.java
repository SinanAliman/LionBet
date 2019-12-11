package softuni.LionBet.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import softuni.LionBet.service.models.MatchesListServiceModel;
import softuni.LionBet.service.services.FootballMatchService;
import softuni.LionBet.web.models.MatchesListViewModel;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {
    private final FootballMatchService matchService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(FootballMatchService matchService, ModelMapper modelMapper) {
        this.matchService = matchService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/matches")
    public ModelAndView getMatchesForm(@ModelAttribute ModelAndView modelAndView){
        List<MatchesListViewModel> footballMatches = this.matchService.getAllMatches()
                .stream().map(m -> this.modelMapper.map(m, MatchesListViewModel.class)).collect(Collectors.toList());


        modelAndView.addObject("matches", footballMatches);
        modelAndView.setViewName("user/matches");
        return modelAndView;

    }
}
