package softuni.LionBet.service.services;

import softuni.LionBet.service.models.predictions.MakePredictionServiceModel;

public interface PredictionService {
    boolean saveBet(MakePredictionServiceModel serviceModel);
}
