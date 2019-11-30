package softuni.LionBet.service.models.moderator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddMatchServiceModel {
    private String homeTeamName;
    private String awayTeamName;
}
