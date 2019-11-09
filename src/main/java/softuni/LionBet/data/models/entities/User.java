package softuni.LionBet.data.models.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table

public class User extends BaseEntity {
    private String username;
    private String password;
    private int points;
}
