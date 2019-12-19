package softuni.LionBet.web.controllers;

import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.LionBet.data.models.entities.FinalScore;
import softuni.LionBet.service.models.predictions.MakePredictionServiceModel;
import softuni.LionBet.service.services.FootballMatchService;
import softuni.LionBet.service.services.PredictionService;
import softuni.LionBet.web.models.predictions.MakePredictionModel;
import softuni.LionBet.web.models.matches.MatchByIdViewModel;
import softuni.LionBet.web.models.predictions.PredictionViewModel;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

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
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getBettingForm(@PathVariable String id, ModelAndView modelAndView) throws NotFoundException {
        MatchByIdViewModel matchModel = this.modelMapper.map(this.footballMatchService
                .getMatchById(id), MatchByIdViewModel.class);

        modelAndView.addObject("match", matchModel);
        modelAndView.setViewName("bet/make-bet");

        return modelAndView;
    }

    @PostMapping("/bet/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView makeBet(@PathVariable String id,
                                @ModelAttribute MakePredictionModel makePredictionModel,
                                Principal principal){

        try {
            MatchByIdViewModel matchModel = this.modelMapper.map(this.footballMatchService
                    .getMatchById(id), MatchByIdViewModel.class);

            makePredictionModel.setMatchModel(matchModel);

            MakePredictionServiceModel serviceModel = this.modelMapper.map(makePredictionModel,
                    MakePredictionServiceModel.class);

            FinalScore prediction = new FinalScore();
            prediction.setHostGoals(makePredictionModel.getHomeTeamGoals());
            prediction.setGuestGoals(makePredictionModel.getAwayTeamGoals());

            serviceModel.setPrediction(prediction);

            String username = principal.getName();

            this.predictionService.saveBet(id ,username ,serviceModel);
            return new ModelAndView("redirect:/matches");

        } catch (Exception ex) {
            return new ModelAndView("redirect:/error");

        }
    }

    @GetMapping("/bets")
    public ModelAndView getBets(@ModelAttribute ModelAndView modelAndView, Principal principal) throws NotFoundException {

        String username = principal.getName();

        List<PredictionViewModel> predictions = this.predictionService.getUsersBets(username).stream()
                .map(p -> this.modelMapper.map(p, PredictionViewModel.class)).collect(Collectors.toList());

        modelAndView.addObject("bets", predictions);
        modelAndView.setViewName("bet/my-bets");

        return modelAndView;

    }

}