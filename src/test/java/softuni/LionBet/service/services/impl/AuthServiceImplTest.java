package softuni.LionBet.service.services.impl;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import softuni.LionBet.data.models.entities.User;
import softuni.LionBet.data.repositories.UserRepository;
import softuni.LionBet.service.models.auth.RegisterUserServiceModel;
import softuni.LionBet.service.services.AuthValidationService;
import softuni.LionBet.service.services.PasswordHashService;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceImplTest {
    UserRepository userRepository;
    AuthValidationService authValidationService;
    PasswordHashService passwordHashService;

    AuthServiceImpl service;

    @BeforeEach
    public void setupTest(){
        userRepository = Mockito.mock(UserRepository.class);
        authValidationService = Mockito.mock(AuthValidationService.class);
        passwordHashService = Mockito.mock(PasswordHashService.class);
        ModelMapper modelMapper = new ModelMapper();
        service = new AuthServiceImpl(userRepository, authValidationService, modelMapper, passwordHashService);
    }
    //register

    //user does not exist
    //user exists

    @Test
    public void registerUser_whenUserDoesNotExist_shouldCreateUser() throws Exception {
        RegisterUserServiceModel user = new RegisterUserServiceModel();
        Mockito.when(authValidationService.isValidUser(user)).thenReturn(true);
        String username = "Pes";
        user.setUsername(username);
        service.register(user);

        assertEquals(username, user.getUsername());

    }


}