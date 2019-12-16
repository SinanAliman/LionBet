package softuni.LionBet.web.models.predictions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.LionBet.web.models.matches.MatchByIdViewModel;

@Getter
@Setter
@NoArgsConstructor
public class MakePredictionModel {
    private MatchByIdViewModel matchModel;

    private int homeTeamGoals;

    private int awayTeamGoals;
}
