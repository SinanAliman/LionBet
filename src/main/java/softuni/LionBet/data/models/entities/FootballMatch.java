package softuni.LionBet.data.models.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "football_matches")
public class FootballMatch extends BaseEntity{

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "home_team_id", referencedColumnName = "id")
    private Team homeTeam;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "away_team_id", referencedColumnName = "id")
    private Team awayTeam;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fs_id", referencedColumnName = "id")
    private FinalScore finalScore;

}
