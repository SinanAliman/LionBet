package softuni.LionBet.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import softuni.LionBet.service.models.moderate.AddTeamServiceModel;
import softuni.LionBet.service.services.TeamService;
import softuni.LionBet.web.models.AddTeamModel;

@Controller
public class ModeratorController {
    private final ModelMapper modelMapper;
    private final TeamService teamService;

    public ModeratorController(ModelMapper modelMapper, TeamService teamService) {
        this.modelMapper = modelMapper;
        this.teamService = teamService;
    }

    @GetMapping("/addTeam")
    public String getAddTeamForm(){
        return "moderate/add-team.html";
    }

    @PostMapping("/addTeam")
    public String addTeam(@ModelAttribute AddTeamModel model){
        AddTeamServiceModel serviceModel = this.modelMapper.map(model, AddTeamServiceModel.class);

        try {
            this.teamService.addTeam(serviceModel);
            return "redirect:/moderate";
        }catch (DataIntegrityViolationException ex){
            return "redirect:/addTeam";
        }
    }
}