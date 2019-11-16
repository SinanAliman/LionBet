package softuni.LionBet.data.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity {
    @Column(unique = true)
    @NotNull
    @Size(min = 3, max = 20)
    private String username;

    @Column(unique = true)
    @NotNull
    @Email
    private String email;

    @Column
    @NotNull
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Prediction> predictions = new ArrayList<>();

    @Column
    @NotNull
    @Min(0)
    private int points;

    public User() {
        this.setPoints(0);
    }
}
