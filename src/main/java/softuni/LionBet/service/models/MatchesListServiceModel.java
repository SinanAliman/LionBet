package softuni.LionBet.service.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.LionBet.data.models.entities.FinalScore;
import softuni.LionBet.data.models.entities.Team;

@Getter
@Setter
@NoArgsConstructor
public class MatchesListServiceModel {
    private Team homeTeam;

    private Team awayTeam;
}
