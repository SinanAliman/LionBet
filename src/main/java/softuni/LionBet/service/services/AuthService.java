package softuni.LionBet.service.services;

import softuni.LionBet.service.models.auth.LoginUserServiceModel;
import softuni.LionBet.service.models.auth.RegisterUserServiceModel;

public interface AuthService {
    void register(RegisterUserServiceModel model);

    void login(LoginUserServiceModel serviceModel) throws Exception;
}
