package softuni.LionBet.service.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import softuni.LionBet.service.models.UserRankingServiceModel;
import softuni.LionBet.service.models.auth.LoginUserServiceModel;
import softuni.LionBet.service.models.auth.RegisterUserServiceModel;

import java.util.List;

public interface UserService extends UserDetailsService {
    void register(RegisterUserServiceModel model) throws Exception;

    List<UserRankingServiceModel> getAllUsers();

}
