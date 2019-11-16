package softuni.LionBet.data.models.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "final_scores")
public class FinalScore extends BaseEntity {

    @Column(name = "host_goals")
    private int hostGoals;

    @Column(name = "guest_goals")
    private int guestGoals;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Player> homeTeamGoalscorers;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Player> awayTeamGoalscorers;
}

