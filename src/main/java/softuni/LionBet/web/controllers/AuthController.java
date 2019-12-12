package softuni.LionBet.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import softuni.LionBet.service.models.auth.RegisterUserServiceModel;
import softuni.LionBet.service.services.UserService;
import softuni.LionBet.web.models.auth.RegisterUserModel;

import javax.servlet.http.HttpSession;

@Controller
public class AuthController {

    private final UserService authService;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthController(UserService userService, ModelMapper modelMapper) {
        this.authService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    public String registerForm(){
        return "auth/register.html";
    }

    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    public String loginForm(){
        return "auth/login.html";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute RegisterUserModel model) {
        RegisterUserServiceModel serviceModel = this.modelMapper.map(model, RegisterUserServiceModel.class);
        try {
            this.authService.register(serviceModel);
            return "redirect:/login";
        } catch (Exception e) {
            return "redirect:/register";
        }
    }
}
