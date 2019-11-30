package softuni.LionBet.service.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.LionBet.data.models.entities.Player;
import softuni.LionBet.data.models.entities.Team;
import softuni.LionBet.data.repositories.PlayerRepository;
import softuni.LionBet.data.repositories.TeamRepository;
import softuni.LionBet.service.models.moderator.AddPlayerServiceModel;
import softuni.LionBet.service.services.PlayerService;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final ModelMapper modelMapper;
    private final TeamRepository teamRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, ModelMapper modelMapper, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.modelMapper = modelMapper;
        this.teamRepository = teamRepository;
    }

    @Override
    public void addPlayer(AddPlayerServiceModel model) {
        Player player = this.modelMapper.map(model, Player.class);
        String teamName = model.getTeamName();
        if (!this.teamRepository.existsByName(teamName)){
            throw new NullPointerException("No such a team in the database!");
        }

        if (player.getCoefficientToScore() < 1){
            player.setCoefficientToScore(1);
        }

        Team team = this.teamRepository.findByName(teamName);
        player.setTeam(team);
        this.playerRepository.saveAndFlush(player);
        List<Player> playerList = team.getPlayers();
        playerList.add(player);
        team.setPlayers(playerList);
        this.teamRepository.saveAndFlush(team);


    }
}
