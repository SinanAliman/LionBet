package softuni.LionBet.data.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "football_matches")
@Getter
@Setter
@NoArgsConstructor
public class FootballMatch extends BaseEntity{

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "home_team_id", referencedColumnName = "id")
    @NotNull
    private Team homeTeam;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "away_team_id", referencedColumnName = "id")
    @NotNull
    private Team awayTeam;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fs_id", referencedColumnName = "id")
    private FinalScore finalScore;

}
