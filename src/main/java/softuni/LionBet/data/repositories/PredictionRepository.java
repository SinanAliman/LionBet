package softuni.LionBet.data.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.LionBet.data.models.entities.Prediction;

import java.util.List;

@Repository
public interface PredictionRepository extends JpaRepository<Prediction, String> {
}
