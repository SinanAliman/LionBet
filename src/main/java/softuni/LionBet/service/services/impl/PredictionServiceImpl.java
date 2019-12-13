package softuni.LionBet.service.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.LionBet.data.repositories.PredictionRepository;
import softuni.LionBet.service.services.PredictionService;

@Service
public class PredictionServiceImpl implements PredictionService {
    private final PredictionRepository predictionRepository;

    @Autowired
    public PredictionServiceImpl(PredictionRepository predictionRepository) {
        this.predictionRepository = predictionRepository;
    }
}
