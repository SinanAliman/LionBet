package softuni.LionBet.service.models.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.LionBet.data.models.entities.Prediction;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class LoginUserServiceModel {
    private String username;
    private List<Prediction> predictions;
    private int points;

    public LoginUserServiceModel(String username, List<Prediction> predictions, int points) {
        this.username = username;
        this.predictions = predictions;
        this.points = points;
    }
}
