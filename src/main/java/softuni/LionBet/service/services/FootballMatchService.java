package softuni.LionBet.service.services;

import javassist.NotFoundException;
import softuni.LionBet.service.models.matches.MatchByIdServiceModel;
import softuni.LionBet.service.models.matches.MatchesListServiceModel;
import softuni.LionBet.service.models.moderator.AddMatchServiceModel;

import java.util.List;

public interface FootballMatchService {
    void addMatch(AddMatchServiceModel model);

    List<MatchesListServiceModel> getAllMatches();

    MatchByIdServiceModel getMatchById(String id) throws NotFoundException;

}
