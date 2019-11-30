package softuni.LionBet.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.LionBet.service.models.moderator.AddMatchServiceModel;
import softuni.LionBet.service.models.moderator.AddPlayerServiceModel;
import softuni.LionBet.service.models.moderator.AddTeamServiceModel;
import softuni.LionBet.service.services.FootballMatchService;
import softuni.LionBet.service.services.PlayerService;
import softuni.LionBet.service.services.TeamService;
import softuni.LionBet.web.models.moderator.AddMatchModel;
import softuni.LionBet.web.models.moderator.AddPlayerModel;
import softuni.LionBet.web.models.moderator.AddTeamModel;

import java.util.List;

@Controller
public class ModeratorController {
    private final ModelMapper modelMapper;
    private final TeamService teamService;
    private final PlayerService playerService;
    private final FootballMatchService matchService;

    @Autowired
    public ModeratorController(ModelMapper modelMapper, TeamService teamService, PlayerService playerService, FootballMatchService matchService) {
        this.modelMapper = modelMapper;
        this.teamService = teamService;
        this.playerService = playerService;
        this.matchService = matchService;
    }

    @GetMapping("/addTeam")
    public String getAddTeamForm(){
        return "moderator/add-team.html";
    }

    @PostMapping("/addTeam")
    public String addTeam(@ModelAttribute AddTeamModel model){
        AddTeamServiceModel serviceModel = this.modelMapper.map(model, AddTeamServiceModel.class);

        try {
            this.teamService.addTeam(serviceModel);
            return "redirect:/addTeam";
        }catch (DataIntegrityViolationException ex){
            return "redirect:/moderator";
        }
    }

    @GetMapping("/addPlayer")
    public ModelAndView getAddPlayerForm(@ModelAttribute ModelAndView modelAndView){
        List<String> teamsNames = this.teamService.getTeamsNames();
        modelAndView.addObject("teamsNames",teamsNames);
        modelAndView.setViewName("moderator/add-player");
        return modelAndView;
    }

    @PostMapping("/addPlayer")
    public ModelAndView addPlayer(@ModelAttribute AddPlayerModel model){
        try {
            AddPlayerServiceModel serviceModel = this.modelMapper.map(model, AddPlayerServiceModel.class);
            this.playerService.addPlayer(serviceModel);

            return new ModelAndView("redirect:/addPlayer");
        }catch (Exception ex){
            ex.getMessage();
            return new ModelAndView(("redirect:/moderator"));
        }
    }

    @GetMapping("/addMatch")
    public ModelAndView getAddMatchForm(@ModelAttribute ModelAndView modelAndView){
        List<String> teamsNames = this.teamService.getTeamsNames();
        modelAndView.addObject("teamsNames",teamsNames);
        modelAndView.setViewName("moderator/add-match");
        return modelAndView;
    }

    @PostMapping("/addMatch")
    public ModelAndView addMatch(@ModelAttribute AddMatchModel model){
        if (model.getHomeTeamName().equals(model.getAwayTeamName())){
            return new ModelAndView("redirect:/addTeam");
            //todo throw appropriate exception!!!
        }

        try {
            AddMatchServiceModel serviceModel = this.modelMapper.map(model, AddMatchServiceModel.class);
            this.matchService.addMatch(serviceModel);
            return new ModelAndView("redirect:/addMatch");

        }catch (Exception ex){
            ex.getMessage();
            return new ModelAndView(("redirect:/moderator"));
        }
    }

}