package softuni.LionBet.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import softuni.LionBet.service.models.auth.LoginUserServiceModel;
import softuni.LionBet.service.models.auth.RegisterUserServiceModel;
import softuni.LionBet.service.services.AuthService;
import softuni.LionBet.web.models.LoginUserModel;
import softuni.LionBet.web.models.RegisterUserModel;

import javax.servlet.http.HttpSession;

@Controller
public class AuthController {

    private final AuthService authService;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthController(AuthService authService, ModelMapper modelMapper) {
        this.authService = authService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public String registerForm(){
        return "auth/register.html";
    }

    @GetMapping("/login")
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
    @PostMapping("/login")
    public String login(@ModelAttribute LoginUserModel model, HttpSession session){
        LoginUserServiceModel serviceModel = this.modelMapper.map(model, LoginUserServiceModel.class);
        try {
            this.authService.login(serviceModel);
            session.setAttribute("username", serviceModel.getUsername());
            return "redirect:/moderate";
        } catch (Exception e) {
            return "redirect:/login";
        }
    }
}
