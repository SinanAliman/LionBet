package softuni.LionBet.service.services;

import softuni.LionBet.service.models.moderator.AddTeamServiceModel;

import java.util.List;

public interface TeamService {
    void addTeam(AddTeamServiceModel model);

    List<String> getTeamsNames();
}
