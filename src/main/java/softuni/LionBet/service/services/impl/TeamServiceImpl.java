package softuni.LionBet.service.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import softuni.LionBet.data.models.entities.Team;
import softuni.LionBet.data.repositories.TeamRepository;
import softuni.LionBet.service.models.moderate.AddTeamServiceModel;
import softuni.LionBet.service.services.TeamService;

import javax.validation.ConstraintViolationException;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, ModelMapper modelMapper) {
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addTeam(AddTeamServiceModel model) {
        if (this.teamRepository.existsByName(model.getName())){
            throw new DataIntegrityViolationException("Invalid name");
        }
        Team team = this.modelMapper.map(model, Team.class);
        this.teamRepository.saveAndFlush(team);
    }
}
