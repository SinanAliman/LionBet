package softuni.LionBet.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.LionBet.data.models.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Role findByName(String name);
}
