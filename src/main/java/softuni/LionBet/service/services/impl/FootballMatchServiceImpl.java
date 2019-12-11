package softuni.LionBet.service.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.LionBet.data.models.entities.FootballMatch;
import softuni.LionBet.data.models.entities.Team;
import softuni.LionBet.data.repositories.FootballMatchRepository;
import softuni.LionBet.data.repositories.TeamRepository;
import softuni.LionBet.service.models.MatchesListServiceModel;
import softuni.LionBet.service.models.moderator.AddMatchServiceModel;
import softuni.LionBet.service.services.FootballMatchService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FootballMatchServiceImpl implements FootballMatchService {
    private final FootballMatchRepository footballMatchRepository;
    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;

    public FootballMatchServiceImpl(FootballMatchRepository footballMatchRepository, TeamRepository teamRepository, ModelMapper modelMapper) {
        this.footballMatchRepository = footballMatchRepository;
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addMatch(AddMatchServiceModel model) {
        if (model.getHomeTeamName() == null || model.getAwayTeamName() == null){
            throw new NullPointerException("There is not such a team in the database!");
        }

        if (!this.teamRepository.existsByName(model.getHomeTeamName()) ||
                !this.teamRepository.existsByName(model.getAwayTeamName())){
            throw new NullPointerException("There is not such a team in the database!");
        }
        Team homeTeam = this.teamRepository.findByName(model.getHomeTeamName());
        Team awayTeam = this.teamRepository.findByName(model.getAwayTeamName());
        FootballMatch match = new FootballMatch();
        match.setHomeTeam(homeTeam);
        match.setAwayTeam(awayTeam);
        this.footballMatchRepository.saveAndFlush(match);
    }

    @Override
    public List<MatchesListServiceModel> getAllMatches() {
        return this.footballMatchRepository.findAll().stream().map(m -> this.modelMapper.map(m, MatchesListServiceModel.class))
                .collect(Collectors.toList());
    }
}
