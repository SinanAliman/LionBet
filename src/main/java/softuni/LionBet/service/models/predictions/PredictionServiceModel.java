package softuni.LionBet.service.models.predictions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.LionBet.data.models.entities.FinalScore;
import softuni.LionBet.data.models.entities.FootballMatch;

@Getter
@Setter
@NoArgsConstructor
public class PredictionServiceModel {
    private FootballMatch footballMatch;
    private FinalScore prediction;
    private int pointsGot;
}
