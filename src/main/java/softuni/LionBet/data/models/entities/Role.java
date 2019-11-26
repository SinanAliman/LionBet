package softuni.LionBet.data.models.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity{

    @Column
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
