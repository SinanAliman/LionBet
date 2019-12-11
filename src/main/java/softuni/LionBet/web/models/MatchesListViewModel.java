package softuni.LionBet.web.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.LionBet.data.models.entities.Team;

@Getter
@Setter
@NoArgsConstructor
public class MatchesListViewModel {
    private Team homeTeam;

    private Team awayTeam;
}
