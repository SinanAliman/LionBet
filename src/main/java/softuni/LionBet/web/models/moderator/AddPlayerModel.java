package softuni.LionBet.web.models.moderator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddPlayerModel {
    private String firstName;
    private String secondName;
    private String teamName;
    private double coefficientToScore;
    private String position;
}
