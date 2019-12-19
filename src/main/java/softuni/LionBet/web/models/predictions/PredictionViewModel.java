package softuni.LionBet.web.models.predictions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.LionBet.data.models.entities.FinalScore;
import softuni.LionBet.data.models.entities.FootballMatch;

@Getter
@Setter
@NoArgsConstructor
public class PredictionViewModel {
    private FootballMatch footballMatch;
    private FinalScore prediction;
    private int pointsGot;
}
