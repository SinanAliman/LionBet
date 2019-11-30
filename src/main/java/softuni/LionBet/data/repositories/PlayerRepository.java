package softuni.LionBet.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.LionBet.data.models.entities.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {
}
