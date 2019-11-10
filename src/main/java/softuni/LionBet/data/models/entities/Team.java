package softuni.LionBet.data.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "teams")
@Getter
@Setter
@NoArgsConstructor
public class Team extends BaseEntity {

    @Column
    @NotNull
    @Size(min = 2, max = 30)
    private String name;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<Player> players;

    @Column(name = "logo_url")
    private String logoUrl;
}
