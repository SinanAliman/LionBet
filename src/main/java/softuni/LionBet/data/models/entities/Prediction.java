package softuni.LionBet.data.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "predictions")
@Getter
@Setter
@NoArgsConstructor
public class Prediction extends BaseEntity{
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "match_id", referencedColumnName = "id")
    private FootballMatch footballMatch;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prediction_id", referencedColumnName = "id")
    private FinalScore prediction;

    @Column(name = "points_got")
    private int pointsGot;
}
