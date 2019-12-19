package softuni.LionBet.service.services.impl;

import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.LionBet.data.models.entities.FootballMatch;
import softuni.LionBet.data.models.entities.Prediction;
import softuni.LionBet.data.models.entities.User;
import softuni.LionBet.data.repositories.FinalScoreRepository;
import softuni.LionBet.data.repositories.FootballMatchRepository;
import softuni.LionBet.data.repositories.PredictionRepository;
import softuni.LionBet.data.repositories.UserRepository;
import softuni.LionBet.service.models.predictions.MakePredictionServiceModel;
import softuni.LionBet.service.models.predictions.PredictionServiceModel;
import softuni.LionBet.service.services.PredictionService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PredictionServiceImpl implements PredictionService {
    private final PredictionRepository predictionRepository;
    private final ModelMapper modelMapper;
    private final FinalScoreRepository finalScoreRepository;
    private final UserRepository userRepository;
    private final FootballMatchRepository matchRepository;


    @Autowired
    public PredictionServiceImpl(PredictionRepository predictionRepository, ModelMapper modelMapper, FinalScoreRepository finalScoreRepository, UserRepository userRepository, FootballMatchRepository matchRepository) {
        this.predictionRepository = predictionRepository;
        this.modelMapper = modelMapper;
        this.finalScoreRepository = finalScoreRepository;

        this.userRepository = userRepository;
        this.matchRepository = matchRepository;
    }

    @Override
    public void saveBet(String matchId, String username, MakePredictionServiceModel serviceModel) throws NotFoundException {
        Prediction prediction = this.modelMapper.map(serviceModel, Prediction.class);

        Optional<User> optionalUser = this.userRepository.findUserByUsername(username);

        if (optionalUser.isEmpty()){
            throw new NotFoundException("User not found!");
        }
        User user = optionalUser.get();

        this.finalScoreRepository.saveAndFlush(prediction.getPrediction());

        Optional<FootballMatch> optionalFootballMatch = this.matchRepository.findById(matchId);

        optionalFootballMatch.ifPresent(prediction::setFootballMatch);

        this.predictionRepository.saveAndFlush(prediction);

        List<Prediction> predictionList = user.getPredictions();
        predictionList.add(prediction);
        user.setPredictions(predictionList);

        this.userRepository.save(user);
    }

    @Override
    public List<PredictionServiceModel> getUsersBets(String username) throws NotFoundException {
        Optional<User> userOptional = this.userRepository.findUserByUsername(username);

        if (userOptional.isEmpty()){
            throw new NotFoundException("User not found!");
        }
        User user = userOptional.get();

        return user.getPredictions().stream().map(prediction ->  this.modelMapper
                .map(prediction, PredictionServiceModel.class)).collect(Collectors.toList());

    }
}