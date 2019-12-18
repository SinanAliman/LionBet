package softuni.LionBet.service.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.LionBet.data.models.entities.Prediction;
import softuni.LionBet.data.repositories.FinalScoreRepository;
import softuni.LionBet.data.repositories.PredictionRepository;
import softuni.LionBet.service.models.predictions.MakePredictionServiceModel;
import softuni.LionBet.service.services.PredictionService;

@Service
public class PredictionServiceImpl implements PredictionService {
    private final PredictionRepository predictionRepository;
    private final ModelMapper modelMapper;
    private final FinalScoreRepository finalScoreRepository;

    @Autowired
    public PredictionServiceImpl(PredictionRepository predictionRepository, ModelMapper modelMapper, FinalScoreRepository finalScoreRepository) {
        this.predictionRepository = predictionRepository;
        this.modelMapper = modelMapper;
        this.finalScoreRepository = finalScoreRepository;
    }

    @Override
    public boolean saveBet(MakePredictionServiceModel serviceModel) {
        Prediction prediction = this.modelMapper.map(serviceModel, Prediction.class);

        this.finalScoreRepository.saveAndFlush(prediction.getPrediction());

        this.predictionRepository.save(prediction);
        return true;
    }
}