package softuni.LionBet.web.controllers;

import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import softuni.LionBet.service.services.FootballMatchService;
import softuni.LionBet.service.services.PredictionService;
import softuni.LionBet.web.models.matches.MatchByIdViewModel;

@Controller
public class PredictionController {
    private final PredictionService predictionService;
    private final FootballMatchService footballMatchService;
    private final ModelMapper modelMapper;

    @Autowired
    public PredictionController(PredictionService predictionService, FootballMatchService footballMatchService, ModelMapper modelMapper) {
        this.predictionService = predictionService;
        this.footballMatchService = footballMatchService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/bet/{id}")
    public ModelAndView getBettingForm(@PathVariable String id, ModelAndView modelAndView) throws NotFoundException {
        MatchByIdViewModel match = this.modelMapper.map(this.footballMatchService
                .getMatchById(id), MatchByIdViewModel.class);

        modelAndView.addObject("match", match);
        modelAndView.setViewName("/bet/make-bet");

        return modelAndView;
    }
}
