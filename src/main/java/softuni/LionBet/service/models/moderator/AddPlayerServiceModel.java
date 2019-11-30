package softuni.LionBet.service.models.moderator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddPlayerServiceModel {
    private String firstName;
    private String secondName;
    private String teamName;
    private double coefficientToScore;
    private String position;
}
