package softuni.LionBet.service.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.LionBet.data.models.entities.User;
import softuni.LionBet.data.repositories.UserRepository;
import softuni.LionBet.service.models.auth.LoginUserServiceModel;
import softuni.LionBet.service.models.auth.RegisterUserServiceModel;
import softuni.LionBet.service.services.AuthService;
import softuni.LionBet.service.services.AuthValidationService;
import softuni.LionBet.service.services.PasswordHashService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthValidationService authValidationService;
    private final ModelMapper modelMapper;
    private final PasswordHashService passwordHashService;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, AuthValidationService authValidationService,
                           ModelMapper modelMapper, PasswordHashService passwordHashService) {
        this.userRepository = userRepository;
        this.authValidationService = authValidationService;
        this.modelMapper = modelMapper;
        this.passwordHashService = passwordHashService;
    }

    @Override
    public void register(RegisterUserServiceModel model) throws Exception {
        if (!authValidationService.isValidUser(model)){
            throw new Exception("Invalid data provided!"); //TODO THROW EXCEPTION
        }
        User user = this.modelMapper.map(model, User.class);
        user.setPassword(this.passwordHashService.hashPassword(user.getPassword()));
        this.userRepository.saveAndFlush(user);

    }

    @Override
    public void login(LoginUserServiceModel serviceModel) throws Exception {
        String hashedPassword = this.passwordHashService.hashPassword(serviceModel.getPassword());
        if (!this.userRepository.existsByUsernameAndPassword(serviceModel.getUsername(), hashedPassword)){
            throw new Exception("Invalid data!");
        }

    }
}
