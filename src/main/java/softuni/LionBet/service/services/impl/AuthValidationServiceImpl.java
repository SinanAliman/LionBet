package softuni.LionBet.service.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.LionBet.data.repositories.UserRepository;
import softuni.LionBet.service.models.auth.RegisterUserServiceModel;
import softuni.LionBet.service.services.AuthValidationService;

@Service
public class AuthValidationServiceImpl implements AuthValidationService {
    private final UserRepository userRepository;

    @Autowired
    public AuthValidationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValidUser(RegisterUserServiceModel model) {
        return this.passwordsMatch(model.getPassword(), model.getConfirmPassword()) &&
                this.isEmailFree(model.getEmail()) &&
                this.isUsernameFree(model.getUsername());
    }

    private boolean passwordsMatch(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    private boolean isUsernameFree(String username) {
        return !this.userRepository.existsByUsername(username);
    }

    private boolean isEmailFree(String email) {
        return !this.userRepository.existsByEmail(email);
    }
}
