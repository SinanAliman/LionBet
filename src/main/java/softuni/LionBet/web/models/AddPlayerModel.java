package softuni.LionBet.web.models;

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
    private double coeffToScore;
    private String position;
}
