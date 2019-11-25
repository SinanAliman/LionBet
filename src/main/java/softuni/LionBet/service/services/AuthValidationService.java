package softuni.LionBet.service.services;

import softuni.LionBet.service.models.auth.RegisterUserServiceModel;

public interface AuthValidationService {
    boolean isValidUser(RegisterUserServiceModel model);
}
