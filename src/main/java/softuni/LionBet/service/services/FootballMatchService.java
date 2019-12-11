package softuni.LionBet.service.services;

import softuni.LionBet.data.models.entities.FootballMatch;
import softuni.LionBet.service.models.MatchesListServiceModel;
import softuni.LionBet.service.models.moderator.AddMatchServiceModel;

import java.util.List;

public interface FootballMatchService {
    void addMatch(AddMatchServiceModel model);

    List<MatchesListServiceModel> getAllMatches();
}
