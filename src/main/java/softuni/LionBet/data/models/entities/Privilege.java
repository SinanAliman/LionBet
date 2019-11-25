package softuni.LionBet.data.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "privileges")
public class Privilege extends BaseEntity{

    @Column
    private String name;

    @ManyToMany(mappedBy = "privileges")
    private List<Role> roles;
}
