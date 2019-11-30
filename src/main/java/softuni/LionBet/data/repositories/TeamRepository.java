package softuni.LionBet.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.LionBet.data.models.entities.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, String> {
    boolean existsByName(String name);

    Team findByName(String name);
}
