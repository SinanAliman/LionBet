package softuni.LionBet.data.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "players")
@Getter
@Setter
public class Player extends BaseEntity{

    @Column(name = "first_name")
    @Size(min = 2, max = 20)
    private String firstName;

    @Column(name = "second_name")
    @NotNull
    @Size(min = 2, max = 20)
    private String secondName;


    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;

    @Column(name = "coefficient")
    @NotNull
    @Min(1)
    private double coefficientToScore;

    @Column
    @Enumerated(EnumType.STRING)
    private Positon positon;

    public Player() {
        this.setCoefficientToScore(1);
    }
}
