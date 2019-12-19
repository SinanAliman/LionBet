package softuni.LionBet.service.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import softuni.LionBet.data.repositories.UserRepository;
import softuni.LionBet.service.models.auth.RegisterUserServiceModel;
import softuni.LionBet.service.services.UserService;

public class UserServiceTest {

    @MockBean
    UserRepository userRepository;

    @MockBean
    UserValidationServiceImpl userValidationService;


    @Autowired
    UserService userService;

    @Test
    void registerUser_IfNotValid_ThenThrowExc() throws Exception {

        RegisterUserServiceModel serviceModel = this.getServiceModel();

        Mockito.when(userValidationService.isValidUser(serviceModel))
                .thenReturn(false);
    }

    private RegisterUserServiceModel getServiceModel(){
       return new RegisterUserServiceModel("user1","fake@abv.bg",
                "123123", "123123");
    }

}
