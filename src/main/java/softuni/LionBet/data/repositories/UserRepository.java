package softuni.LionBet.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.LionBet.data.models.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Optional<User> findUserByUsername(String username);

    List<User> findAllByPointsIsNotNullOrderByPointsDesc();

    Optional<User> findByUsername(String name);
}
