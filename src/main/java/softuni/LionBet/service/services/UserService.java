package softuni.LionBet.service.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import softuni.LionBet.service.models.auth.LoginUserServiceModel;
import softuni.LionBet.service.models.auth.RegisterUserServiceModel;

public interface UserService extends UserDetailsService {
    void register(RegisterUserServiceModel model) throws Exception;

}
