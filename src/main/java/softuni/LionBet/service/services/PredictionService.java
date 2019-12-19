package softuni.LionBet.service.services;

import javassist.NotFoundException;
import softuni.LionBet.service.models.predictions.MakePredictionServiceModel;
import softuni.LionBet.service.models.predictions.PredictionServiceModel;

import java.util.List;

public interface PredictionService {
    void saveBet(String id, String username, MakePredictionServiceModel serviceModel) throws NotFoundException;

    List<PredictionServiceModel> getUsersBets(String username) throws NotFoundException;
}
