package softuni.LionBet.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.LionBet.service.models.moderate.AddTeamServiceModel;
import softuni.LionBet.service.services.PlayerService;
import softuni.LionBet.service.services.TeamService;
import softuni.LionBet.web.models.AddPlayerModel;
import softuni.LionBet.web.models.AddTeamModel;

import java.util.List;

@Controller
public class ModeratorController {
    private final ModelMapper modelMapper;
    private final TeamService teamService;
    private final PlayerService playerService;

    public ModeratorController(ModelMapper modelMapper, TeamService teamService, PlayerService playerService) {
        this.modelMapper = modelMapper;
        this.teamService = teamService;
        this.playerService = playerService;
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
            return "redirect:/moderator";
        }catch (DataIntegrityViolationException ex){
            return "redirect:/addTeam";
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

        return new ModelAndView("redirect:/moderator");

    }
}